package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.CentroCosto;
import java.util.List;

public interface CcostoDao {
    public List<CentroCosto> listarCentroCosto(Integer codcia, String text);
    public CentroCosto getCentroCosto(Integer codcia, String codccosto);
    public Integer getIdCentroCosto(Integer codcia);
    public void insertarCentroCosto(CentroCosto ccosto);
}
