package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.EmpAcum;

import java.util.List;

public interface AcumuladoDao {
    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra);

    public void insertarEmpAcum(EmpAcum empacu);

    public Integer validarAnioTrib(EmpAcum empacu);

    public EmpAcum getEmpAcum(Integer codcia, Integer codtra, String anio);

    public void actualizarEmpAcum(EmpAcum empacu);
    public void eliminarEmpAcum(EmpAcum empacu);

}
