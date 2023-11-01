package software.baller.eventchat.config;

import com.rabbitmq.stream.ConsumerUpdateListener;
import com.rabbitmq.stream.Environment;
import com.rabbitmq.stream.OffsetSpecification;
import software.baller.chat.ChatMessage;
import software.baller.eventchat.domain.ChatService;
import org.jline.terminal.Terminal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.rabbit.stream.config.StreamRabbitListenerContainerFactory;
import org.springframework.rabbit.stream.listener.StreamListenerContainer;

@Configuration
public class ChatReceiveConfig {

    private final Terminal terminal;
    private final ChatService chatService;

    public ChatReceiveConfig(Terminal terminal, ChatService chatService) {
        this.terminal = terminal;
        this.chatService = chatService;
    }

    @Bean
    RabbitListenerContainerFactory<StreamListenerContainer> rabbitListenerContainerFactory(Environment env) {
        StreamRabbitListenerContainerFactory factory = new StreamRabbitListenerContainerFactory(env);
        factory.setConsumerCustomizer((id, builder) -> {
            builder.name("messageConsumer")
                .offset(OffsetSpecification.first())
                .manualTrackingStrategy();
        });
        return factory;
    }

    @RabbitListener(queues = "persistent-chat-room", messageConverter = "converter")
    void listen(ChatMessage event) {
        terminal.writer().println(event.name() + ": " + event.message());
        terminal.flush();
        chatService.saveEvent(event);
    }
}
