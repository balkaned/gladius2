package com.balkaned.gladius.banco.Domain;


import java.util.List;

public interface BancoProService {

    public List<BancoPro> listarBancoPro(Integer codcia, String text);

    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco);

    public void insertarBancoPro(BancoPro bancopro);

    public void actualizarBancoPro(BancoPro bancopro);

    public void eliminarBancoPro(BancoPro bancopro);
}
