package com.mgmoura.dtos;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ValidationResponseDto {
	
	private HttpStatus status;
	private String message;
	private List<ValidationErrorResponseDto> errors;

}
