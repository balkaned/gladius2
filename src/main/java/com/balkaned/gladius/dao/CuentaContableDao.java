package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.CuentaContable;
import java.util.List;

public interface CuentaContableDao {
    public List<CuentaContable> listarCuentasContables();

    public void insertarCuentaContable(CuentaContable cuentaContable, Integer idCompania);

    public CuentaContable getCuentaContable(Integer codcia, String ccontable);

    public void actualizarCuentaContable(CuentaContable ccontable);

    public void eliminarCuentaContable(CuentaContable ccontable);
}
