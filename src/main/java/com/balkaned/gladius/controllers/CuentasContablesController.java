package com.balkaned.gladius.controllers;

import com.balkaned.gladius.models.CuentaContable;
import com.balkaned.gladius.models.Lovs;
import com.balkaned.gladius.services.CuentasContablesService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class CuentasContablesController {
    @Autowired
    CuentasContablesService cuentasContablesService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listarCuentasContables")
    public ModelAndView listarCuentasContables(ModelMap model, HttpServletRequest request) {
        log.info("/listarCuentasContables");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        List<CuentaContable> cuentasContablesList = cuentasContablesService.listarCuentasContables();
        model.addAttribute("cuentasContablesList", cuentasContablesList);

        return new ModelAndView("public/gladius/confPlanilla/cuentasContables/listarCuentasContables");
    }

    @RequestMapping("/insertarCuentasContables")
    public ModelAndView insertarCuentasContables(ModelMap model, HttpServletRequest request) {
        log.info("/insertarCuentasContables");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        List<Lovs> lovConcepto = lovsService.getLovsCContables();
        model.addAttribute("lovConcepto", lovConcepto);

        return new ModelAndView("public/gladius/confPlanilla/cuentasContables/nuevaCuentaContable");
    }

    @RequestMapping("/addCuentaContable")
    public ModelAndView addCuentaContable(ModelMap model, HttpServletRequest request) {
        log.info("/addCuentaContable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        CuentaContable cuentaContable = new CuentaContable();
        cuentaContable.setIexccodcta(request.getParameter("iexccodcta"));
        cuentaContable.setIexdescta(request.getParameter("iexdescta"));
        cuentaContable.setDesdet(request.getParameter("desdet"));

        cuentasContablesService.insertarCuentaContable(cuentaContable, idCompania);

        return new ModelAndView("redirect:/listarCuentasContables");
    }

    @RequestMapping("/editarCuentaContable@{idCContable}")
    public ModelAndView editarCuentaContable(ModelMap model, HttpServletRequest request, @PathVariable String idCContable) {
        log.info("/editarCuentaContable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<Lovs> lovConcepto = lovsService.getLovsCContables();
        model.addAttribute("lovConcepto", lovConcepto);
        model.addAttribute("xCcontable",cuentasContablesService.getCuentaContable(idCompania,idCContable));

        return new ModelAndView("public/gladius/confPlanilla/cuentasContables/editarCuentaContable");
    }

    @RequestMapping("/modificarCcontable")
    public ModelAndView modificarCcontable(ModelMap model, HttpServletRequest request) {
        log.info("/modificarCcontable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexccodcta = request.getParameter("iexccodcta");
        String iexdescta = request.getParameter("iexdescta");
        String iextipocta  = request.getParameter("desdet");

        CuentaContable ccontable = new CuentaContable();
        ccontable.setIexcodcia(idCompania);
        ccontable.setIexccodcta(iexccodcta);
        ccontable.setIexdescta(iexdescta);
        ccontable.setIextipocta(iextipocta);
        ccontable.setIexusucrea(user);
        cuentasContablesService.actualizarCuentaContable(ccontable);

        return new ModelAndView("redirect:/listarCuentasContables");
    }

    @RequestMapping("/deleteCuentaContable@{idCContable}")
    public ModelAndView deleteCuentaContable(ModelMap model, HttpServletRequest request, @PathVariable String idCContable) {
        log.info("/deleteCuentaContable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idCContable", idCContable);

        CuentaContable ccontable = new CuentaContable();
        ccontable.setIexcodcia(idCompania);
        ccontable.setIexccodcta(idCContable);

        cuentasContablesService.eliminarCuentaContable(ccontable);

        return new ModelAndView("redirect:/listarCuentasContables");
    }
}
