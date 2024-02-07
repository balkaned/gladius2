package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.*;

import java.util.List;

public interface EmpleadoDao {
    public List<Empleado> listarEmpCabecera(Empleado empleado);

    public List<Empleado> listarEmpleado(Empleado empleado);

    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);

    public void actualizarCabecera(Empleado empleado);

    public void actualizarLaboral(Empleado empleado);

    public void actualizarPagos(Empleado empleado);

    public Empleado recuperarLaboral(Integer ciaid, Integer codtra);

    public Empleado recuperarPagos(Integer ciaid, Integer codtra);

    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra);

    public Empleado recuperarDireccion(Integer ciaid, Integer codtra);

    public void actualizarSegSocial(Empleado empleado);

    public void actualizarDireccion(Empleado empleado);

    public List<Empleado> validarCabecera(Empleado empleado);

    public Integer obtieneIdEmpleado(Empleado empleado);

    public void insertarCabecera(Empleado empleado);

    public void actualizarFoto(Empleado empleado);

    public Empleado recuperarTurnos(Integer ciaid, Integer codtra);

    public void actualizarTurnos(Empleado empleado);

    public List<Empleado> listarEmpleadoInactivos(Integer codcia);

    public void reingresarEmpleado(Integer ciaid, Integer codtra, String fechaing, String desusu, Integer codnew);

    public List<Empleado> listarEmpleadoByCodTrab(Empleado empleado);

    public List<Cumpleanos> traerListaDeCumpleañosPorMes(Integer codcia);

    public List<Ingresantes> traerListaDeIngresantesPorMes(Integer codcia);

    public List<Retirados> traerListaDeRetiradosPorMes(Integer codcia);

    public Integer getCantidadEmpl(Integer codcia);

    public Integer getCantidadAreas(Integer codcia);

    public Integer getCantidadBancos(Integer codcia);

    public dashboardSexoPie obtenerDashboardPieSexo(Integer codcia);

}
