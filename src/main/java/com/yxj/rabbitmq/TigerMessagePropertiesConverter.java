package com.yxj.rabbitmq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-18 15:19
 */
public class TigerMessagePropertiesConverter extends DefaultMessagePropertiesConverter {


    @Override
    public AMQP.BasicProperties fromMessageProperties(MessageProperties source, String charset) {
        AMQP.BasicProperties basicProperties = super.fromMessageProperties(source, charset);
        AMQP.BasicProperties.Builder builder = basicProperties.builder();
        builder.deliveryMode(2);
        builder.priority(5);//最大不能超过队列的优先级
        builder.expiration("6000");
        return builder.build();
    }
}
