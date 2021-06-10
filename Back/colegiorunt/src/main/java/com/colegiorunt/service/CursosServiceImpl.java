package  com.colegiorunt.service;


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

import com.colegiorunt.domain.Cursos;
import com.colegiorunt.domain.CursosHasAsignaturas;
import com.colegiorunt.domain.Estudiantes;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.CursosRepository;
import com.colegiorunt.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class CursosServiceImpl implements CursosService{

	@Autowired
	private CursosRepository cursosRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Cursos cursos)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Cursos>> constraintViolations =validator.validate(cursos);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return cursosRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Cursos> findAll(){
		log.debug("finding all Cursos instances");
       	return cursosRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Cursos save(Cursos entity) throws Exception {
		log.debug("saving Cursos instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Cursos");
		}
		
		validate(entity);	
	
		if(cursosRepository.existsById(entity.getIdCurso())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return cursosRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Cursos entity) throws Exception {
            	log.debug("deleting Cursos instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Cursos");
	    		}
    	
                                if(entity.getIdCurso()==null){
                    throw new ZMessManager().new EmptyFieldException("idCurso");
                    }
                        
            if(cursosRepository.existsById(entity.getIdCurso())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getIdCurso()).ifPresent(entidad->{	            	
	                													List<CursosHasAsignaturas> cursosHasAsignaturases = entidad.getCursosHasAsignaturases();
	                    	                    if(Utilities.validationsList(cursosHasAsignaturases)==true){
                       	 	throw new ZMessManager().new DeletingException("cursosHasAsignaturases");
                        }
	                													List<Estudiantes> estudianteses = entidad.getEstudianteses();
	                    	                    if(Utilities.validationsList(estudianteses)==true){
                       	 	throw new ZMessManager().new DeletingException("estudianteses");
                        }
	                	            });
                       

           
            
            cursosRepository.deleteById(entity.getIdCurso());
            log.debug("delete Cursos successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Cursos instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("idCurso");
            	}
            	if(cursosRepository.existsById(id)){
           			delete(cursosRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Cursos update(Cursos entity) throws Exception {

				log.debug("updating Cursos instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Cursos");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(cursosRepository.existsById(entity.getIdCurso())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return cursosRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Cursos> findById(Integer idCurso) {            
            	log.debug("getting Cursos instance");
            	return cursosRepository.findById(idCurso);
            }
			
}
