package com.yxj.rabbitmq;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-18 14:59
 */
public class FishRabbitTemplate extends RabbitTemplate {


    public FishRabbitTemplate(ConnectionFactory connectionFactory) {
        super();
        setConnectionFactory(connectionFactory);
    }


    @Override
    public void send(String exchange, String routingKey, Message message) throws AmqpException {
        MessageProperties messageProperties = message.getMessageProperties();
        //每个消息都设置超时时间
        messageProperties.setExpiration("6000");
        super.send(exchange, routingKey, message, null);
    }
}
