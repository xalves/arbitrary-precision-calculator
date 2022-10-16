package com.example.restCalculator.adapters.messaging;

import com.example.restCalculator.adapters.RequestProcessor;
import com.example.restCalculator.domain.OperationMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

@Service
public class MessageClientService implements RequestProcessor {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper mapper;

    @Override
    public OperationMessage send(OperationMessage message) throws IOException {
        Message newMessage = MessageBuilder
                .withBody(mapper.writeValueAsString(message).getBytes())
                .build();

        Message result = rabbitTemplate.sendAndReceive(MessagingConfig.RPC_EXCHANGE, MessagingConfig.RPC_QUEUE1, newMessage);

        if (result != null) {
            String correlationId = newMessage.getMessageProperties().getCorrelationId();

            HashMap<String, Object> headers = (HashMap<String, Object>) result.getMessageProperties().getHeaders();

            String msgId = (String) headers.get("spring_returned_message_correlation");

            if (msgId.equals(correlationId)) {
                return mapper.readValue(result.getBody(), OperationMessage.class);
            }

        }
        return null;
    }
}
