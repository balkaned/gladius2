package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Usuario;
import java.util.List;

public interface UsuarioDao {
    public List<Usuario> listar(String text, Integer pag, Integer numregs);
    public void insertar(Usuario usuario);

}
