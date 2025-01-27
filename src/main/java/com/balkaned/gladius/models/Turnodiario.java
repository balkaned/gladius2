package com.balkaned.gladius.models;

import com.balkaned.gladius.util.FormatterFecha;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Turnodiario{

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String iexcodfec;
    private String iexfecdia;
    private String desfecdia;
    private Integer iexcodturno;
    private String desturno;
    private String iexflgturno;
    private String iexiniturno;
    private String iexfinturno;
    private String iexiniasist;
    private String iexfinasist;
    private Double iexhrstotal;
    private Double iexmintotal;
    private Double iexminantes;
    private Double iexminpost;
    private Double iexhrspost;
    private Integer iexcoddiasem;
    private String iexfeccrea;
    private String iexdesusu;
    private String iexflgest;
    private Double iexhrsantes;
    private Double iexhrssale_antes;
    private Double iexminsale_antes;
    private Double iexhrstarde;
    private Double iexmintarde;
    private String desiniturno;
    private String desfinturno;
    private String desiniasist;
    private String desfinasist;
    private String iexvacaind;
    private String iexauseind;
    private String iexpermiso;
    private String iexhriniperm;
    private String iexhrfinperm;
    private Double iexhrsperm;
    private Double iexminsperm;
    private Integer correl;
    private String iexdestra;
    private String cencos;
    private Double horas;
    private Integer iexflgturno_conf;
    private String iexindferiado;
    private String iexindfalta;
    private Double totalhoras;
    private Double canthoras;
    private String diaCalendar;
    private String desfecdia2;

    public void setDesfecdia(String desfecdia) {

        FormatterFecha fec = new FormatterFecha();
        this.diaCalendar = fec.fechaFormatterDia(desfecdia);
    }
}
