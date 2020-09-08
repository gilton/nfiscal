package br.com.fiscal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
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
	
	@Mappings({
		 @Mapping(source = "tomador.id", target = "tomador.id"),
		 @Mapping(source = "prestador.id", target = "prestador.id")
	 })
	NotaFiscalDTO toDTO(NotaFiscal entity);
	NotaFiscal toModel(NotaFiscalDTO dto);
	
	TomadorDTO toDTO(Tomador entity);
	Tomador toModel(TomadorDTO dto);
	
	PrestadorDTO toDTO(Prestador entity);
	Prestador toModel(PrestadorDTO dto);
	
}
