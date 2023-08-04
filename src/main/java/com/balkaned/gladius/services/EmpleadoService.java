package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface EmpleadoService {

    public List<Empleado> listarEmpCabecera(Empleado empleado);
    public List<Empleado> listarEmpleado(Empleado empleado);
    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);
    public Empleado recuperarLaboral(Integer ciaid, Integer codtra);
    public Empleado recuperarPagos(Integer ciaid, Integer codtra);
    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra);
    public Empleado recuperarDireccion(Integer ciaid, Integer codtra);
    public void  actualizarCabecera(Empleado empleado);
    public void  actualizarLaboral(Empleado empleado);

}
