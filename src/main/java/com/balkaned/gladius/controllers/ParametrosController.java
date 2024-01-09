package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.ParametrosGen;
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
import java.util.logging.Logger;

@RestController
@Slf4j
public class ParametrosController {
    @Autowired
    ParametroService parametroService;

    @Autowired
    LovsService lovsService;

    @Autowired
    ConceptoService conceptoService;

    @Autowired
    Sessionattributes sessionattributes;


    @RequestMapping("/listParametros")
    public ModelAndView listParametros(ModelMap model, HttpServletRequest request) {
        log.info("/listParametros");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstParametro", parametroService.listarParametrosGen());

        return new ModelAndView("public/gladius/configuracion/parametros/listParametros");
    }

    @RequestMapping("/nuevoParametro")
    public ModelAndView nuevoParametro(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoParametro");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovConcepto", conceptoService.listardet());
        model.addAttribute("lovTippar", lovsService.getLovs("67", "%"));

        return new ModelAndView("public/gladius/configuracion/parametros/nuevoParametro");
    }

    @RequestMapping("/insertarParametro")
    public ModelAndView insertarParametro(ModelMap model, HttpServletRequest request) {
        log.info("/insertarParametro");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        ParametrosGen par = new ParametrosGen();

        par.setIexcodcon(request.getParameter("iexcodcon"));
        par.setIextippar(request.getParameter("iextippar"));
        par.setIexvalcon(Double.parseDouble(request.getParameter("iexvalcon")));
        par.setIexdesobs(request.getParameter("iexdesobs"));
        par.setIexusucrea(usuario);

        parametroService.insertarParametrosGen(par);

        return new ModelAndView("redirect:/listParametros");
    }

    @RequestMapping("/editarParametro@{idParam}")
    public ModelAndView editarParametro(ModelMap model, HttpServletRequest request, @PathVariable String idParam) {
        log.info("/editarParametro");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idParam", idParam);
        model.addAttribute("xParametro", parametroService.getParametrosGen(idParam));
        model.addAttribute("lovConcepto", conceptoService.listardet());
        model.addAttribute("lovTippar", lovsService.getLovs("67", "%"));

        return new ModelAndView("public/gladius/configuracion/parametros/editarParametro");
    }

    @RequestMapping("/modificarParametro")
    public ModelAndView modificarParametro(ModelMap model, HttpServletRequest request) {
        log.info("/modificarParametro");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        ParametrosGen par = new ParametrosGen();
        par.setIexcodcon(request.getParameter("iexcodcon"));
        par.setIextippar(request.getParameter("iextippar"));
        par.setIexvalcon(Double.parseDouble(request.getParameter("iexvalcon")));
        par.setIexdesobs(request.getParameter("iexdesobs"));
        par.setIexusumod(usuario);

        parametroService.actualizarParametrosGen(par);

        return new ModelAndView("redirect:/listParametros");
    }

    @RequestMapping("/deleteParametro@{idParam}")
    public ModelAndView deleteParametro(ModelMap model, HttpServletRequest request, @PathVariable String idParam) {
        log.info("/deleteParametro");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        ParametrosGen par = new ParametrosGen();
        par.setIexcodcon(idParam);

        parametroService.eliminarParametrosGen(par);

        return new ModelAndView("redirect:/listParametros");
    }
}

