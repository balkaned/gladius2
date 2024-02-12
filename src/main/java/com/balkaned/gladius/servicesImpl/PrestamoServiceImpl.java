package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.PrestamoCab;
import com.balkaned.gladius.beans.PrestamoDet;
import com.balkaned.gladius.dao.PrestamoDao;
import com.balkaned.gladius.services.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrestamoServiceImpl implements PrestamoService {

    @Autowired
    PrestamoDao dao;

    public List<PrestamoCab> listarPrestamoCab(Empleado empleado) {
        return dao.listarPrestamoCab(empleado);
    }

    public Integer getIdPrestamoCab(PrestamoCab prestcab) {
        return dao.getIdPrestamoCab(prestcab);
    }

    public void insertarPrestamoCab(PrestamoCab prestcab) {
        dao.insertarPrestamoCab(prestcab);
    }

    public void generacuotasPrestamoCab(PrestamoCab prestcab) {
        dao.generacuotasPrestamoCab(prestcab);
    }

    public PrestamoCab getPrestamoCab(PrestamoCab prestcab) {
        return dao.getPrestamoCab(prestcab);
    }

    public List<PrestamoDet> listarPrestamoDet(PrestamoCab prestcab) {
        return dao.listarPrestamoDet(prestcab);
    }

    public void eliminarPrestamoDetAll(PrestamoCab prestcab) {
        dao.eliminarPrestamoDetAll(prestcab);
    }

    public void eliminarPrestamoCab(PrestamoCab prestcab) {
        dao.eliminarPrestamoCab(prestcab);
    }

}
