package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ConceptoxAgrup;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.WorkerThread;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.PlanillaService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.services.SueldoService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    @Autowired
    SueldoService sueldoService;

    @RequestMapping("/listPlanillaGeneral")
    public ModelAndView listPlanillaGeneral(ModelMap model, HttpServletRequest request) {
        log.info("/listPlanillaGeneral");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/buscarPlanillaGen")
    public ModelAndView buscarPlanillaGen(ModelMap model, HttpServletRequest request) {
        log.info("/buscarPlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodreg = request.getParameter("iexcodreg");
        String iexpermes = request.getParameter("iexpermes");

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("List_Procesos", procesoPlanillaService.listarProRegpla(idCompania, iexcodreg, iexpermes));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/planillaGeneral");
    }

    @RequestMapping("/insertarPeriodoPlan")
    public ModelAndView insertarPeriodoPlan(ModelMap model, HttpServletRequest request) {
        log.info("/insertarPeriodoPlan");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String proceso = request.getParameter("idproceso");
        Integer p_Iexcodpro = 0;

        if (request.getParameter("idproceso") == null) {
            p_Iexcodpro = 0;
        } else {
            p_Iexcodpro = Integer.parseInt(proceso);
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

    @RequestMapping(value = "/getDatosPeriodo", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getDatosPeriodo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/getDatosPeriodo");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String periodo = request.getParameter("periodo");

        ProcesoPeriodo proper = procesoPlanillaService.recuperarPeriodo2(idCompania, codproceso, periodo);

        String json = new Gson().toJson(proper);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping("/modificarPeriodoPlan")
    public ModelAndView modificarPeriodoPlan(ModelMap model, HttpServletRequest request) {
        log.info("/modificarPeriodoPlan");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String proceso = request.getParameter("idprocesoEdit");
        Integer p_Iexcodpro = 0;

        if (request.getParameter("idprocesoEdit") == null) {
            p_Iexcodpro = 0;
        } else {
            p_Iexcodpro = Integer.parseInt(proceso);
        }

        String p_Iexanio = request.getParameter("idanioEdit");
        String p_Iexpermes = request.getParameter("idpermesEdit");
        String p_Iexnroper = request.getParameter("idperiodoEdit");
        String p_Iexfecini = request.getParameter("feciniEdit");
        String p_Iexfecfin = request.getParameter("fecfinEdit");
        String p_Timerfecini = request.getParameter("fecinitEdit");
        String p_Timerfecfin = request.getParameter("fecfintEdit");
        String p_Iexfecpago = request.getParameter("fecpagoEdit");
        String p_Iexfeccerti = request.getParameter("feccertiEdit");

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

        procesoPlanillaService.actualizarProper(p);

        return new ModelAndView("redirect:/listPlanillaGeneral");
    }

    @RequestMapping("/listarDetallePlanillaGen@{codreg}@{codproceso}@{periodo}")
    public ModelAndView listarDetallePlanillaGen(ModelMap model, HttpServletRequest request,
                                                 @PathVariable Integer codreg,
                                                 @PathVariable Integer codproceso,
                                                 @PathVariable String periodo) {
        log.info("/listarDetallePlanillaGen");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("iexcodreg", codreg);
        model.addAttribute("iexcodpro", codproceso);
        model.addAttribute("iexperiodo", periodo);

        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(codproceso), periodo));
        List<PlaProPeriodo> lista = planillaService.listPlaProper(idCompania, codproceso, periodo, -1, 1, "%");
        model.addAttribute("LstPlanillaRes", lista);

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

    @RequestMapping("/procesarPlanilla")
    public ModelAndView procesarPlanilla(ModelMap model, HttpServletRequest request) {
        log.info("/procesarPlanilla");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String accion = request.getParameter("accion");
        String iexcodreg = request.getParameter("iexcodreg");
        Integer iexcodpro = Integer.valueOf(request.getParameter("iexcodpro"));
        String iexperiodo = request.getParameter("iexperiodo");
        Integer iexcodtra = Integer.valueOf(request.getParameter("iexcodtra"));
        Integer iexcorrel = Integer.valueOf(request.getParameter("iexcorrel"));
        String grupopla = request.getParameter("grppla");

        if (accion.equals("INIPRO")) {
            planillaService.iniPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, grupopla, user);
        }

        if (accion.equals("CALFASIST")) {
            planillaService.calificacion_tiempo_mas(idCompania, iexcodpro, iexperiodo, -1, 1);
        }

        if (accion.equals("EXEPRO")) {
            planillaService.iniPlaProper_proc(idCompania, iexcodpro, iexperiodo, -1, 1, grupopla, user);

            // Obtiene la lista de trabajadores
            planillaService.timeIniexe(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);

            // Obtener la lista de conceptos  de todos las personas
            Date utilDate = new Date();
            log.info("************** Inicia Proceso de planilla :" + utilDate + "  *****************");

            // La lista de todas las personas
            String txt_buscar = request.getParameter("txt_buscar");

            if (txt_buscar == null) {
                txt_buscar = "%";
            }

            // Obtiene el universo de trabajadores a procesar
            List<PlaProPeriodo> lp_persona = planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, txt_buscar);

            // Ejecuta la formulacion
            // daoplanilla.procesarFormulaMix(lp_persona,v_codcia , Integer.parseInt(v_idproceso), v_idperiodo ,Integer.parseInt(iexcodtra), Integer.parseInt(iexcorrel));

            Iterator<PlaProPeriodo> pi;
            PlaProPeriodo pi_persona = null;
            Integer counter = 0;

            List<PlaProPeriodo> lp_persona_s1 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s2 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s3 = new ArrayList<PlaProPeriodo>();
            List<PlaProPeriodo> lp_persona_s4 = new ArrayList<PlaProPeriodo>();

            pi = lp_persona.iterator();

            while (pi.hasNext()) {
                pi_persona = pi.next();

                if (counter >= 0 && counter <= 350) {
                    lp_persona_s1.add(pi_persona);
                } else if (counter > 350 && counter <= 700) {
                    lp_persona_s2.add(pi_persona);
                } else if (counter > 700 && counter <= 1000) {
                    lp_persona_s3.add(pi_persona);
                } else if (counter > 1000 && counter <= 1350) {
                    lp_persona_s4.add(pi_persona);
                }

                log.info(" MAESTRO ===>  <--------->  p_codtra =" + pi_persona.getDestra() + " ");
                counter++;
            }

            ExecutorService executor = Executors.newFixedThreadPool(4);

            Runnable worker = new WorkerThread("Hilo 1", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s1, 1);
            executor.execute(worker);

            Runnable worker2 = new WorkerThread("Hilo 2", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s2, 2);
            executor.execute(worker2);

            Runnable worker3 = new WorkerThread("Hilo 3", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s3, 3);
            executor.execute(worker3);

            Runnable worker4 = new WorkerThread("Hilo 4", idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, lp_persona_s4, 4);
            executor.execute(worker4);

            /////////////////////////////////////////////////////////////////////
            executor.shutdown();
            while (!executor.isTerminated()) {
            }

            log.info("Finished all threads");

            planillaService.guardarNomina2020(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);

            Date utilDatef = new Date();
            log.info("************** Fin de  Proceso de planilla :" + utilDatef + " **********************");

            planillaService.timeFinexe(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel);
        }

        if (accion.equals("DELPRO")) {
            planillaService.delPlaProper(idCompania, iexcodpro, iexperiodo, iexcodtra, iexcorrel, grupopla, user);
        }

        if (accion.equals("QRYPLA")) {
            model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
            model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));
        }

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);

        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
        model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detallePlanillaGeneral");
    }

    @RequestMapping("/verDetalleVariable@{iexcodreg}@{iexcodpro}@{iexperiodo}")
    public ModelAndView verDetalleVariable(ModelMap model, HttpServletRequest request,
                                           @PathVariable Integer iexcodreg,
                                           @PathVariable Integer iexcodpro,
                                           @PathVariable String iexperiodo) {
        log.info("/verDetalleVariable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("iexcodreg", iexcodreg);
        model.addAttribute("iexcodpro", iexcodpro);
        model.addAttribute("iexperiodo", iexperiodo);
        model.addAttribute("xproplaper", procesoPlanillaService.recuperarPeriodo2(idCompania, Integer.valueOf(iexcodpro), iexperiodo));
        model.addAttribute("LstPlanillaRes", planillaService.listPlaProper(idCompania, iexcodpro, iexperiodo, -1, 1, "%"));

        model.addAttribute("lovConcepProVar", sueldoService.ListConcepProVar(idCompania, iexcodpro, "2"));
        model.addAttribute("fdatavar", sueldoService.obtenerEmpResvar(idCompania, iexcodpro, iexperiodo, 1));

        return new ModelAndView("public/gladius/gestionDePlanilla/planillaGeneral/detalleVariablePlanGen");
    }

}

