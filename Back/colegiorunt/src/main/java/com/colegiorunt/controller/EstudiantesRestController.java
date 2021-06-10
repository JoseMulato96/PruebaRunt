package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.EstudiantesDTO;

import com.colegiorunt.mapper.EstudiantesMapper;

import com.colegiorunt.service.EstudiantesService;

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
@RequestMapping("/api/v1/estudiantes")
@CrossOrigin(origins = "*")
@Slf4j
public class EstudiantesRestController {
	@Autowired
	private EstudiantesService estudiantesService;
	@Autowired
	private EstudiantesMapper estudiantesMapper;

	@GetMapping(value = "/{idEstudiante}")
	public ResponseEntity<?> findById(@PathVariable("idEstudiante") Integer idEstudiante) throws Exception {
		log.debug("Request to findById() Estudiantes");

		Estudiantes estudiantes = (estudiantesService.findById(idEstudiante).isPresent() == true)
				? estudiantesService.findById(idEstudiante).get()
				: null;

		return ResponseEntity.ok().body(estudiantesMapper.estudiantesToEstudiantesDTO(estudiantes));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Estudiantes");

		return ResponseEntity.ok()
				.body(estudiantesMapper.listEstudiantesToListEstudiantesDTO(estudiantesService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody EstudiantesDTO estudiantesDTO) throws Exception {
		log.debug("Request to save Estudiantes: {}", estudiantesDTO);

		Estudiantes estudiantes = estudiantesMapper.estudiantesDTOToEstudiantes(estudiantesDTO);
		estudiantes = estudiantesService.save(estudiantes);

		return ResponseEntity.ok().body(estudiantesMapper.estudiantesToEstudiantesDTO(estudiantes));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody EstudiantesDTO estudiantesDTO) throws Exception {
		log.debug("Request to update Estudiantes: {}", estudiantesDTO);

		Estudiantes estudiantes = estudiantesMapper.estudiantesDTOToEstudiantes(estudiantesDTO);
		estudiantes = estudiantesService.update(estudiantes);

		return ResponseEntity.ok().body(estudiantesMapper.estudiantesToEstudiantesDTO(estudiantes));
	}

	@DeleteMapping(value = "/{idEstudiante}")
	public ResponseEntity<?> delete(@PathVariable("idEstudiante") Integer idEstudiante) throws Exception {
		log.debug("Request to delete Estudiantes");

		estudiantesService.deleteById(idEstudiante);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(estudiantesService.count());
	}
}
