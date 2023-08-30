package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.ContratoEmp;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface ContratoService {

    public List<ContratoEmp> listarContratoEmp(Empleado empleado);
    public Integer getIdContratoEmp(ContratoEmp contemp);
    public void insertarContratoEmp(ContratoEmp contemp);

}
