package com.balkaned.gladius.locales.Domain;

import com.balkaned.gladius.locales.Infrastructure.LocalDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalServiceImpl implements LocalService {
    @Autowired
    LocalDao dao;

    public List<Local> listarLocales(Integer codcia, String text) {
        return dao.listarLocales(codcia, text);
    }

    public Local getLocales(Integer codcia, String codarea) {
        return dao.getLocales(codcia, codarea);
    }

    public Integer getIdUbicaion(Integer codcia) {
        return dao.getIdUbicaion(codcia);
    }

    public void insertarUbicacion(Local ubic) {
        dao.insertarUbicacion(ubic);
    }

    public void actualizarUbicaion(Local ubic) {
        dao.actualizarUbicaion(ubic);
    }

    public void eliminarUbicacion(Local ubic) {
        dao.eliminarUbicacion(ubic);
    }

}
