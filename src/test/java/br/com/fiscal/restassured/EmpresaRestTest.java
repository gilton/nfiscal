package br.com.fiscal.restassured;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.standaloneSetup;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import br.com.fiscal.dto.response.MensagemResponseDTO;
import br.com.fiscal.endpoints.EmpresaController;
import br.com.fiscal.entity.Empresa;
import br.com.fiscal.exception.EmpresaNotFoundException;
import br.com.fiscal.service.impl.EmpresaService;
import br.com.fiscal.service.impl.NotaFiscalService;
import io.restassured.http.ContentType;


@WebMvcTest
public class EmpresaRestTest {
	
	@Autowired
	private EmpresaController empresaController;
	
	@MockBean
	private EmpresaService empresaService;

	@MockBean
	private NotaFiscalService notaFiscalService;
	
	@BeforeEach
	public void setup() {
		standaloneSetup(this.empresaController);
		baseURI = "http://localhost:8080/api/v1/empresa/";
	}
	
	@Test
	public void deveRetornarSucesso_QuandoInserirEmpresa() {
		String body= "{\"fantasia\": \"Murai\", \"razaoSocial\": \"Autobrand Comercio de Veiculos LTDA\", \"cnpj\": \"92.217.304/0001-23\", \"tipo\": \"Prestador\"}";
		
		Empresa empresaCriada = new Empresa(6L, "92.217.304/0001-23","Murai","Autobrand Comercio de Veiculos LTDA", "Prestador");
		
		when(this.empresaService.insert(empresaCriada))
		.thenReturn( MensagemResponseDTO.builder()
				.mensagem("Empresa de ID "+6L+", adicionado com sucesso!")
				.build() );
		
		given().body(body).contentType("application/json")
		.when().post("add")
		.then().statusCode(HttpStatus.CREATED.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoAlterarEmpresa() {

		final long id = 1L;
		String body= "{\"fantasia\": \"Murai Thai\", \"razaoSocial\": \"Comercio de Veiculos LTDA\", \"cnpj\": \"92.217.304/0001-23\", \"tipo\": \"Tomador\"}";
		
		Empresa empresaAlterada = new Empresa( 	id, 
												"92.217.304/0001-23",
												"Murai Thai",
												"Comercio de Veiculos LTDA", "Tomador");
		this.empresaService.alterar(id, empresaAlterada);
		
		when(this.empresaService.alterar(id, empresaAlterada))
		.thenReturn( MensagemResponseDTO.builder()
				.mensagem("Empresa de ID "+id+", alterado com sucesso!")
				.build() );
		
		given().body(body).contentType("application/json")
		.when().put("update/{codigo}", id)
		.then().statusCode(HttpStatus.OK.value());
		
	}
	
	@Test
	public void deveRetornarSucesso_QuandoBuscarEmpresa() throws EmpresaNotFoundException {
		Empresa empresaCriada = new Empresa(1L, "70.282.646/0001-94","POSTO LOPES","POSTO LOPES SEL", "Tomador");
		Optional<Empresa> empresaEncontrada = this.empresaService.findById(1L);
		
		when(empresaEncontrada)
		.thenReturn(Optional.of(empresaCriada));
	
		given()
			.accept(ContentType.JSON)
		.when()
			.get("find/{codigo}", 1L)
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarNaoEncontrado_QuandoBuscarEmpresaPorIdInexistente() {
		final long id = 100L;
	
		when(this.empresaService.findById(id).orElse(null))
		.thenReturn(null);
		
		given()
			.accept(ContentType.JSON) 
		.when()
			.get("find/{codigo}", id)
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	@Test
	public void deveRetornarBadRequest_QuandoBuscarEmpresaPorIdNegativo() {
		final long id = -1L;
		
		given()
			.accept(ContentType.JSON) 
		.when()
			.get("find/{codigo}", id)
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
		
		verify(this.empresaService, never()).findById(id);
		
	}
	
}
