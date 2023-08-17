package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.dao.BancoProDao;
import com.balkaned.gladius.services.BancoProService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoProServiceImpl implements BancoProService {

    @Autowired
    BancoProDao dao;

    public List<BancoPro> listarBancoPro(Integer codcia, String text){
        return dao.listarBancoPro(codcia,text);
    }
    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco){
        return dao.getBancoPro(codcia,codpro,banco);
    }
    public void insertarBancoPro(BancoPro bancopro){dao.insertarBancoPro(bancopro);}

}
