package br.com.fiscal.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	@Override
	public Optional<NotaFiscal> findById(Long id) {
		
		if( id < 0 ) throw new EntityBadRequestException();
		
		return repository.findById(id);
	}
	
	public List<NotaFiscal> findAll() {
		return repository.findAll();
	} 
	
	@Override
	public List<NotaFiscal> findyByEmpresaSTR(String str) {
		return repository.findyByEmpresaSTR(str);
	}
	
	
	@Override
	public MensagemResponseDTO insert(@RequestBody NotaFiscal notaFiscal) {
		repository.save(notaFiscal);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, adicionado com sucesso!", notaFiscal.getId())).build();
	}
	
	@Override
	public MensagemResponseDTO alterar(@PathVariable Long id, @RequestBody NotaFiscal update) {
		
		NotaFiscal notaFiscalRecuperado = findById(id).orElseThrow(() -> new NotaFiscalNotFoundException(id));
		update.setId( notaFiscalRecuperado.getId() );
		
		repository.save(update);
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, alterado com sucesso!", update.getId())).build(); 
	}
	
	
	@Override
	public MensagemResponseDTO remover(@PathVariable Long id) {
		NotaFiscal nf = findById(id).orElseThrow(() -> new NotaFiscalNotFoundException(id));
		repository.delete( nf );
		return MensagemResponseDTO.builder()
				.mensagem(String.format("Nota Fiscal de ID %s, removido com sucesso!", nf.getId())).build();
	}


}
