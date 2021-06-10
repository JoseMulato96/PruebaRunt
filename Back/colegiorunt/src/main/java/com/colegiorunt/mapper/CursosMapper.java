package com.colegiorunt.mapper;

import com.colegiorunt.domain.Cursos;

import com.colegiorunt.dto.CursosDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CursosMapper {
	@Mapping(source = "colegios.idColegio", target = "idColegio_Colegios")
	public CursosDTO cursosToCursosDTO(Cursos cursos);

	@Mapping(source = "idColegio_Colegios", target = "colegios.idColegio")
	public Cursos cursosDTOToCursos(CursosDTO cursosDTO);

	public List<CursosDTO> listCursosToListCursosDTO(List<Cursos> cursoss);

	public List<Cursos> listCursosDTOToListCursos(List<CursosDTO> cursosDTOs);
}
