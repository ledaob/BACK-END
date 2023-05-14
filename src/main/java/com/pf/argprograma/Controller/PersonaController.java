
package com.pf.argprograma.Controller;

import com.pf.argprograma.Dto.DtoPersona;

import com.pf.argprograma.Entity.Persona;
import com.pf.argprograma.Security.Controller.Mensaje;
import com.pf.argprograma.Service.ImplPersonaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/personas")
@CrossOrigin (origins ="https://frontendportfolio-5dd24.web.app")
public class PersonaController {
    
   @Autowired
    ImplPersonaService implpersonaService;
    
    
    @GetMapping("/lista")
    public ResponseEntity<List <Persona>> list(){
        List<Persona> list = implpersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable ("id") int id){
        if(!implpersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("No existe el ID"), HttpStatus.BAD_REQUEST);
         }
        
        Persona persona = implpersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }
    
    /*@DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete (@PathVariable("id") int id){
        if(!implpersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        implpersonaService.delete(id);
        return new ResponseEntity(new Mensaje("Persona eliminada"), HttpStatus.OK);
    }*/
    
    /*@PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody DtoPersona dtopersona){
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje ("Nombre obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if(implpersonaService.existsByNombre(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("Nombre ya existente"), HttpStatus.BAD_REQUEST);
        }        
        
        
        Persona persona = new Persona(
                dtopersona.getNombre(), dtopersona.getDescripcion()
        );
        
        implpersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Formacion académica creada"), HttpStatus.OK);
    }*/
    
    
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody DtoPersona dtopersona){
        if(!implpersonaService.existsById(id)){
            return new ResponseEntity(new Mensaje("ID inexistente"), HttpStatus.NOT_FOUND);
        }
        
        if(implpersonaService.existsByNombre(dtopersona.getNombre()) && implpersonaService.getByNombre(dtopersona.getNombre()).get().getId() != id){
            return new ResponseEntity (new Mensaje("Nombre existente"), HttpStatus.BAD_REQUEST);
        }
        
        if(StringUtils.isBlank(dtopersona.getNombre())){
            return new ResponseEntity(new Mensaje("El campo es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        
        Persona persona =  implpersonaService.getOne(id).get();
        
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion(dtopersona.getDescripcion());
        persona.setImg(dtopersona.getImg());
        
        implpersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Se actualizó la persona"), HttpStatus.OK);
    }
    
}
    
