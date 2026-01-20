package com.balkaned.gladius.acumulado.Domain;

import com.balkaned.gladius.acumulado.Infrastructure.AcumuladoDao;
import com.balkaned.gladius.empleado.Domain.EmpAcum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AcumuladoServiceImpl implements AcumuladoService {

    @Autowired
    AcumuladoDao dao;

    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra) {
        return dao.listarEmpAcum(codcia, codtra);
    }

    public void insertarEmpAcum(EmpAcum empacu) {
        dao.insertarEmpAcum(empacu);
    }

    public Integer validarAnioTrib(EmpAcum empacu) {
        return dao.validarAnioTrib(empacu);
    }

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio) {
        return dao.getEmpAcum(codcia, codtra, anio);
    }

    public void actualizarEmpAcum(EmpAcum empacu) {
        dao.actualizarEmpAcum(empacu);
    }

    public void eliminarEmpAcum(EmpAcum empacu) {
        dao.eliminarEmpAcum(empacu);
    }
}
