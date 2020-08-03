package br.com.fiscal.service.interfaces;

import java.util.Optional;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;

public interface EmpresaServiceInterface extends ServiceInterface<Empresa> {
	
	MensagemResponseDTO insert(Empresa empresa);
	MensagemResponseDTO alterar(Long id, Empresa empresa);
	MensagemResponseDTO remover(Long id);
	
	Optional<Empresa> findById(Long id);
}
