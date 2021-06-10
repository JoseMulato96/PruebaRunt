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

import com.colegiorunt.domain.CursosHasAsignaturas;
import com.colegiorunt.domain.CursosHasAsignaturasId;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.CursosHasAsignaturasRepository;

import lombok.extern.slf4j.Slf4j;


@Scope("singleton")
@Service
@Slf4j
public class CursosHasAsignaturasServiceImpl
    implements CursosHasAsignaturasService {
    @Autowired
    private CursosHasAsignaturasRepository cursosHasAsignaturasRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(CursosHasAsignaturas cursosHasAsignaturas)
        throws ConstraintViolationException {
        Set<ConstraintViolation<CursosHasAsignaturas>> constraintViolations = validator.validate(cursosHasAsignaturas);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return cursosHasAsignaturasRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<CursosHasAsignaturas> findAll() {
        log.debug("finding all CursosHasAsignaturas instances");

        return cursosHasAsignaturasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CursosHasAsignaturas save(CursosHasAsignaturas entity)
        throws Exception {
        log.debug("saving CursosHasAsignaturas instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "CursosHasAsignaturas");
        }

        validate(entity);

        if (cursosHasAsignaturasRepository.existsById(entity.getId())) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return cursosHasAsignaturasRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(CursosHasAsignaturas entity) throws Exception {
        log.debug("deleting CursosHasAsignaturas instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "CursosHasAsignaturas");
        }

        if (entity.getId().getIdCurso() == null) {
            throw new ZMessManager().new EmptyFieldException("idCurso");
        }

        if (entity.getId().getIdAsignatura() == null) {
            throw new ZMessManager().new EmptyFieldException("idAsignatura");
        }

        if (cursosHasAsignaturasRepository.existsById(entity.getId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        cursosHasAsignaturasRepository.deleteById(entity.getId());
        log.debug("delete CursosHasAsignaturas successful");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(CursosHasAsignaturasId id) throws Exception {
        log.debug("deleting CursosHasAsignaturas instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("id");
        }

        if (cursosHasAsignaturasRepository.existsById(id)) {
            delete(cursosHasAsignaturasRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public CursosHasAsignaturas update(CursosHasAsignaturas entity)
        throws Exception {
        log.debug("updating CursosHasAsignaturas instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion(
                "CursosHasAsignaturas");
        }

        validate(entity);

        if (cursosHasAsignaturasRepository.existsById(entity.getId()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return cursosHasAsignaturasRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CursosHasAsignaturas> findById(CursosHasAsignaturasId id) {
        log.debug("getting CursosHasAsignaturas instance");

        return cursosHasAsignaturasRepository.findById(id);
    }
}
