package br.com.fiscal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiscal.entity.NotaFiscal;
import br.com.fiscal.exception.NotaFiscalNotFoundException;
import br.com.fiscal.repository.NotaFiscalRepository;
import br.com.fiscal.service.interfaces.NotaFiscalServiceInterface;

@Service
public class NotaFiscalService implements NotaFiscalServiceInterface {
	
	@Autowired
	private NotaFiscalRepository repository;

	@Override
	public NotaFiscal findById(Long id) {
		return repository.findById(id).orElseThrow(() -> new NotaFiscalNotFoundException("Nota Fiscal não encontrada!!"));
	}
	
	public List<NotaFiscal> findAll() {
		return repository.findAll();
	} 
	
	public NotaFiscal insert(@RequestBody NotaFiscal notaFiscal) {
		return repository.save(notaFiscal);
	}
	
	public NotaFiscal alter(@PathVariable Long id, @RequestBody NotaFiscal update) {
		
		NotaFiscal notaFiscalRecuperado = findById(id);
		update.setId( notaFiscalRecuperado.getId() );
		
		return repository.save(update);
	}
	
	
	public void delete(@PathVariable Long id) {
		repository.delete( findById(id) );
	}

	@Override
	public List<NotaFiscal> findyByEmpresaSTR(String str) {
		return repository.findyByEmpresaSTR(str);
	}

}
