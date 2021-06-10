package com.colegiorunt.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "cursos_has_asignaturas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursosHasAsignaturas implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "idCurso", column = @Column(name = "id_curso", nullable = false)),
			@AttributeOverride(name = "idAsignatura", column = @Column(name = "id_asignatura", nullable = false)) })
	@NotNull
	private CursosHasAsignaturasId id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_asignatura", insertable = false, updatable = false)
	@NotNull
	private Asignaturas asignaturas;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_curso", insertable = false, updatable = false)
	@NotNull
	private Cursos cursos;
}
