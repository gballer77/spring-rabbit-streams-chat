package software.baller.randommessageproducer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RandomMessageEmitter {

	public static void main(String[] args) {
		SpringApplication.run(RandomMessageEmitter.class, args);
	}

}
