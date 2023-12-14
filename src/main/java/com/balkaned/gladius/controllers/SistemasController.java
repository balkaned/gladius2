package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.Sistemas;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class SistemasController {
    static Logger logger = Logger.getLogger(SistemasController.class.getName());


    @Autowired
    SistemaService sistemaService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listSistemas")
    public ModelAndView listSistemas(ModelMap model, HttpServletRequest request) {
        logger.info("/listSistemas");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstSistema", sistemaService.listarSistemas());

        return new ModelAndView("public/gladius/configuracion/sistemas/listSistemas");
    }

    @RequestMapping("/nuevoSistema")
    public ModelAndView nuevoSistema(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoSistema");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        return new ModelAndView("public/gladius/configuracion/sistemas/nuevoSistema");
    }

    @RequestMapping("/insertarSistemas")
    public ModelAndView insertarSistemas(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarSistemas");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String codsys = request.getParameter("iexcodsys");

        Sistemas sis = new Sistemas();
        sis.setIexcodsys(Integer.parseInt(codsys));
        sis.setIexdessys(request.getParameter("iexdessys"));

        sistemaService.insertarSistemas(sis);

        return new ModelAndView("redirect:/listSistemas");
    }

    @RequestMapping("/editarSistema@{idSis}")
    public ModelAndView editarSistema(ModelMap model, HttpServletRequest request, @PathVariable String idSis) {
        logger.info("/editarSistema");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idSis", idSis);
        model.addAttribute("xSys", sistemaService.getSistemas(Integer.valueOf(idSis)));

        return new ModelAndView("public/gladius/configuracion/sistemas/editarSistema");
    }

    @RequestMapping("/modificarSistema")
    public ModelAndView modificarSistema(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarSistema");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String codsys2 = request.getParameter("iexcodsys2");
        Sistemas sis = new Sistemas();
        sis.setIexcodsys(Integer.parseInt(codsys2));
        sis.setIexdessys(request.getParameter("iexdessys"));

        sistemaService.actualizarSistemas(sis);

        return new ModelAndView("redirect:/listSistemas");
    }

    @RequestMapping("/deleteSistema@{idSys}")
    public ModelAndView deleteSistema(ModelMap model, HttpServletRequest request, @PathVariable String idSys) {
        logger.info("/deleteSistema");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Sistemas sis = new Sistemas();
        sis.setIexcodsys(Integer.valueOf(idSys));

        sistemaService.eliminarSistemas(sis);

        return new ModelAndView("redirect:/listSistemas");
    }


}

