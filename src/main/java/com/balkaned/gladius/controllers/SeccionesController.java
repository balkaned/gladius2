package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.Seccion;
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
public class SeccionesController {

    @Autowired
    SeccionService seccionService;

    @Autowired
    SistemaService sistemaService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listSecciones")
    public ModelAndView listSecciones(ModelMap model, HttpServletRequest request) {
        log.info("/listSecciones");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstSeccion", seccionService.listarSeccion());

        return new ModelAndView("public/gladius/configuracion/secciones/listSecciones");
    }

    @RequestMapping("/nuevaSeccion")
    public ModelAndView nuevaSeccion(ModelMap model, HttpServletRequest request) {
        log.info("/nuevaSeccion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovSys", sistemaService.listarSistemas());

        return new ModelAndView("public/gladius/configuracion/secciones/nuevaSeccion");
    }

    @RequestMapping("/insertarSeccion")
    public ModelAndView insertarSeccion(ModelMap model, HttpServletRequest request) {
        log.info("/insertarSeccion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Seccion sec = new Seccion();
        Integer idsec = seccionService.getIdSeccion();
        sec.setIexcodsec(idsec);
        sec.setIexdessec(request.getParameter("iexdessec"));
        sec.setIexcodsys(Integer.parseInt(request.getParameter("iexcodsys")));
        sec.setIexordsec(Integer.parseInt(request.getParameter("iexordsec")));
        sec.setIexsecimg(request.getParameter("iexsecimg"));
        sec.setIexsecurl(request.getParameter("iexsecurl"));
        sec.setIexsecobs(request.getParameter("iexsecobs"));
        sec.setIexactiondef(request.getParameter("iexactiondef"));

        seccionService.insertarSeccion(sec);

        return new ModelAndView("redirect:/listSecciones");
    }

    @RequestMapping("/editarSeccion@{idSec}")
    public ModelAndView editarSeccion(ModelMap model, HttpServletRequest request, @PathVariable String idSec) {
        log.info("/editarSeccion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idSec", idSec);
        model.addAttribute("xSeccion", seccionService.getSeccion(Integer.valueOf(idSec)));
        model.addAttribute("lovSys", sistemaService.listarSistemas());

        return new ModelAndView("public/gladius/configuracion/secciones/editarSeccion");
    }

    @RequestMapping("/modificarSeccion")
    public ModelAndView modificarSeccion(ModelMap model, HttpServletRequest request) {
        log.info("/modificarSeccion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Seccion sec = new Seccion();

        sec.setIexcodsec(Integer.parseInt(request.getParameter("iexcodsec")));
        sec.setIexdessec(request.getParameter("iexdessec"));
        sec.setIexcodsys(Integer.parseInt(request.getParameter("iexcodsys")));
        sec.setIexordsec(Integer.parseInt(request.getParameter("iexordsec")));
        sec.setIexsecimg(request.getParameter("iexsecimg"));
        sec.setIexsecurl(request.getParameter("iexsecurl"));
        sec.setIexsecobs(request.getParameter("iexsecobs"));
        sec.setIexactiondef(request.getParameter("iexactiondef"));

        seccionService.actualizarSeccion(sec);

        return new ModelAndView("redirect:/listSecciones");
    }

    @RequestMapping("/deleteSeccion@{idSec}")
    public ModelAndView deleteSeccion(ModelMap model, HttpServletRequest request, @PathVariable String idSec) {
        log.info("/deleteSeccion");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:"+user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Seccion sec = new Seccion();
        sec.setIexcodsec(Integer.valueOf(idSec));
        seccionService.eliminarSeccion(sec);

        return new ModelAndView("redirect:/listSecciones");
    }
}

