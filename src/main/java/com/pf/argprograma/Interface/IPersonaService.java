
package com.pf.argprograma.Interface;

import com.pf.argprograma.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    public List<Persona> getPersonas();
    
    public void savePersona (Persona persona);
    
    public void deletePersona(Long id);
    
    public Persona findPersona (Long id);
}
