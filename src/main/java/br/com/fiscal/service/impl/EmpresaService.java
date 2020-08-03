package br.com.fiscal.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.exception.EntityBadRequestException;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.repository.EmpresaRepository;
import br.com.fiscal.service.interfaces.EmpresaServiceInterface;

@Service
public class EmpresaService implements EmpresaServiceInterface {
	
	@Autowired
	private EmpresaRepository repository;

	@Override
	public Optional<Empresa> findById(Long id) {
		
		if( id < 0 ) throw new EntityBadRequestException();
		
		return repository.findById(id);
	}
	
	public List<Empresa> findAll() {
		return repository.findAll();
	} 
	
	@Override
	public MensagemResponseDTO insert(@RequestBody Empresa empresa) {
		repository.save(empresa);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, adicionado com sucesso!", empresa.getId())).build();
	}
	
	@Override
	public MensagemResponseDTO alterar(@PathVariable Long id, @RequestBody Empresa update) {
		
		Empresa empresaRecuperado = findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
		update.setId( empresaRecuperado.getId() );
		
		repository.save(update);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, alterado com sucesso!", update.getId())).build(); 
	}
	
	
	@Override
	public MensagemResponseDTO remover(@PathVariable Long id) {
		Empresa empresa;
		empresa = findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
		repository.delete( empresa );
		
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, removido com sucesso!", empresa.getId())).build();
		
	}

}
