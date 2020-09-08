package br.com.fiscal.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.fiscal.entity.Prestador;
import br.com.fiscal.entity.Servico;
import br.com.fiscal.entity.Tomador;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotaFiscalDTO {
	
	private Long id;
	
	@NotBlank
	private String numero;
	
	@NotNull
	private String data;
	
	@NotNull
	private Double valorTotal;
	
	@NotNull
	private Tomador tomador;
	
	@NotNull
	private Prestador prestador;
	
	private List<Servico> servicos;
	
}
