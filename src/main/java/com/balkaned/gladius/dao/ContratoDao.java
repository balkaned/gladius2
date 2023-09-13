package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.ContratoEmp;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface ContratoDao {
    public List<ContratoEmp> listarContratoEmp(Empleado empleado);
    public Integer getIdContratoEmp(ContratoEmp contemp);
    public void insertarContratoEmp(ContratoEmp contemp);

}