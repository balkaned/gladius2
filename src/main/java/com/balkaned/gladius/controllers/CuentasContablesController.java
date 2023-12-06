package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.beans.Lovs;
import com.balkaned.gladius.services.CuentasContablesService;
import com.balkaned.gladius.services.LovsService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
public class CuentasContablesController {
    @Autowired
    CuentasContablesService service;

    @Autowired
    LovsService lovsService;

    @RequestMapping("/listarCuentasContables")
    public ModelAndView listarCuentasContables(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/listConceptos");

         String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        List<CuentaContable> cuentasContablesList = service.listarCuentasContables();

        log.info("cuentasContablesList: " + cuentasContablesList);

        model.addAttribute("cuentasContablesList", cuentasContablesList);

        return new ModelAndView("public/gladius/confPlanilla/cuentasContables/listarCuentasContables");
    }

    @RequestMapping("/insertarCuentasContables")
    public ModelAndView insertarCuentasContables(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/insertarCuentasContables");

         String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        List<Lovs> lovConcepto = lovsService.getLovsCContables();

        log.info("lovConcepto: " + lovConcepto);

        model.addAttribute("lovConcepto", lovConcepto);

        return new ModelAndView("public/gladius/confPlanilla/cuentasContables/nuevaCuentaContable");
    }


    @RequestMapping("/addCuentaContable")
    public ModelAndView addCuentaContable(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/addCuentaContable");

         String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        CuentaContable cuentaContable = new CuentaContable();
        cuentaContable.setIexccodcta(request.getParameter("iexccodcta"));
        cuentaContable.setIexdescta(request.getParameter("iexdescta"));
        cuentaContable.setDesdet(request.getParameter("desdet"));

        log.info("usuario: " + idCompania);

        log.info("request -> cuentaContable: " + cuentaContable);

        service.insertarCuentaContable(cuentaContable, idCompania);

        return new ModelAndView("redirect:/listarCuentasContables");
    }
}
