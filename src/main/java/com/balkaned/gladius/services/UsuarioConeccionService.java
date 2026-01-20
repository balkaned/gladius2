package com.balkaned.gladius.services;

import com.balkaned.gladius.companias.Domain.Compania;
import com.balkaned.gladius.models.UsuarioConeccion;

import java.util.List;

public interface UsuarioConeccionService {

    public UsuarioConeccion obtenerUsuarioConeccionById(String id);

    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc);

    public List<Compania> listarCompaniasBycodUsu(String idUser);

}

