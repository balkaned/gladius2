package com.balkaned.gladius.services;


import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.models.*;
import java.util.List;

public interface TurnoDiarioService {

    public List<Turno> listarTurnosModalAsis(Integer codcia, String fecini);

    public List<Turno> listarTurnos(Integer codcia);

    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin);

    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin);

    public void insertarTurno(Turno turno);

    public Turno getTurno(Integer codcia, Integer codturno);

    public void actualizarTurno(Turno turno);

    public void eliminarTurno(Turno turno);

    public Turnodiario obtenerTurnoDia(Integer codcia, Integer codtra, String codfec);

    public void actualizaTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu);

    public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu);

    public void programarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu);

    public void marcacionesTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu);

    public void eliminaTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu);

    public void consolidaAsistencia(Integer codcia, Integer codpro, Integer codtra, String nroper, Integer correl, String desusu);

    public List<TurnoMarks> obtenerTurnoDiaMarks(Integer codcia, Integer codtra, String codfec);

    public void insertaMarkDia(Integer codcia, Integer codtra, String codfec, String fechora, String desusu);

    public List<MarkaManual> obtenerMarksMDia(Integer codcia, Integer codtra, String codfec);

    public void automarkTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu);

    public void deleteMarkDia(Integer codcia, Integer codtra, String codfec, String fechora);

    public void actualizaTurnoDiaCol(Integer codcia, Integer codtra, Integer codturno, String fecini, String fecfin, Integer diasem, String desusu);
}
