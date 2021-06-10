package com.colegiorunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiorunt.domain.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Integer> {
}
