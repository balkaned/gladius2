package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Compania {

    @Id
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
    private String idActividadCia;
    private String desActividadCia;
    private String nomRepresentante;
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

    private String idRubroCia;
    private String desRubroCia;

    private String iexflgsource;
    private String iexususource;
    private String iexpasssource;
    private String iexportsource;
    private String iexsourcedes;
    private String iexregiondes;
    private String iexdesobservacion;
    private String schema;

    private String nombreCap;
    private String direccionCap;

    public void setDescCiaCorto(String descCiaCorto) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.descCiaCorto = cap.letras(descCiaCorto);
    }

    public void setDireccionCia(String direccionCia) {
        CapitalizarCadena cap1 = new CapitalizarCadena();
        this.direccionCia = cap1.letras(direccionCia);
    }

    public void setNomRepresentante(String nomRepresentante) {
        CapitalizarCadena cap2 = new CapitalizarCadena();
        this.nomRepresentante = cap2.letras(nomRepresentante);
    }

    public void setDesCargoRep(String desCargoRep) {
        CapitalizarCadena cap3 = new CapitalizarCadena();
        this.desCargoRep = cap3.letras(desCargoRep);
    }

    public void setNombreCap(String nombreCap) {
        CapitalizarCadena cap4 = new CapitalizarCadena();
        this.nombreCap = cap4.letras(nombreCap);
    }

    public void setDireccionCap(String direccionCap) {
        CapitalizarCadena cap5 = new CapitalizarCadena();
        this.direccionCap = cap5.letras(direccionCap);
    }
}
