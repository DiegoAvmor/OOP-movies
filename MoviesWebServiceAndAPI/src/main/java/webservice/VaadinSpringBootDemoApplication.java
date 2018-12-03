package webservice;

import com.vaadin.spring.annotation.EnableVaadin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaadinSpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(VaadinSpringBootDemoApplication.class, args);
	}
}
