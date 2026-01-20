package com.balkaned.gladius.afp.Domain;

import com.balkaned.gladius.afp.Infrastructure.AfpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AfpServiceImpl implements AfpService {

    @Autowired
    AfpDao dao;

    public List<Afp> listar(String text){
        return dao.listar(text);
    }

    public void insertar(Afp afp){
        dao.insertar(afp);
    }

    public Afp recuperar(Afp afp){
        return dao.recuperar(afp);
    }

    public void actualizar(Afp afp){
        dao.actualizar(afp);
    }

    public void eliminar(Afp afp){
        dao.eliminar(afp);
    }

    public void insertarDuplicado(String perini, String perfin2){
        dao.insertarDuplicado(perini,perfin2);
    }

}
