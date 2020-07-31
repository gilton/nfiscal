package br.com.fiscal.exception;

public class EmpresaInvalidaException extends RuntimeException {
	
	public final static String MSG_EMPRESA_INVALIDA = "Empresa Invalida!";
	
	public EmpresaInvalidaException(String msg) {
		super(msg);
	}
	
}
