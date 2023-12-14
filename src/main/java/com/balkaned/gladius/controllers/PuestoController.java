package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class PuestoController {
    static Logger logger = Logger.getLogger(PuestoController.class.getName());
    @Autowired
    PuestoService puestoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listPuestos")
    public ModelAndView listPuestos(ModelMap model, HttpServletRequest request) {
        logger.info("/listPuestos");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<Puesto> puestoList = puestoService.listarPuesto(idCompania, "");

        logger.info("puestoList: " + puestoList);

        model.addAttribute("puestoList", puestoList);

        return new ModelAndView("public/gladius/organizacion/puestos/listPuestos");
    }

    @RequestMapping("/nuevoPuesto")
    public ModelAndView nuevoPuesto(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoPuesto");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idx", puestoService.getIdPuesto(idCompania));
        model.addAttribute("lovCatPuesto", lovsService.getLovs("63", "%"));

        return new ModelAndView("public/gladius/organizacion/puestos/nuevoPuesto");
    }

    @RequestMapping("/insertarPuesto")
    public ModelAndView insertarPuesto(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarPuesto");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexpuesto = request.getParameter("iexpuesto2");
        String iexdespuesto = request.getParameter("iexdespuesto");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexusucrea = usuario;

        Puesto puesto = new Puesto();
        puesto.setIexcodcia(iexcodcia);
        puesto.setIexpuesto(iexpuesto);
        puesto.setIexdespuesto(iexdespuesto);
        puesto.setIexcodcat(iexcodcat);
        puesto.setIexusucrea(iexusucrea);

        puestoService.insertarPuesto(puesto);

        return new ModelAndView("redirect:/listPuestos");
    }

    @RequestMapping("/editarPuesto@{idPuesto}")
    public ModelAndView editarPuesto(ModelMap model, HttpServletRequest request, @PathVariable String idPuesto) {
        logger.info("/editarPuesto");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idPuesto", idPuesto);
        model.addAttribute("lovCatPuesto", lovsService.getLovs("63", "%"));
        model.addAttribute("xPuesto", puestoService.getPuesto(idCompania, idPuesto));

        return new ModelAndView("public/gladius/organizacion/puestos/editarPuesto");
    }

    @RequestMapping("/modificarPuesto")
    public ModelAndView modificarPuesto(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarPuesto");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexpuesto = request.getParameter("iexpuesto2");
        String iexdespuesto = request.getParameter("iexdespuesto");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexusucrea = usuario;

        Puesto puesto = new Puesto();
        puesto.setIexcodcia(iexcodcia);
        puesto.setIexpuesto(iexpuesto);
        puesto.setIexdespuesto(iexdespuesto);
        puesto.setIexcodcat(iexcodcat);
        puesto.setIexusumod(iexusucrea);

        puestoService.actualizarPuesto(puesto);

        return new ModelAndView("redirect:/listPuestos");
    }

    @RequestMapping("/deleterPuesto@{idPuesto}")
    public ModelAndView deleterPuesto(ModelMap model, HttpServletRequest request, @PathVariable String idPuesto) {
        logger.info("/deleterPuesto");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idPuesto", idPuesto);

        Puesto puesto = new Puesto();
        puesto.setIexcodcia(idCompania);
        puesto.setIexpuesto(idPuesto);

        puestoService.eliminarPuesto(puesto);

        return new ModelAndView("redirect:/listPuestos");
    }

}

