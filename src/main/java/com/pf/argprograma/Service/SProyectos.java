
package com.pf.argprograma.Service;

import com.pf.argprograma.Entity.Proyectos;
import com.pf.argprograma.Repository.RProyectos;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SProyectos {
    @Autowired
    RProyectos rproyectos;
    
    public List<Proyectos> list(){
        return rproyectos.findAll();
    }
    
    public Optional<Proyectos> getOne(int id){
        return rproyectos.findById(id);
    }
    
    public Optional<Proyectos> getByNombreProyecto(String nombreProyecto){
        return rproyectos.findByNombreProyecto(nombreProyecto);
    }
    
    public void save (Proyectos proyectos){
        rproyectos.save(proyectos);
    }
    
    public void delete(int id){
        rproyectos.deleteById(id);
    }
    
    public boolean existsById(int id){
        return rproyectos.existsById(id);
    }
    
    public boolean existsByNombreProyecto(String nombreProyecto){
        return rproyectos.existsByNombreProyecto(nombreProyecto);
    }
}
