package yura.valkiv.SpringApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringApiApp {

	public static void main(String[] args) {
		SpringApplication.run(SpringApiApp.class, args);
	}

}
