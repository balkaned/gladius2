package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionControl;
import com.balkaned.gladius.beans.VacacionProgramacion;

import java.util.List;

public interface VacacionesService {
    public List<VacacionControl> listarVacacionesCtl(Empleado empleado);
    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin);
    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin);

}
