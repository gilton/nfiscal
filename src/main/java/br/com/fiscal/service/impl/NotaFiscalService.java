package br.com.fiscal.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiscal.dto.mapper.NotaFiscalMapper;
import br.com.fiscal.dto.request.NotaFiscalDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.NotaFiscal;
import br.com.fiscal.exception.EntityBadRequestException;
import br.com.fiscal.exception.NotaFiscalNotFoundException;
import br.com.fiscal.repository.NotaFiscalRepository;
import br.com.fiscal.service.interfaces.NotaFiscalServiceInterface;

@Service
public class NotaFiscalService implements NotaFiscalServiceInterface {
	
	@Autowired
	private NotaFiscalRepository repository;
	
	private NotaFiscalMapper notaFiscalMapper = NotaFiscalMapper.INSTANCE;
	
	
	@Override
	public NotaFiscalDTO findById(Long id) {
		
		if( id < 0 ) throw new EntityBadRequestException();
		
		NotaFiscal nf = repository.findById(id).orElseThrow(() -> new NotaFiscalNotFoundException(id));
		
		return notaFiscalMapper.toDTO( nf );
	}
	
	public List<NotaFiscalDTO> findAll() {
		List<NotaFiscal> notasficais = repository.findAll();
		
		return notasficais
				.stream()
				.map(notaFiscalMapper::toDTO)
				.collect(Collectors.toList());
	} 
	
	@Override
	public List<NotaFiscalDTO> findyByEmpresaSTR(String str) {
		List<NotaFiscal> notasFiscais = repository.findyByEmpresaSTR(str);
		return notasFiscais
				.stream()
				.map(notaFiscalMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	
	@Override
	public MensagemResponseDTO insert(@Valid @RequestBody NotaFiscalDTO notaFiscalDto) {
		NotaFiscal nf = notaFiscalMapper.toModel(notaFiscalDto);
		repository.save(nf);
		
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, adicionado com sucesso!", nf.getId())).build();
	}
	
	@Override
	public MensagemResponseDTO alterar(@PathVariable Long id, @Valid @RequestBody NotaFiscalDTO update) {
		
		verificarSeNotaFiscalExiste(id);
		NotaFiscal nf = notaFiscalMapper.toModel(update);
		
		repository.save(nf);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, alterado com sucesso!", update.getId())).build(); 
	}
	
	
	@Override
	public MensagemResponseDTO remover(@PathVariable Long id) {
		NotaFiscal nf = verificarSeNotaFiscalExiste(id);
		repository.delete( nf );
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, removido com sucesso!", nf.getId())).build();
	}

	
//	AUXILIARY METHOD
	private NotaFiscal verificarSeNotaFiscalExiste(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotaFiscalNotFoundException(id));

	}

}
