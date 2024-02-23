package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Compania {

    private String id_companias;
    private String nombre;
    private String ruc;
    private String direccion;
    private String rol;
    private String id_usuario;

    private Integer idCodcia;
    private String descCia;
    private String nroRuc;
    private String descCiaCorto;
    private String direccionCia;
    private String nroTelfCia;
    private String idRubroCia;
    private String desRubroCia;
    private String IdActividadCia;
    private String desActividadCia;
    private String nomRepesentante;
    private String apepatRepesentante;
    private String apematRepesentante;
    private String codCargoRep;
    private String desCargoRep;
    private String idTipoDocuRep;
    private String nroDocuRep;
    private String urlLogo;
    private String usuCrea;
    private String usuMod;
    private String fecCrea;
    private String fecMod;
    private String iexurlfileserver;
    private String iexurlfilereport;
    private String iexurlfileimg;
    private String urlflgsource;

    private String iexflgsource;
    private String iexususource;
    private String iexpasssource;
    private String iexportsource;
    private String iexsourcedes;
    private String iexregiondes;
    private String iexdesobservacion;
    private String schema;
}
