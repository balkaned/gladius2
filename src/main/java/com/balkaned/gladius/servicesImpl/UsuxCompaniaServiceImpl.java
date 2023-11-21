package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Usuario;
import com.balkaned.gladius.beans.UsuxCompania;
import com.balkaned.gladius.dao.UsuarioDao;
import com.balkaned.gladius.dao.UsuxCompaniaDao;
import com.balkaned.gladius.services.UsuarioService;
import com.balkaned.gladius.services.UsuxCompaniaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuxCompaniaServiceImpl implements UsuxCompaniaService {

    @Autowired
    UsuxCompaniaDao dao;

    public List<UsuxCompania> listar(Integer codusu) {
        return dao.listar(codusu);
    }

    public List<Empleado> listaTrabajadoresCia(Integer codcia) {
        return dao.listaTrabajadoresCia(codcia);
    }

    public void insertar(UsuxCompania usuxcia) {
        dao.insertar(usuxcia);
    }

    public void eliminar(UsuxCompania usuxcia) {
        dao.eliminar(usuxcia);
    }


}
