package br.com.zupacademy.rafael.casadocogido.Controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rafael.casadocogido.Entity.Autor;
import br.com.zupacademy.rafael.casadocogido.Repository.AutorRepository;
import br.com.zupacademy.rafael.casadocogido.dto.AutorRequest;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@PostMapping
	public ResponseEntity<AutorRequest> inserindoAutor(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.toModel();
		Optional<Autor> verificaEmail = autorRepository.findByEmail(request.getEmail());
		if(verificaEmail.isPresent()) {
			return ResponseEntity.badRequest().build();
		}
		autorRepository.save(autor);
		return ResponseEntity.ok().build();	
	}
}