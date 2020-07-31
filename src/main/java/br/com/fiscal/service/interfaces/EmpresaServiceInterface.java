package br.com.fiscal.service.interfaces;

import br.com.fiscal.entity.Empresa;

public interface EmpresaServiceInterface extends ServiceInterface<Empresa> {
	
	Empresa findById(Long id);
}
