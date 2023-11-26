package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.*;

import java.util.List;

public interface TurnoDiarioService {
    public List<Turno> listarTurnos(Integer codcia);
    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin);
    public List<Empleado> listarTurMasTra(Integer codcia,  String fecini, String fecfin );
    public void insertarTurno(Turno turno);
    public Turno getTurno (Integer codcia, Integer codturno);
    public void actualizarTurno(Turno turno);
}
