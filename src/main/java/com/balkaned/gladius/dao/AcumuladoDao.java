package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.EmpAcum;
import java.util.List;

public interface AcumuladoDao {
    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra);
    public void insertarEmpAcum(EmpAcum empacu);

}
