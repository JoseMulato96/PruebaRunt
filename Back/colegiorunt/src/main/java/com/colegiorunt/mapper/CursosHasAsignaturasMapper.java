package com.colegiorunt.mapper;

import com.colegiorunt.domain.CursosHasAsignaturas;

import com.colegiorunt.dto.CursosHasAsignaturasDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CursosHasAsignaturasMapper {
	@Mapping(source = "asignaturas.idAsignatura", target = "idAsignatura_Asignaturas")
	@Mapping(source = "cursos.idCurso", target = "idCurso_Cursos")
	public CursosHasAsignaturasDTO cursosHasAsignaturasToCursosHasAsignaturasDTO(
			CursosHasAsignaturas cursosHasAsignaturas);

	@Mapping(source = "idAsignatura_Asignaturas", target = "asignaturas.idAsignatura")
	@Mapping(source = "idCurso_Cursos", target = "cursos.idCurso")
	public CursosHasAsignaturas cursosHasAsignaturasDTOToCursosHasAsignaturas(
			CursosHasAsignaturasDTO cursosHasAsignaturasDTO);

	public List<CursosHasAsignaturasDTO> listCursosHasAsignaturasToListCursosHasAsignaturasDTO(
			List<CursosHasAsignaturas> cursosHasAsignaturass);

	public List<CursosHasAsignaturas> listCursosHasAsignaturasDTOToListCursosHasAsignaturas(
			List<CursosHasAsignaturasDTO> cursosHasAsignaturasDTOs);
}
