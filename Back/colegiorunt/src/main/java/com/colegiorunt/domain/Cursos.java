package com.colegiorunt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cursos")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cursos implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_curso", unique = true, nullable = false)
	@NotNull
	private Integer idCurso;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_colegio")
	@NotNull
	private Colegios colegios;

	@NotNull
	@Column(name = "grado", nullable = false)
	private Integer grado;
	@NotNull
	@NotEmpty
	@Size(max = 45)
	@Column(name = "salon", nullable = false)
	private String salon;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cursos")
	private List<CursosHasAsignaturas> cursosHasAsignaturases = new ArrayList<>();
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cursos")
	private List<Estudiantes> estudianteses = new ArrayList<>();

}