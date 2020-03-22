package com.yxj;

import com.yxj.rabbitmq.FishRabbitTemplate;
import com.yxj.rabbitmq.TigerMessagePropertiesConverter;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.DefaultMessagePropertiesConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.policy.SimpleRetryPolicy;
import org.springframework.retry.support.RetryTemplate;

/**
 * @author:ycjx
 * @descriptio
 * @create:2020-03-18 14:58
 */
@Configuration
@AutoConfigureBefore({RabbitAutoConfiguration.class})
@ConditionalOnClass(EnableRabbit.class)
public class RabbitMqConfiguration {


    private final ObjectProvider<MessageConverter> messageConverter;

    private final RabbitProperties properties;

    public final static String deadQueueName = "dead_queue";
    public final static String deadRoutingKey = "dead_routing_key";
    public final static String deadExchangeName = "dead_exchange";

    public RabbitMqConfiguration(
            ObjectProvider<MessageConverter> messageConverter,
            RabbitProperties properties) {
        this.messageConverter = messageConverter;
        this.properties = properties;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new FishRabbitTemplate(connectionFactory());
        MessageConverter messageConverter = this.messageConverter.getIfUnique();
        if (messageConverter != null) {
            rabbitTemplate.setMessageConverter(messageConverter);
        }
        rabbitTemplate.setMandatory(determineMandatoryFlag());
        RabbitProperties.Template templateProperties = this.properties.getTemplate();
        RabbitProperties.Retry retryProperties = templateProperties.getRetry();
        if (retryProperties.isEnabled()) {
            rabbitTemplate.setRetryTemplate(createRetryTemplate(retryProperties));
        }
        if (templateProperties.getReceiveTimeout() != null) {
            rabbitTemplate.setReceiveTimeout(templateProperties.getReceiveTimeout());
        }
        if (templateProperties.getReplyTimeout() != null) {
            rabbitTemplate.setReplyTimeout(templateProperties.getReplyTimeout());
        }
        return rabbitTemplate;
    }

    private boolean determineMandatoryFlag() {
        Boolean mandatory = this.properties.getTemplate().getMandatory();
        return (mandatory != null) ? mandatory : this.properties.isPublisherReturns();
    }

    private RetryTemplate createRetryTemplate(RabbitProperties.Retry properties) {
        RetryTemplate template = new RetryTemplate();
        SimpleRetryPolicy policy = new SimpleRetryPolicy();
        policy.setMaxAttempts(properties.getMaxAttempts());
        template.setRetryPolicy(policy);
        ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
        backOffPolicy.setInitialInterval(properties.getInitialInterval());
        backOffPolicy.setMultiplier(properties.getMultiplier());
        backOffPolicy.setMaxInterval(properties.getMaxInterval());
        template.setBackOffPolicy(backOffPolicy);
        return template;
    }


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("192.68.75.36");
        connectionFactory.setUsername("visual_training");
        connectionFactory.setPassword("visual_training");
        return connectionFactory;
    }


    @Bean
    public DefaultMessagePropertiesConverter defaultMessagePropertiesConverter() {
        DefaultMessagePropertiesConverter defaultMessagePropertiesConverter = new TigerMessagePropertiesConverter();
        return defaultMessagePropertiesConverter;
    }


    @Bean
    public Queue declarQueue() {
        Queue queue = QueueBuilder
                .durable("ycjx") //durable 表示持久化
                .autoDelete() //没有消费者的话会自动删除
                .exclusive() //说明是排他队列 1.只对首次声明它的连接（Connection）可见，同一connection中的不同channel也是可以访问的 2.会在其连接断开的时候自动删除。
                .withArgument("x-message-ttl", 600L)//队列设置过期时间
                .withArgument("x-dead-letter-exchange", deadExchangeName) //死信的exchange
                .withArgument("x-dead-letter-routing-key", deadRoutingKey) //死信的routekey
                .withArgument("x-max-priority",10) //队列优先级 这是设置了队列的最大优先级 然后通过builder.priority(5);设置消息的优先级0-10
                .withArgument("x-expires",10000000)//这个是队列的超时时间，在一定时间内没有被使用就删除 有消息不算使用 。1。没有被重新神明 2。没有消费者。3。没有basicget操作
                .build();
        return queue;
    }

    @Bean
    public Exchange ex() {
        return ExchangeBuilder.directExchange("ycjx")
                .durable(true) //设置exchane持久化
                .build();
    }


    /**
     * 死信交换机，其实是一个普通的exchange
     *
     * @return
     */
    @Bean
    public Exchange deadExchange() {
        return ExchangeBuilder.directExchange(deadExchangeName)
                .durable(true)
                .build();
    }

    /**
     * 死信队列，其实是一个普通的queue
     *
     * @return
     */
    @Bean
    public Queue deadQueue() {
        Queue queue = QueueBuilder
                .durable(deadQueueName)
                .exclusive()
                .withArgument("x-message-ttl", 600L)//队列设置过期时间
                .build();
        return queue;
    }

    @Bean
    public Binding b1() {
        return BindingBuilder.bind(declarQueue()).to(ex()).with("ycjx").noargs();
    }

    @Bean
    public Binding deadBind() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with(deadRoutingKey).noargs();
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory());
        rabbitAdmin.declareExchange(ex());
        rabbitAdmin.declareQueue(declarQueue());
        rabbitAdmin.declareBinding(b1());

        rabbitAdmin.declareExchange(deadExchange());
        rabbitAdmin.declareQueue(deadQueue());
        rabbitAdmin.declareBinding(deadBind());

        return rabbitAdmin;
    }


}
