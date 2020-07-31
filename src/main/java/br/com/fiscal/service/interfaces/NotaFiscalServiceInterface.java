package br.com.fiscal.service.interfaces;

import java.util.List;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.NotaFiscal;

public interface NotaFiscalServiceInterface extends ServiceInterface<NotaFiscal> {
	
	
	MensagemResponseDTO insert(NotaFiscal notaFiscal);
	MensagemResponseDTO alterar(Long id, NotaFiscal notaFiscal);
	MensagemResponseDTO remover(Long id);
	
	NotaFiscal findById(Long id);
	
	List<NotaFiscal> findyByEmpresaSTR(String str);
	
}
