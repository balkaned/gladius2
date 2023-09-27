package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Compania;

import java.util.List;

public interface CompaniaDao {

    public Compania getCompaniaAll(Integer codcia);
    public void logoCompania(Compania com);
    public List<Compania> listarTodo();
    public void insertarCompania(Compania com);
}
