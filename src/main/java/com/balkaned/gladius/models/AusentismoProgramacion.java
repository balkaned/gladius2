package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
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

    public void setIexfecini(String iexfecini) {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit = new CapitalizarCadena();
        this.iexfecini = f.fechaFormatterDia(iexfecini) + " " + capit.letras(f.fechaFormatterMes(iexfecini)) + ", " + f.fechaFormatterAnio(iexfecini);
    }

    public void setIexfecfin(String iexfecfin) {
        FormatterFecha f2 = new FormatterFecha();
        CapitalizarCadena capit2 = new CapitalizarCadena();
        this.iexfecfin = f2.fechaFormatterDia(iexfecfin) + " " + capit2.letras(f2.fechaFormatterMes(iexfecfin)) + ", " + f2.fechaFormatterAnio(iexfecfin);
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
}
