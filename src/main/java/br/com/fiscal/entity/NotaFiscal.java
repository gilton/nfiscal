package br.com.fiscal.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class NotaFiscal {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String numero;
	
	@Column(nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    @CreatedDate
	private LocalDateTime data;
	
	@Column(name = "valor_total", nullable = false)
	private Double valorTotal;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, optional = false)
	@JoinColumn(name = "tomador_id")
	private Tomador tomador;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, optional = false)
	@JoinColumn(name = "prestador_id")
	private Prestador prestador;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, targetEntity = Servico.class)
	private List<Servico> servicos;
	
	//Construtor criado para o test Rest API método deveRetornarSucesso_QuandoInserirEmpresa
	public NotaFiscal (String numero, LocalDateTime data, Double valorTotal, Tomador tomador, Prestador prestador, List<Servico> servicos) {
		this.numero = numero;
		this.data = data;
		this.valorTotal = valorTotal;
		this.tomador = tomador;
		this.prestador = prestador;
		this.servicos = servicos;
	}
	
}
