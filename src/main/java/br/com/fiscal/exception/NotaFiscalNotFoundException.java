package br.com.fiscal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotaFiscalNotFoundException extends RuntimeException {
	
	public NotaFiscalNotFoundException(long id) {
		super("Nota Fiscal de ID: "+id+" não encontrado.");
	}
}
