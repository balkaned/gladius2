package com.balkaned.gladius.concepto.Infrastructure;


import com.balkaned.gladius.concepto.Domain.Concepto;

import java.util.List;

public interface ConceptoDao {
    public List<Concepto> listardet();

    List<Concepto> listConceptos();

    List<Concepto> listarConceptoIns(Integer idProceso);

    void insertarConcepto(Concepto concepto);

    Concepto getById(String id);

    void actualizarConcepto(Concepto concepto);

    public Concepto recuperar(String id);

    public void eliminar(String id);
}
