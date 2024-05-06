package com.balkaned.gladius.services;

import com.balkaned.gladius.models.CuentaContable;

import java.util.List;

public interface CuentasContablesService {
    public List<CuentaContable> listarCuentasContables();

    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania);

    public CuentaContable getCuentaContable(Integer codcia, String ccontable);

    public void actualizarCuentaContable(CuentaContable ccontable);

    public void eliminarCuentaContable(CuentaContable ccontable);
}
