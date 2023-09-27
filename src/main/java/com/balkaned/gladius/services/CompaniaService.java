package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.Compania;

import java.util.List;

public interface CompaniaService {

    public Compania getCompaniaAll(Integer id);
    public void logoCompania(Compania com);
    public List<Compania> listarTodo();
    public void insertarCompania(Compania com);
}
