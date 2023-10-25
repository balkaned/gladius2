package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.TTablaCabecera;
import java.util.List;

public interface TtableService {

    public List<TTablaCabecera> listarTTablac(String text);

    public void insertarTtablac(TTablaCabecera ttc);

}
