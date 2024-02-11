package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.*;

import java.util.List;

public interface DashboardDao {

    public List<Cumpleanos> traerListaDeCumpleañosPorMes(Integer codcia);

    public List<Ingresantes> traerListaDeIngresantesPorMes(Integer codcia);

    public List<Retirados> traerListaDeRetiradosPorMes(Integer codcia);

    public Integer getCantidadEmpl(Integer codcia);

    public List<DashboardCcosto> obtenerDatosDashboardCcosto(Integer codcia);

    public List<DashboardPuestos> obtenerDatosDashboardPuestos(Integer codcia);

    public Integer getCantidadAreas(Integer codcia);

    public Integer getCantidadBancos(Integer codcia);

    public DashboardSexoPie obtenerDashboardPieSexo(Integer codcia);

    public List<DashboardAreaBar> obtenerDatosDashboardArea(Integer codcia);

    public List<DashboardFondosBar> obtenerDatosDashboardFodos(Integer codcia);

    public List<DashboardBancosPie> obtenerDatosDashboardBancos(Integer codcia);

    public List<DashboardCcosto> obtenerDatosDashboardCCosto(Integer codcia);
    public List<DashboardLocal> obtenerDatosDashboardLocales(Integer codcia);

}
