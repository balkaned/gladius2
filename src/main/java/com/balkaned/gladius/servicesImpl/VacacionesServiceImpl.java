package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.SueldoDao;
import com.balkaned.gladius.dao.VacacionesDao;
import com.balkaned.gladius.services.SueldoService;
import com.balkaned.gladius.services.VacacionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacacionesServiceImpl implements VacacionesService {
    @Autowired
    VacacionesDao dao;

    public List<VacacionControl> listarVacacionesCtl(Empleado empleado){return dao.listarVacacionesCtl(empleado);}
    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin){return dao.listarVacacionesPer(empleado,perini,perfin);}
    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin){return dao.saldotraVac(codcia,codtra,perini,perfin);}
}
