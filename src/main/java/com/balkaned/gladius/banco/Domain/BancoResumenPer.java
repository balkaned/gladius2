package com.balkaned.gladius.banco.Domain;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BancoResumenPer {

    @Id
    private Integer iexcodcia;
    private Integer iexcodpro;
    private Integer correl;
    private String iexnroper;
    private String permes;
    private String codbank;
    private String desbank;
    private String moneda;
    private String desmoneda;
    private String nroctabank;
    private Double impneto;
    private Double heads;
    private String desmonReport;


    public void setMoneda(String moneda) {
        if (moneda.equals("S/.")) {
            this.desmonReport = "S";
        } else if (moneda.equals("EU")) {
            this.desmonReport = "E";
        }
    }
}
