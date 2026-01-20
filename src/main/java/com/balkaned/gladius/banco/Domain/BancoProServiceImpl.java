package com.balkaned.gladius.banco.Domain;

import com.balkaned.gladius.banco.Infrastructure.BancoProDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoProServiceImpl implements BancoProService {

    @Autowired
    BancoProDao dao;

    public List<BancoPro> listarBancoPro(Integer codcia, String text) {
        return dao.listarBancoPro(codcia, text);
    }

    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco) {
        return dao.getBancoPro(codcia, codpro, banco);
    }

    public void insertarBancoPro(BancoPro bancopro) {
        dao.insertarBancoPro(bancopro);
    }

    public void actualizarBancoPro(BancoPro bancopro) {
        dao.actualizarBancoPro(bancopro);
    }

    public void eliminarBancoPro(BancoPro bancopro) {
        dao.eliminarBancoPro(bancopro);
    }

}
