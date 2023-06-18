package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.UsuarioConeccion;

public interface UsuarioConeccionService {

    public UsuarioConeccion obtenerUsuarioConeccionById(String id);

    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc);

}

