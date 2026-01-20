package com.balkaned.gladius.ausentismo.Infrastructure;


import com.balkaned.gladius.ausentismo.Domain.AusentismoProgramacion;
import com.balkaned.gladius.empleado.Domain.Empleado;
import java.util.List;

public interface AusentismoDao {
    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado);
    public List<AusentismoProgramacion> listaAusentismoGen(Integer codcia, String regimen, String fecini , String fecfin , Integer codtra );
    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg);
    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin , Integer iexcorrel);
    public void insertarAusentismoPrg(AusentismoProgramacion ausprg);
    public AusentismoProgramacion getAusentismoPrg(AusentismoProgramacion ausprg);
    public void actualizarAusentismoPrg (AusentismoProgramacion ausprg);
    public void  eliminarAusentismoPrg(AusentismoProgramacion ausprg);

}
