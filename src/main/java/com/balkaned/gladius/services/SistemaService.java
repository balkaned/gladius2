package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Sistemas;
import java.util.List;

public interface SistemaService {
    public List<Sistemas> listarSistemas();
    public void insertarSistemas(Sistemas sistemas);
}
