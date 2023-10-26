package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Concepto;

import java.util.List;

public interface ConceptoDao {
    public List<Concepto> listardet();

    List<Concepto> listConceptos();

    void insertarConcepto(Concepto concepto);

    Concepto getById(String id);

    void actualizarConcepto(Concepto concepto);
}
