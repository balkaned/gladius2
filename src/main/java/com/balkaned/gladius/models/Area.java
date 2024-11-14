package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Area {

    @Id
    private Integer iexcodcia;
    private String iexcodarea;
    private String iexdesarea;
    private String iexdesarea_descripcion;
    private String iexcodcat;
    private String descodcat;
    private String iexareapadre;
    private String desareapadre;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;

    public void setIexdesarea_descripcion(String iexdesarea_descripcion) {
        CapitalizarCadena cap = new CapitalizarCadena();
       this.iexdesarea_descripcion = cap.letras(iexdesarea_descripcion);
    }

    public void setDescodcat(String descodcat) {
        CapitalizarCadena cap2 = new CapitalizarCadena();
        this.descodcat = cap2.letras(descodcat);
    }

}
