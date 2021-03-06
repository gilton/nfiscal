package br.com.fiscal.endpoints;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiscal.dto.request.EmpresaDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.service.impl.EmpresaService;

@RestController
@RequestMapping("/api/v1/empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService service;
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponseDTO insert(@Valid @RequestBody EmpresaDTO empresaDto) {
		return service.insert(empresaDto);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public MensagemResponseDTO alterar(@PathVariable Long id, @Valid @RequestBody EmpresaDTO empresaDto) throws EmpresaNotFoundException {
		return service.alterar(id, empresaDto);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.GONE)
	public MensagemResponseDTO remover(@PathVariable Long id) throws EmpresaNotFoundException {
		return service.remover(id);
	}
	
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	public EmpresaDTO findById(@PathVariable Long id) throws EmpresaNotFoundException {
		return service.findById(id);
	}
	
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<EmpresaDTO> findAll() {
		return service.findAll();
	}
}
