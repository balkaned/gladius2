package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.dao.AusentismoDao;
import com.balkaned.gladius.services.AusentismoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AusentismoServiceImpl implements AusentismoService {

    @Autowired
    AusentismoDao dao;

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado){return dao.listarAusentismoPrg(empleado);}
    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg){return dao.getIdAusentismoPrg(ausprg);}
    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin , Integer iexcorrel){return dao.validaAus(codcia,codtra,fecini,fecfin,iexcorrel);}
    public void insertarAusentismoPrg(AusentismoProgramacion ausprg){dao.insertarAusentismoPrg(ausprg);}

}
