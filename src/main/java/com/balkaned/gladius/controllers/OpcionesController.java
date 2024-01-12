package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Opciones;
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
public class OpcionesController {

    @Autowired
    OpcionService opcionService;
    @Autowired
    SeccionService seccionService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listOpciones")
    public ModelAndView listOpciones(ModelMap model, HttpServletRequest request) {
        log.info("/listOpciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstOpciones", opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/opciones/listOpciones");
    }

    @RequestMapping("/nuevaOpcion")
    public ModelAndView nuevaOpcion(ModelMap model, HttpServletRequest request) {
        log.info("/nuevaOpcion");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovSeccion", seccionService.listarSeccion());

        return new ModelAndView("public/gladius/configuracion/opciones/nuevaOpcion");
    }

    @RequestMapping("/insertarOpcion")
    public ModelAndView insertarOpcion(ModelMap model, HttpServletRequest request) {
        log.info("/insertarOpcion");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String usuario = (String) request.getSession().getAttribute("user");

        Integer idopc = opcionService.getIdOpciones();
        Opciones opc = new Opciones();
        opc.setIexcodopc(idopc);
        opc.setIexdesopc(request.getParameter("iexdesopc"));
        opc.setIexflgest(request.getParameter("iexflgest"));
        opc.setIexcodsec(Integer.parseInt(request.getParameter("iexcodsec")));
        opc.setIexurlopc(request.getParameter("iexurlopc"));
        opc.setIexurlimg(request.getParameter("iexurlimg"));
        opc.setIexaction(request.getParameter("iexaction"));
        opc.setIexactionspring(request.getParameter("iexactionspring"));
        opc.setIexcodapps(request.getParameter("iexcodapps"));
        opc.setIexdescripcion(request.getParameter("iexdescripcion"));
        opc.setIexusucre(usuario);

        opcionService.insertarOpciones(opc);

        return new ModelAndView("redirect:/listOpciones");
    }

    @RequestMapping("/editarOpc@{idOpc}")
    public ModelAndView editarOpc(ModelMap model, HttpServletRequest request, @PathVariable String idOpc) {
        log.info("/editarOpc");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idOpc", idOpc);
        model.addAttribute("xOpcion", opcionService.getOpciones(Integer.valueOf(idOpc)));
        model.addAttribute("lovSeccion", seccionService.listarSeccion());

        return new ModelAndView("public/gladius/configuracion/opciones/editarOpcion");
    }

    @RequestMapping("/modificarOpc")
    public ModelAndView modificarOpc(ModelMap model, HttpServletRequest request) {
        log.info("/modificarOpc");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Opciones opc = new Opciones();
        opc.setIexcodopc(Integer.parseInt(request.getParameter("iexcodopc")));
        opc.setIexdesopc(request.getParameter("iexdesopc"));
        opc.setIexflgest(request.getParameter("iexflgest"));
        opc.setIexcodsec(Integer.parseInt(request.getParameter("iexcodsec")));
        opc.setIexurlopc(request.getParameter("iexurlopc"));
        opc.setIexurlimg(request.getParameter("iexurlimg"));
        opc.setIexaction(request.getParameter("iexaction"));
        opc.setIexactionspring(request.getParameter("iexactionspring"));
        opc.setIexcodapps(request.getParameter("iexcodapps"));
        opc.setIexdescripcion(request.getParameter("iexdescripcion"));

        opcionService.actualizarOpciones(opc);

        return new ModelAndView("redirect:/listOpciones");
    }

    @RequestMapping("/deleteOpc@{idOpc}")
    public ModelAndView deleteOpc(ModelMap model, HttpServletRequest request, @PathVariable String idOpc) {
        log.info("/deleteOpc");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Opciones opc = new Opciones();
        opc.setIexcodopc(Integer.valueOf(idOpc));
        opcionService.eliminarOpciones(opc);

        return new ModelAndView("redirect:/listOpciones");
    }
}

