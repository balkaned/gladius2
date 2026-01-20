package com.balkaned.gladius.cuentasContables.Domain;

import com.balkaned.gladius.cuentasContables.Infrastructure.CuentaContableDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentasContablesServiceImpl implements CuentasContablesService {
    @Autowired
    CuentaContableDao dao;

    @Override
    public List<CuentaContable> listarCuentasContables() {
        return dao.listarCuentasContables();
    }

    @Override
    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania) {
        dao.insertarCuentaContable(cuentaContable, idCompania);
    }

    public CuentaContable getCuentaContable(Integer codcia, String ccontable){
        return dao.getCuentaContable(codcia,ccontable);
    }

    public void actualizarCuentaContable(CuentaContable ccontable){
        dao.actualizarCuentaContable(ccontable);
    }

    public void eliminarCuentaContable(CuentaContable ccontable){
        dao.eliminarCuentaContable(ccontable);
    }
}
