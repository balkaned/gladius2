package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.CentroCosto;

import java.util.List;

public interface CcostoService {

    public List<CentroCosto> listarCentroCosto(Integer codcia, String text);

    public CentroCosto getCentroCosto(Integer codcia, String codccosto);

    public Integer getIdCentroCosto(Integer codcia);

    public void insertarCentroCosto(CentroCosto ccosto);

    public void actualizarCentroCosto(CentroCosto ccosto);

    public void eliminarCentroCosto(CentroCosto ccosto);
}
