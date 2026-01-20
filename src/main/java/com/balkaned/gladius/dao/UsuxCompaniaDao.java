package com.balkaned.gladius.dao;


import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.models.UsuarioxRol;
import com.balkaned.gladius.models.UsuxCompania;

import java.util.List;

public interface UsuxCompaniaDao {

    public List<UsuxCompania> listar(Integer codusu);

    public List<Empleado> listaTrabajadoresCia(Integer codcia);

    public void insertar(UsuxCompania usuxcia);

    public void eliminar(UsuxCompania usuxcia);

    public UsuarioxRol obtenerRolxUsuario(Integer codcia, Integer codusu);

}
