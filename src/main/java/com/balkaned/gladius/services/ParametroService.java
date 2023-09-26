package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ParametrosGen;
import java.util.List;

public interface ParametroService {

    public List<ParametrosGen> listarParametrosGen();
    public void insertarParametrosGen(ParametrosGen par);

}
