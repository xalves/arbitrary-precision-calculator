package com.example.restCalculator.adapters.messaging;

import com.example.restCalculator.domain.MessageHandler;
import com.example.restCalculator.domain.OperationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class MessageServerService {

    private static final Logger logger = LoggerFactory.getLogger(MessageServerService.class);
    private final RabbitTemplate rabbitTemplate;
    private final MessageHandler messageHandler;

    @Autowired
    private MessageServerService(RabbitTemplate rabbitTemplate, MessageHandler messageHandler) {
        this.rabbitTemplate = rabbitTemplate;
        this.messageHandler = messageHandler;
    }


    @RabbitListener(queues = MessageConfig.RPC_QUEUE1)
    public void receiveAndProcess(Message msg) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        OperationMessage operationMessage = mapper.readValue(msg.getBody(), OperationMessage.class);
        logger.info("Message received operationMessage={}", operationMessage);
        operationMessage.setResult(messageHandler.handleMessage(operationMessage));
        Message response = MessageBuilder
                .withBody(mapper.writeValueAsString(operationMessage).getBytes())
                .setCorrelationId(msg.getMessageProperties().getCorrelationId())
                .build();

        CorrelationData correlationData = new CorrelationData(msg.getMessageProperties().getCorrelationId());
        logger.debug("Message to be sent operationMessage={}", response);
        rabbitTemplate.send(MessageConfig.RPC_EXCHANGE, MessageConfig.RPC_QUEUE2, response, correlationData);
    }
}
