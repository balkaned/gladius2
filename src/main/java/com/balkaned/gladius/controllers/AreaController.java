package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
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
public class AreaController {
    @Autowired
    AreaService areaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;


    @RequestMapping("/listAreas")
    public ModelAndView listAreas(ModelMap model, HttpServletRequest request) {
        log.info("/listAreas");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<Area> areasList = areaService.listarArea(idCompania, "");
        log.info("areasList: " + areasList);
        model.addAttribute("areasList", areasList);

        return new ModelAndView("public/gladius/organizacion/areas/listAreas");
    }

    @RequestMapping("/nuevaArea")
    public ModelAndView nuevaArea(ModelMap model, HttpServletRequest request) {
        log.info("/nuevaArea");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovCatArea", lovsService.getLovs("62", "%"));
        model.addAttribute("lovArea", areaService.listarArea(idCompania, ""));
        model.addAttribute("idx", areaService.getIdArea(idCompania));

        return new ModelAndView("public/gladius/organizacion/areas/nuevaArea");
    }

    @RequestMapping("/insertarArea")
    public ModelAndView insertarArea(ModelMap model, HttpServletRequest request) {
        log.info("/insertarArea");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexcodarea = request.getParameter("iexcodarea2");
        String iexdesarea = request.getParameter("iexdesarea");
        String iexdesarea_des = request.getParameter("iexdesarea_descripcion");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexareapadre = request.getParameter("iexareapadre");
        String iexusucrea = usuario;

        Area area = new Area();
        area.setIexcodcia(iexcodcia);
        area.setIexcodarea(iexcodarea);
        area.setIexdesarea(iexdesarea);
        area.setIexdesarea_descripcion(iexdesarea_des);
        area.setIexcodcat(iexcodcat);
        area.setIexareapadre(iexareapadre);
        area.setIexusucrea(iexusucrea);

        areaService.insertarArea(area);

        return new ModelAndView("redirect:/listAreas");
    }

    @RequestMapping("/editarArea@{idArea}")
    public ModelAndView editarArea(ModelMap model, HttpServletRequest request, @PathVariable String idArea) {
        log.info("/editarArea");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovCatArea", lovsService.getLovs("62", "%"));
        model.addAttribute("lovArea", areaService.listarArea(idCompania, ""));
        model.addAttribute("xArea", areaService.getArea(idCompania, idArea));

        return new ModelAndView("public/gladius/organizacion/areas/editarArea");
    }

    @RequestMapping("/modificarArea")
    public ModelAndView modificarAreaa(ModelMap model, HttpServletRequest request) {
        log.info("/modificarArea");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexcodarea = request.getParameter("iexcodarea2");
        String iexdesarea = request.getParameter("iexdesarea");
        String iexdesarea_des = request.getParameter("iexdesarea_descripcion");
        String iexcodcat = request.getParameter("iexcodcat");
        String iexareapadre = request.getParameter("iexareapadre");
        String iexusucrea = usuario;

        Area area = new Area();
        area.setIexcodcia(iexcodcia);
        area.setIexcodarea(iexcodarea);
        area.setIexdesarea(iexdesarea);
        area.setIexdesarea_descripcion(iexdesarea_des);
        area.setIexcodcat(iexcodcat);
        area.setIexareapadre(iexareapadre);
        area.setIexusumod(iexusucrea);

        areaService.actualizarArea(area);

        return new ModelAndView("redirect:/listAreas");
    }

    @RequestMapping("/deleteArea@{idArea}")
    public ModelAndView deleteArea(ModelMap model, HttpServletRequest request, @PathVariable String idArea) {
        log.info("/deleteArea");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idArea", idArea);

        Area area = new Area();
        area.setIexcodcia(idCompania);
        area.setIexcodarea(idArea);

        areaService.eliminarArea(area);

        return new ModelAndView("redirect:/listAreas");
    }

}

