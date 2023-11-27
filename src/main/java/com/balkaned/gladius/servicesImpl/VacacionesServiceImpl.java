package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.VacacionesDao;
import com.balkaned.gladius.services.VacacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacacionesServiceImpl implements VacacionesService {
    @Autowired
    VacacionesDao dao;

    public List<VacacionControl> listarVacacionesCtl(Empleado empleado) {
        return dao.listarVacacionesCtl(empleado);
    }

    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin) {
        return dao.listarVacacionesPer(empleado, perini, perfin);
    }

    public List<VacacionProgramacion> listaVacacionesGen(Integer codcia, String regimen, String fecini, String fecfin, Integer codtra) {
        return dao.listaVacacionesGen(codcia, regimen, fecini, fecfin, codtra);
    }

    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen) {
        return dao.listaTrabajadoresReg(codcia, regimen);
    }

    public List<VacacionControl> listaSaldoVacTra(Integer codcia, String regimen, Integer codtra) {
        return dao.listaSaldoVacTra(codcia, regimen, codtra);
    }

    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin) {
        return dao.saldotraVac(codcia, codtra, perini, perfin);
    }

    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin) {
        return dao.validaVac(codcia, codtra, fecini, fecfin);
    }

    public Integer getIdVacacionPrg(VacacionProgramacion vacprg) {
        return dao.getIdVacacionPrg(vacprg);
    }

    public void insertarVacacionPrg(VacacionProgramacion vacprg) {
        dao.insertarVacacionPrg(vacprg);
    }

    public void procesaVacacionCtl(Empleado empleado) {
        dao.procesaVacacionCtl(empleado);
    }

    public void actualizarVacacionPrg(VacacionProgramacion vacprg) {
        dao.actualizarVacacionPrg(vacprg);
    }

    public VacacionProgramacion getVacacionPrg(VacacionProgramacion vacprg) {
        return dao.getVacacionPrg(vacprg);
    }
}
