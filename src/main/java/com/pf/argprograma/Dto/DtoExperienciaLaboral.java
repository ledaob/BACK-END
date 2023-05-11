
package com.pf.argprograma.Dto;

import javax.validation.constraints.NotBlank;


public class DtoExperienciaLaboral {
    @NotBlank
    private String nombreExp;
    @NotBlank
    private String descripcionExp;

    public DtoExperienciaLaboral() {
    }

    public DtoExperienciaLaboral(String nombreExp, String descripcionExp) {
        this.nombreExp = nombreExp;
        this.descripcionExp = descripcionExp;
    }

    public String getNombreExp() {
        return nombreExp;
    }

    public void setNombreExp(String nombreExp) {
        this.nombreExp = nombreExp;
    }

    public String getDescripcionExp() {
        return descripcionExp;
    }

    public void setDescripcionExp(String descripcionExp) {
        this.descripcionExp = descripcionExp;
    }
    
    
    
}
