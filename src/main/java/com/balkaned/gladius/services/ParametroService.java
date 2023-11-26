package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ParametrosGen;
import org.springframework.web.servlet.tags.Param;

import java.util.List;

public interface ParametroService {

    public List<ParametrosGen> listarParametrosGen();

    public void insertarParametrosGen(ParametrosGen par);

    public ParametrosGen getParametrosGen(String codcon);

    public void actualizarParametrosGen(ParametrosGen par);

    public void eliminarParametrosGen(ParametrosGen par);

}
