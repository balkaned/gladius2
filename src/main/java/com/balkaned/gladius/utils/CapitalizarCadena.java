package com.balkaned.gladius.utils;


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


}
