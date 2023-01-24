package org.openapitools.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestTextController {
	@Value("${test-text}")
	private String testText;
	

	@GetMapping("/text")
	public ResponseEntity<String> testText() {
		System.out.println("alma");
		return new ResponseEntity<>(testText,
				HttpStatus.OK);
	}
	
}
