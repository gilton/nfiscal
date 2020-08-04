package br.com.fiscal.restassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.fiscal.dto.mapper.NotaFiscalMapper;
import br.com.fiscal.dto.request.NotaFiscalDTO;
import br.com.fiscal.dto.request.PrestadorDTO;
import br.com.fiscal.dto.request.TomadorDTO;
import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.endpoints.NotaFiscalController;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.entity.NotaFiscal;
import br.com.fiscal.entity.Prestador;
import br.com.fiscal.entity.Tomador;
import br.com.fiscal.exception.NotaFiscalNotFoundException;
import br.com.fiscal.service.impl.EmpresaService;
import br.com.fiscal.service.impl.NotaFiscalService;
import io.restassured.http.ContentType;


@WebMvcTest
public class NotaFiscalRestTest {
	
	@Autowired
	private NotaFiscalController nfController;
	
	@MockBean
	private EmpresaService empresaService;

	@MockBean
	private NotaFiscalService nfService;
	
	@MockBean
	private NotaFiscalMapper notafiscalMapper;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.nfController);
		baseURI = "http://localhost:8080/api/v1/notafiscal/";
	}
	
	@Test
	public void deveRetornarSucesso_QuandoInserirNotaFiscal() {
		String body= "{\"numero\": \"A-113x\",\"data\": \"2020-07-30 17:40\",\"valorTotal\": 1554.56,\n" + 
				"\"tomador\":{\"valorTotal\": 1554.56,"
					+ "\"empresa\": {\"fantasia\": \"POSTO LOPES\",\"razaoSocial\": \"POSTO LOPES SEL\",\"cnpj\": \"70.282.646/0001-94\",\"tipo\":  \"Tomador\"}},\n" + 
				"\"prestador\": {\"valorTotal\": 1554.56,"
					+ "\"empresa\": {\"fantasia\": \"Artefatos de  Borracharia Inovatex\",\"razaoSocial\": \"Industria de Artefatos de Borracha Inovatex LTDA\",\"cnpj\": \"48.265.133/0001-16\",\"tipo\":  \"Prestador\"}}}";
		
		Double valorTotal1 = 1455.56;
		Tomador tomador = new Tomador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
				new Empresa(2L, "POSTO LOPES","POSTO LOPES SEL","70.282.646/0001-94", "Tomador"),null);
		
		TomadorDTO tomadorDTO = notafiscalMapper.toDTO(tomador);
		
		Prestador prestador = new Prestador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
				new Empresa(1L,"Artefatos de Borracha Inovatex",
						" Industria de Artefatos de Borracha Inovatex LTDA",
						"48.265.133/0001-16", "Prestador"), null);
		
		PrestadorDTO prestadorDTO = notafiscalMapper.toDTO(prestador);
		
		
		NotaFiscal nfCriada = new NotaFiscal("2133-9AFE12",LocalDateTime.now(),1455.56, tomador, prestador, null);
		NotaFiscalDTO nfiscalDto = new NotaFiscalDTO(tomadorDTO, prestadorDTO);
		nfiscalDto = notafiscalMapper.toDTO(nfCriada);
		
		
		when(this.nfService.insert(nfiscalDto))
		.thenReturn( MensagemResponseDTO.builder()
				.mensagem("Empresa de ID "+nfCriada.getId()+", adicionado com sucesso!")
				.build() );
		
		given().body(body).contentType("application/json")
		.when().post("add")
		.then().statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarNotaFiscal() throws NotaFiscalNotFoundException {
		
		Double valorTotal1 = 1455.56;
		Tomador tomador = new Tomador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
				new Empresa(2L, "POSTO LOPES","POSTO LOPES SEL","70.282.646/0001-94", "Tomador"),null);
		
		Prestador prestador = new Prestador(1L, valorTotal1, LocalDateTime.now(), LocalDateTime.now(),
				new Empresa(1L,"Artefatos de Borracha Inovatex",
						" Industria de Artefatos de Borracha Inovatex LTDA",
						"48.265.133/0001-16", "Prestador"), null);
		
		NotaFiscal nfCriada = new NotaFiscal(11L,"numero cod",LocalDateTime.now(),1455.56, tomador, prestador, null);
		
		NotaFiscalDTO nfEncontradoDTO = this.nfService.findById(1L);
		NotaFiscalDTO nfCriadaDTO = notafiscalMapper.toDTO(nfCriada);
		
		when( nfEncontradoDTO )
		.thenReturn( nfCriadaDTO );
	
		given()
			.accept(ContentType.JSON)
		.when()
			.get("find/{codigo}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarNotaFiscalPorIdInexistente() {
		final long id = 100L;
		
		NotaFiscalDTO dto = nfService.findById(id);
		
		when(dto)
		.thenReturn(null);
		
		given()
			.accept(ContentType.JSON) 
		.when()
			.get("find/{codigo}", id)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoBuscarNotaFiscalPorIdNegativo() {
		final long id = -1L;
		
		given()
			.accept(ContentType.JSON) 
		.when()
			.get("find/{codigo}", id)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.nfService, never()).findById(id);
		
	}
	
}
