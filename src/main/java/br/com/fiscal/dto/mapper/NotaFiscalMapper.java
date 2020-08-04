package br.com.fiscal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fiscal.dto.request.NotaFiscalDTO;
import br.com.fiscal.dto.request.PrestadorDTO;
import br.com.fiscal.dto.request.TomadorDTO;
import br.com.fiscal.entity.NotaFiscal;
import br.com.fiscal.entity.Prestador;
import br.com.fiscal.entity.Tomador;

@Mapper(componentModel = "spring")
public interface NotaFiscalMapper {
	
	NotaFiscalMapper INSTANCE = Mappers.getMapper(NotaFiscalMapper.class);
	
	NotaFiscal toModel(NotaFiscalDTO dto);
	
	NotaFiscalDTO toDTO(NotaFiscal entity);
	
	Tomador toModel(TomadorDTO dto);
	
	TomadorDTO toDTO(Tomador entity);
	
	Prestador toModel(PrestadorDTO dto);
	
	PrestadorDTO toDTO(Prestador entity);
	
}
