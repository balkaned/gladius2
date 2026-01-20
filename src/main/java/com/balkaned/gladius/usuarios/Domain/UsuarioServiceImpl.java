package com.balkaned.gladius.usuarios.Domain;

import com.balkaned.gladius.usuarios.Infrastructure.UsuarioDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDao dao;

    public List<Usuario> listar(String text, Integer pag, Integer numregs) {
        return dao.listar(text, pag, numregs);
    }

    public void insertar(Usuario usuario) {
        dao.insertar(usuario);
    }

    public Usuario recuperar(Integer id) {
        return dao.recuperar(id);
    }

    public void actualizar(Usuario usuario) {
        dao.actualizar(usuario);
    }

    public void eliminar(Integer id) {
        dao.eliminar(id);
    }

}
