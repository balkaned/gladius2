package com.balkaned.gladius.prestamo.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class PrestamoCab {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private String iextippres;
    private String destippres;
    private String iextipinteres;
    private String destipinteres;
    private String iexfrecuencia;
    private String destipfrecuencia;
    private String iexfecpres;
    private String iexfecinivig;
    private Double iexnrocuotas;
    private Double ieximpbru;
    private Double iexinteres;
    private Double ieximptotal;
    private String iexglosa;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String iexestado;

}
