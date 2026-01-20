package com.balkaned.gladius.sistemas.Domain;


import java.util.List;

public interface SistemaService {
    public List<Sistemas> listarSistemas();

    public void insertarSistemas(Sistemas sistemas);

    public Sistemas getSistemas(Integer codsis);

    public void actualizarSistemas(Sistemas systema);

    public void eliminarSistemas(Sistemas systema);
}
