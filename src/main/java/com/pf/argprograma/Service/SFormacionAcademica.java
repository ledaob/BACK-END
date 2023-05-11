
package com.pf.argprograma.Service;

import com.pf.argprograma.Entity.FormacionAcademica;
import com.pf.argprograma.Repository.RFormacionAcademica;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SFormacionAcademica {
    @Autowired
    RFormacionAcademica rformacionAcademica;
    
    public List<FormacionAcademica> list(){
        return rformacionAcademica.findAll();
    }
    
    public Optional<FormacionAcademica> getOne(int id){
        return rformacionAcademica.findById(id);
    }
    
    public Optional<FormacionAcademica> getByNombreFA(String nombreFA){
        return rformacionAcademica.findByNombreFA(nombreFA);
    }
    
    public void save(FormacionAcademica formacionAcademica){
        rformacionAcademica.save(formacionAcademica);
    }
    
    public void delete(int id){
        rformacionAcademica.deleteById(id);
        
    }
    
    public boolean existsById(int id){
        return rformacionAcademica.existsById(id);
    }
    public boolean existsByNombreFA(String nombreFA){
        return rformacionAcademica.existsByNombreFA(nombreFA);
    }
    
    
}
