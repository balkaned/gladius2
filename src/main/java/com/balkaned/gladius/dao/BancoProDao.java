package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.BancoPro;
import java.util.List;

public interface BancoProDao {
    public List<BancoPro> listarBancoPro(Integer codcia, String text);
    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco);
}
