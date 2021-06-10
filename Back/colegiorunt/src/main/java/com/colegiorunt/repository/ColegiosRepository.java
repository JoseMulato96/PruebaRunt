package com.colegiorunt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.colegiorunt.domain.Colegios;

public interface ColegiosRepository extends JpaRepository<Colegios, Integer> {
}
