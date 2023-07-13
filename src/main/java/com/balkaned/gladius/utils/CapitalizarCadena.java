package com.balkaned.gladius.utils;

import static java.lang.String.join;

public class CapitalizarCadena {

    public String letras(String cadena) {

        String cadena2 = cadena.toLowerCase();
        String cap = cadena2.substring(0, 1).toUpperCase() + cadena2.substring(1);

        return cap;
    }
}
