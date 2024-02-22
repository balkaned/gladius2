package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.ConceptoxAgrup;
import com.balkaned.gladius.beans.ConceptoxProms;
import com.balkaned.gladius.dao.ConceptoXProcesoDao;
import com.balkaned.gladius.services.ConceptoXProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoXProcesoServiceImpl implements ConceptoXProcesoService {
    @Autowired
    ConceptoXProcesoDao dao;

    @Override
    public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {
        return dao.listarTipconCtb(xcodcia, idProceso, slc_grpconcepto);
    }

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto) {
        return dao.recuperar(idproceso, idconcepto);
    }

    public void eliminar(Integer idproceso, String idconcepto) {

        dao.eliminar(idproceso, idconcepto);
    }

    public List<ConceptoxProms> listarPromCon(Integer idproceso, String idconcepto) {
        return dao.listarPromCon(idproceso, idconcepto);
    }

    public List<ConceptoXProceso> listar(Integer idproceso, String text) {
        return dao.listar(idproceso, text);
    }

    public void insertarProm(ConceptoxProms conxproms) {

        dao.insertarProm(conxproms);
    }

    public void eliminaProm(ConceptoxProms conxproms) {

        dao.eliminaProm(conxproms);
    }

    public List<ConceptoxAgrup> listarAgrupCon(Integer idproceso, String idconcepto) {
        return dao.listarAgrupCon(idproceso, idconcepto);
    }

    public void insertarAgrup(ConceptoxAgrup conxagrup){
        dao.insertarAgrup(conxagrup);
    }

    public void eliminaAgrup(ConceptoxAgrup conxagrup){
        dao.eliminaAgrup(conxagrup);
    }
}
