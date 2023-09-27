package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Usuario;
import com.balkaned.gladius.dao.UsuarioDao;
import com.balkaned.gladius.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioDao dao;

    public List<Usuario> listar(String text, Integer pag, Integer numregs){return dao.listar(text,pag,numregs);}
    public void insertar(Usuario usuario){dao.insertar(usuario);}

}
