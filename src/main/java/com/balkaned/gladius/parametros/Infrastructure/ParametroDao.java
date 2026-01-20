package com.balkaned.gladius.parametros.Infrastructure;


import com.balkaned.gladius.parametros.Domain.ParametrosGen;

import java.util.List;

public interface ParametroDao {
    public List<ParametrosGen> listarParametrosGen();

    public void insertarParametrosGen(ParametrosGen par);

    public ParametrosGen getParametrosGen(String codcon);

    public void actualizarParametrosGen(ParametrosGen par);

    public void eliminarParametrosGen(ParametrosGen par);
}
