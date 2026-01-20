package com.balkaned.gladius.ausentismo.Domain;

import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Slf4j
public class AusentismoProgramacion {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private String iextipaus;
    private String destipaus;
    private String iexfecini;
    private String iexfecfin;
    private Double iexnrodias;
    private String iexglosa;
    private Double iexmins;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String desnomtra;
    private String fecing;
    private String nrodoc;
    private String desestado;
    private String fecfinrep;

    private Integer aus_id;
    private String iexnrodoc;
    private String nomtra;
    private Double dias_aus;

    private String iexfeciniDes;
    private String iexfecfinDes;

    private String nomtra2;
    private String fecing2;

    public String getIexfeciniDes() {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit = new CapitalizarCadena();
        String fec = this.iexfecini;
        return f.fechaFormatterDia(fec) + " " + capit.letras(f.fechaFormatterMes(fec)) + ", " + f.fechaFormatterAnio(fec);
    }

    public String getIexfecfinDes() {
        FormatterFecha f2 = new FormatterFecha();
        CapitalizarCadena capit2 = new CapitalizarCadena();
        return  f2.fechaFormatterDia(iexfecfin) + " " + capit2.letras(f2.fechaFormatterMes(iexfecfin)) + ", " + f2.fechaFormatterAnio(iexfecfin);
    }

    public void setFecing(String fecing) {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit = new CapitalizarCadena();
        this.fecing = f.fechaFormatterDia(fecing) + " " + capit.letras(f.fechaFormatterMes(fecing)) + ", " + f.fechaFormatterAnio(fecing);
    }

    public void setDesnomtra(String desnomtra) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.desnomtra = cap.letras(desnomtra);
    }

    public void setNomtra(String nomtra) {
        CapitalizarCadena cap3 = new CapitalizarCadena();
        this.nomtra = cap3.letras(nomtra);
    }

    /*public String getDestipaus() {
        return destipaus;
    }

    public void setDestipaus(String destipaus) {
        this.destipaus = destipaus;
    }*/
}
