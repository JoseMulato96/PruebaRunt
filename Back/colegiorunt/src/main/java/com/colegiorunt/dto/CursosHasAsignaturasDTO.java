package com.colegiorunt.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursosHasAsignaturasDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idCurso;
	private Integer idAsignatura;
	private Integer idAsignatura_Asignaturas;
	private Integer idCurso_Cursos;
}
