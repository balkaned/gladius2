package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ConceptoxProms {

    @Id
    private Integer idproceso;
    private String codconcepto;
    private String codconceptaux;
    private Integer idprocesoaux;
    private String desconceptaux;
    private String desprocesoaux;

    public void setDesprocesoaux(String desprocesoaux) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.desprocesoaux = cap.letras(desprocesoaux);
    }

    public void setDesconceptaux(String desconceptaux) {
        CapitalizarCadena cap2 = new CapitalizarCadena();
        this.desconceptaux = cap2.letras(desconceptaux);
    }
}
