package com.balkaned.gladius.cuentasContables.Infrastructure;

import com.balkaned.gladius.cuentasContables.Domain.CuentaContable;

import java.util.List;

public interface CuentaContableDao {
    public List<CuentaContable> listarCuentasContables();

    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania);

    public CuentaContable getCuentaContable(Integer codcia, String ccontable);

    public void actualizarCuentaContable(CuentaContable ccontable);

    public void eliminarCuentaContable(CuentaContable ccontable);
}
