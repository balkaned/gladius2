package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.Compania;
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
public class CompaniasController {
    static Logger logger = Logger.getLogger(CompaniasController.class.getName());

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listCompanias")
    public ModelAndView listCompanias(ModelMap model, HttpServletRequest request) {
        logger.info("/listCompanias");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstCompania", companiaService.listarTodo());

        return new ModelAndView("public/gladius/configuracion/companias/listCompanias");
    }

    @RequestMapping("/nuevaCompania")
    public ModelAndView nuevaCompania(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaCompania");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovTipAct", lovsService.getLovs("1", "%"));

        return new ModelAndView("public/gladius/configuracion/companias/nuevaCompania");
    }

    @RequestMapping("/insertarCompania")
    public ModelAndView insertarCompania(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarCompania");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Compania cia = new Compania();

        cia.setIdCodcia(Integer.parseInt(request.getParameter("iexcodcia")));
        cia.setDescCia(request.getParameter("iexdescia"));
        cia.setDescCiaCorto(request.getParameter("iexdescorto"));
        cia.setNroRuc(request.getParameter("iexnroruc"));
        cia.setDireccionCia(request.getParameter("iexdireccion"));
        cia.setNroTelfCia(request.getParameter("iexnrotelf"));
        cia.setIdActividadCia(request.getParameter("iexcodact"));
        cia.setNomRepesentante(request.getParameter("iexrepnombre"));
        cia.setDesCargoRep(request.getParameter("iexrepcargo"));
        cia.setNroDocuRep(request.getParameter("iexrepdocid"));
        cia.setUrlLogo(request.getParameter("iexreplogo"));
        cia.setUsuCrea(usuario);
        cia.setIexurlfileserver(request.getParameter("iexurlfileserver"));
        cia.setIexurlfilereport(request.getParameter("iexurlfilereport"));

        companiaService.insertarCompania(cia);

        return new ModelAndView("redirect:/listCompanias");
    }

    @RequestMapping("/editarCompania@{idCia}")
    public ModelAndView editarCompania(ModelMap model, HttpServletRequest request, @PathVariable String idCia) {
        logger.info("/editarCompania");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idCia", idCia);
        model.addAttribute("lovTipAct", lovsService.getLovs("1", "%"));
        model.addAttribute("xCia", companiaService.getCompania(Integer.valueOf(idCia)));
        model.addAttribute("xCiaFij", companiaService.listarCiaxcon(Integer.valueOf(idCia), "1"));
        model.addAttribute("xCiaVar", companiaService.listarCiaxcon(Integer.valueOf(idCia), "2"));
        model.addAttribute("lovConcepto", lovsService.getConceptoLov());

        return new ModelAndView("public/gladius/configuracion/companias/editarCompania");
    }

    @RequestMapping("/modificarCompania")
    public ModelAndView modificarCompania(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarCompania");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Compania cia = new Compania();
        cia.setIdCodcia(Integer.parseInt(request.getParameter("iexcodcia2")));
        cia.setDescCia(request.getParameter("iexdescia"));
        cia.setDescCiaCorto(request.getParameter("iexdescorto"));
        cia.setNroRuc(request.getParameter("iexnroruc"));
        cia.setDireccionCia(request.getParameter("iexdireccion"));
        cia.setNroTelfCia(request.getParameter("iexnrotelf"));
        cia.setIdActividadCia(request.getParameter("iexcodact"));
        cia.setNomRepesentante(request.getParameter("iexrepnombre"));
        cia.setDesCargoRep(request.getParameter("iexrepcargo"));
        cia.setNroDocuRep(request.getParameter("iexrepdocid"));
        cia.setUrlLogo(request.getParameter("iexreplogo"));
        cia.setUsuCrea(usuario);
        cia.setIexurlfilereport(request.getParameter("iexurlfilereport"));

        cia.setIexflgsource(request.getParameter("iexflgsource"));
        cia.setIexurlfileserver(request.getParameter("iexurlfileserver"));
        cia.setIexportsource(request.getParameter("iexportsource"));
        cia.setIexususource(request.getParameter("iexususource"));
        cia.setIexpasssource(request.getParameter("iexpasssource"));
        cia.setIexsourcedes(request.getParameter("iexsourcedes"));
        cia.setIexregiondes(request.getParameter("iexregiondes"));

        companiaService.actualizarCompania(cia);

        return new ModelAndView("redirect:/listCompanias");
    }

    @RequestMapping("/insertarConceptoComp")
    public ModelAndView insertarConceptoCom(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarConceptoComp");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String pcodcia = request.getParameter("idcia");
        String pcodcon = request.getParameter("id_concepto");
        String ptipreg = request.getParameter("tipo_reg");
        companiaService.insertarCiaxcion(Integer.valueOf(pcodcia), pcodcon, ptipreg);

        return new ModelAndView("redirect:/editarCompania@" + pcodcia);
    }

    @RequestMapping("/delConceptoComp@{idCia}@{idCon}")
    public ModelAndView delConceptoComp(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idCia,
                                        @PathVariable String idCon) {
        logger.info("/delConceptoComp");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        companiaService.deleteCiaxcon(Integer.valueOf(idCia), idCon);

        return new ModelAndView("redirect:/editarCompania@" + idCia);
    }

    @RequestMapping("/deleteCompania@{idComp}")
    public ModelAndView deleteCompania(ModelMap model, HttpServletRequest request, @PathVariable String idComp) {
        logger.info("/deleteCompania");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idComp", idComp);

        Compania com = new Compania();
        com.setIdCodcia(Integer.valueOf(idComp));
        companiaService.eliminarCompania(com);

        return new ModelAndView("redirect:/listCompanias");
    }

}

