package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;
import java.util.List;

public interface UsuarioConeccionDao {

    public UsuarioConeccion obtenerUsuarioConeccionById(String id);

    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc);

    public List<Compania> listarCompaniasBycodUsu(String idUser);

}
