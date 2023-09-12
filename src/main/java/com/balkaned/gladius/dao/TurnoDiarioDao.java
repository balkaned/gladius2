package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Turno;
import com.balkaned.gladius.beans.Turnodiario;
import java.util.List;

public interface TurnoDiarioDao {
    public List<Turno> listarTurnos(Integer codcia);
    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin);

}
