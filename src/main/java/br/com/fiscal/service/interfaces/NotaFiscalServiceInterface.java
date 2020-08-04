package br.com.fiscal.service.interfaces;

import java.util.List;

import br.com.fiscal.dto.request.NotaFiscalDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.NotaFiscal;

public interface NotaFiscalServiceInterface extends ServiceInterface<NotaFiscal> {
	
	
	MensagemResponseDTO insert(NotaFiscalDTO notaFiscalDto);
	MensagemResponseDTO alterar(Long id, NotaFiscalDTO notaFiscalDto);
	MensagemResponseDTO remover(Long id);
	
	NotaFiscalDTO findById(Long id);
	
	List<NotaFiscalDTO> findyByEmpresaSTR(String str);
	
}
