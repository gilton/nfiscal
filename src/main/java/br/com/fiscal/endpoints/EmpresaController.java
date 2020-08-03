package br.com.fiscal.endpoints;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.service.impl.EmpresaService;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService service;
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponseDTO insert(@Valid @RequestBody Empresa empresa) {
		return service.insert(empresa);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public MensagemResponseDTO alterar(@PathVariable Long id, @Valid @RequestBody Empresa update) throws EmpresaNotFoundException {
		
		Empresa empresaRecuperado = service.findById(id).orElseThrow(() -> new EmpresaNotFoundException(id));
		update.setId( empresaRecuperado.getId() );
		
		return service.insert(update);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.GONE)
	public MensagemResponseDTO remover(@PathVariable Long id) throws EmpresaNotFoundException {
		return service.remover(id);
	}
	
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> findById(@PathVariable Long id) throws EmpresaNotFoundException {
		return 
				new ResponseEntity<Empresa>(service.findById(id)
						.orElseThrow(() -> new EmpresaNotFoundException(id)),
						HttpStatus.OK);
	}
	
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<Empresa> findAll() {
		return service.findAll();
	}
}
