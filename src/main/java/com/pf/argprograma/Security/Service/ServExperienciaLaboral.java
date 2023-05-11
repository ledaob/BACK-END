
package com.pf.argprograma.Security.Service;

import com.pf.argprograma.Entity.ExperienciaLaboral;
import com.pf.argprograma.Repository.RExperienciaLaboral;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ServExperienciaLaboral {
    @Autowired
    RExperienciaLaboral rexperienciaLaboral;
    
    public List <ExperienciaLaboral> list(){
        return rexperienciaLaboral.findAll();
    }
    
    public Optional<ExperienciaLaboral> getOne (int id){
        return rexperienciaLaboral.findById(id);
    }
    
    public Optional<ExperienciaLaboral> getByNombreExp (String nombreExp){
        return rexperienciaLaboral.findByNombreExp(nombreExp);
    }
    
    public void save(ExperienciaLaboral expe){
    rexperienciaLaboral.save(expe);
    }
    
    public void delete(int id){
        rexperienciaLaboral.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rexperienciaLaboral.existsById(id);
    }
    
    public boolean existsByNombreExp(String nombreExp){
        return rexperienciaLaboral.existsByNombreExp(nombreExp);
    }
}
