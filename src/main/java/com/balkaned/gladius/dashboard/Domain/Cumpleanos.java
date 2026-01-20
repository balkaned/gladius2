package com.balkaned.gladius.dashboard.Domain;

import com.balkaned.gladius.util.CapitalizarCadena;
import com.balkaned.gladius.util.FormatterFecha;
import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Cumpleanos {

    @Id
    private int iexcodtra;
    private String iexlogo;
    private String sexo;
    private String iexnomtra;
    private String iexapepat;
    private String iexapemat;
    private String iexfecnac;
    private String iexfecnacFormat;
    private String mesActual;
    private int edad;

    public void setIexnomtra(String iexnomtra) {
        CapitalizarCadena cap = new CapitalizarCadena();
        this.iexnomtra = cap.letras(iexnomtra);
    }

    public void setIexapepat(String iexapepat) {
        CapitalizarCadena cap2 = new CapitalizarCadena();
        this.iexapepat = cap2.letras(iexapepat);
    }

    public void setIexapemat(String iexapemat) {
        CapitalizarCadena cap3 = new CapitalizarCadena();
        this.iexapemat = cap3.letras(iexapemat);
    }

    public String getIexfecnacFormat() {
        FormatterFecha f = new FormatterFecha();
        CapitalizarCadena capit = new CapitalizarCadena();
        return  f.fechaFormatterDia(iexfecnacFormat) + " " + capit.letras(f.fechaFormatterMes(iexfecnacFormat)) + ", " + f.fechaFormatterAnio(iexfecnacFormat);
    }
}