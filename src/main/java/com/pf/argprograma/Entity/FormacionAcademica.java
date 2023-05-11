

package com.pf.argprograma.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormacionAcademica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreFA;
    private String descripcionFA;

    public FormacionAcademica() {
    }

    public FormacionAcademica(String nombreFA, String descripcionFA) {
        this.nombreFA = nombreFA;
        this.descripcionFA = descripcionFA;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
