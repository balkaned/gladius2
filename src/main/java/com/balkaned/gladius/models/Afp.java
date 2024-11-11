package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Afp {

    @Id
    private String iexpermes;
    private String iexcodafp;
    private String iexdesafp;
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

}
