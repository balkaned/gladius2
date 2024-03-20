package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.balkaned.gladius.utils.CapitalizarCadena;
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


    @RequestMapping("/home@{idComp}@{idUser}")
    public ModelAndView home(ModelMap model, HttpServletRequest request,
                             @PathVariable String idComp,
                             @PathVariable String idUser) {
        log.info("/home");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        UsuarioConeccion uc1 = usuarioConeccionService.obtenerUsuarioConeccionById(idUser);
        Compania comp1 = companiaService.getCompaniaAll(Integer.parseInt(idComp));

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");

        request.getSession().setAttribute("idCompania", comp1.getIdCodcia());
        request.getSession().setAttribute("urlLogo", comp1.getUrlLogo());
        request.getSession().setAttribute("nombrecomp", comp1.getDescCia());
        request.getSession().setAttribute("ruccomp", comp1.getNroRuc());
        request.getSession().setAttribute("schema", comp1.getSchema());

        model.addAttribute("idComp", idComp);
        model.addAttribute("urlLogo", comp1.getUrlLogo());
        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", comp1.getDescCia());
        model.addAttribute("rucComp", comp1.getNroRuc());
        model.addAttribute("schema", comp1.getSchema());

        int idCompconv = Integer.parseInt(idComp);
        int idUsuarioconv = Integer.parseInt(idUser);

        List<UsuxOpciones> listaMenus = usuxOpcionesService.listarOpciones(idCompconv, idUsuarioconv, 1);
        model.addAttribute("usuxsysxopc", listaMenus);
        model.addAttribute("ususys", usuxSystemaService.eligeSystema(idCompconv, idUsuarioconv, 1));

        List<Cumpleanos> listCumpl = dashboardService.traerListaDeCumpleañosPorMes(Integer.valueOf(idComp));
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

        List<Ingresantes> listIngresantes = dashboardService.traerListaDeIngresantesPorMes(Integer.valueOf(idComp));
        model.addAttribute("cantIngresantes", listIngresantes.size());

        log.info("ListIngresantes.size(): " + listIngresantes.size());

        if (listIngresantes.size() == 0) {
            model.addAttribute("mensaje", "Este mes no ingresó ningun nuevo trabajador! ");
        } else {
            model.addAttribute("listIngresantes", listIngresantes);
            model.addAttribute("mensaje", null);
        }

        List<Retirados> listRetirados = dashboardService.traerListaDeRetiradosPorMes(Integer.valueOf(idComp));
        model.addAttribute("cantRetirados", listRetirados.size());

        if (listRetirados.size() == 0) {
            model.addAttribute("mensaje2", "Este mes no se realizó liquidación de ningún trabajador! ");
        } else {
            model.addAttribute("listRetirados", listRetirados);
            model.addAttribute("mensaje2", null);
        }

        model.addAttribute("cantEmpl", dashboardService.getCantidadEmpl(Integer.valueOf(idComp)));
        model.addAttribute("cantAreas", dashboardService.getCantidadAreas(Integer.valueOf(idComp)));
        model.addAttribute("cantFondos", lovsService.getLovs("11", "%").size());
        model.addAttribute("cantBancosHab", lovsService.getLovs("36", "%").size());
        model.addAttribute("cantCcostos", lovsService.getCCostoCia(Integer.valueOf(idComp)).size());
        model.addAttribute("cantLocales",lovsService.getUbicacionCia(Integer.valueOf(idComp)).size());
        model.addAttribute("cantPuestos",lovsService.getPuestoCia(Integer.valueOf(idComp)).size());

        //Obtenemos los datos para el Grafico Pie por sexo
        DashboardSexoPie ds = dashboardService.obtenerDashboardPieSexo(Integer.valueOf(idComp));
        model.addAttribute("cantidad_total", ds.getCantidad_total());
        model.addAttribute("cantidad_m", ds.getCantidad_m());
        model.addAttribute("cantidad_f", ds.getCantidad_f());
        model.addAttribute("cantidad_ma", ds.getCantidad_ma());
        model.addAttribute("ds", ds);

        List<DashboardAreaBar> lsAreaBar = dashboardService.obtenerDatosDashboardArea(Integer.valueOf(idComp));
        model.addAttribute("lsAreaBar", lsAreaBar);

        List<DashboardFondosBar> lsFondBar=dashboardService.obtenerDatosDashboardFodos(Integer.valueOf(idComp));
        model.addAttribute("lsFondBar",lsFondBar);

        List<DashboardBancosPie> lsBanPie= dashboardService.obtenerDatosDashboardBancos(Integer.valueOf(idComp));
        model.addAttribute("lsBanPie",lsBanPie);

        List<DashboardCcosto> lsCcostoBar=dashboardService.obtenerDatosDashboardCCosto(Integer.valueOf(idComp));
        model.addAttribute("lsCcostoBar",lsCcostoBar);

        List<DashboardPuestos> lsPuestosBar=dashboardService.obtenerDatosDashboardPuestos(Integer.valueOf(idComp));
        model.addAttribute("lsPuestosBar",lsPuestosBar);

        List<DashboardLocal> lsLocalBar=dashboardService.obtenerDatosDashboardLocales(Integer.valueOf(idComp));
        model.addAttribute("lsLocalBar",lsLocalBar);

        return new ModelAndView("public/dashboard");
    }
}
