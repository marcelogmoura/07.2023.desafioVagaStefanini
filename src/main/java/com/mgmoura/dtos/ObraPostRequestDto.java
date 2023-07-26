package com.mgmoura.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class ObraPostRequestDto {
	
	@Min(value = 1 , message = "ID da obra deve ser maior ou igual a 1.")
	private Integer idAutor;
	
	@NotBlank(message = "Campo NOME é obrigatório.")
	private String nome;
		
	@NotBlank(message = "Campo NOME é obrigatório." )
	@Pattern(regexp = "^[A-Za-zÀ-Üà-ü0-9\\\\s]{3,150}$" , message = "Limite de caracteres é 240.")
	private String descricao;
		
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}" , message = "Formato correto'yyyy-MM-dd'.")
	private String dataPublicacao;
	
	@Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}" , message = "Formato correto'yyyy-MM-dd'.")
	private String dataExposicao;

}
