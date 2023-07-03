package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;

import java.util.List;

public interface UsuarioConeccionService {

    public UsuarioConeccion obtenerUsuarioConeccionById(String id);

    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc);

    public List<Compania> listarCompaniasBycodUsu(String idUser);

}

