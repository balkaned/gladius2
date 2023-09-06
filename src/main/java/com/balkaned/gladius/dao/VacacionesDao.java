package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.*;
import java.util.List;

public interface VacacionesDao {
    public List<VacacionControl> listarVacacionesCtl(Empleado empleado);
    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin);
    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin);
    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin);
    public Integer getIdVacacionPrg(VacacionProgramacion vacprg);
    public void insertarVacacionPrg(VacacionProgramacion vacprg);
    public void procesaVacacionCtl(Empleado empleado);

}
