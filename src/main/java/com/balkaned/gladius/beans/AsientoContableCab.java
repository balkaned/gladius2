package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AsientoContableCab {

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
