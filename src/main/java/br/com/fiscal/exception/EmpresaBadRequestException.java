package br.com.fiscal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmpresaBadRequestException extends RuntimeException {
	
	public EmpresaBadRequestException() {
		super("Argumento de buscar inválido!");
	}
	
}
