package com.balkaned.gladius.sistemas.Infrastructure;


import com.balkaned.gladius.sistemas.Domain.Sistemas;

import java.util.List;

public interface SistemaDao {
    public List<Sistemas> listarSistemas();

    public void insertarSistemas(Sistemas sistema);

    public Sistemas getSistemas(Integer codsis);

    public void actualizarSistemas(Sistemas systema);

    public void eliminarSistemas(Sistemas systema);

}
