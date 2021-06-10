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

import com.colegiorunt.domain.Estudiantes;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.EstudiantesRepository;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class EstudiantesServiceImpl implements EstudiantesService {
    @Autowired
    private EstudiantesRepository estudiantesRepository;
    @Autowired
    private Validator validator;

    @Override
    public void validate(Estudiantes estudiantes)
        throws ConstraintViolationException {
        Set<ConstraintViolation<Estudiantes>> constraintViolations = validator.validate(estudiantes);

        if (!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return estudiantesRepository.count();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Estudiantes> findAll() {
        log.debug("finding all Estudiantes instances");

        return estudiantesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Estudiantes save(Estudiantes entity) throws Exception {
        log.debug("saving Estudiantes instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Estudiantes");
        }

        validate(entity);

        if (estudiantesRepository.existsById(entity.getIdEstudiante())) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return estudiantesRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void delete(Estudiantes entity) throws Exception {
        log.debug("deleting Estudiantes instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Estudiantes");
        }

        if (entity.getIdEstudiante() == null) {
            throw new ZMessManager().new EmptyFieldException("idEstudiante");
        }

        if (estudiantesRepository.existsById(entity.getIdEstudiante()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        estudiantesRepository.deleteById(entity.getIdEstudiante());
        log.debug("delete Estudiantes successful");
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void deleteById(Integer id) throws Exception {
        log.debug("deleting Estudiantes instance");

        if (id == null) {
            throw new ZMessManager().new EmptyFieldException("idEstudiante");
        }

        if (estudiantesRepository.existsById(id)) {
            delete(estudiantesRepository.findById(id).get());
        }
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Estudiantes update(Estudiantes entity) throws Exception {
        log.debug("updating Estudiantes instance");

        if (entity == null) {
            throw new ZMessManager().new NullEntityExcepcion("Estudiantes");
        }

        validate(entity);

        if (estudiantesRepository.existsById(entity.getIdEstudiante()) == false) {
            throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }

        return estudiantesRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Estudiantes> findById(Integer idEstudiante) {
        log.debug("getting Estudiantes instance");

        return estudiantesRepository.findById(idEstudiante);
    }
}
