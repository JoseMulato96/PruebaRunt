package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.CursosDTO;

import com.colegiorunt.mapper.CursosMapper;

import com.colegiorunt.service.CursosService;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/cursos")
@CrossOrigin(origins = "*")
@Slf4j
public class CursosRestController {
	@Autowired
	private CursosService cursosService;
	@Autowired
	private CursosMapper cursosMapper;

	@GetMapping(value = "/{idCurso}")
	public ResponseEntity<?> findById(@PathVariable("idCurso") Integer idCurso) throws Exception {
		log.debug("Request to findById() Cursos");

		Cursos cursos = (cursosService.findById(idCurso).isPresent() == true) ? cursosService.findById(idCurso).get()
				: null;

		return ResponseEntity.ok().body(cursosMapper.cursosToCursosDTO(cursos));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Cursos");

		return ResponseEntity.ok().body(cursosMapper.listCursosToListCursosDTO(cursosService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody CursosDTO cursosDTO) throws Exception {
		log.debug("Request to save Cursos: {}", cursosDTO);

		Cursos cursos = cursosMapper.cursosDTOToCursos(cursosDTO);
		cursos = cursosService.save(cursos);

		return ResponseEntity.ok().body(cursosMapper.cursosToCursosDTO(cursos));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody CursosDTO cursosDTO) throws Exception {
		log.debug("Request to update Cursos: {}", cursosDTO);

		Cursos cursos = cursosMapper.cursosDTOToCursos(cursosDTO);
		cursos = cursosService.update(cursos);

		return ResponseEntity.ok().body(cursosMapper.cursosToCursosDTO(cursos));
	}

	@DeleteMapping(value = "/{idCurso}")
	public ResponseEntity<?> delete(@PathVariable("idCurso") Integer idCurso) throws Exception {
		log.debug("Request to delete Cursos");

		cursosService.deleteById(idCurso);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(cursosService.count());
	}
}
