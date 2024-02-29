package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.PlanillaService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
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
public class PlanillaController {
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    PlanillaService planillaService;

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

        model.addAttribute("iexcodreg",iexcodreg);
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

    @RequestMapping("/listarDetallePlanillaGen@{codreg}@{codproceso}@{periodo}")
    public ModelAndView listarDetallePlanillaGen(ModelMap model, HttpServletRequest request,
                                                 @PathVariable Integer codreg,
                                                 @PathVariable Integer codproceso,
                                                 @PathVariable String periodo) {
        log.info("/listarDetallePlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("iexcodreg",codreg);
        model.addAttribute("iexcodpro",codproceso);
        model.addAttribute("iexperiodo",periodo);

        model.addAttribute("xproplaper",procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(codproceso),periodo));
        List<PlaProPeriodo> lista = planillaService.listPlaProper(idCompania,codproceso,periodo,-1,1,"%");
        model.addAttribute("LstPlanillaRes", lista);

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

    @RequestMapping("/procesarPlanilla")
    public ModelAndView procesarPlanilla(ModelMap model, HttpServletRequest request) {
        log.info("/procesarPlanilla");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        String accion = request.getParameter("accion");
        String iexcodreg = request.getParameter("iexcodreg");
        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        String iexperiodo = request.getParameter("iexperiodo");
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String grupopla = request.getParameter("grppla");

        if(accion.equals("INIPRO")){
            planillaService.iniPlaProper(idCompania,iexcodpro,iexperiodo,iexcodtra,iexcorrel,grupopla,user);
        }

        if(accion.equals("CALFASIST")){
            planillaService.calificacion_tiempo_mas(idCompania,iexcodpro,iexperiodo,-1,1);
        }

        if(accion.equals("EXEPRO")){
            planillaService.iniPlaProper_proc(idCompania,iexcodpro,iexperiodo,-1,1,grupopla,user);
        }

        model.addAttribute("iexcodreg",iexcodreg);
        model.addAttribute("iexcodpro",iexcodpro);
        model.addAttribute("iexperiodo",iexperiodo);
        model.addAttribute("xproplaper",procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro),iexperiodo));

        List<PlaProPeriodo> lista = planillaService.listPlaProper(idCompania,iexcodpro,iexperiodo,-1,1,"%");
        model.addAttribute("LstPlanillaRes", lista);

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

}

