package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.EmpSueldo;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.SueldoDao;
import com.balkaned.gladius.services.SueldoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SueldoServiceImpl implements SueldoService {
    @Autowired
    SueldoDao dao;

    public List<EmpSueldo> obtenerEmpSueldo(Empleado empleado){return dao.obtenerEmpSueldo(empleado);}
    public List<Concepto> ListConceptos(Integer codcia, String Tipo){return dao.ListConceptos(codcia,Tipo);}
    public void insertarEmpSueldo(EmpSueldo empsueldo){dao.insertarEmpSueldo(empsueldo);}

}
