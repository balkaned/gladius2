package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.beans.Turno;
import com.balkaned.gladius.beans.Turnodiario;

import java.util.List;

public interface TurnoDiarioService {
    public List<Turno> listarTurnos(Integer codcia);
    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin);

}
