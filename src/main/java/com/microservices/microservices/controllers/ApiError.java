package com.microservices.microservices.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
class ApiError {

	private WebRequest webRequest;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
	private final LocalDateTime timestamp = LocalDateTime.now();
	private String message;
	private Throwable throwable;
	private List<ApiSubError> subErrors;



}

