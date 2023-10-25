package software.baller.eventchat.config;

import software.baller.eventchat.domain.ChatMessageDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Sinks;

@Configuration
public class BusConfig {

    @Bean
    public Sinks.Many<ChatMessageDTO> bus(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

}
