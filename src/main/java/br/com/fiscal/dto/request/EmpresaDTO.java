package br.com.fiscal.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CNPJ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
	
	private Long id;
	
	@NotBlank
	@Size(max = 200)
	private String fantasia;
	
	@NotBlank
	private String razaoSocial;
	
	@NotBlank
	@CNPJ
	@Size(min = 18, max = 18, message = "CNPJ contém 18 caracteres.")
	private String cnpj;
	
	private String tipo;

}