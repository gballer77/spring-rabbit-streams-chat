package software.baller.eventchat.config;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.rabbit.stream.support.StreamAdmin;

@Configuration
public class ChatSendConfig {

    @Bean
    RabbitStreamTemplate streamTemplate(Environment env) {
        RabbitStreamTemplate streamTemplate = new RabbitStreamTemplate(env, "persistent-chat-room");
        streamTemplate.setMessageConverter(converter());
        return streamTemplate;
    }

    @Bean
    StreamAdmin streamAdmin(Environment env) {
        return new StreamAdmin(env, sc -> {
            sc.stream("persistent-chat-room").create();
        });
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
