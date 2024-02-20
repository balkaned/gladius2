package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.EmpleadoDao;
import com.balkaned.gladius.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoDao dao;

    public List<Empleado> listarEmpCabecera(Empleado empleado) {

        return dao.listarEmpCabecera(empleado);
    }

    public List<Empleado> listarEmpleado(Empleado empleado) {

        return dao.listarEmpleado(empleado);
    }

    public Empleado recuperarCabecera(Integer ciaid, Integer codtra) {
        return dao.recuperarCabecera(ciaid, codtra);
    }

    public Empleado recuperarLaboral(Integer ciaid, Integer codtra) {

        return dao.recuperarLaboral(ciaid, codtra);
    }

    public Empleado recuperarPagos(Integer ciaid, Integer codtra) {

        return dao.recuperarPagos(ciaid, codtra);
    }

    public Empleado recuperarSegSocial(Integer ciaid, Integer codtra) {

        return dao.recuperarSegSocial(ciaid, codtra);
    }

    public Empleado recuperarDireccion(Integer ciaid, Integer codtra) {

        return dao.recuperarDireccion(ciaid, codtra);
    }

    public void actualizarCabecera(Empleado empleado) {

        dao.actualizarCabecera(empleado);
    }

    public void actualizarLaboral(Empleado empleado) {

        dao.actualizarLaboral(empleado);
    }

    public void actualizarPagos(Empleado empleado) {

        dao.actualizarPagos(empleado);
    }

    public void actualizarSegSocial(Empleado empleado) {

        dao.actualizarSegSocial(empleado);
    }

    public void actualizarDireccion(Empleado empleado) {

        dao.actualizarDireccion(empleado);
    }

    public List<Empleado> validarCabecera(Empleado empleado) {

        return dao.validarCabecera(empleado);
    }

    public Integer obtieneIdEmpleado(Empleado empleado) {

        return dao.obtieneIdEmpleado(empleado);
    }

    public void insertarCabecera(Empleado empleado) {

        dao.insertarCabecera(empleado);
    }

    public void actualizarFoto(Empleado empleado) {

        dao.actualizarFoto(empleado);
    }

    public void reingresarEmpleado(Integer ciaid, Integer codtra, String fechaing, String desusu, Integer codnew) {
        dao.reingresarEmpleado(ciaid, codtra, fechaing, desusu, codnew);
    }

    public Empleado recuperarTurnos(Integer ciaid, Integer codtra) {

        return dao.recuperarTurnos(ciaid, codtra);
    }

    public void actualizarTurnos(Empleado empleado) {

        dao.actualizarTurnos(empleado);
    }

    public List<Empleado> listarEmpleadoInactivos(Integer codcia) {

        return dao.listarEmpleadoInactivos(codcia);
    }

    public List<Empleado> listarEmpleadoByCodTrab(Empleado empleado) {

        return dao.listarEmpleadoByCodTrab(empleado);
    }

}
