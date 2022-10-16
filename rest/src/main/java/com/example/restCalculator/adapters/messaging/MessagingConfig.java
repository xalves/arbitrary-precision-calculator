package com.example.restCalculator.adapters.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
    public static final String RPC_QUEUE1 = "queue_1";
    public static final String RPC_QUEUE2 = "queue_2";
    public static final String RPC_EXCHANGE = "rpc_exchange";

    @Bean
    Queue msgQueue() {
        return new Queue(RPC_QUEUE1);
    }

    @Bean
    Queue replyQueue() {
        return new Queue(RPC_QUEUE2);
    }

    @Bean
    RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setReplyAddress(RPC_QUEUE2);
        template.setReplyTimeout(6000);
        return template;
    }

    @Bean
    SimpleMessageListenerContainer replyContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RPC_QUEUE2);
        container.setMessageListener(rabbitTemplate(connectionFactory));
        return container;
    }
}
