package com.balkaned.gladius.services;



import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import java.util.List;

public interface ProcesoPlanillaService {

    public List<ProcesoPlanilla> listar(String text);
    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper);
    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes);
    public void insertarProper(ProcesoPeriodo pperiodo);
    public ProcesoPeriodo recuperarPeriodo2(Integer codcia, Integer idproceso, String pperiodo);
    public void actualizarProper(ProcesoPeriodo pperiodo);
}
