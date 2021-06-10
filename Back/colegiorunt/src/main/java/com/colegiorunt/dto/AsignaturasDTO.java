package com.colegiorunt.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AsignaturasDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer idAsignatura;
	@NotNull
	@NotEmpty
	@Size(max = 45)
	private String nombre;
	private Integer idProfesor_Profesores;
}
