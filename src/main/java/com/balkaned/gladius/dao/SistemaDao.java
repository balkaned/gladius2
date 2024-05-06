package com.balkaned.gladius.dao;


import com.balkaned.gladius.models.Sistemas;

import java.util.List;

public interface SistemaDao {
    public List<Sistemas> listarSistemas();

    public void insertarSistemas(Sistemas sistema);

    public Sistemas getSistemas(Integer codsis);

    public void actualizarSistemas(Sistemas systema);

    public void eliminarSistemas(Sistemas systema);

}
