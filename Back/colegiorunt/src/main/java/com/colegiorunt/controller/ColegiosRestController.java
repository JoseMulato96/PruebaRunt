package com.colegiorunt.controller;

import com.colegiorunt.domain.*;

import com.colegiorunt.dto.ColegiosDTO;

import com.colegiorunt.mapper.ColegiosMapper;

import com.colegiorunt.service.ColegiosService;

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
@RequestMapping("/api/v1/colegios")
@CrossOrigin(origins = "*")
@Slf4j
public class ColegiosRestController {
	@Autowired
	private ColegiosService colegiosService;
	@Autowired
	private ColegiosMapper colegiosMapper;

	@GetMapping(value = "/{idColegio}")
	public ResponseEntity<?> findById(@PathVariable("idColegio") Integer idColegio) throws Exception {
		log.debug("Request to findById() Colegios");

		Colegios colegios = (colegiosService.findById(idColegio).isPresent() == true)
				? colegiosService.findById(idColegio).get()
				: null;

		return ResponseEntity.ok().body(colegiosMapper.colegiosToColegiosDTO(colegios));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws Exception {
		log.debug("Request to findAll() Colegios");

		return ResponseEntity.ok().body(colegiosMapper.listColegiosToListColegiosDTO(colegiosService.findAll()));
	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ColegiosDTO colegiosDTO) throws Exception {
		log.debug("Request to save Colegios: {}", colegiosDTO);

		Colegios colegios = colegiosMapper.colegiosDTOToColegios(colegiosDTO);
		colegios = colegiosService.save(colegios);

		return ResponseEntity.ok().body(colegiosMapper.colegiosToColegiosDTO(colegios));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ColegiosDTO colegiosDTO) throws Exception {
		log.debug("Request to update Colegios: {}", colegiosDTO);

		Colegios colegios = colegiosMapper.colegiosDTOToColegios(colegiosDTO);
		colegios = colegiosService.update(colegios);

		return ResponseEntity.ok().body(colegiosMapper.colegiosToColegiosDTO(colegios));
	}

	@DeleteMapping(value = "/{idColegio}")
	public ResponseEntity<?> delete(@PathVariable("idColegio") Integer idColegio) throws Exception {
		log.debug("Request to delete Colegios");

		colegiosService.deleteById(idColegio);

		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(colegiosService.count());
	}
}
