package br.com.zupacademy.rafael.casadocogido.validacaoCompartilhada;

import java.lang.reflect.Field;
import java.util.function.Function;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CampoUnico<T, P> implements Validator {

	private String campo;
	private Class<? extends T> classeParaSerValidada;
	private Function<P, Boolean> metodoQueVaiNoBanco;
	
	public CampoUnico() {	
	}
	 
	@Override
	public void validate(Object o, Errors errors) {
		
		try {
			Field declaredField = classeParaSerValidada.getDeclaredField(this.campo);
			declaredField.setAccessible(true);
			Object valorASerPesquisado = declaredField.get(o);
			Boolean existeNome = metodoQueVaiNoBanco.apply((P) valorASerPesquisado);
			
			if(existeNome) {
				errors.rejectValue(campo, "campoUnico", "O Campo deve ser unico");
			}
			
		}catch(IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
	}
	
	public CampoUnico(String campo,
					Class<? extends T> classeParaSerValidada,
					Function<P, Boolean> metodoQueVaiNoBanco) {
		this.campo = campo;
		this.classeParaSerValidada = classeParaSerValidada;
		this.metodoQueVaiNoBanco = metodoQueVaiNoBanco;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return this.classeParaSerValidada.isAssignableFrom(clazz);
	}
}