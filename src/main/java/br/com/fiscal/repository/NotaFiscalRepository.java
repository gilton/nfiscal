package br.com.fiscal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiscal.entity.NotaFiscal;

@Repository
public interface NotaFiscalRepository extends JpaRepository<NotaFiscal, Long> {
	
	
	@Query(value = "SELECT * FROM NOTA_FISCAL AS n " + 
			"INNER JOIN TOMADOR AS t " + 
			"ON t.id = n.tomador_id " + 
			"INNER JOIN PRESTADOR AS p " + 
			"ON p.id = n.prestador_id " + 
			"INNER JOIN EMPRESA AS e " + 
			"ON t.empresa_id = e.id " + 
			"WHERE UPPER(e.fantasia) LIKE %:str% " + 
			"OR UPPER(e.razao_social) LIKE %:str%", nativeQuery = true)
	
	List<NotaFiscal> findyByEmpresaSTR(@Param("str") String str);
	
}
