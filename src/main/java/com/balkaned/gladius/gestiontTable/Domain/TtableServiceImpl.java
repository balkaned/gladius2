package com.balkaned.gladius.gestiontTable.Domain;


import com.balkaned.gladius.gestiontTable.Infrastructure.TtableDao;
import com.balkaned.gladius.models.TTablaCabecera;
import com.balkaned.gladius.models.TTablaDetalle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TtableServiceImpl implements TtableService {

    @Autowired
    TtableDao dao;

    public List<TTablaCabecera> listarTTablac(String text) {

        return dao.listarTTablac(text);
    }

    public void insertarTtablac(TTablaCabecera ttc) {

        dao.insertarTtablac(ttc);
    }

    public TTablaCabecera recuperarTTablac(String idttabla) {
        return dao.recuperarTTablac(idttabla);
    }

    public void actualizarTTablac(TTablaCabecera ttc) {

        dao.actualizarTTablac(ttc);
    }

    public List<TTablaDetalle> listarTTablad(String idttabla) {

        return dao.listarTTablad(idttabla);
    }

    public void actualizarTTablad(TTablaDetalle ttd) {
        dao.actualizarTTablad(ttd);
    }

    public TTablaDetalle recuperarTTablad(String idttabla, String idttabladet) {
        return dao.recuperarTTablad(idttabla, idttabladet);
    }

    public void eliminarTTablac(String idttabla) {

        dao.eliminarTTablac(idttabla);
    }

    public void eliminarTTablad(String idttabla) {

        dao.eliminarTTablad(idttabla);
    }

    public void eliminarTTablade(String idttabla, String idttabladet) {

        dao.eliminarTTablade(idttabla, idttabladet);
    }

    public void insertarTtablad(TTablaDetalle ttd) {
        dao.insertarTtablad(ttd);
    }
}
