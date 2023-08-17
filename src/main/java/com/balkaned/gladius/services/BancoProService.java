package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.BancoPro;

import java.util.List;

public interface BancoProService {

    public List<BancoPro> listarBancoPro(Integer codcia, String text);
    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco);
    public void insertarBancoPro(BancoPro bancopro);
}
