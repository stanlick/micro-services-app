package com.microservices.microservices.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
class ApiError {

	private WebRequest webRequest;
	private HttpStatus status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	private String message;
	private String debugMessage;
	private List<ApiSubError> subErrors;

	ApiError(HttpStatus status) {
		this.status = status;
		this.timestamp = LocalDateTime.now(ZoneId.of("America/Chicago"));
	}

	ApiError(WebRequest request, HttpStatus status, String message, Throwable ex) {
		this(status);
		this.webRequest=request;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
}

