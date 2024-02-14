package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.dao.ConceptoDao;
import com.balkaned.gladius.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoServiceImpl implements ConceptoService {
    @Autowired
    ConceptoDao dao;

    public List<Concepto> listardet() {
        return dao.listardet();
    }

    @Override
    public List<Concepto> listConceptos() {
        return dao.listConceptos();
    }

    @Override
    public List<Concepto> listarConceptoIns(Integer idProceso) {
        return dao.listarConceptoIns(idProceso);
    }

    @Override
    public void insertarConcepto(Concepto concepto) {
        dao.insertarConcepto(concepto);
    }

    @Override
    public Concepto getById(String id) {
        return dao.getById(id);
    }

    @Override
    public void actualizarConcepto(Concepto concepto) {
        dao.actualizarConcepto(concepto);
    }

    public Concepto recuperar(String id) {
        return dao.recuperar(id);
    }

    public void eliminar(String id){
        dao.eliminar(id);
    }
}
