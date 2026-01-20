package com.balkaned.gladius.opciones.Infrastructure;


import com.balkaned.gladius.opciones.Domain.Opciones;

import java.util.List;

public interface OpcionDao {
    public List<Opciones> listarOpciones();

    public Integer getIdOpciones();

    public void insertarOpciones(Opciones opc);

    public Opciones getOpciones(Integer codopc);

    public void actualizarOpciones(Opciones opc);

    public void eliminarOpciones(Opciones opc);

}
