package com.balkaned.gladius.services;



import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import java.util.List;

public interface ProcesoPlanillaService {

    public List<ProcesoPlanilla> listar(String text);
    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper);
    ProcesoPlanilla listarPorProcodpro(Integer cod);
}
