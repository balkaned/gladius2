package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;

import java.util.List;

public interface RetJudicialDao {
    public List<RetencionJudicial> listarRetencionJudicial(Empleado empleado);

    public Integer getIdRetencionJudicial(RetencionJudicial retjud);

    public void insertarRetencionJudicial(RetencionJudicial retjud);

    public RetencionJudicial getRetencionJudicial(RetencionJudicial retjud);

    public void actualizarRetencionJudicial(RetencionJudicial retjud);

    public void eliminarRetencionJudicial(RetencionJudicial retjud);

}
