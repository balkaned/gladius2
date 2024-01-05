package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import java.util.List;

public interface ProcesoPlanillaDao {
    public List<ProcesoPlanilla> listar(String text);
    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper);
    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes);
}
