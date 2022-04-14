package capston.noodles;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class NoodlesApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(NoodlesApplication.class, args);
//	}

	public static final String APPLICATION_LOCATIONS =
			"spring.config.location="
					+ "classpath:application.properties,"
					+ "classpath:application-db.properties,"
					+ "classpath:application-jwt.properties,"
					+ "classpath:application-aws.properties";

	public static void main(String[] args) {
		new SpringApplicationBuilder(NoodlesApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
//		SpringApplication.run(NoodlesApplication.class, args);
	}


	@Bean
	public PasswordEncoder passwordEncoder(){
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

}
