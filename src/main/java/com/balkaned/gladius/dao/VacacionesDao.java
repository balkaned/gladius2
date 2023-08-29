package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.*;
import java.util.List;

public interface VacacionesDao {
    public List<VacacionControl> listarVacacionesCtl(Empleado empleado);
    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin);
    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin);

}
