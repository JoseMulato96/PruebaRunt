package com.colegiorunt.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.colegiorunt.domain.Profesores;
import com.colegiorunt.dto.ProfesoresDTO;

@Mapper
public interface ProfesoresMapper {
	public ProfesoresDTO profesoresToProfesoresDTO(Profesores profesores);

	public Profesores profesoresDTOToProfesores(ProfesoresDTO profesoresDTO);

	public List<ProfesoresDTO> listProfesoresToListProfesoresDTO(List<Profesores> profesoress);

	public List<Profesores> listProfesoresDTOToListProfesores(List<ProfesoresDTO> profesoresDTOs);
}
