package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface EmpleadoDao {
    public List<Empleado> listarEmpCabecera(Empleado empleado);
    public List<Empleado> listarEmpleado(Empleado empleado);
    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);
    public void actualizarCabecera(Empleado empleado);
    public void actualizarLaboral(Empleado empleado);
    public void actualizarPagos(Empleado empleado);
    public Empleado recuperarLaboral(Integer ciaid , Integer codtra);
    public Empleado recuperarPagos(Integer ciaid , Integer codtra);
    public Empleado recuperarSegSocial(Integer ciaid , Integer codtra);
    public Empleado recuperarDireccion(Integer ciaid , Integer codtra);
    public void actualizarSegSocial(Empleado empleado);
    public void actualizarDireccion(Empleado empleado);
    public List<Empleado> validarCabecera(Empleado empleado);
    public Integer obtieneIdEmpleado(Empleado empleado);
    public void insertarCabecera(Empleado empleado);
    public void actualizarFoto(Empleado empleado);
    public Empleado recuperarTurnos(Integer ciaid, Integer codtra);

}
