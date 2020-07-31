package br.com.fiscal.service.interfaces;

import java.util.List;

import br.com.fiscal.entity.NotaFiscal;

public interface NotaFiscalServiceInterface extends ServiceInterface<NotaFiscal> {
	
	NotaFiscal findById(Long id);
	
	List<NotaFiscal> findyByEmpresaSTR(String str);
	
}
