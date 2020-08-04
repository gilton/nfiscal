package br.com.fiscal.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.fiscal.entity.Servico;

public class PrestadorDTO {
	
	private Long id;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private String dataInicio;
	
	@NotNull
	private String dataFim;
	
	@NotNull
	private EmpresaDTO empresaDto;
	
	private List<Servico> servicos;
	
}
