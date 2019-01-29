package com.felipe.uolChallenge.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
public class Cliente {
	
	@JsonInclude(Include.NON_NULL)
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="O campo nome não pode ser vazio.")
	private String nome;
	
	@NotNull(message="O campo idade é de preenchimento obrigatório.")
	private Long idade;
	
	@JsonInclude(Include.NON_EMPTY)
	@OneToOne(mappedBy = "cliente")
	private DadosDoCadastrante dadosDoCadastrante;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getIdade() {
		return idade;
	}
	public void setIdade(Long idade) {
		this.idade = idade;
	}
	public DadosDoCadastrante getDadosDoCadastrante() {
		return dadosDoCadastrante;
	}
	public void setDadosDoCadastrante(DadosDoCadastrante dadosDoCadastrante) {
		this.dadosDoCadastrante = dadosDoCadastrante;
	}
	
	
	
	
}
