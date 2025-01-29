package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class UsuxOpciones {

    @Id
    private Integer codcia;
    private Integer codusu;
    private Integer codrol;
    private Integer codopc;
    private String desopc;
    private String urlopc;
    private String urlimg;
    private Integer codsec;
    private String dessec;
    private Integer ordsec;
    private String dessys;
    private String dessecimg;
    private String consultarOpc;
    private String registrarOpc;
    private String modificarOpc;
    private String eliminarOpc;
    private String descargarPdfOpc;
    private String descargarXlsOpc;
    private String desaction;
    public String dessecCapi;
    public String icon;
    public String path;
    private String dessec2;

    public void setPath(String path) {
        String cadena = path;
        String nuevacadena = path.substring(1, cadena.length());

        this.path = nuevacadena;
    }

    public void setDessec(String dessec) {

        CapitalizarCadena cap = new CapitalizarCadena();

        this.dessec = cap.letras(dessec);
        this.dessecCapi = cap.letras(dessec);
    }
}
