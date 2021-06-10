package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.AsignaturasDTO;

import com.colegiorunt.mapper.AsignaturasMapper;

import com.colegiorunt.service.AsignaturasService;

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
@RequestMapping("/api/v1/asignaturas")
@CrossOrigin(origins = "*")
@Slf4j
public class AsignaturasRestController {
	@Autowired
	private AsignaturasService asignaturasService;
	@Autowired
	private AsignaturasMapper asignaturasMapper;

	@GetMapping(value = "/{idAsignatura}")
	public ResponseEntity<?> findById(@PathVariable("idAsignatura") Integer idAsignatura) throws Exception {
		log.debug("Request to findById() Asignaturas");

		Asignaturas asignaturas = (asignaturasService.findById(idAsignatura).isPresent() == true)
				? asignaturasService.findById(idAsignatura).get()
				: null;

		return ResponseEntity.ok().body(asignaturasMapper.asignaturasToAsignaturasDTO(asignaturas));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Asignaturas");

		return ResponseEntity.ok()
				.body(asignaturasMapper.listAsignaturasToListAsignaturasDTO(asignaturasService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody AsignaturasDTO asignaturasDTO) throws Exception {
		log.debug("Request to save Asignaturas: {}", asignaturasDTO);

		Asignaturas asignaturas = asignaturasMapper.asignaturasDTOToAsignaturas(asignaturasDTO);
		asignaturas = asignaturasService.save(asignaturas);

		return ResponseEntity.ok().body(asignaturasMapper.asignaturasToAsignaturasDTO(asignaturas));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody AsignaturasDTO asignaturasDTO) throws Exception {
		log.debug("Request to update Asignaturas: {}", asignaturasDTO);

		Asignaturas asignaturas = asignaturasMapper.asignaturasDTOToAsignaturas(asignaturasDTO);
		asignaturas = asignaturasService.update(asignaturas);

		return ResponseEntity.ok().body(asignaturasMapper.asignaturasToAsignaturasDTO(asignaturas));
	}

	@DeleteMapping(value = "/{idAsignatura}")
	public ResponseEntity<?> delete(@PathVariable("idAsignatura") Integer idAsignatura) throws Exception {
		log.debug("Request to delete Asignaturas");

		asignaturasService.deleteById(idAsignatura);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(asignaturasService.count());
	}
}
