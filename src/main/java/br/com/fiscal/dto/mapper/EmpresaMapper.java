package br.com.fiscal.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.fiscal.dto.request.EmpresaDTO;
import br.com.fiscal.entity.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
	
	EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);
	
	Empresa toModel(EmpresaDTO dto);
	
	EmpresaDTO toDTO(Empresa entity);
	
}
