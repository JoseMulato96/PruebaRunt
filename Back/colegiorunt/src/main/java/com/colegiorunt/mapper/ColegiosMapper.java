package com.colegiorunt.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.colegiorunt.domain.Colegios;
import com.colegiorunt.dto.ColegiosDTO;

 
@Mapper
public interface ColegiosMapper {
	public ColegiosDTO colegiosToColegiosDTO(Colegios colegios);

	public Colegios colegiosDTOToColegios(ColegiosDTO colegiosDTO);

	public List<ColegiosDTO> listColegiosToListColegiosDTO(List<Colegios> colegioss);

	public List<Colegios> listColegiosDTOToListColegios(List<ColegiosDTO> colegiosDTOs);
}
