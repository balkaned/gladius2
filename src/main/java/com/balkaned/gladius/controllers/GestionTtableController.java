package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
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

@RestController
@Slf4j
public class GestionTtableController {

    @Autowired
    TtableService ttableService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listTablasGen")
    public ModelAndView listTablasGen(ModelMap model, HttpServletRequest request) {
        log.info("/listTablasGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("LstTTablac", ttableService.listarTTablac("%"));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/listTablasGen");
    }

    @RequestMapping("/nuevaTablaGen")
    public ModelAndView nuevaTablaGen(ModelMap model, HttpServletRequest request) {
        log.info("/nuevaTablaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/nuevaTablaGen");
    }

    @RequestMapping("/insertarNuevaTblGen")
    public ModelAndView insertarNuevaTblGen(ModelMap model, HttpServletRequest request) {
        log.info("/insertarNuevaTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodtab = request.getParameter("iexcodtab");
        String iexdestab = request.getParameter("iexdestab");
        String iexlbl1 = request.getParameter("iexlbl1");
        String iexlbl2 = request.getParameter("iexlbl2");
        String iexlbl3 = request.getParameter("iexlbl3");
        String iexlbl4 = request.getParameter("iexlbl4");
        String iexlbl5 = request.getParameter("iexlbl5");
        String iexlbl6 = request.getParameter("iexlbl6");
        String iexlbl7 = request.getParameter("iexlbl7");
        String iexlbl8 = request.getParameter("iexlbl8");
        String iexlblflg1 = request.getParameter("iexlblflg1");
        String iexlblflg2 = request.getParameter("iexlblflg2");
        String iexlblflg3 = request.getParameter("iexlblflg3");
        String iexlblflg4 = request.getParameter("iexlblflg4");
        String iexlblflg5 = request.getParameter("iexlblflg5");
        String iexlblflg6 = request.getParameter("iexlblflg6");
        String iexlblflg7 = request.getParameter("iexlblflg7");
        String iexlblflg8 = request.getParameter("iexlblflg8");
        String iexlblval9 = request.getParameter("iexlblval9");
        String iexlblval10 = request.getParameter("iexlblval10");
        String iexlblval11 = request.getParameter("iexlblval11");
        String iexlblval12 = request.getParameter("iexlblval12");
        String iexlblval13 = request.getParameter("iexlblval13");
        String iexlblval14 = request.getParameter("iexlblval14");
        String iexlblval15 = request.getParameter("iexlblval15");
        String iexlblval16 = request.getParameter("iexlblval16");
        String iexlblflg9 = request.getParameter("iexlblflg9");
        String iexlblflg10 = request.getParameter("iexlblflg10");
        String iexlblflg11 = request.getParameter("iexlblflg11");
        String iexlblflg12 = request.getParameter("iexlblflg12");
        String iexlblflg13 = request.getParameter("iexlblflg13");
        String iexlblflg14 = request.getParameter("iexlblflg14");
        String iexlblflg15 = request.getParameter("iexlblflg15");
        String iexlblflg16 = request.getParameter("iexlblflg16");

        TTablaCabecera p = new TTablaCabecera();
        p.setIexcodtab(iexcodtab);
        p.setIexdestab(iexdestab.toUpperCase());
        p.setIexlbl1(iexlbl1);
        p.setIexlbl2(iexlbl2);
        p.setIexlbl3(iexlbl3);
        p.setIexlbl4(iexlbl4);
        p.setIexlbl5(iexlbl5);
        p.setIexlbl6(iexlbl6);
        p.setIexlbl7(iexlbl7);
        p.setIexlbl8(iexlbl8);
        p.setIexlblflg1(iexlblflg1);
        p.setIexlblflg2(iexlblflg2);
        p.setIexlblflg3(iexlblflg3);
        p.setIexlblflg4(iexlblflg4);
        p.setIexlblflg5(iexlblflg5);
        p.setIexlblflg6(iexlblflg6);
        p.setIexlblflg7(iexlblflg7);
        p.setIexlblflg8(iexlblflg8);
        p.setIexlblval9(iexlblval9);
        p.setIexlblval10(iexlblval10);
        p.setIexlblval11(iexlblval11);
        p.setIexlblval12(iexlblval12);
        p.setIexlblval13(iexlblval13);
        p.setIexlblval14(iexlblval14);
        p.setIexlblval15(iexlblval15);
        p.setIexlblval16(iexlblval16);
        p.setIexlblflg9(iexlblflg9);
        p.setIexlblflg10(iexlblflg10);
        p.setIexlblflg11(iexlblflg11);
        p.setIexlblflg12(iexlblflg12);
        p.setIexlblflg13(iexlblflg13);
        p.setIexlblflg14(iexlblflg14);
        p.setIexlblflg15(iexlblflg15);
        p.setIexlblflg16(iexlblflg16);

        ttableService.insertarTtablac(p);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/editarTblGen@{idTbl}")
    public ModelAndView editarTblGen(ModelMap model, HttpServletRequest request, @PathVariable String idTbl) {
        log.info("/editarTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("ttablacx", ttableService.recuperarTTablac(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/editarTablaGen");
    }

    @RequestMapping("/modificarTblGen")
    public ModelAndView modificarTblGen(ModelMap model, HttpServletRequest request) {
        log.info("/modificarTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodrol = request.getParameter("iexcodrol2");
        String iexdesrol = request.getParameter("iexdesrol");
        String iexlbl1 = request.getParameter("iexlbl1");
        String iexlbl2 = request.getParameter("iexlbl2");
        String iexlbl3 = request.getParameter("iexlbl3");
        String iexlbl4 = request.getParameter("iexlbl4");
        String iexlbl5 = request.getParameter("iexlbl5");
        String iexlbl6 = request.getParameter("iexlbl6");
        String iexlbl7 = request.getParameter("iexlbl7");
        String iexlbl8 = request.getParameter("iexlbl8");
        String iexlblflg1 = request.getParameter("iexlblflg1");
        String iexlblflg2 = request.getParameter("iexlblflg2");
        String iexlblflg3 = request.getParameter("iexlblflg3");
        String iexlblflg4 = request.getParameter("iexlblflg4");
        String iexlblflg5 = request.getParameter("iexlblflg5");
        String iexlblflg6 = request.getParameter("iexlblflg6");
        String iexlblflg7 = request.getParameter("iexlblflg7");
        String iexlblflg8 = request.getParameter("iexlblflg8");
        String iexlblval9 = request.getParameter("iexlblval9");
        String iexlblval10 = request.getParameter("iexlblval10");
        String iexlblval11 = request.getParameter("iexlblval11");
        String iexlblval12 = request.getParameter("iexlblval12");
        String iexlblval13 = request.getParameter("iexlblval13");
        String iexlblval14 = request.getParameter("iexlblval14");
        String iexlblval15 = request.getParameter("iexlblval15");
        String iexlblval16 = request.getParameter("iexlblval16");
        String iexlblflg9 = request.getParameter("iexlblflg9");
        String iexlblflg10 = request.getParameter("iexlblflg10");
        String iexlblflg11 = request.getParameter("iexlblflg11");
        String iexlblflg12 = request.getParameter("iexlblflg12");
        String iexlblflg13 = request.getParameter("iexlblflg13");
        String iexlblflg14 = request.getParameter("iexlblflg14");
        String iexlblflg15 = request.getParameter("iexlblflg15");
        String iexlblflg16 = request.getParameter("iexlblflg16");

        TTablaCabecera p = new TTablaCabecera();
        p.setIexcodtab(iexcodrol);
        p.setIexdestab(iexdesrol.toUpperCase());
        p.setIexlbl1(iexlbl1);
        p.setIexlbl2(iexlbl2);
        p.setIexlbl3(iexlbl3);
        p.setIexlbl4(iexlbl4);
        p.setIexlbl5(iexlbl5);
        p.setIexlbl6(iexlbl6);
        p.setIexlbl7(iexlbl7);
        p.setIexlbl8(iexlbl8);
        p.setIexlblflg1(iexlblflg1);
        p.setIexlblflg2(iexlblflg2);
        p.setIexlblflg3(iexlblflg3);
        p.setIexlblflg4(iexlblflg4);
        p.setIexlblflg5(iexlblflg5);
        p.setIexlblflg6(iexlblflg6);
        p.setIexlblflg7(iexlblflg7);
        p.setIexlblflg8(iexlblflg8);
        p.setIexlblval9(iexlblval9);
        p.setIexlblval10(iexlblval10);
        p.setIexlblval11(iexlblval11);
        p.setIexlblval12(iexlblval12);
        p.setIexlblval13(iexlblval13);
        p.setIexlblval14(iexlblval14);
        p.setIexlblval15(iexlblval15);
        p.setIexlblval16(iexlblval16);
        p.setIexlblflg9(iexlblflg9);
        p.setIexlblflg10(iexlblflg10);
        p.setIexlblflg11(iexlblflg11);
        p.setIexlblflg12(iexlblflg12);
        p.setIexlblflg13(iexlblflg13);
        p.setIexlblflg14(iexlblflg14);
        p.setIexlblflg15(iexlblflg15);
        p.setIexlblflg16(iexlblflg16);

        ttableService.actualizarTTablac(p);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/verDetalleTblGen@{idTbl}")
    public ModelAndView verDetalleTblGen(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTbl) {
        log.info("/verDetalleTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idTbl", idTbl);
        request.getSession().setAttribute("ttablaclbl", ttableService.recuperarTTablac(idTbl));
        model.addAttribute("LstTTablad", ttableService.listarTTablad(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/verDetalleTablasGen");
    }

    @RequestMapping("/addTblGenDetalle")
    public ModelAndView modificarTblGenDetalle(ModelMap model, HttpServletRequest request) {
        log.info("/addTblGenDetalle");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        String iexcodtab = request.getParameter("iexcodtab2");
        String iexkey = request.getParameter("iexkey");
        String desdet = request.getParameter("desdet");

        TTablaDetalle p = new TTablaDetalle();
        p.setIexcodtab(iexcodtab);
        p.setIexkey(iexkey);
        p.setDesdet(desdet);

        ttableService.insertarTtablad(p);

        return new ModelAndView("redirect:/verDetalleTblGen@"+iexcodtab);
    }

    @RequestMapping("/curDetalleTblGen@{idTbl}@{idKey}")
    public ModelAndView verDetalleTblGen(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTbl, @PathVariable String idKey) {
        log.info("/curDetalleTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("idKey", idKey);

        TTablaDetalle ttabladxx = ttableService.recuperarTTablad(idTbl, idKey);
        model.addAttribute("ttabladxx", ttabladxx);

        request.getSession().setAttribute("ttablaclbl", ttableService.recuperarTTablac(idTbl));
        model.addAttribute("LstTTablad", ttableService.listarTTablad(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/verDetalleTablasGen");
    }

    @RequestMapping("/deleteTablaGen@{idTbl}")
    public ModelAndView deleteTablaGen(ModelMap model, HttpServletRequest request, @PathVariable String idTbl) {
        log.info("/deleteTablaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        ttableService.eliminarTTablac(idTbl);
        ttableService.eliminarTTablad(idTbl);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/deletecurDetalleTblGen@{idTbl}@{idKey}")
    public ModelAndView deletecurDetalleTblGen(ModelMap model, HttpServletRequest request,
                                               @PathVariable String idTbl,
                                               @PathVariable String idKey) {
        log.info("/deletecurDetalleTblGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("idKey", idKey);

        ttableService.eliminarTTablade(idTbl, idKey);

        return new ModelAndView("redirect:/verDetalleTblGen@" + idTbl);
    }

}

