package com.colegiorunt.mapper;

import com.colegiorunt.domain.Estudiantes;

import com.colegiorunt.dto.EstudiantesDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface EstudiantesMapper {
	@Mapping(source = "cursos.idCurso", target = "idCurso_Cursos")
	public EstudiantesDTO estudiantesToEstudiantesDTO(Estudiantes estudiantes);

	@Mapping(source = "idCurso_Cursos", target = "cursos.idCurso")
	public Estudiantes estudiantesDTOToEstudiantes(EstudiantesDTO estudiantesDTO);

	public List<EstudiantesDTO> listEstudiantesToListEstudiantesDTO(List<Estudiantes> estudiantess);

	public List<Estudiantes> listEstudiantesDTOToListEstudiantes(List<EstudiantesDTO> estudiantesDTOs);
}
