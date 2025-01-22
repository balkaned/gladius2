package com.balkaned.gladius.models;

import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ContratoEmp {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private Integer iexcorrel;
    private String iextipcont;
    private String destipcont;
    private String iexfecini;
    private String iexfecfin;
    private String iexmodcont;
    private String desmodcont;
    private String iexusucrea;
    private String iexfeccrea;
    private String iexusumod;
    private String iexfecmod;
    private String iexestado;

    /*public void setIexfecini(String iexfecini) {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit = new CapitalizarCadena();
        this.iexfecini = f.fechaFormatterDia(iexfecini) + " " + capit.letras(f.fechaFormatterMes(iexfecini)) + ", " + f.fechaFormatterAnio(iexfecini);
    }

    public void setIexfecfin(String iexfecfin) {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit2 = new CapitalizarCadena();
        this.iexfecfin = f.fechaFormatterDia(iexfecfin) + " " + capit2.letras(f.fechaFormatterMes(iexfecfin)) + ", " + f.fechaFormatterAnio(iexfecfin);
    }*/

}
