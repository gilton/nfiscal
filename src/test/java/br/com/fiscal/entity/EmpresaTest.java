package br.com.fiscal.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmpresaTest {
	
	
	private Empresa empresa1;
//	private Empresa<Tomador> empresa2;
	private final int TAMANHO_DO_CNPJ = 18;
	
	@BeforeEach
	public void cenario() {
		
//		final double valorTotal2 = 1570.5;
		
		empresa1 = new Empresa (
				1L,"Artefatos de Borracha Inovatex",
				"Industria de Artefatos de Borracha Inovatex LTDA",
				"48.265.133/0001-16",
				"");
		
//		empresa2 = new Empresa<Tomador>(
//				2L,"POSTO LOPES","POSTO LOPES SEL","70.282.646/0001-94",
//				new Tomador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(), null));
//		
//		Empresa<Prestador> empresa3 = new Empresa<Prestador>(
//				3L,"Paloschi Fotos & Comunicacoes","Eduardo Pascoal Paloschi","29.214.623/0001-49",
//				new Prestador(2L, valorTotal2, LocalDateTime.now(), LocalDateTime.now(), null));
//		
//		Empresa<Tomador> empresa4 = new Empresa<Tomador>(
//				4L,"Mgt Promocoes e Eventos","Mgt Promocoes e Eventos LTDA","43.221.138/0001-23",
//				new Tomador(2L, valorTotal2, LocalDateTime.now(), LocalDateTime.now(), null));
	}
	
	@AfterEach
	public void limpeza() {
		empresa1 = null;
	}
	
	
	@Test
	public void validaCnpjIsNotNull() {
		assertFalse(empresa1.getCnpj().isEmpty());
	}
	
	@Test
	public void validaTamanhoDeCaracteresDoCnpj() {
		assertEquals(TAMANHO_DO_CNPJ, empresa1.getCnpj().length());
	}
	
	@Test
	public void validaRazaoSocialIsNotNull() {
		assertFalse(empresa1.getRazaoSocial().isEmpty());
	}
	
	
}
