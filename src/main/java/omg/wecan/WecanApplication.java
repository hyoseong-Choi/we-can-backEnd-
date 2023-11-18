package omg.wecan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class WecanApplication {

	public static void main(String[] args) {
		SpringApplication.run(WecanApplication.class, args);
	}

}
