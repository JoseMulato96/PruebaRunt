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

import com.colegiorunt.domain.Colegios;
import com.colegiorunt.domain.Cursos;
import com.colegiorunt.exception.ZMessManager;
import com.colegiorunt.repository.ColegiosRepository;
import com.colegiorunt.utility.Utilities;

import lombok.extern.slf4j.Slf4j;

@Scope("singleton")
@Service
@Slf4j
public class ColegiosServiceImpl implements ColegiosService{

	@Autowired
	private ColegiosRepository colegiosRepository;
	
	@Autowired
	private Validator validator;
                
    @Override        		
	public void validate(Colegios colegios)throws ConstraintViolationException{		
		
		Set<ConstraintViolation<Colegios>> constraintViolations =validator.validate(colegios);
		 if (!constraintViolations.isEmpty()) {			
			throw new ConstraintViolationException(constraintViolations);
		}
		
	}
	
	@Override
	@Transactional(readOnly=true)
	public Long count(){
	 	return colegiosRepository.count();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Colegios> findAll(){
		log.debug("finding all Colegios instances");
       	return colegiosRepository.findAll();
    }
			
			
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)			
    public Colegios save(Colegios entity) throws Exception {
		log.debug("saving Colegios instance");
	   
	    
	    if(entity==null){
			throw new ZMessManager().new NullEntityExcepcion("Colegios");
		}
		
		validate(entity);	
	
		if(colegiosRepository.existsById(entity.getIdColegio())){
           throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        }    
	
	    return colegiosRepository.save(entity);
	   
    }
			
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void delete(Colegios entity) throws Exception {
            	log.debug("deleting Colegios instance");
            	
	            if(entity==null){
	    			throw new ZMessManager().new NullEntityExcepcion("Colegios");
	    		}
    	
                                if(entity.getIdColegio()==null){
                    throw new ZMessManager().new EmptyFieldException("idColegio");
                    }
                        
            if(colegiosRepository.existsById(entity.getIdColegio())==false){
           		throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        	} 
            
            	            findById(entity.getIdColegio()).ifPresent(entidad->{	            	
	                													List<Cursos> cursoses = entidad.getCursoses();
	                    	                    if(Utilities.validationsList(cursoses)==true){
                       	 	throw new ZMessManager().new DeletingException("cursoses");
                        }
	                	            });
                       

           
            
            colegiosRepository.deleteById(entity.getIdColegio());
            log.debug("delete Colegios successful");
            
           
            	
            }
            
            @Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public void deleteById(Integer id) throws Exception {            
            	log.debug("deleting Colegios instance");
            	if(id==null){
            		throw new ZMessManager().new EmptyFieldException("idColegio");
            	}
            	if(colegiosRepository.existsById(id)){
           			delete(colegiosRepository.findById(id).get());
       			}    
            }	
			
			@Override
			@Transactional(readOnly = false, propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
            public Colegios update(Colegios entity) throws Exception {

				log.debug("updating Colegios instance");
				
	           
	            
	            	if(entity==null){
		    			throw new ZMessManager().new NullEntityExcepcion("Colegios");
		    		}
		    		
	            validate(entity);
	            
	            
	            if(colegiosRepository.existsById(entity.getIdColegio())==false){
           			throw new ZMessManager(ZMessManager.ENTITY_WITHSAMEKEY);
        		}	            
	
	            return colegiosRepository.save(entity);
	        
            }
			
			@Override
			@Transactional(readOnly=true)
            public Optional<Colegios> findById(Integer idColegio) {            
            	log.debug("getting Colegios instance");
            	return colegiosRepository.findById(idColegio);
            }
			
}
