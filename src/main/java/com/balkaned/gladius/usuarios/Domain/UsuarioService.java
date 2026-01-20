package com.balkaned.gladius.usuarios.Domain;


import java.util.List;

public interface UsuarioService {
    public List<Usuario> listar(String text, Integer pag, Integer numregs);

    public void insertar(Usuario usuario);

    public Usuario recuperar(Integer id);

    public void actualizar(Usuario usuario);

    public void eliminar(Integer id);

}
