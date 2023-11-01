package software.baller.eventchat.config;

import software.baller.eventchat.domain.ChatMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class WebsocketBusConfig {

    @Bean
    public Sinks.Many<ChatMessageDTO> websocketBus(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

}
