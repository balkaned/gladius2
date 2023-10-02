package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Sistemas;
import java.util.List;

public interface SistemaDao {
    public List<Sistemas> listarSistemas();
    public void insertarSistemas(Sistemas sistema);

}
