package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.CuentaContable;

import java.util.List;

public interface CuentasContablesService {
    List<CuentaContable> listarCuentasContables();

    void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania);
}
