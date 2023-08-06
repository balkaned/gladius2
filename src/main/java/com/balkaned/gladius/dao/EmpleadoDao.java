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

    /*public List<Empleado> listarEmpleadoActivos(Integer codcia);
    public List<Empleado> listarEmpleadoInactivos(Integer codcia);
    public List<Empleado>  validarCabecera(Empleado Empleado);
    public Integer obtieneIdEmpleado(Empleado Empleado);
    public void insertarCabecera(Empleado Empleado);
    public void actualizarOtros(Empleado Empleado);
    public void  actualizarTurnos(Empleado Empleado) ;*/
    /*public String listarEmpleadoDyn(Integer ciaid, String destra);
    public Empleado recuperarTurnos(Integer ciaid , Integer codtra);
    public void  cesarEmpleado(Empleado empleado);
    public void  eliminarEmpleado(Integer ciaid , Integer codtra);
    public void   actualizarFoto(Empleado Empleado);
    public void insertarTraMas(List<Empleado> Empleado);
    public Integer  reingresarEmpleado(Integer ciaid, Integer codtrareing , String fechaing,  String desusu);*/
}
