package br.com.zupacademy.rafael.casadocogido.Controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.rafael.casadocogido.Entity.Autor;
import br.com.zupacademy.rafael.casadocogido.Repository.AutorRepository;
import br.com.zupacademy.rafael.casadocogido.dto.AutorRequest;
import br.com.zupacademy.rafael.casadocogido.validacaoCompartilhada.CampoUnico;

@RestController
@RequestMapping(value = "/autores")
public class AutorController {
	
	@Autowired
	private AutorRepository autorRepository;
	
	@PostMapping
	public ResponseEntity<AutorRequest> inserindoAutor(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.toModel();
		if(autorRepository.existsByEmail(request.getEmail())) {
			return ResponseEntity.ok().build();
		}
		autorRepository.save(autor);
		return ResponseEntity.ok().build();
	}

	@InitBinder
	public void validacao(WebDataBinder binder) {
		CampoUnico<AutorRequest, String> validadorEmail = new CampoUnico<>("email", AutorRequest.class, autorRepository::existsByEmail);
		binder.addValidators(validadorEmail);
	}
}