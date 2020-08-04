package br.com.fiscal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiscal.dto.mapper.EmpresaMapper;
import br.com.fiscal.dto.request.EmpresaDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.exception.EntityBadRequestException;
import br.com.fiscal.repository.EmpresaRepository;
import br.com.fiscal.service.interfaces.EmpresaServiceInterface;

@Service
public class EmpresaService implements EmpresaServiceInterface {
	
	@Autowired
	private EmpresaRepository repository;
	
	private final EmpresaMapper empresaMapper = EmpresaMapper.INSTANCE;
	
	
//	CRUD METHODS
	public List<Empresa> findAll() { return repository.findAll(); }

	@Override
	public EmpresaDTO findById(Long id) {
		
		if( id < 0 ) throw new EntityBadRequestException();
		Empresa empresa = verificarSeEmpresaExiste(id);
		
		return empresaMapper.toDTO(empresa);
	}
	
	
	@Override
	public MensagemResponseDTO insert(@RequestBody EmpresaDTO empresaDto) {
		Empresa empresa = empresaMapper.toModel(empresaDto);
		
		repository.save(empresa);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, adicionado com sucesso!", empresa.getId())).build();
	}
	
	@Override
	public MensagemResponseDTO alterar(@PathVariable Long id, @RequestBody EmpresaDTO empresaDto) {
		verificarSeEmpresaExiste(id);
		
		if( empresaDto == null ) throw new EmpresaNotFoundException(id);
		
		Empresa empresaAAlterar = empresaMapper.toModel(empresaDto); 
		
		repository.saveAndFlush(empresaAAlterar);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, alterado com sucesso!", empresaAAlterar.getId())).build(); 
	}
	
	
	@Override
	public MensagemResponseDTO remover(@PathVariable Long id) {
		verificarSeEmpresaExiste(id);
		repository.deleteById( id );
		
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Empresa de ID %s, removido com sucesso!", id)).build();
		
	}
	
//	AUXILIARY METHOD
	private Empresa verificarSeEmpresaExiste(Long id) {
		Empresa empresa = repository.findById(id)
				.orElseThrow( () -> new EmpresaNotFoundException(id) );
		return empresa;
	}
	
}
