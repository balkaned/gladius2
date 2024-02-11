package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.DashboardDao;
import com.balkaned.gladius.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    DashboardDao dao;

    public List<Cumpleanos> traerListaDeCumpleañosPorMes(Integer codcia) {
        return dao.traerListaDeCumpleañosPorMes(codcia);
    }

    public List<Ingresantes> traerListaDeIngresantesPorMes(Integer codcia) {
        return dao.traerListaDeIngresantesPorMes(codcia);
    }

    public List<Retirados> traerListaDeRetiradosPorMes(Integer codcia) {
        return dao.traerListaDeRetiradosPorMes(codcia);
    }

    public Integer getCantidadEmpl(Integer codcia) {
        return dao.getCantidadEmpl(codcia);
    }

    public Integer getCantidadAreas(Integer codcia) {
        return dao.getCantidadAreas(codcia);
    }

    public Integer getCantidadBancos(Integer codcia) {
        return dao.getCantidadBancos(codcia);
    }

    public DashboardSexoPie obtenerDashboardPieSexo(Integer codcia) {
        return dao.obtenerDashboardPieSexo(codcia);
    }

    public List<DashboardAreaBar> obtenerDatosDashboardArea(Integer codcia) {
        return dao.obtenerDatosDashboardArea(codcia);
    }

    public List<DashboardFondosBar> obtenerDatosDashboardFodos(Integer codcia) {
        return dao.obtenerDatosDashboardFodos(codcia);
    }

    public List<DashboardBancosPie> obtenerDatosDashboardBancos(Integer codcia) {
        return dao.obtenerDatosDashboardBancos(codcia);
    }

    public List<DashboardCcosto> obtenerDatosDashboardCCosto(Integer codcia){
        return dao.obtenerDatosDashboardCcosto(codcia);
    }

    public List<DashboardPuestos> obtenerDatosDashboardPuestos(Integer codcia){
        return dao.obtenerDatosDashboardPuestos(codcia);
    }
    public List<DashboardLocal> obtenerDatosDashboardLocales(Integer codcia){
        return dao.obtenerDatosDashboardLocales(codcia);
    }
}
