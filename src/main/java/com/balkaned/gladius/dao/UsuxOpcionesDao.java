package com.balkaned.gladius.dao;



import com.balkaned.gladius.beans.UsuxOpciones;

import java.util.List;


public interface UsuxOpcionesDao {

    public UsuxOpciones ObtieneAccesoOpcion(Integer codcia, Integer codusu, Integer codopc);

    public List<UsuxOpciones> listarOpciones(Integer codcia, Integer codusu, Integer codsys);

}
