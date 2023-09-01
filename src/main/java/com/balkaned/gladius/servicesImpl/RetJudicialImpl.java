package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.RetJudicialDao;
import com.balkaned.gladius.services.RetJudicialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RetJudicialImpl implements RetJudicialService {

    @Autowired
    RetJudicialDao dao;
    public List<RetencionJudicial> listarRetencionJudicial(Empleado empleado){return dao.listarRetencionJudicial(empleado);};
    public Integer getIdRetencionJudicial(RetencionJudicial retjud){return dao.getIdRetencionJudicial(retjud);};
    public void insertarRetencionJudicial(RetencionJudicial retjud){dao.insertarRetencionJudicial(retjud);};

}
