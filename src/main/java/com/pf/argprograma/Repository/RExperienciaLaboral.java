
package com.pf.argprograma.Repository;

import com.pf.argprograma.Entity.ExperienciaLaboral;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RExperienciaLaboral extends JpaRepository<ExperienciaLaboral, Integer> {
    public Optional<ExperienciaLaboral> findByNombreExp(String nombreExp);
    public boolean existsByNombreExp(String nombreExp);
    
}
