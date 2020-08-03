package br.com.fiscal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmpresaNotFoundException extends RuntimeException {

	
	public EmpresaNotFoundException(long id) {
		super("Empresa de ID: "+id+" não encontrada.");
	}
	
}
