package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.ParametrosGen;
import java.util.List;

public interface ParametroDao {
    public List<ParametrosGen> listarParametrosGen();
    public void insertarParametrosGen(ParametrosGen par);

}
