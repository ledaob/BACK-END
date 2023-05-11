
package com.pf.argprograma.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Persona  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min = 1, max = 40, message = "Longitud errónea")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 40, message ="Longitud errónea")
    private String apellido;
    
    @NotNull
    private int edad;
    
    
    
    

    
    
    
}
