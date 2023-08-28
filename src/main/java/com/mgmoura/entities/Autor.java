package com.mgmoura.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "autor")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Autor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idautor")
	private Integer idAutor;
	
	@Column(name = "nome", nullable = false)
	private String nome;
	
	@Column(name = "sexo")
	private String sexo;
	
	@Column(name = "email" , unique = true)
	private String email;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataNascimento;
	
	@Column(name = "paisOrigem",  nullable = false)
	private String paisOrigem;
	
	@Column(name = "cpf")
	private String cpf;


	

}




