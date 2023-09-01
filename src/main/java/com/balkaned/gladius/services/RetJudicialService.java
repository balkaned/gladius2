package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import java.util.List;

public interface RetJudicialService {

    public List<RetencionJudicial> listarRetencionJudicial(Empleado empleado);
    public Integer getIdRetencionJudicial(RetencionJudicial retjud);
    public void insertarRetencionJudicial(RetencionJudicial retjud);

}
