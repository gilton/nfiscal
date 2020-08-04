package br.com.fiscal.service.interfaces;

import br.com.fiscal.dto.request.EmpresaDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;

public interface EmpresaServiceInterface extends ServiceInterface<Empresa> {
	
	MensagemResponseDTO insert(EmpresaDTO empresaDto);
	MensagemResponseDTO alterar(Long id, EmpresaDTO empresaDto);
	MensagemResponseDTO remover(Long id);
	
	EmpresaDTO findById(Long id);
}
