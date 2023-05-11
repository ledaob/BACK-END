
package com.pf.argprograma.Controller;

import com.pf.argprograma.Dto.DtoFormacionAcademica;
import com.pf.argprograma.Entity.FormacionAcademica;
import com.pf.argprograma.Security.Controller.Mensaje;
import com.pf.argprograma.Service.SFormacionAcademica;
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
@RequestMapping("/formacionacademica")
@CrossOrigin(origins= "http://localhost:4200")
public class CFormacionAcademica {
    @Autowired
    SFormacionAcademica sformacionAcademica;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List <FormacionAcademica>> list(){
        List<FormacionAcademica> list = sformacionAcademica.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<FormacionAcademica> getById(@PathVariable ("id") int id){
        if(!sformacionAcademica.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
         }
        
        FormacionAcademica formacionAcademica = sformacionAcademica.getOne(id).get();
        return new ResponseEntity(formacionAcademica, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!sformacionAcademica.existsById(id)){
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        sformacionAcademica.delete(id);
        return new ResponseEntity(new Mensaje("Formacion académica eliminada"), HttpStatus.OK);
    }
    
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody DtoFormacionAcademica dtoformacionAcademica){
        if(StringUtils.isBlank(dtoformacionAcademica.getNombreFA())){
            return new ResponseEntity(new Mensaje ("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(sformacionAcademica.existsByNombreFA(dtoformacionAcademica.getNombreFA())){
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }        
        
        
        FormacionAcademica formacionAcademica = new FormacionAcademica(
                dtoformacionAcademica.getNombreFA(), dtoformacionAcademica.getDescripcionFA()
        );
        
        sformacionAcademica.save(formacionAcademica);
        return new ResponseEntity(new Mensaje("Formacion académica creada"), HttpStatus.OK);
    }
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoFormacionAcademica dtoformacionAcademica){
        if(!sformacionAcademica.existsById(id)){
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        
        if(sformacionAcademica.existsByNombreFA(dtoformacionAcademica.getNombreFA()) && sformacionAcademica.getByNombreFA(dtoformacionAcademica.getNombreFA()).get().getId() != id){
            return new ResponseEntity (new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }
        
        FormacionAcademica formacionAcademica =  sformacionAcademica.getOne(id).get();
        
        formacionAcademica.setNombreFA(dtoformacionAcademica.getNombreFA());
        formacionAcademica.setDescripcionFA(dtoformacionAcademica.getDescripcionFA());
        
        sformacionAcademica.save(formacionAcademica);
        
        return new ResponseEntity(new Mensaje("Se actualizó la formación académica"), HttpStatus.OK);
    }
    
}
