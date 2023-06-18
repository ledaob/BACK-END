
package com.pf.argprograma.Controller;

import com.pf.argprograma.Dto.DtoProyectos;
import com.pf.argprograma.Entity.Proyectos;
import com.pf.argprograma.Security.Controller.Mensaje;
import com.pf.argprograma.Service.SProyectos;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

@RestController
@RequestMapping("/proyectos")
@CrossOrigin(origins = "https://oviedoledaportfolio.web.app/")
public class CProyectos {
    @Autowired
    SProyectos sProyectos;
    
    @GetMapping("/lista")
    public ResponseEntity<List<Proyectos>> list(){
        List<Proyectos> list = sProyectos.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoProyectos dtoproyectos){
        if(StringUtils.isBlank(dtoproyectos.getNombreProyecto()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
    
        if(sProyectos.existsByNombreProyecto(dtoproyectos.getNombreProyecto()))
            return new ResponseEntity(new Mensaje("Proyecto existente"), HttpStatus.BAD_REQUEST);
        
        Proyectos proyectos = new Proyectos(dtoproyectos.getNombreProyecto(), dtoproyectos.getDescripcionProyecto());
        sProyectos.save(proyectos);
        
        return new ResponseEntity(new Mensaje ("Se agregó el proyecto"), HttpStatus.OK); 
    
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody DtoProyectos dtoproyectos){
          if(!sProyectos.existsById(id)) 
              return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
          
          if(sProyectos.existsByNombreProyecto(dtoproyectos.getNombreProyecto()) && sProyectos.getByNombreProyecto(dtoproyectos.getNombreProyecto()).get().getId() !=id)
              return new ResponseEntity(new Mensaje("Ese proyecto ya existe"), HttpStatus.BAD_REQUEST);
          
          
          
          if(StringUtils.isBlank(dtoproyectos.getNombreProyecto()))
              return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
          
          
          Proyectos proyectos = sProyectos.getOne(id).get();
          proyectos.setNombreProyecto(dtoproyectos.getNombreProyecto());
          proyectos.setDescripcionProyecto((dtoproyectos.getDescripcionProyecto()));
          
          sProyectos.save(proyectos);
          return new ResponseEntity(new Mensaje("Se actualizó el proyecto"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        
        sProyectos.delete(id);
        
        return new ResponseEntity(new Mensaje("Se eliminó el proyecto"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Proyectos> getById (@PathVariable("id") int id){
        if(!sProyectos.existsById(id))
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.NOT_FOUND);
        Proyectos proyectos = sProyectos.getOne(id).get();
        return new ResponseEntity(proyectos, HttpStatus.OK);
    }
}
