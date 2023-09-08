package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.DerechoHabiente;
import com.balkaned.gladius.dao.DerechoHabientesDao;
import com.balkaned.gladius.services.DerechoHabientesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DerechoHabientesServiceImpl implements DerechoHabientesService {

    @Autowired
    DerechoHabientesDao dao;
    public List<DerechoHabiente> listar(Integer codcia, Integer codtra){return dao.listar(codcia,codtra);}
    public Integer validarDerhabiente(DerechoHabiente derhab){return dao.validarDerhabiente(derhab);}
    public Integer getIdDerechoHab(DerechoHabiente derhab){return dao.getIdDerechoHab(derhab);}
    public void insertar(DerechoHabiente derhab){dao.insertar(derhab);}

}
