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

import com.colegiorunt.domain.Asignaturas;
import com.colegiorunt.domain.Profesores;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.ProfesoresRepository;
import com.colegiorunt.utility.Utilities;

import lombok.extern.slf4j.Slf4j;


@Scope("singleton")
@Service
@Slf4j
public class ProfesoresServiceImpl implements ProfesoresService{

	@Autowired
	private ProfesoresRepository profesoresRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Profesores profesores)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Profesores>> constraintViolations =validator.validate(profesores);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return profesoresRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Profesores> findAll(){
		log.debug("finding all Profesores instances");
       	return profesoresRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Profesores save(Profesores entity) throws Exception {
		log.debug("saving Profesores instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Profesores");
		}
		
		validate(entity);	
	
		if(profesoresRepository.existsById(entity.getIdProfesor())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return profesoresRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Profesores entity) throws Exception {
            	log.debug("deleting Profesores instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Profesores");
	    		}
    	
                                if(entity.getIdProfesor()==null){
                    throw new ZMessManager().new EmptyFieldException("idProfesor");
                    }
                        
            if(profesoresRepository.existsById(entity.getIdProfesor())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getIdProfesor()).ifPresent(entidad->{	            	
	                													List<Asignaturas> asignaturases = entidad.getAsignaturases();
	                    	                    if(Utilities.validationsList(asignaturases)==true){
                       	 	throw new ZMessManager().new DeletingException("asignaturases");
                        }
	                	            });
                       

           
            
            profesoresRepository.deleteById(entity.getIdProfesor());
            log.debug("delete Profesores successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Profesores instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("idProfesor");
            	}
            	if(profesoresRepository.existsById(id)){
           			delete(profesoresRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Profesores update(Profesores entity) throws Exception {

				log.debug("updating Profesores instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Profesores");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(profesoresRepository.existsById(entity.getIdProfesor())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return profesoresRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Profesores> findById(Integer idProfesor) {            
            	log.debug("getting Profesores instance");
            	return profesoresRepository.findById(idProfesor);
            }
			
}
