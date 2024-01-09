package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.CentroCosto;
import com.balkaned.gladius.services.*;
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
public class CcostosController {
    @Autowired
    CcostoService ccostoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listCcostos")
    public ModelAndView litsCcostos(ModelMap model, HttpServletRequest request) {
        log.info("/litsCcostos");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<CentroCosto> ccostosList = ccostoService.listarCentroCosto(idCompania, "");
        log.info("ccostosList: " + ccostosList);
        model.addAttribute("ccostosList", ccostosList);

        return new ModelAndView("public/gladius/organizacion/ccostos/listCcostos");
    }

    @RequestMapping("/nuevoCcosto")
    public ModelAndView nuevoCcosto(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoCcosto");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovCatCencos", lovsService.getLovs("64", "%"));
        model.addAttribute("idx", ccostoService.getIdCentroCosto(idCompania));

        return new ModelAndView("public/gladius/organizacion/ccostos/nuevoCcosto");
    }

    @RequestMapping("/insertarCcosto")
    public ModelAndView insertarCcosto(ModelMap model, HttpServletRequest request) {
        log.info("/insertarCcosto");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexccosto = request.getParameter("iexccosto2");
        String iexdesccosto = request.getParameter("iexdesccosto");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexusucrea = usuario;

        CentroCosto cencos = new CentroCosto();
        cencos.setIexcodcia(iexcodcia);
        cencos.setIexccosto(iexccosto);
        cencos.setIexdesccosto(iexdesccosto);
        cencos.setIexcodcat(iexcodcat);
        cencos.setIexusucrea(iexusucrea);

        ccostoService.insertarCentroCosto(cencos);

        return new ModelAndView("redirect:/listCcostos");
    }

    @RequestMapping("/editarCcosto@{idCosto}")
    public ModelAndView editarCcosto(ModelMap model, HttpServletRequest request, @PathVariable String idCosto) {
        log.info("/editarCcosto");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idCosto", idCosto);
        model.addAttribute("lovCatCencos", lovsService.getLovs("64", "%"));
        model.addAttribute("xCcosto", ccostoService.getCentroCosto(idCompania, idCosto));

        return new ModelAndView("public/gladius/organizacion/ccostos/editarCcosto");
    }

    @RequestMapping("/modificarCcosto")
    public ModelAndView modificarCcosto(ModelMap model, HttpServletRequest request) {
        log.info("/modificarCcosto");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexccosto = request.getParameter("iexccosto2");
        String iexdesccosto = request.getParameter("iexdesccosto");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexusucrea = usuario;

        CentroCosto cencos = new CentroCosto();
        cencos.setIexcodcia(iexcodcia);
        cencos.setIexccosto(iexccosto);
        cencos.setIexdesccosto(iexdesccosto);
        cencos.setIexcodcat(iexcodcat);
        cencos.setIexusucrea(iexusucrea);

        ccostoService.actualizarCentroCosto(cencos);

        return new ModelAndView("redirect:/listCcostos");
    }

    @RequestMapping("/deleteCcosto@{idCosto}")
    public ModelAndView deleteCcosto(ModelMap model, HttpServletRequest request, @PathVariable String idCosto) {
        log.info("/deleteCcosto");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idCosto", idCosto);

        CentroCosto cencos = new CentroCosto();
        cencos.setIexcodcia(idCompania);
        cencos.setIexccosto(idCosto);

        ccostoService.eliminarCentroCosto(cencos);

        return new ModelAndView("redirect:/listCcostos");
    }
}

