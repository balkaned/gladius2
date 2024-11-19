package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ConceptoxAgrup {

    @Id
    private Integer idproceso;
    private String codconcepto;
    private String codconceptaux;
    private String desconceptaux;
    private String desvarcon;
    private String desvarcon_aux;

    public void setDesconceptaux(String desconceptaux) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.desconceptaux = cap.letras(desconceptaux);
    }
}
