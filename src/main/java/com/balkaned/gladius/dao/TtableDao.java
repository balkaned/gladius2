package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.TTablaCabecera;
import java.util.List;

public interface TtableDao {
    public List<TTablaCabecera> listarTTablac(String text);

    public void insertarTtablac(TTablaCabecera ttc);

}
