package com.colegiorunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiorunt.domain.CursosHasAsignaturas;
import com.colegiorunt.domain.CursosHasAsignaturasId;

public interface CursosHasAsignaturasRepository extends JpaRepository<CursosHasAsignaturas, CursosHasAsignaturasId> {
}
