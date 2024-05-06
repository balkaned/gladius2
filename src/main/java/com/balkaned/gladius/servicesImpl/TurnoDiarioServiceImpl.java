package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.models.Empleado;
import com.balkaned.gladius.models.Turno;
import com.balkaned.gladius.models.Turnodiario;
import com.balkaned.gladius.dao.TurnoDiarioDao;
import com.balkaned.gladius.services.TurnoDiarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@Slf4j
public class TurnoDiarioServiceImpl implements TurnoDiarioService {

    @Autowired
    TurnoDiarioDao dao;

    public List<Turno> listarTurnosModalAsis(Integer codcia, String fecini) {
        return dao.listarTurnosModalAsis(codcia, fecini);
    }

    public List<Turno> listarTurnos(Integer codcia) {
        return dao.listarTurnos(codcia);
    }

    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin) {
        return dao.listarTurnoDia(codcia, codtra, fecini, fecfin);
    }

    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin) {
        return dao.listarTurMasTra(codcia, fecini, fecfin);
    }

    public void insertarTurno(Turno turno) {

        dao.insertarTurno(turno);
    }

    public Turno getTurno(Integer codcia, Integer codturno) {

        return dao.getTurno(codcia, codturno);
    }

    public void actualizarTurno(Turno turno) {

        dao.actualizarTurno(turno);
    }

    public void eliminarTurno(Turno turno) {
        dao.eliminarTurno(turno);
    }

    public Turnodiario obtenerTurnoDia(Integer codcia, Integer codtra, String codfec) {
        return dao.obtenerTurnoDia(codcia, codtra, codfec);
    }

    public void actualizaTurnoDia(Integer codcia, Integer codtra, Integer codturno, String fecdia, String desusu){
        dao.actualizaTurnoDia(codcia,codtra,codturno,fecdia,desusu);
    }
    public void calificarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu){
        dao.calificarTurnoDia(codcia,codtra,fecdia,desusu);
    }
    public void programarTurnoDia(Integer codcia, Integer codtra, String fecdia, String desusu){
        dao.programarTurnoDia(codcia,codtra,fecdia,desusu);
    }
}
