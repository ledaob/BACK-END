
package com.pf.argprograma.Controller;

import com.pf.argprograma.Interface.IPersonaService;
import com.pf.argprograma.Entity.Persona;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@CrossOrigin (origins = "http://localhost:4200")
public class PersonaController {
    
    @Autowired
    private IPersonaService ipersonaService;
    
    @GetMapping ("/personas/traer")
    public List<Persona> getPersonas(){
    
        return ipersonaService.getPersonas();
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/personas/crear")
    public String createPersona (@RequestBody Persona persona){
        
        ipersonaService.savePersona(persona);
        return "El usuario fue creado correctamente";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping ("/personas/borrar/{id}")
    public String deletePersona (@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "El usuario fue elimiando correctamente.";
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona  (@PathVariable Long id,            
                                 @RequestParam ("nombre") String nuevoNombre,
                                 @RequestParam ("apellido") String nuevoApellido,
                                 @RequestParam ("edad") int nuevaEdad){
        
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setApellido(nuevoApellido);
        persona.setNombre(nuevoNombre);
        persona.setEdad(nuevaEdad);
        
        ipersonaService.savePersona(persona);
        
        return persona;
    }
     @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)2);
    }
    
}
    
