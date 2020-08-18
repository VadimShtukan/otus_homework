package vadim.shtukan.otus.architect.finelproject.Key;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import vadim.shtukan.otus.architect.finelproject.KafkaModel.UserKafka;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class JwtApplication {

	public static void main(String[] args) {
		SpringApplication.run(JwtApplication.class, args);
	}

	@Bean
	public RecordMessageConverter converter() {
		StringJsonMessageConverter converter = new StringJsonMessageConverter();
		DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
		typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
		typeMapper.addTrustedPackages("vadim.shtukan.otus.architect.finelproject.KafkaModel");
		Map<String, Class<?>> mappings = new HashMap<>();
		mappings.put("UserKafka", UserKafka.class);
		typeMapper.setIdClassMapping(mappings);
		converter.setTypeMapper(typeMapper);
		return converter;
	}
}
