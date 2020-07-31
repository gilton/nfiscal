package br.com.fiscal.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.entity.NotaFiscal;
import br.com.fiscal.exception.NotaFiscalNotFoundException;
import br.com.fiscal.service.impl.NotaFiscalService;

@RestController
@RequestMapping("/api/v1/notafiscal")
public class NotaFiscalController {

	@Autowired
	private NotaFiscalService service;
	
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public MensagemResponseDTO insert(@RequestBody NotaFiscal NotaFiscal) {
		return service.insert(NotaFiscal);
	}
	
	@RequestMapping(path = "/update/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public MensagemResponseDTO alter(@PathVariable Long id, @RequestBody NotaFiscal update) {
		
		NotaFiscal NotaFiscalRecuperado = findById(id);
		update.setId( NotaFiscalRecuperado.getId() );
		
		return service.insert(update);
	}
	
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public MensagemResponseDTO remover(@PathVariable Long id) {
		return service.remover(id);
	}
	
	@RequestMapping(path = "/find/{id}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public NotaFiscal findById(@PathVariable Long id) {
		System.out.println("id: "+id+"\n");
		return service.findById(id);
	}
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<NotaFiscal> findAll() {
		return service.findAll();
	}
	
	@RequestMapping(path = "/pesquisaPorEmpresa/{pesquisa}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public List<NotaFiscal> findyByEmpresa(@PathVariable String pesquisa) throws NotaFiscalNotFoundException {
		return service.findyByEmpresaSTR(pesquisa);
	}
	
	
}
