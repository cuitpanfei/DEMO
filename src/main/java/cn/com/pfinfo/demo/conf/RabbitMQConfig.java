package cn.com.pfinfo.demo.conf;

import cn.com.pfinfo.demo.base.ControllerHandler;
import cn.com.pfinfo.demo.util.JsonUtil;
import cn.com.pfinfo.demo.util.context.ControllerURLHandlerCatch;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class RabbitMQConfig {
    @Autowired
    private Environment env;

    public RabbitMQConfig() {
    }

    @Bean
    ConnectionFactory connectionFactory() {
        com.rabbitmq.client.ConnectionFactory rabbitConnectionFactory = new com.rabbitmq.client.ConnectionFactory();
        rabbitConnectionFactory.setUsername(this.env.getProperty("rabbitMQ.username"));
        rabbitConnectionFactory.setPassword(this.env.getProperty("rabbitMQ.password"));
        rabbitConnectionFactory.setHost(this.env.getProperty("rabbitMQ.host"));
        rabbitConnectionFactory.setPort(Integer.parseInt(this.env.getProperty("rabbitMQ.port")));
        ConnectionFactory connectionFactory = new CachingConnectionFactory(rabbitConnectionFactory);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate instance(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setExchange(this.env.getProperty("rabbitMQ.exchange"));
        return rabbitTemplate;
    }

    @Bean
    Queue queue() {
        return new Queue("dev", false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("dev", false, false);
    }

    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("dev");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory, MessageListener listener) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(new String[]{"dev"});
        container.setMessageListener(listener);
        return container;
    }

    @Bean
    MessageListener listener() {
        return (message) -> {
            JsonNode mjson = JsonUtil.toJSONObject(message.getBody());
            Map<String, Object> info = (Map)ControllerURLHandlerCatch.getControllers().get("");
            ControllerHandler handler = (ControllerHandler)info.get("bean");
            handler.invoke(message);
        };
    }
}
