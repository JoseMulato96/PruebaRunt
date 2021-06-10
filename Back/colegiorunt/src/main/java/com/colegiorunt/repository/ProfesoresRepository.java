package com.colegiorunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiorunt.domain.Profesores;

public interface ProfesoresRepository extends JpaRepository<Profesores, Integer> {
}
