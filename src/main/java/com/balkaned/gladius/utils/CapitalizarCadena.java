package com.balkaned.gladius.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.join;

public class CapitalizarCadena {

    public String letras(String cadena) {

        if (cadena == null || cadena.equals("")) {
            return null;
        } else {
            String cadena2 = cadena.toLowerCase();
            String cap = cadena2.substring(0, 1).toUpperCase() + cadena2.substring(1);
            return cap;
        }
    }

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
