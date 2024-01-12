package com.balkaned.gladius.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatterFecha {

    public String fechaFormatter(String fecha) {
        if (fecha == null) {
            return null;
        }
        try {
            SimpleDateFormat formatoOriginal = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaDate = formatoOriginal.parse(fecha);

            SimpleDateFormat formatoDeseado = new SimpleDateFormat("dd-MM-yyyy");
            String fechaFormateada = formatoDeseado.format(fechaDate);

            return fechaFormateada;

        } catch (ParseException e) {
            return null;
        }
    }
}
