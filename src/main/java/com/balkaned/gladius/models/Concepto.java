package com.balkaned.gladius.models;


import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Concepto {

    @Id
    private Integer idProceso;
    private String codConcepto;
    private String desConcepto;
    private String desVariable;
    private String descripcion;
    private String desAbreviacion;
    private String desAbreviacionCapit;

    public void setDesConcepto(String desConcepto) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.desConcepto = cap.letras(desConcepto);
    }

    public void setDesAbreviacion(String desAbreviacion) {
        CapitalizarCadena cap2 = new CapitalizarCadena();
        this.desAbreviacion = cap2.letras(desAbreviacion);
    }
}
