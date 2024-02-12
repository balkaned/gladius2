package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
import com.balkaned.gladius.beans.PrestamoDet;

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
