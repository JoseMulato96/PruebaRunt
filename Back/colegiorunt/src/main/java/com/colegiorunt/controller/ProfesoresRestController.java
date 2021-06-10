package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.ProfesoresDTO;

import com.colegiorunt.mapper.ProfesoresMapper;

import com.colegiorunt.service.ProfesoresService;

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
@RequestMapping("/api/v1/profesores")
@CrossOrigin(origins = "*")
@Slf4j
public class ProfesoresRestController {
	@Autowired
	private ProfesoresService profesoresService;
	@Autowired
	private ProfesoresMapper profesoresMapper;

	@GetMapping(value = "/{idProfesor}")
	public ResponseEntity<?> findById(@PathVariable("idProfesor") Integer idProfesor) throws Exception {
		log.debug("Request to findById() Profesores");

		Profesores profesores = (profesoresService.findById(idProfesor).isPresent() == true)
				? profesoresService.findById(idProfesor).get()
				: null;

		return ResponseEntity.ok().body(profesoresMapper.profesoresToProfesoresDTO(profesores));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Profesores");

		return ResponseEntity.ok()
				.body(profesoresMapper.listProfesoresToListProfesoresDTO(profesoresService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ProfesoresDTO profesoresDTO) throws Exception {
		log.debug("Request to save Profesores: {}", profesoresDTO);

		Profesores profesores = profesoresMapper.profesoresDTOToProfesores(profesoresDTO);
		profesores = profesoresService.save(profesores);

		return ResponseEntity.ok().body(profesoresMapper.profesoresToProfesoresDTO(profesores));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ProfesoresDTO profesoresDTO) throws Exception {
		log.debug("Request to update Profesores: {}", profesoresDTO);

		Profesores profesores = profesoresMapper.profesoresDTOToProfesores(profesoresDTO);
		profesores = profesoresService.update(profesores);

		return ResponseEntity.ok().body(profesoresMapper.profesoresToProfesoresDTO(profesores));
	}

	@DeleteMapping(value = "/{idProfesor}")
	public ResponseEntity<?> delete(@PathVariable("idProfesor") Integer idProfesor) throws Exception {
		log.debug("Request to delete Profesores");

		profesoresService.deleteById(idProfesor);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(profesoresService.count());
	}
}
