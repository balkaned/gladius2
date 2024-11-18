package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BancoPro {

    @Id
    private Integer iexcodcia;
    private String iexcodban;
    private String desban;
    private Integer iexcodpro;
    private String prodespro;
    private String iextipcta;
    private String destipcta;
    private String iexctaban;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;

    public void setDesban(String desban) {
        CapitalizarCadena cap= new CapitalizarCadena();
        this.desban = cap.letras(desban);
    }

    public void setProdespro(String prodespro) {
        CapitalizarCadena cap2= new CapitalizarCadena();
        this.prodespro = cap2.letras(prodespro);
    }
}
