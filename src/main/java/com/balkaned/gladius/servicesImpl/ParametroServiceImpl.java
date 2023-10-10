package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ParametrosGen;
import com.balkaned.gladius.dao.ParametroDao;
import com.balkaned.gladius.services.ParametroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParametroServiceImpl implements ParametroService {

    @Autowired
    ParametroDao dao;

    public List<ParametrosGen> listarParametrosGen(){return dao.listarParametrosGen();}
    public void insertarParametrosGen(ParametrosGen par){dao.insertarParametrosGen(par);}
    public ParametrosGen getParametrosGen(String codcon){return dao.getParametrosGen(codcon);}
    public void actualizarParametrosGen(ParametrosGen par){dao.actualizarParametrosGen(par);}

}
