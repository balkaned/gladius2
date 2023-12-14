package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.UsuxOpciones;

import java.util.List;


public interface UsuxOpcionesService {
    public UsuxOpciones ObtieneAccesoOpcion(Integer codcia, Integer codusu, Integer codopc);

    public List<UsuxOpciones> listarOpciones(Integer codcia, Integer codusu, Integer codsys);

}
