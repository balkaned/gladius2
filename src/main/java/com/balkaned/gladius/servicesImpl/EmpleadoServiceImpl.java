package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.EmpleadoDao;
import com.balkaned.gladius.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

    @Autowired
    EmpleadoDao dao;

    public List<Empleado> listarEmpCabecera(Empleado empleado){
        return dao.listarEmpCabecera(empleado);
    }
    public List<Empleado> listarEmpleado(Empleado empleado){
        return dao.listarEmpleado(empleado);
    }
    public Empleado recuperarCabecera(Integer ciaid, Integer codtra){
        return dao.recuperarCabecera(ciaid,codtra);
    }
    public Empleado recuperarLaboral(Integer ciaid , Integer codtra){return dao.recuperarLaboral(ciaid,codtra);}
    public Empleado recuperarPagos(Integer ciaid , Integer codtra){return dao.recuperarPagos(ciaid,codtra);}
    public Empleado recuperarSegSocial(Integer ciaid , Integer codtra){return dao.recuperarSegSocial(ciaid,codtra);}
    public Empleado recuperarDireccion(Integer ciaid , Integer codtra){return dao.recuperarDireccion(ciaid,codtra);}
    public void  actualizarCabecera(Empleado empleado){dao.actualizarCabecera(empleado);}
    public void  actualizarLaboral(Empleado empleado){dao.actualizarLaboral(empleado);}


}
