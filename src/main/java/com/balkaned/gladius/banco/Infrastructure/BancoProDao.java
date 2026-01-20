package com.balkaned.gladius.banco.Infrastructure;

import com.balkaned.gladius.banco.Domain.BancoPro;

import java.util.List;

public interface BancoProDao {
    public List<BancoPro> listarBancoPro(Integer codcia, String text);

    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco);

    public void insertarBancoPro(BancoPro bancopro);

    public void actualizarBancoPro(BancoPro bancopro);

    public void eliminarBancoPro(BancoPro bancopro);
}
