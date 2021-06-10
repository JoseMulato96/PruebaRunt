package com.colegiorunt.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "colegios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Colegios implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_colegio", unique = true, nullable = false)
	@NotNull
	private Integer idColegio;

	@NotNull
	@NotEmpty
	@Size(max = 45)
	@Column(name = "nombre", nullable = false)
	private String nombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "colegios")
	private List<Cursos> cursoses = new ArrayList<>();

}