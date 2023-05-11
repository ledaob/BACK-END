
package com.pf.argprograma.Repository;

import com.pf.argprograma.Entity.FormacionAcademica;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RFormacionAcademica extends JpaRepository<FormacionAcademica, Integer> {
    Optional<FormacionAcademica> findByNombreFA(String nombreFA);
    public boolean existsByNombreFA(String nombreFA);
    
    
}
