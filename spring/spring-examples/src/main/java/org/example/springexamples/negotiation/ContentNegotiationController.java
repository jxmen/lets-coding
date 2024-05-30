package org.example.springexamples.negotiation;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("content-negotiation")
public class ContentNegotiationController {

	@GetMapping(value = "hello", produces = { "application/json", "application/xml" })
	public ResponseEntity<HelloResponse> hello() {
		HelloResponse response = new HelloResponse("Hello, World!");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@JacksonXmlRootElement(localName = "greeting")
	record HelloResponse(String message) { }
}
