package achmad.rifai.inventory.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class InventoryConfig {

	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
	}

}
