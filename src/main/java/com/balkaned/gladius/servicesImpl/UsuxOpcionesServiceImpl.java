package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuxCompania;
import com.balkaned.gladius.beans.UsuxOpciones;
import com.balkaned.gladius.dao.UsuxCompaniaDao;
import com.balkaned.gladius.dao.UsuxOpcionesDao;
import com.balkaned.gladius.services.UsuxCompaniaService;
import com.balkaned.gladius.services.UsuxOpcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuxOpcionesServiceImpl implements UsuxOpcionesService {

    @Autowired
    UsuxOpcionesDao dao;

    public UsuxOpciones ObtieneAccesoOpcion(Integer codcia, Integer codusu, Integer codopc) {
        return dao.ObtieneAccesoOpcion(codcia, codusu, codopc);
    }

    public List<UsuxOpciones> listarOpciones(Integer codcia, Integer codusu, Integer codsys){
        return dao.listarOpciones(codcia,codusu,codsys);
    }


}
