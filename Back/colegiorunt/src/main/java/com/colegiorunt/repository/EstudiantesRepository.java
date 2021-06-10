package com.colegiorunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiorunt.domain.Estudiantes;

public interface EstudiantesRepository extends JpaRepository<Estudiantes, Integer> {
}
