package com.mgmoura.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "obra")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Obra {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idObra")
	private Integer idObra;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "descricao", length = 240 , nullable = false)
	private String descricao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataPublicacao;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataExposicao;
	
	@ManyToOne
	@JoinColumn(name = "idAutor")
	private Autor autor;

}
