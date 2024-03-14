package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.*;

import java.util.List;

public interface ProcesoPlanillaDao {
    public List<ProcesoPlanilla> listar(String text);
    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper);
    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes);
    public void insertarProper(ProcesoPeriodo pperiodo);
    public ProcesoPeriodo recuperarPeriodo2(Integer codcia, Integer idproceso, String pperiodo);
    public void actualizarProper(ProcesoPeriodo pperiodo);
    public ProcesoPlanillaxCia recuperar_reporte(Integer codcia, Integer codpro);

}
