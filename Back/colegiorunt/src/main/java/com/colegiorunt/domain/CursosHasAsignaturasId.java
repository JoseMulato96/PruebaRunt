package com.colegiorunt.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;


@Embeddable
public class CursosHasAsignaturasId implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@NotNull
	private Integer idCurso;
	@NotNull
	private Integer idAsignatura;

	public CursosHasAsignaturasId() {
	}

	@Column(name = "id_curso", nullable = false)
	public Integer getIdCurso() {
		return this.idCurso;
	}

	public void setIdCurso(Integer idCurso) {
		this.idCurso = idCurso;
	}

	@Column(name = "id_asignatura", nullable = false)
	public Integer getIdAsignatura() {
		return this.idAsignatura;
	}

	public void setIdAsignatura(Integer idAsignatura) {
		this.idAsignatura = idAsignatura;
	}

	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}

		if ((other == null)) {
			return false;
		}

		if (!(other instanceof CursosHasAsignaturasId)) {
			return false;
		}

		CursosHasAsignaturasId castOther = (CursosHasAsignaturasId) other;

		return ((this.getIdCurso() == castOther.getIdCurso()) || ((this.getIdCurso() != null)
				&& (castOther.getIdCurso() != null) && this.getIdCurso().equals(castOther.getIdCurso())))
				&& ((this.getIdAsignatura() == castOther.getIdAsignatura())
						|| ((this.getIdAsignatura() != null) && (castOther.getIdAsignatura() != null)
								&& this.getIdAsignatura().equals(castOther.getIdAsignatura())));
	}

	public int hashCode() {
		int result = 17;

		result = (37 * result) + ((getIdCurso() == null) ? 0 : this.getIdCurso().hashCode());
		result = (37 * result) + ((getIdAsignatura() == null) ? 0 : this.getIdAsignatura().hashCode());

		return result;
	}
}
