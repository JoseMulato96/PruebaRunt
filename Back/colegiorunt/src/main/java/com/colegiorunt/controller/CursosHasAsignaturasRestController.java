package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.CursosHasAsignaturasDTO;

import com.colegiorunt.mapper.CursosHasAsignaturasMapper;

import com.colegiorunt.service.CursosHasAsignaturasService;

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
@RequestMapping("/api/v1/cursosHasAsignaturas")
@CrossOrigin(origins = "*")
@Slf4j
public class CursosHasAsignaturasRestController {
	@Autowired
	private CursosHasAsignaturasService cursosHasAsignaturasService;
	@Autowired
	private CursosHasAsignaturasMapper cursosHasAsignaturasMapper;

	@GetMapping(value = "//{idCurso}/{idAsignatura}")
	public ResponseEntity<?> findById(@PathVariable("idCurso") Integer idCurso,
			@PathVariable("idAsignatura") Integer idAsignatura) throws Exception {
		log.debug("Request to findById() CursosHasAsignaturas");

		CursosHasAsignaturasId id = new CursosHasAsignaturasId();

		id.setIdCurso(idCurso);
		id.setIdAsignatura(idAsignatura);

		CursosHasAsignaturas cursosHasAsignaturas = (cursosHasAsignaturasService.findById(id).isPresent() == true)
				? cursosHasAsignaturasService.findById(id).get()
				: null;

		return ResponseEntity.ok()
				.body(cursosHasAsignaturasMapper.cursosHasAsignaturasToCursosHasAsignaturasDTO(cursosHasAsignaturas));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() CursosHasAsignaturas");

		return ResponseEntity.ok().body(cursosHasAsignaturasMapper
				.listCursosHasAsignaturasToListCursosHasAsignaturasDTO(cursosHasAsignaturasService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody CursosHasAsignaturasDTO cursosHasAsignaturasDTO)
			throws Exception {
		log.debug("Request to save CursosHasAsignaturas: {}", cursosHasAsignaturasDTO);

		CursosHasAsignaturas cursosHasAsignaturas = cursosHasAsignaturasMapper
				.cursosHasAsignaturasDTOToCursosHasAsignaturas(cursosHasAsignaturasDTO);
		cursosHasAsignaturas = cursosHasAsignaturasService.save(cursosHasAsignaturas);

		return ResponseEntity.ok()
				.body(cursosHasAsignaturasMapper.cursosHasAsignaturasToCursosHasAsignaturasDTO(cursosHasAsignaturas));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody CursosHasAsignaturasDTO cursosHasAsignaturasDTO)
			throws Exception {
		log.debug("Request to update CursosHasAsignaturas: {}", cursosHasAsignaturasDTO);

		CursosHasAsignaturas cursosHasAsignaturas = cursosHasAsignaturasMapper
				.cursosHasAsignaturasDTOToCursosHasAsignaturas(cursosHasAsignaturasDTO);
		cursosHasAsignaturas = cursosHasAsignaturasService.update(cursosHasAsignaturas);

		return ResponseEntity.ok()
				.body(cursosHasAsignaturasMapper.cursosHasAsignaturasToCursosHasAsignaturasDTO(cursosHasAsignaturas));
	}

	@DeleteMapping(value = "//{idCurso}/{idAsignatura}")
	public ResponseEntity<?> delete(@PathVariable("idCurso") Integer idCurso,
			@PathVariable("idAsignatura") Integer idAsignatura) throws Exception {
		log.debug("Request to delete CursosHasAsignaturas");

		CursosHasAsignaturasId id = new CursosHasAsignaturasId();

		id.setIdCurso(idCurso);
		id.setIdAsignatura(idAsignatura);

		cursosHasAsignaturasService.deleteById(id);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(cursosHasAsignaturasService.count());
	}
}
