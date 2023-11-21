package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuxCompania;

import java.util.List;

public interface UsuxCompaniaService {
    public List<UsuxCompania> listar(Integer codusu);

    public List<Empleado> listaTrabajadoresCia(Integer codcia);

    public void insertar(UsuxCompania usuxcia);

    public void eliminar(UsuxCompania usuxcia);

}
