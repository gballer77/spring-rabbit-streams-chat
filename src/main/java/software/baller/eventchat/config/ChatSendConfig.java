package software.baller.eventchat.config;

import com.rabbitmq.stream.Environment;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.producer.RabbitStreamTemplate;
import org.springframework.rabbit.stream.support.StreamAdmin;

@Configuration
public class ChatSendConfig {

    @Bean
    RabbitStreamTemplate streamTemplate(Environment env) {
        RabbitStreamTemplate streamTemplate = new RabbitStreamTemplate(env, "chat-room-1");
        streamTemplate.setMessageConverter(converter());
        return streamTemplate;
    }

    @Bean
    Queue stream() {
        return QueueBuilder.durable("chat-room-1")
            .stream()
            .build();
    }

    @Bean
    StreamAdmin streamAdmin(Environment env) {
        return new StreamAdmin(env, sc -> {
            sc.stream("chat-room-1").create();
        });
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }
}
