package br.com.zupacademy.rafael.casadocogido.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadrao> validation(MethodArgumentNotValidException e, HttpServletRequest request){
		ErroPadrao err = new ErroPadrao();
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		err.setTimestamp(Instant.now());
		err.setStatus(status.value());
		err.setError("Validation exception");
		err.setMessage("Não pode haver duplicação no banco de dados e não pode ser em branco");
		err.setPath(request.getRequestURI());
	
		return ResponseEntity.status(status).body(err);
	}
}

