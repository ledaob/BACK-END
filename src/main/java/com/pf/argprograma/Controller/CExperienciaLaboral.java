
package com.pf.argprograma.Controller;

import com.pf.argprograma.Dto.DtoExperienciaLaboral;
import com.pf.argprograma.Entity.ExperienciaLaboral;
import com.pf.argprograma.Security.Controller.Mensaje;
import com.pf.argprograma.Security.Service.ServExperienciaLaboral;
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
@RequestMapping("/experiencialaboral")
@CrossOrigin (origins = "http://localhost:4200")
public class CExperienciaLaboral {
    @Autowired
    ServExperienciaLaboral servexperienciaLaboral;
    
    @GetMapping("/lista")
    public ResponseEntity<List<ExperienciaLaboral>> list(){
        List<ExperienciaLaboral> list = servexperienciaLaboral.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DtoExperienciaLaboral dtoexpe){
        if(StringUtils.isBlank(dtoexpe.getNombreExp()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
    
        if(servexperienciaLaboral.existsByNombreExp(dtoexpe.getNombreExp()))
            return new ResponseEntity(new Mensaje("Experiencia existente"), HttpStatus.BAD_REQUEST);
        
        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral(dtoexpe.getNombreExp(), dtoexpe.getDescripcionExp());
        servexperienciaLaboral.save(experienciaLaboral);
        
        return new ResponseEntity(new Mensaje ("Se agregó la experiencia"), HttpStatus.OK); 
    
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody DtoExperienciaLaboral dtoexpe){
          if(!servexperienciaLaboral.existsById(id)) 
              return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
          
          if(servexperienciaLaboral.existsByNombreExp(dtoexpe.getNombreExp()) && servexperienciaLaboral.getByNombreExp(dtoexpe.getNombreExp()).get().getId() !=id)
              return new ResponseEntity(new Mensaje("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
          
          
          
          if(StringUtils.isBlank(dtoexpe.getNombreExp()))
              return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
          
          
          ExperienciaLaboral experienciaLaboral = servexperienciaLaboral.getOne(id).get();
          experienciaLaboral.setNombreExp(dtoexpe.getNombreExp());
          experienciaLaboral.setDescripcionExp((dtoexpe.getDescripcionExp()));
          
          servexperienciaLaboral.save(experienciaLaboral);
          return new ResponseEntity(new Mensaje("Se actualizó la experiencia"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id){
        if(!servexperienciaLaboral.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
        
        servexperienciaLaboral.delete(id);
        
        return new ResponseEntity(new Mensaje("Se eliminó la experiencia"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<ExperienciaLaboral> getById (@PathVariable("id") int id){
        if(!servexperienciaLaboral.existsById(id))
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.NOT_FOUND);
        ExperienciaLaboral experienciaLaboral = servexperienciaLaboral.getOne(id).get();
        return new ResponseEntity(experienciaLaboral, HttpStatus.OK);
    }
}
