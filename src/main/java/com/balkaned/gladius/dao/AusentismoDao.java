package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.Empleado;
import java.util.List;

public interface AusentismoDao {
    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado);
    public List<AusentismoProgramacion> listaAusentismoGen(Integer codcia, String regimen, String fecini , String fecfin , Integer codtra );
    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg);
    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin , Integer iexcorrel);
    public void insertarAusentismoPrg(AusentismoProgramacion ausprg);
    public AusentismoProgramacion getAusentismoPrg(AusentismoProgramacion ausprg);
    public void actualizarAusentismoPrg (AusentismoProgramacion ausprg);

}
