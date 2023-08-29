package com.balkaned.gladius.dao;


import com.balkaned.gladius.beans.AusentismoProgramacion;
import com.balkaned.gladius.beans.Empleado;

import java.util.List;

public interface AusentismoDao {
    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado);

}
