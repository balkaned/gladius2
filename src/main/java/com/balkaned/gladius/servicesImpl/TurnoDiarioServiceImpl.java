package com.balkaned.gladius.servicesImpl;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.beans.Turno;
import com.balkaned.gladius.beans.Turnodiario;
import com.balkaned.gladius.dao.RetJudicialDao;
import com.balkaned.gladius.dao.TurnoDiarioDao;
import com.balkaned.gladius.services.TurnoDiarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TurnoDiarioServiceImpl implements TurnoDiarioService {

    @Autowired
    TurnoDiarioDao dao;
    public List<Turno> listarTurnos(Integer codcia){return dao.listarTurnos(codcia);}
    public List<Turnodiario> listarTurnoDia(Integer codcia, Integer codtra, String fecini, String fecfin){return dao.listarTurnoDia(codcia,codtra,fecini,fecfin);}
    public List<Empleado> listarTurMasTra(Integer codcia, String fecini, String fecfin) {  return dao.listarTurMasTra(codcia,fecini,fecfin);  }
    public void insertarTurno(Turno turno) {dao.insertarTurno(turno);}
    public Turno getTurno(Integer codcia, Integer codturno) {  return dao.getTurno( codcia, codturno);}
    public void actualizarTurno(Turno turno) { dao.actualizarTurno(turno);}

}
