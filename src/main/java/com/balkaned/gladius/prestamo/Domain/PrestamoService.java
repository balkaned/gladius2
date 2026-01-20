package com.balkaned.gladius.prestamo.Domain;


import com.balkaned.gladius.empleado.Domain.Empleado;

import java.util.List;

public interface PrestamoService {

    public List<PrestamoCab> listarPrestamoCab(Empleado empleado);

    public Integer getIdPrestamoCab(PrestamoCab prestcab);

    public void insertarPrestamoCab(PrestamoCab prestcab);

    public void generacuotasPrestamoCab(PrestamoCab prestcab);

    public PrestamoCab getPrestamoCab(PrestamoCab prestcab);

    public List<PrestamoDet> listarPrestamoDet(PrestamoCab prestcab);

    public void eliminarPrestamoDetAll(PrestamoCab prestcab);

    public void eliminarPrestamoCab(PrestamoCab prestcab);

}
