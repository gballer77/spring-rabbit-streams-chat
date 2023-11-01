package software.baller.randommessageproducer;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import software.baller.chat.ChatMessage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

@Configuration
public class RandomMessageEmitterConfig {
    private static final double MIN_LATITUDE = 25.8371;  // Southernmost point of Texas
    private static final double MAX_LATITUDE = 36.5007;  // Northernmost point of Texas
    private static final double MIN_LONGITUDE = -106.6495;  // Westernmost point of Texas
    private static final double MAX_LONGITUDE = -93.5072;  // Easternmost point of Texas

    @Bean
    public Sinks.Many<ChatMessage> bus(){
        return Sinks.many().multicast().onBackpressureBuffer();
    }

    @Bean
    public Supplier<Flux<ChatMessage>> randomChat() {
        return () -> bus().asFlux();
    }

    @Scheduled(fixedRate = 10000)
    public void scheduleFixedDelayTask() {
        bus().tryEmitNext(new ChatMessage("Emergency Broadcast", randomEmergencyMessage()));
        System.out.print(".");
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    private String randomEmergencyMessage() {
        Random rand = new Random();
        String emergencyType = emergencyTypes().get(rand.nextInt(emergencyTypes().size()));
        String latLon = generateRandomCoordinates();

        return "Emergency Type: " + emergencyType + " @ " + latLon;
    }

    @Bean
    public List<String> emergencyTypes() {
        return List.of("Vehicle Accident",
            "Medical Emergency",
            "Fire Incident",
            "Missing Person",
            "Hazardous Materials Spill",
            "Suspicious Activity",
            "Flooding",
            "Gas Leak",
            "Severe Weather Warning",
            "Robbery",
            "Suspected Domestic Violence",
            "Wildlife Encounter",
            "Chemical Spill",
            "Child Abduction",
            "House Fire",
            "Tornado Warning",
            "Suspicious Package",
            "Drowning Incident",
            "Domestic Disturbance",
            "Hazardous Material Leak",
            "Armed Robbery",
            "Wildfire",
            "Suspicious Person",
            "Medical Emergency",
            "Vehicle Accident",
            "Flash Flood Warning",
            "Missing Child",
            "Gas Leak",
            "Suspicious Activity");
    }

    private static String generateRandomCoordinates() {
        Random random = new Random();

        // Generate random latitude within the bounding box
        double latitude = MIN_LATITUDE + (MAX_LATITUDE - MIN_LATITUDE) * random.nextDouble();

        // Generate random longitude within the bounding box
        double longitude = MIN_LONGITUDE + (MAX_LONGITUDE - MIN_LONGITUDE) * random.nextDouble();

        // Format the coordinates as human-readable strings
        String latitudeString = String.format("%.4f° %s", Math.abs(latitude), (latitude >= 0) ? "N" : "S");
        String longitudeString = String.format("%.4f° %s", Math.abs(longitude), (longitude >= 0) ? "E" : "W");

        return latitudeString + ", " + longitudeString;
    }
}
