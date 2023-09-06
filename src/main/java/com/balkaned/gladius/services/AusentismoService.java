package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.Empleado;

import java.util.List;

public interface AusentismoService {

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado);
    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg);
    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin , Integer iexcorrel);
    public void insertarAusentismoPrg(AusentismoProgramacion ausprg);

}
