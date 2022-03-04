package com.microservices.microservices.controllers;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class CalendarUtils {

	@GetMapping("/now")
	public LocalDateTime now(){
		return LocalDateTime.now();
	}
}
