package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.EmpAcum;
import java.util.List;

public interface AcumuladoService {
    public List<EmpAcum> listarEmpAcum(Integer codcia, Integer codtra);
    public void insertarEmpAcum(EmpAcum empacu);

}
