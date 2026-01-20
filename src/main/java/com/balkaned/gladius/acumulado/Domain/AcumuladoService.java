package com.balkaned.gladius.acumulado.Domain;


import com.balkaned.gladius.empleado.Domain.EmpAcum;

import java.util.List;

public interface AcumuladoService {
    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra);

    public void insertarEmpAcum(EmpAcum empacu);

    public Integer validarAnioTrib(EmpAcum empacu);

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio);

    public void actualizarEmpAcum(EmpAcum empacu);

    public void eliminarEmpAcum(EmpAcum empacu);

}
