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

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado) {
        return dao.listarAusentismoPrg(empleado);
    }

    public List<AusentismoProgramacion> listaAusentismoGen(Integer codcia, String regimen, String fecini, String fecfin, Integer codtra) {
        return dao.listaAusentismoGen(codcia, regimen, fecini, fecfin, codtra);
    }

    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg) {
        return dao.getIdAusentismoPrg(ausprg);
    }

    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin, Integer iexcorrel) {
        return dao.validaAus(codcia, codtra, fecini, fecfin, iexcorrel);
    }

    public void insertarAusentismoPrg(AusentismoProgramacion ausprg) {
        dao.insertarAusentismoPrg(ausprg);
    }

    public AusentismoProgramacion getAusentismoPrg(AusentismoProgramacion ausprg) {
        return dao.getAusentismoPrg(ausprg);
    }


    public void actualizarAusentismoPrg(AusentismoProgramacion ausprg) {
        dao.actualizarAusentismoPrg(ausprg);
    }


    public void eliminarAusentismoPrg(AusentismoProgramacion ausprg) {
        dao.eliminarAusentismoPrg(ausprg);
    }

}
