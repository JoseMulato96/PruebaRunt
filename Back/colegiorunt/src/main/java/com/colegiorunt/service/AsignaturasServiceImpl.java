package com.colegiorunt.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.colegiorunt.domain.Asignaturas;
import com.colegiorunt.domain.CursosHasAsignaturas;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.AsignaturasRepository;
import com.colegiorunt.utility.Utilities;

import lombok.extern.slf4j.Slf4j;


@Scope("singleton")
@Service
@Slf4j
public class AsignaturasServiceImpl implements AsignaturasService {

	@Autowired
	private AsignaturasRepository asignaturasRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Asignaturas asignaturas) throws ConstraintViolationException {

		Set<ConstraintViolation<Asignaturas>> constraintViolations = validator.validate(asignaturas);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return asignaturasRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Asignaturas> findAll() {
		log.debug("finding all Asignaturas instances");
		return asignaturasRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Asignaturas save(Asignaturas entity) throws Exception {
		log.debug("saving Asignaturas instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Asignaturas");
		}

		validate(entity);

		if (asignaturasRepository.existsById(entity.getIdAsignatura())) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return asignaturasRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Asignaturas entity) throws Exception {
		log.debug("deleting Asignaturas instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Asignaturas");
		}

		if (entity.getIdAsignatura() == null) {
			throw new ZMessManager().new EmptyFieldException("idAsignatura");
		}

		if (asignaturasRepository.existsById(entity.getIdAsignatura()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdAsignatura()).ifPresent(entidad -> {
			List<CursosHasAsignaturas> cursosHasAsignaturases = entidad.getCursosHasAsignaturases();
			if (Utilities.validationsList(cursosHasAsignaturases) == true) {
				throw new ZMessManager().new DeletingException("cursosHasAsignaturases");
			}
		});

		asignaturasRepository.deleteById(entity.getIdAsignatura());
		log.debug("delete Asignaturas successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws Exception {
		log.debug("deleting Asignaturas instance");
		if (id == null) {
			throw new ZMessManager().new EmptyFieldException("idAsignatura");
		}
		if (asignaturasRepository.existsById(id)) {
			delete(asignaturasRepository.findById(id).get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Asignaturas update(Asignaturas entity) throws Exception {

		log.debug("updating Asignaturas instance");

		if (entity == null) {
			throw new ZMessManager().new NullEntityExcepcion("Asignaturas");
		}

		validate(entity);

		if (asignaturasRepository.existsById(entity.getIdAsignatura()) == false) {
			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
		}

		return asignaturasRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Asignaturas> findById(Integer idAsignatura) {
		log.debug("getting Asignaturas instance");
		return asignaturasRepository.findById(idAsignatura);
	}

}
