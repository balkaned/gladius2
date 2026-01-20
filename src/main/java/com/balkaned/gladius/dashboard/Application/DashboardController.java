package com.balkaned.gladius.dashboard.Application;


import com.balkaned.gladius.companias.Domain.Compania;
import com.balkaned.gladius.companias.Domain.CompaniaService;
import com.balkaned.gladius.dashboard.Domain.*;
import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.lovs.Domain.LovsService;
import com.balkaned.gladius.models.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.balkaned.gladius.util.CapitalizarCadena;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@RestController
@Slf4j
public class DashboardController {
    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    UsuxOpcionesService usuxOpcionesService;

    @Autowired
    UsuxSystemaService usuxSystemaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    DashboardService dashboardService;

    @Autowired
    UsuxCompaniaService usuxCompaniaService;

    @Autowired
    Sessionattributes sessionattributes;


    @RequestMapping("/dashboard")
    public ModelAndView dashboard(ModelMap model, HttpServletRequest request) {
        log.info("/dashboard");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String idusuario = (String) request.getSession().getAttribute("idUser");

        UsuarioConeccion uc1 = usuarioConeccionService.obtenerUsuarioConeccionById(String.valueOf(idusuario));
        Compania comp1 = companiaService.getCompaniaAll(idCompania);

        String usuario = (String) request.getSession().getAttribute("user");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");

        request.getSession().setAttribute("idCompania", comp1.getIdCodcia());
        request.getSession().setAttribute("urlLogo", comp1.getUrlLogo());
        request.getSession().setAttribute("nombrecomp", comp1.getDescCia());
        request.getSession().setAttribute("ruccomp", comp1.getNroRuc());
        request.getSession().setAttribute("schema", comp1.getSchema());

        String nombreComp = comp1.getDescCia();
        CapitalizarCadena cap2 = new CapitalizarCadena();
        nombreComp = cap2.letras(nombreComp);

        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", comp1.getUrlLogo());
        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", comp1.getNroRuc());
        model.addAttribute("schema", comp1.getSchema());

        List<UsuxOpciones> listaMenus = usuxOpcionesService.listarOpciones(idCompania, Integer.valueOf(idusuario), 1);
        model.addAttribute("usuxsysxopc", listaMenus);
        model.addAttribute("ususys", usuxSystemaService.eligeSystema(idCompania, Integer.valueOf(idusuario), 1));

        List<Cumpleanos> listCumpl = dashboardService.traerListaDeCumpleañosPorMes(idCompania);
        model.addAttribute("listCumple", listCumpl);
        model.addAttribute("cantCumpl", listCumpl.size());

        if (listCumpl.size() == 0) {
            model.addAttribute("mensaje3", "No hay ningun trabajador que cumpla años en este mes! ");
        } else {
            model.addAttribute("listCumple", listCumpl);
            model.addAttribute("mensaje3", null);
        }

        Month mes = LocalDate.now().getMonth();
        String nombreEnMes = mes.getDisplayName(TextStyle.FULL, new Locale("es", "ES"));
        CapitalizarCadena cap = new CapitalizarCadena();
        String mesCapitalizado = cap.letras(nombreEnMes);
        model.addAttribute("nombreEnMes", mesCapitalizado);
        log.info("nombreEnMes: " + mesCapitalizado);

        List<Ingresantes> listIngresantes = dashboardService.traerListaDeIngresantesPorMes(idCompania);
        model.addAttribute("cantIngresantes", listIngresantes.size());

        log.info("ListIngresantes.size(): " + listIngresantes.size());

        if (listIngresantes.size() == 0) {
            model.addAttribute("mensaje", "Este mes no ingresó ningun nuevo trabajador! ");
        } else {
            model.addAttribute("listIngresantes", listIngresantes);
            model.addAttribute("mensaje", null);
        }

        List<Retirados> listRetirados = dashboardService.traerListaDeRetiradosPorMes(idCompania);
        model.addAttribute("cantRetirados", listRetirados.size());

        if (listRetirados.size() == 0) {
            model.addAttribute("mensaje2", "Este mes no se realizó liquidación de ningún trabajador! ");
        } else {
            model.addAttribute("listRetirados", listRetirados);
            model.addAttribute("mensaje2", null);
        }

        model.addAttribute("cantEmpl", dashboardService.getCantidadEmpl(idCompania));
        model.addAttribute("cantAreas", dashboardService.getCantidadAreas(idCompania));
        model.addAttribute("cantFondos", lovsService.getLovs("11", "%").size());
        model.addAttribute("cantBancosHab", lovsService.getLovs("36", "%").size());
        model.addAttribute("cantCcostos", lovsService.getCCostoCia(idCompania).size());
        model.addAttribute("cantLocales", lovsService.getUbicacionCia(idCompania).size());
        model.addAttribute("cantPuestos", lovsService.getPuestoCia(idCompania).size());

        // Obtenemos los datos para el Grafico Pie por sexo
        DashboardSexoPie ds = dashboardService.obtenerDashboardPieSexo(idCompania);
        model.addAttribute("cantidad_total", ds.getCantidad_total());
        model.addAttribute("cantidad_m", ds.getCantidad_m());
        model.addAttribute("cantidad_f", ds.getCantidad_f());
        model.addAttribute("cantidad_ma", ds.getCantidad_ma());
        model.addAttribute("ds", ds);

        List<DashboardAreaBar> lsAreaBar = dashboardService.obtenerDatosDashboardArea(idCompania);
        model.addAttribute("lsAreaBar", lsAreaBar);

        List<DashboardFondosBar> lsFondBar = dashboardService.obtenerDatosDashboardFodos(idCompania);
        model.addAttribute("lsFondBar", lsFondBar);

        List<DashboardBancosPie> lsBanPie = dashboardService.obtenerDatosDashboardBancos(idCompania);
        model.addAttribute("lsBanPie", lsBanPie);

        List<DashboardCcosto> lsCcostoBar = dashboardService.obtenerDatosDashboardCCosto(idCompania);
        model.addAttribute("lsCcostoBar", lsCcostoBar);

        List<DashboardPuestos> lsPuestosBar = dashboardService.obtenerDatosDashboardPuestos(idCompania);
        model.addAttribute("lsPuestosBar", lsPuestosBar);

        List<DashboardLocal> lsLocalBar = dashboardService.obtenerDatosDashboardLocales(idCompania);
        model.addAttribute("lsLocalBar", lsLocalBar);


        // Obtiene datos del usuario y rol si es SYSHRSELF redirecciona listaTrabajadores
        UsuarioxRol ur = usuxCompaniaService.obtenerRolxUsuario(idCompania, Integer.valueOf(idusuario));
        Empleado emp = new Empleado();
        emp.setIexcodcia(Integer.valueOf(idCompania));
        emp.setIexcodtra(ur.getIexcodtra());

        log.info("ur.getIexdesrol(): " + ur.getIexdesrol());
        log.info("ur.getIexcodTra(): " + ur.getIexcodtra());

        if (ur.getIexdesrol().equals("SYSHRSELF")) {
            return new ModelAndView("redirect:/listEmpleados");
        } else {
            return new ModelAndView("public/dashboard");
        }
    }
}
