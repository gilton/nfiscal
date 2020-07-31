package br.com.fiscal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

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
	
	public Empresa insert(@RequestBody Empresa empresa) {
		return repository.save(empresa);
	}
	
	public Empresa alter(@PathVariable Long id, @RequestBody Empresa update) {
		
		Empresa empresaRecuperado = findById(id);
		update.setId( empresaRecuperado.getId() );
		
		return repository.save(update);
	}
	
	
	public void delete(@PathVariable Long id) {
		repository.delete( findById(id) );
	}

}
