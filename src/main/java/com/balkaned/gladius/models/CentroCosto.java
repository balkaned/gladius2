package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class CentroCosto {

    @Id
    private Integer iexcodcia;
    private String iexccosto;
    private String iexdesccosto;
    private String iexcodcat;
    private String desdet;
    private String iexusucrea;
    private String iexusumod;
    private String iexfeccrea;
    private String iexfecmod;

    public void setIexdesccosto(String iexdesccosto) {
        CapitalizarCadena cap= new CapitalizarCadena();
        this.iexdesccosto = cap.letras(iexdesccosto);
    }
}
