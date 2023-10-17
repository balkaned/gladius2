package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Opciones;
import java.util.List;

public interface OpcionService {
    public List<Opciones> listarOpciones();
    public Integer getIdOpciones();
    public void insertarOpciones(Opciones opc);
    public Opciones getOpciones(Integer codopc);
    public void actualizarOpciones(Opciones opc);

}
