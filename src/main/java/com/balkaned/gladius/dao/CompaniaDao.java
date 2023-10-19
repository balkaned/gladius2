package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Ciaxcon;
import com.balkaned.gladius.beans.Compania;
import java.util.List;

public interface CompaniaDao {

    public Compania getCompaniaAll(Integer codcia);
    public void logoCompania(Compania com);
    public List<Compania> listarTodo();
    public void insertarCompania(Compania com);
    public Compania getCompania(Integer codcia);
    public List<Ciaxcon> listarCiaxcon(Integer codcia, String flgtipreg);
    public void actualizarCompania(Compania com);
    public void insertarCiaxcon(Integer codcia,String codcon,String tipreg);
    public void deleteCiaxcon(Integer codcia,String codcon);
}
