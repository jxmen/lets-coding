package org.example.springexamples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 이걸 붙여야 @Async 어노테이션이 동작한다.
 *
 * Thread Pool 관련 설정들도 같이 해주는게 좋다.
 */
@EnableAsync
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
