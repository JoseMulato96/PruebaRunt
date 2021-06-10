package com.colegiorunt.mapper;

import com.colegiorunt.domain.Asignaturas;

import com.colegiorunt.dto.AsignaturasDTO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;


@Mapper
public interface AsignaturasMapper {
	@Mapping(source = "profesores.idProfesor", target = "idProfesor_Profesores")
	public AsignaturasDTO asignaturasToAsignaturasDTO(Asignaturas asignaturas);

	@Mapping(source = "idProfesor_Profesores", target = "profesores.idProfesor")
	public Asignaturas asignaturasDTOToAsignaturas(AsignaturasDTO asignaturasDTO);

	public List<AsignaturasDTO> listAsignaturasToListAsignaturasDTO(List<Asignaturas> asignaturass);

	public List<Asignaturas> listAsignaturasDTOToListAsignaturas(List<AsignaturasDTO> asignaturasDTOs);
}
