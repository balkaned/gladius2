package com.balkaned.gladius.contrato.Domain;


import com.balkaned.gladius.empleado.Domain.Empleado;

import java.util.List;

public interface ContratoService {

    public List<ContratoEmp> listarContratoEmp(Empleado empleado);

    public Integer getIdContratoEmp(ContratoEmp contemp);

    public void insertarContratoEmp(ContratoEmp contemp);

    public ContratoEmp getContratoEmp(ContratoEmp contemp);

    public void actualizarContratoEmp(ContratoEmp contemp);
    public void eliminarContratoEmp(ContratoEmp contemp);

}
