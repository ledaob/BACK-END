
package com.pf.argprograma.Controller;


import com.pf.argprograma.Dto.dtoHyS;
import com.pf.argprograma.Entity.HyS;
import com.pf.argprograma.Security.Controller.Mensaje;
import com.pf.argprograma.Service.SHyS;
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
@CrossOrigin(origins = "https://oviedoledaportfolio.web.app/")
@RequestMapping("/skill")
public class CHys {
    @Autowired
    SHyS shys;
    
    @GetMapping("/lista")
    public ResponseEntity<List<HyS>> list(){
        List<HyS> list = shys.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHyS dtohys){
        if(StringUtils.isBlank(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
    
        if(shys.existsByNombre(dtohys.getNombre()))
            return new ResponseEntity(new Mensaje("Ya existe!"), HttpStatus.BAD_REQUEST);
        
        HyS hys = new HyS(dtohys.getNombre(), dtohys.getPorcentaje());
        shys.save(hys);
        
        return new ResponseEntity(new Mensaje ("Agregado"), HttpStatus.OK); 
    
    }
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update (@PathVariable("id") int id, @RequestBody dtoHyS dtohys){
          if(!shys.existsById(id)) 
              return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.BAD_REQUEST);
          
          if(shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() !=id)
              return new ResponseEntity(new Mensaje("Ya existe"), HttpStatus.BAD_REQUEST);
                   
          
          if(StringUtils.isBlank(dtohys.getNombre()))
              return new ResponseEntity(new Mensaje("Nombre obligatorio!"), HttpStatus.BAD_REQUEST);
          
          
          HyS hys = shys.getOne(id).get();
          hys.setNombre(dtohys.getNombre());
          hys.setPorcentaje((dtohys.getPorcentaje()));
          
          shys.save(hys);
          return new ResponseEntity(new Mensaje("Actualizado"), HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable ("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.NOT_FOUND);
        
        shys.delete(id);        
        return new ResponseEntity(new Mensaje("Eliminado"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<HyS> getById (@PathVariable("id") int id){
        if(!shys.existsById(id))
            return new ResponseEntity(new Mensaje("No existe."), HttpStatus.NOT_FOUND);
        HyS hys = shys.getOne(id).get();
        return new ResponseEntity(hys, HttpStatus.OK);
    }
    
}
