package com.balkaned.gladius.beans;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BancoResumenPer {

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

}
