package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.*;
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
public class AreaController {
    static Logger logger = Logger.getLogger(AreaController.class.getName());
    @Autowired
    AreaService areaService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @RequestMapping("/listAreas")
    public ModelAndView listAreas(ModelMap model, HttpServletRequest request) {
        logger.info("/listAreas");

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

        logger.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        List<Area> areasList = areaService.listarArea(idCompania, "");

        logger.info("areasList: " + areasList);

        model.addAttribute("areasList", areasList);

        return new ModelAndView("public/gladius/organizacion/areas/listAreas");
    }

    @RequestMapping("/nuevaArea")
    public ModelAndView nuevaArea(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaArea");
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        model.addAttribute("lovCatArea", lovsService.getLovs("62", "%"));
        model.addAttribute("lovArea", areaService.listarArea(idCompania, ""));
        model.addAttribute("idx", areaService.getIdArea(idCompania));

        return new ModelAndView("public/gladius/organizacion/areas/nuevaArea");
    }

    @RequestMapping("/insertarArea")
    public ModelAndView insertarArea(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarArea");
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

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
        logger.info("/editarArea");
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        model.addAttribute("idArea", idArea);
        model.addAttribute("lovCatArea", lovsService.getLovs("62", "%"));
        model.addAttribute("lovArea", areaService.listarArea(idCompania, ""));
        model.addAttribute("xArea", areaService.getArea(idCompania, idArea));

        return new ModelAndView("public/gladius/organizacion/areas/editarArea");
    }

    @RequestMapping("/modificarArea")
    public ModelAndView modificarAreaa(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarArea");
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

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
        logger.info("/deleteArea");
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        model.addAttribute("idArea", idArea);


        return new ModelAndView("redirect:/listAreas");
    }

}

