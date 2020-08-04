package br.com.fiscal.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String fantasia;
	
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(nullable = false, unique = true)
	private String cnpj;
	
	@Column
	private String tipo;
	
	//Construtor criado para o test Rest API método deveRetornarSucesso_QuandoInserirEmpresa
	public Empresa(String fantasia, String razaoSocial, String cnpj, String tipo) {
		this.fantasia = fantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.tipo = tipo;
	}
}
