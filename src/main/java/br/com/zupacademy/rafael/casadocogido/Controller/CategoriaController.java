package br.com.zupacademy.rafael.casadocogido.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rafael.casadocogido.Entity.Categoria;
import br.com.zupacademy.rafael.casadocogido.Repository.CategoriaRepository;
import br.com.zupacademy.rafael.casadocogido.dto.CategoriaRequest;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	
	@PostMapping
	public ResponseEntity<CategoriaRequest> inserindoCategoria(@RequestBody @Valid CategoriaRequest request) {
		Categoria cat = request.toModel();
		if(repository.existsByNome(request.getNome())) {
			return ResponseEntity.badRequest().build();
		}
		repository.save(cat);
		return ResponseEntity.ok().build();
	}
}