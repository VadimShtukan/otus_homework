package stukan.vadim.otus.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Lesson2Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson2Application.class, args);
	}

}
