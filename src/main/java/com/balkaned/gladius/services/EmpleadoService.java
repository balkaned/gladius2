package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Cumpleanos;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Ingresantes;
import com.balkaned.gladius.beans.Retirados;

import java.util.List;

public interface EmpleadoService {

    public List<Empleado> listarEmpCabecera(Empleado empleado);

    public List<Empleado> listarEmpleado(Empleado empleado);

    public Empleado recuperarCabecera(Integer ciaid, Integer codtra);

    public Empleado recuperarLaboral(Integer ciaid, Integer codtra);

    public Empleado recuperarPagos(Integer ciaid, Integer codtra);

    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra);

    public Empleado recuperarDireccion(Integer ciaid, Integer codtra);

    public void actualizarCabecera(Empleado empleado);

    public void actualizarLaboral(Empleado empleado);

    public void actualizarPagos(Empleado empleado);

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

    public List<Cumpleanos> traerListaDeCumpleañosPorMes();

    public List<Ingresantes> traerListaDeIngresantesPorMes();

    public List<Retirados> traerListaDeRetiradosPorMes();

}
