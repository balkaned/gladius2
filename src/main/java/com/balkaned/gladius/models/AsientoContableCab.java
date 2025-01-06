package com.balkaned.gladius.models;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class AsientoContableCab {

    @Id
    private Integer iexcodcia;
    private Integer iexctbper_id;
    private String iexnroasiento;
    private Integer iexcodpro;
    private String desproceso;
    private String iexnroper;
    private String iexpermes;
    private Double iextcmb;
    private String iexcodmon;
    private String codmon_des;
    private String iexcodmon_ext;
    private String codmon_ext_des;
    private Double tot_cre_na;
    private Double tot_deb_na;
    private Double numeric;
    private Double tot_cre_me;
    private Double tot_deb_me;
    private String iexglosacab;
    private String estado;
    private String iexestado;
    private String anio;

}
