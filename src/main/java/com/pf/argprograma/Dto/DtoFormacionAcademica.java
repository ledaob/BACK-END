
package com.pf.argprograma.Dto;

import javax.validation.constraints.NotBlank;


public class DtoFormacionAcademica {
    @NotBlank
    private String nombreFA;
    
    @NotBlank
    private String descripcionFA;

    public DtoFormacionAcademica() {
    }

    public DtoFormacionAcademica(String nombreFA, String descripcionFA) {
        this.nombreFA = nombreFA;
        this.descripcionFA = descripcionFA;
    }

    public String getNombreFA() {
        return nombreFA;
    }

    public void setNombreFA(String nombreFA) {
        this.nombreFA = nombreFA;
    }

    public String getDescripcionFA() {
        return descripcionFA;
    }

    public void setDescripcionFA(String descripcionFA) {
        this.descripcionFA = descripcionFA;
    }

    


}
