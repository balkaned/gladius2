package com.balkaned.gladius.dao;

import com.balkaned.gladius.models.Compania;
import com.balkaned.gladius.models.UsuarioConeccion;
import java.util.List;

public interface UsuarioConeccionDao {

    public UsuarioConeccion obtenerUsuarioConeccionById(String id);

    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc);

    public List<Compania> listarCompaniasBycodUsu(String idUser);

}
