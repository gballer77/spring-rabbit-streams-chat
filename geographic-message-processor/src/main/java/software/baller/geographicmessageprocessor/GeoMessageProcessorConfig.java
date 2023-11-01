package software.baller.geographicmessageprocessor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.baller.chat.ChatMessage;
import software.baller.geo.GeoEvent;

import java.math.BigDecimal;
import java.util.function.Function;

@Configuration
public class GeoMessageProcessorConfig {
    @Bean
    public Function<ChatMessage, GeoEvent> processName() {
        return message -> {
            System.out.println("Received");
            return new GeoEvent(message.message(), new BigDecimal(0), new BigDecimal(0));
        };
    }
}
