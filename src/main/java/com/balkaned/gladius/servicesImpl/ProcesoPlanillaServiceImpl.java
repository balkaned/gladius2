package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcesoPlanillaServiceImpl implements ProcesoPlanillaService {

    @Autowired
    ProcesoPlanillaDao dao;

    public List<ProcesoPlanilla> listar(String text) {
        return dao.listar(text);
    }

    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper) {
        return dao.listarAsieCab(codcia, codpro, nroper);
    }

    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes) {
        return dao.listarProRegpla(codcia, regpla, permes);
    }

    public void insertarProper(ProcesoPeriodo pperiodo){
        dao.insertarProper(pperiodo);
    }

}
