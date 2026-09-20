package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController

public class cx 
{	@GetMapping("/hello")
String m1()
	
	{	Logger logger = LoggerFactory.getLogger(cx.class);
logger.info("Execution Started");
logger.info("Execution Ended");
		return "hello";
		

	}
}
