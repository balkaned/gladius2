package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.DerechoHabiente;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface DerechoHabientesDao {
    public List<DerechoHabiente> listar(Integer codcia, Integer codtra);
    public Integer validarDerhabiente(DerechoHabiente derhab);
    public Integer getIdDerechoHab(DerechoHabiente derhab);
    public void insertar(DerechoHabiente derhab);

}
