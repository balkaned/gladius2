package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.TTablaCabecera;
import com.balkaned.gladius.beans.TTablaDetalle;

import java.util.List;

public interface TtableDao {
    public List<TTablaCabecera> listarTTablac(String text);

    public void insertarTtablac(TTablaCabecera ttc);

    public TTablaCabecera recuperarTTablac(String idttabla);

    public void actualizarTTablac(TTablaCabecera ttc);

    public List<TTablaDetalle> listarTTablad(String idttabla);

    public void actualizarTTablad(TTablaDetalle ttd);
    public TTablaDetalle recuperarTTablad(String idttabla, String idttabladet);

}
