package rest_api.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestApiEcommerceProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiEcommerceProjectApplication.class, args);
	}

}
