package vadim.shtukan.otus.architect.finelproject.Document;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.Jackson2JavaTypeMapper;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import vadim.shtukan.otus.architect.finelproject.Document.Models.UserGroup;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableCaching
public class DocumentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DocumentApplication.class, args);
	}

	@Bean
	public NewTopic topic() {
		return new NewTopic("userGroup", 1, (short) 1);
	}

	@Bean
	public RecordMessageConverter converter() {
		StringJsonMessageConverter converter = new StringJsonMessageConverter();
		DefaultJackson2JavaTypeMapper typeMapper = new DefaultJackson2JavaTypeMapper();
		typeMapper.setTypePrecedence(Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID);
		typeMapper.addTrustedPackages("vadim.shtukan.otus.architect.finelproject.Document.Models");
		Map<String, Class<?>> mappings = new HashMap<>();
		mappings.put("userGroup", UserGroup.class);
		//mappings.put("bar", Bar2.class);
		typeMapper.setIdClassMapping(mappings);
		converter.setTypeMapper(typeMapper);
		return converter;
	}

//	@Bean
//	public ApplicationRunner runner() {
//		return args -> {
//			System.out.println("Hit Enter to terminate...");
//			System.in.read();
//		};
//	}

}
