
package com.pf.argprograma.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;




@Entity
public class Persona  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    @Size(min = 1, max = 40, message = "Longitud errónea")
    private String nombre;
    
    @NotNull
    @Size(min = 1, max = 40, message ="Longitud errónea")
    private String apellido;
    
    @NotNull
    private int edad;
    
    
    private String img;
    
    @NotNull
    private String descripcion;

    public Persona() {
    }

    public Persona(String nombre, String apellido, int edad, String img, String descripcion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.img = img;
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
     
    
    
}
