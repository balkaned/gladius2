package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Concepto;

import java.util.List;

public interface ConceptoDao {
    public List<Concepto> listardet();

    List<Concepto> listConceptos();

    List<Concepto> listarConceptoIns(Integer idProceso);

    void insertarConcepto(Concepto concepto);

    Concepto getById(String id);

    void actualizarConcepto(Concepto concepto);

    public Concepto recuperar(String id);
}
