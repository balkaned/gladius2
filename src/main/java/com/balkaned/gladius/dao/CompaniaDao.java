package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Compania;

public interface CompaniaDao {

    public Compania getCompaniaAll(Integer codcia);
    public void logoCompania(Compania com);
}
