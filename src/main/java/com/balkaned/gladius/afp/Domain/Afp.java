package com.balkaned.gladius.afp.Domain;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Afp {

    @Id
    private String iexpermes;
    private String iexcodafp;
    private String desafp;
    private Double iexcomis_fija;
    private Double iexcomis_sflu;
    private Double iexcomis_sflu_mix;
    private Double iexcomis_anual_mix;
    private Double iexprima_seguro;
    private Double iexaporte_oblig;
    private Double iexremmax_asegu;
    private Double iexcomis_onp;
    private String iexusucrea;
    private String iexusumod;

    public void setDesafp(String desafp) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.desafp = cap.letras(desafp);
    }
}
