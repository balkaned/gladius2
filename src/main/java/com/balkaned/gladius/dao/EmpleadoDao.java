package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface EmpleadoDao {

    public List<Empleado> listarEmpCabecera(Empleado empleado);
    public List<Empleado> listarEmpleado(Empleado empleado);
    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);

    /*public List<Empleado> listarEmpleadoActivos(Integer codcia);

    public List<Empleado> listarEmpleadoInactivos(Integer codcia);

    public List<Empleado>  validarCabecera(Empleado Empleado);
    public Integer obtieneIdEmpleado(Empleado Empleado);
    public void insertarCabecera(Empleado Empleado);

    public void   actualizarCabecera(Empleado Empleado);
    public void  actualizarLaboral(Empleado Empleado);
    public void  actualizarPagos(Empleado Empleado);
    public void  actualizarSegSocial(Empleado Empleado);
    public void actualizarOtros(Empleado Empleado);
    public void  actualizarDireccion(Empleado Empleado);
    public void  actualizarTurnos(Empleado Empleado) ;


    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);
    public Empleado recuperarLaboral(Integer ciaid , Integer codtra);
    public Empleado recuperarPagos(Integer ciaid , Integer codtra);
    public Empleado recuperarSegSocial(Integer ciaid , Integer codtra);
    public Empleado recuperarOtros(Integer ciaid , Integer codtra);
    public Empleado recuperarDireccion(Integer ciaid , Integer codtra);

    public String listarEmpleadoDyn(Integer ciaid, String destra);

    public Empleado recuperarTurnos(Integer ciaid , Integer codtra);

    public void  cesarEmpleado(Empleado empleado);

    public void  eliminarEmpleado(Integer ciaid , Integer codtra);

    public void   actualizarFoto(Empleado Empleado);


    public void insertarTraMas(List<Empleado> Empleado);


    public Integer  reingresarEmpleado(Integer ciaid, Integer codtrareing , String fechaing,  String desusu);*/
}