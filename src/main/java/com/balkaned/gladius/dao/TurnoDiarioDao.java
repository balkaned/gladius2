package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.Turno;
import com.balkaned.gladius.beans.Turnodiario;
import java.util.List;

public interface TurnoDiarioDao {
    public List<Turno> listarTurnosModalAsis(Integer codcia, String fecini);
    public List<Turno> listarTurnos(Integer codcia);
    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin);
    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin );
    public void insertarTurno(Turno turno);
    public Turno getTurno (Integer codcia, Integer codturno);
    public void actualizarTurno(Turno turno);
    public void  eliminarTurno(Turno turno);
    public Turnodiario obtenerTurnoDia(Integer codcia, Integer codtra, String codfec);
    public void actualizaTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu);
    public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu);

}
