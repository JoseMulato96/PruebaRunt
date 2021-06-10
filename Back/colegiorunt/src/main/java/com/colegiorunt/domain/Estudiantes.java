package com.colegiorunt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "estudiantes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estudiantes implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id_estudiante", unique = true, nullable = false)
	@NotNull
	private Integer idEstudiante;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso")
	@NotNull
	private Cursos cursos;
	@NotNull
	@NotEmpty
	@Size(max = 45)
	@Column(name = "nombre", nullable = false)
	private String nombre;
}
