package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.CuentaContable;

import java.util.List;

public interface CuentaContableDao {
    List<CuentaContable> listarCuentasContables();

    void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania);
}
