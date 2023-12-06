package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.dao.CuentaContableDao;
import com.balkaned.gladius.services.CuentasContablesService;
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
}
