package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface EmpleadoService {

    public List<Empleado> listarEmpCabecera(Empleado empleado);
    public List<Empleado> listarEmpleado(Empleado empleado);
    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);
}
