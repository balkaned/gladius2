package com.balkaned.gladius.models;

import com.balkaned.gladius.util.FormatterFecha;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Asistencia {

    @Id
    private Integer iexcodcia;
    private Integer iexcodtra;
    private String destra;
    private String iexcodfec;
    private String tipmarka;
    private String iexfeccrea;
    private String iexusucrea;
    private String fechaEnLetras;
    private String hora;

    public void setIexcodfec(String iexcodfec) {

        FormatterFecha fd = new FormatterFecha();
        String dia = fd.fechaFormatterDia(iexcodfec);

        FormatterFecha fm = new FormatterFecha();
        String mes = fm.fechaFormatterMes(iexcodfec);

        FormatterFecha fy = new FormatterFecha();
        String anio = fy.fechaFormatterAnio(iexcodfec);

        String fechaEnLetras2 = dia + " " + mes + ", " + anio;
        this.fechaEnLetras = fechaEnLetras2;

        FormatterFecha fh = new FormatterFecha();
        this.hora = fh.fechaFormatterHoraAMyPM(iexcodfec);
    }

}
