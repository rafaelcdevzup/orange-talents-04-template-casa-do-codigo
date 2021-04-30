package br.com.zupacademy.rafael.casadocogido.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.rafael.casadocogido.Entity.Autor;

public class AutorRequest {
	
	@NotBlank @NotNull
	private String nome;
	@NotBlank @Email
	private String email;
	@NotNull @Size(max = 400)
	private String descricao;
	
	public AutorRequest(String nome, String email, String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}
}