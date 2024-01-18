package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

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

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("Lovs_regimen",lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/buscarPlanillaGen")
    public ModelAndView buscarPlanillaGen(ModelMap model, HttpServletRequest request) {
        log.info("/buscarPlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodreg = request.getParameter("iexcodreg");
        String iexpermes = request.getParameter("iexpermes");

        model.addAttribute("Lovs_regimen",lovsService.getRegimenProc());
        model.addAttribute("List_Procesos",procesoPlanillaService.listarProRegpla(idCompania,iexcodreg,iexpermes));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/insertarPeriodoPlan")
    public ModelAndView insertarPeriodoPlan(ModelMap model, HttpServletRequest request) {
        log.info("/insertarPeriodoPlan");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String proceso = request.getParameter("idproceso");
        Integer p_Iexcodpro =0;

        if( request.getParameter("idproceso")==null){
            p_Iexcodpro =0;
        }else {
            p_Iexcodpro=Integer.parseInt(proceso);
        }

        String p_Iexanio = request.getParameter("idanio");
        String p_Iexpermes = request.getParameter("idpermes");
        String p_Iexnroper = request.getParameter("idperiodo");
        String p_Iexfecini = request.getParameter("fecini");
        String p_Iexfecfin = request.getParameter("fecfin");
        String p_Timerfecini = request.getParameter("fecinit");
        String p_Timerfecfin = request.getParameter("fecfint");
        String p_Iexfecpago = request.getParameter("fecpago");
        String p_Iexfeccerti = request.getParameter("feccerti");

        ProcesoPeriodo p = new ProcesoPeriodo();
        p.setIexcodcia(idCompania);
        p.setIexcodpro(p_Iexcodpro);
        p.setIexanio(p_Iexanio);
        p.setIexpermes(p_Iexpermes);
        p.setIexnroper(p_Iexnroper);
        p.setIexfecini(p_Iexfecini);
        p.setIexfecfin(p_Iexfecfin);
        p.setTimerfecini(p_Timerfecini);
        p.setTimerfecfin(p_Timerfecfin);
        p.setIexfecpago(p_Iexfecpago);
        p.setIexfeccerti(p_Iexfeccerti);

        procesoPlanillaService.insertarProper(p);

        return new ModelAndView("redirect:/listPlanillaGeneral");
    }
}
