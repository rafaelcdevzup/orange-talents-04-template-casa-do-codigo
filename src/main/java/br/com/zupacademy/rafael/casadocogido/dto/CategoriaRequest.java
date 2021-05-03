package br.com.zupacademy.rafael.casadocogido.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.rafael.casadocogido.Entity.Categoria;

public class CategoriaRequest {
	@NotBlank @NotNull
	private String nome;

	public CategoriaRequest() {
		
	}
	
	public CategoriaRequest(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Categoria toModel() {
		return new Categoria(this.nome);
	}
}