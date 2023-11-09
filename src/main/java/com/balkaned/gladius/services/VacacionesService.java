package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionControl;
import com.balkaned.gladius.beans.VacacionProgramacion;

import java.util.List;

public interface VacacionesService {
    public List<VacacionControl> listarVacacionesCtl(Empleado empleado);
    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin);
    public List<VacacionProgramacion> listaVacacionesGen(Integer codcia,  String regimen, String fecini , String fecfin , Integer codtra);
    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen  );
    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin);
    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin);
    public Integer getIdVacacionPrg(VacacionProgramacion vacprg);
    public void insertarVacacionPrg(VacacionProgramacion vacprg);
    public void procesaVacacionCtl(Empleado empleado);

}
