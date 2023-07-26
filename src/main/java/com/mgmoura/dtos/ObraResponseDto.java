package com.mgmoura.dtos;

import org.springframework.http.HttpStatus;

import com.mgmoura.entities.Obra;

import lombok.Data;

@Data
public class ObraResponseDto {
	
	private HttpStatus status;
	private String mensagem;
	private Obra obra;

}
