package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.services.AreaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import net.sf.jasperreports.engine.export.HtmlExporter;
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
public class PlanillaController {
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;


    @RequestMapping("/listPlanillaGeneral")
    public ModelAndView listPlanillaGeneral(ModelMap model, HttpServletRequest request) {
        log.info("/listPlanillaGeneral");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("Lovs_regimen",lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/buscarPlanillaGen")
    public ModelAndView buscarPlanillaGen(ModelMap model, HttpServletRequest request) {
        log.info("/buscarPlanillaGen");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodreg = request.getParameter("iexcodreg");
        String iexpermes = request.getParameter("iexpermes");

        model.addAttribute("Lovs_regimen",lovsService.getRegimenProc());
        model.addAttribute("List_Procesos",procesoPlanillaService.listarProRegpla(idCompania,iexcodreg,iexpermes));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    /*@RequestMapping("/insertarArea")
    public ModelAndView insertarArea(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarArea");

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
        logger.info("/editarArea");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovCatArea", lovsService.getLovs("62", "%"));
        model.addAttribute("lovArea", areaService.listarArea(idCompania, ""));
        model.addAttribute("xArea", areaService.getArea(idCompania, idArea));

        return new ModelAndView("public/gladius/organizacion/areas/editarArea");
    }

    @RequestMapping("/modificarArea")
    public ModelAndView modificarAreaa(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarArea");

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
        logger.info("/deleteArea");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idArea", idArea);

        Area area = new Area();
        area.setIexcodcia(idCompania);
        area.setIexcodarea(idArea);

        areaService.eliminarArea(area);

        return new ModelAndView("redirect:/listAreas");
    }*/

}

