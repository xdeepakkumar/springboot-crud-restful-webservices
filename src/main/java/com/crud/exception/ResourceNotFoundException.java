package com.crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javassist.SerialVersionUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
	
	private static final long SerialVersionUID = 1L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}
}
