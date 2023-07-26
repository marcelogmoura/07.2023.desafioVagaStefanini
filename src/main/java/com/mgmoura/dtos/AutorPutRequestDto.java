package com.mgmoura.dtos;

import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class AutorPutRequestDto {
	
	@Min(value = 1, message = "ID da Autor deve ser maior ou igual a 1.")
	private Integer idAutor;
	
	@NotBlank(message = "Campo NOME é obrigatório .")
	private String nome;
	
	@NotBlank(message = "Campo DATA DE NASCIMENTO é obrigatório .")
	private String dataNascimento;
	
	private String sexo;
	private String email;
	private String paisOrigem;
	private String cpf;
	

}
