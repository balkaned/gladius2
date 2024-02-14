package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Concepto;

import java.util.List;

public interface ConceptoService {
    public List<Concepto> listardet();

    List<Concepto> listConceptos();

    List<Concepto> listarConceptoIns(Integer idProceso);

    void insertarConcepto(Concepto concepto);

    Concepto getById(String id);

    void actualizarConcepto(Concepto concepto);

    public Concepto recuperar(String id);

    public void eliminar(String id);
}
