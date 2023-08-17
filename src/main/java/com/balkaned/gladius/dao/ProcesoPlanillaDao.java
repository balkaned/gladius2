package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.ProcesoPlanilla;
import java.util.List;

public interface ProcesoPlanillaDao {
    public List<ProcesoPlanilla> listar(String text);
}
