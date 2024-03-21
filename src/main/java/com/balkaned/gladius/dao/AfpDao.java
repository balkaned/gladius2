package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.Afp;
import java.util.List;

public interface AfpDao {
    public List<Afp> listar(String text);
    public void insertar(Afp afp);
    public Afp recuperar(Afp afp);
    public void actualizar(Afp afp);
    public void eliminar(Afp afp);
    public void insertarDuplicado(String perini, String perfin2);

}
