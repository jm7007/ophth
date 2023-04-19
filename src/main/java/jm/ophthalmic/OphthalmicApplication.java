package jm.ophthalmic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class OphthalmicApplication {

	public static void main(String[] args) {
		SpringApplication.run(OphthalmicApplication.class, args);
	}
}
