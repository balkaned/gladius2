package com.balkaned.gladius.prestamo.Infrastructure;

import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.prestamo.Domain.PrestamoCab;
import com.balkaned.gladius.prestamo.Domain.PrestamoDet;

import java.util.List;

public interface PrestamoDao {
    public List<PrestamoCab> listarPrestamoCab(Empleado empleado);

    public Integer getIdPrestamoCab(PrestamoCab prestcab);

    public void insertarPrestamoCab(PrestamoCab prestcab);

    public void generacuotasPrestamoCab(PrestamoCab prestcab);

    public PrestamoCab getPrestamoCab(PrestamoCab prestcab);

    public List<PrestamoDet> listarPrestamoDet(PrestamoCab prestcab);

    public void eliminarPrestamoDetAll(PrestamoCab prestcab);

    public void eliminarPrestamoCab(PrestamoCab prestcab);

}
