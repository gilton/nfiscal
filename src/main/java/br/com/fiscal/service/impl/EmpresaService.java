package br.com.fiscal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.repository.EmpresaRepository;
import br.com.fiscal.service.interfaces.EmpresaServiceInterface;

@Service
public class EmpresaService implements EmpresaServiceInterface {
	
	@Autowired
	private EmpresaRepository repository;

	@Override
	public Empresa findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new EmpresaNotFoundException("Empresa não encontrada!!"));
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
		
		Empresa empresaRecuperado = findById(id);
		update.setId( empresaRecuperado.getId() );
		
		repository.save(update);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, alterado com sucesso!", update.getId())).build(); 
	}
	
	
	@Override
	public MensagemResponseDTO remover(@PathVariable Long id) {
		Empresa empresa = findById(id); 
		repository.delete( empresa );
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, removido com sucesso!", empresa.getId())).build();
	
	}

}
