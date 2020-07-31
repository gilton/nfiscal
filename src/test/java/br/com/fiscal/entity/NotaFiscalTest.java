package br.com.fiscal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NotaFiscalTest {

	private NotaFiscal nf;
	private Double valorTotal1 = 1455.56;
	private Tomador tomador = new Tomador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
			new Empresa(2L, "POSTO LOPES","POSTO LOPES SEL","70.282.646/0001-94", "Tomador"),null);
	
	private Prestador prestador = new Prestador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
			new Empresa(1L,"Artefatos de Borracha Inovatex",
					" Industria de Artefatos de Borracha Inovatex LTDA",
					"48.265.133/0001-16", "Prestador"), null);

	@BeforeEach
	public void cenario() {
		
		nf = new NotaFiscal(1L,"numero cod",LocalDateTime.now(),1455.56, tomador, prestador, null);
	}
	
	@AfterEach
	public void limpeza() { nf = null; }
	
	@Test
	public void notaFiscalPossuiTomador() {
		assertEquals(Tomador.class, nf.getTomador().getClass());
	}
	
	@Test
	public void notaFiscalPossuiPrestador() {
		assertEquals(Prestador.class, nf.getPrestador().getClass());
	}
	
	@Test
	public void tomadorNaoEhPrestador() {
		assertEquals(false, nf.getTomador().equals(nf.getPrestador()));
	}
	
}
