package org.example.springexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringExamplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringExamplesApplication.class, args);
	}

	@GetMapping("/")
	public String hello() {
		return "Hello, World!";
	}
}
