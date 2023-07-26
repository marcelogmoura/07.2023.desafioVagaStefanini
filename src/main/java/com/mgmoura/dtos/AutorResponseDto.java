package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.Autor;

import lombok.Data;

@Data
public class AutorResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private Autor autor;

}
