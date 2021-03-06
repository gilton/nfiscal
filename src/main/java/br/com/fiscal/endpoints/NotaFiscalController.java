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

import br.com.fiscal.dto.request.NotaFiscalDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.exception.NotaFiscalNotFoundException;
import br.com.fiscal.service.impl.NotaFiscalService;

@RestController
@RequestMapping("/api/v1/notafiscais")
public class NotaFiscalController {

	@Autowired
	private NotaFiscalService service;
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponseDTO insert(@Valid @RequestBody NotaFiscalDTO NotaFiscalDto) {
		return service.insert(NotaFiscalDto);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public MensagemResponseDTO alterar(@PathVariable Long id, @Valid @RequestBody NotaFiscalDTO updateDto) {
		return service.alterar(id, updateDto);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public MensagemResponseDTO remover(@PathVariable Long id) {
		return service.remover(id);
	}
	
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public NotaFiscalDTO findById(@PathVariable Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<NotaFiscalDTO> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(path = "/pesquisaPorEmpresa/{pesquisa}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<NotaFiscalDTO> findyByEmpresa(@PathVariable String pesquisa) throws NotaFiscalNotFoundException {
		return service.findyByEmpresaSTR(pesquisa);
	}
	
}
