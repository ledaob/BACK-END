
package com.pf.argprograma.Service;

import com.pf.argprograma.Interface.IPersonaService;
import com.pf.argprograma.Entity.Persona;
import com.pf.argprograma.Repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplPersonaService implements IPersonaService{
    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public List<Persona> getPersonas() {
        List<Persona> listaPersonas = personaRepository.findAll();
        return listaPersonas;
    }

    @Override
    public void savePersona(Persona persona) {
        personaRepository.save(persona);
    }

    @Override
    public void deletePersona(Long id) {
        personaRepository.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
        Persona persona= personaRepository.findById(id).orElse(null);
        return persona;
    }
    
}
