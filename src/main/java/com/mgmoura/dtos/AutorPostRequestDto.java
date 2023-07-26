package com.mgmoura.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AutorPostRequestDto {

	@NotBlank(message = "Campo NOME é obrigatório .")
	private String nome;
	
	private String sexo;

	private String email;
	
	@NotBlank(message = "Campo DATA DE NASCIMENTO é obrigatório .")
	private String dataNascimento;
	
	private String paisOrigem;
	
	private String cpf;
	
	
}
