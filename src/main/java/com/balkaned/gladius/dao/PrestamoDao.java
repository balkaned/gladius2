package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
import java.util.List;

public interface PrestamoDao {
    public List<PrestamoCab> listarPrestamoCab(Empleado empleado);
    public Integer getIdPrestamoCab(PrestamoCab prestcab);
    public void insertarPrestamoCab(PrestamoCab prestcab);
    public void generacuotasPrestamoCab(PrestamoCab prestcab);

}
