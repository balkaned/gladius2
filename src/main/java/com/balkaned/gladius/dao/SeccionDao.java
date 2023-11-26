package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Seccion;

import java.util.List;

public interface SeccionDao {
    public List<Seccion> listarSeccion();

    public Integer getIdSeccion();

    public void insertarSeccion(Seccion seccion);

    public Seccion getSeccion(Integer codsec);

    public void actualizarSeccion(Seccion seccion);

    public void eliminarSeccion(Seccion seccion);

}
