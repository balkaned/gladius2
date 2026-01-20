package com.balkaned.gladius.contrato.Domain;

import com.balkaned.gladius.contrato.Infrastructure.ContratoDao;
import com.balkaned.gladius.empleado.Domain.Empleado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContratoServiceImpl implements ContratoService {

    @Autowired
    ContratoDao dao;

    public List<ContratoEmp> listarContratoEmp(Empleado empleado) {
        return dao.listarContratoEmp(empleado);
    }

    public Integer getIdContratoEmp(ContratoEmp contemp) {
        return dao.getIdContratoEmp(contemp);
    }

    public void insertarContratoEmp(ContratoEmp contemp) {
        dao.insertarContratoEmp(contemp);
    }

    public ContratoEmp getContratoEmp(ContratoEmp contemp) {
        return dao.getContratoEmp(contemp);
    }

    public void actualizarContratoEmp(ContratoEmp contemp) {
        dao.actualizarContratoEmp(contemp);
    }

    public void eliminarContratoEmp(ContratoEmp contemp){
        dao.eliminarContratoEmp(contemp);
    }

}
