package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class GestionReportesController {
    static Logger logger = Logger.getLogger(GestionReportesController.class.getName());

    @Autowired
    PlanillaService planillaService;
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;
    @Autowired
    LovsService lovsService;
    @Autowired
    EmpleadoService empleadoService;
    @Autowired
    ConceptoService conceptoService;
    @Autowired
    CompaniaService companiaService;
    @Autowired
    EmpAcumService empAcumService;
    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listReporte5taNomina")
    public ModelAndView listReporte5taNomina(ModelMap model, HttpServletRequest request) {
        logger.info("/listReporte5taNomina");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(idCompania);
        List<Empleado> listarEmp = empleadoService.listarEmpleado(Empleado);


        String v_idproceso = "";
        String v_idperiodo = "";

        String anio = request.getParameter("peranio");
        String iexcodtra = request.getParameter("percodtra");

        List<PlaProPeriodo> lista = new ArrayList<>();
        Empleado empleado = new Empleado();
        EmpAcum xEmpAcumAnio = new EmpAcum();
        try {
            if (iexcodtra != null && !iexcodtra.isEmpty() && iexcodtra.matches("\\d+")) {

                empleado = empleadoService.recuperarCabecera(idCompania, Integer.valueOf(iexcodtra));
                lista = planillaService.listPla5ta(idCompania, anio, Integer.parseInt(iexcodtra));
                xEmpAcumAnio = empAcumService.getEmpAcum(idCompania, Integer.parseInt(iexcodtra), anio);
            } else {
                logger.info("Error no encontrado" + iexcodtra);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        model.addAttribute("Res_planilla5ta", lista);
        model.addAttribute("peranio", anio);
        model.addAttribute("percodtra", iexcodtra);
        model.addAttribute("LstEmpleadoRes", listarEmp);
        model.addAttribute("fichaEmp", empleado);
        model.addAttribute("xEmpAcumAnio", xEmpAcumAnio);

        return new ModelAndView("public/gladius/gestionReporte/reporte5Categoria/listReporte5taNomina");
    }


    @RequestMapping("/listReportePlanillas")
    public ModelAndView listReportePlanillas(ModelMap model, HttpServletRequest request) {
        logger.info("/listReportePlanillas");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String v_idproceso = "";
        String v_idperiodo = "";
        String v_nroper = request.getParameter("nroper");
        String v_nroper2 = request.getParameter("nroper2");
        v_idproceso = request.getParameter("codpro");


        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionReporte/reportePlanilla/listReportePlanillas");
    }


    @RequestMapping("/listReportePlanillaxConcepto")
    public ModelAndView listReportePlanillaxConcepto(ModelMap model, HttpServletRequest request) {
        logger.info("/listReportePlanillaxConcepto");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String codcon = request.getParameter("codcon");
        String perini = request.getParameter("perini");
        String perfin = request.getParameter("perfin");


        List<Concepto> listacon = (List<Concepto>) model.getAttribute("listacon");
        List<Concepto> lista = (List<Concepto>) model.getAttribute("lstConcepto");

        if (listacon == null) {
            listacon = new ArrayList<>();
            model.addAttribute("listacon", listacon);
        }
        if (lista == null) lista = conceptoService.listardet();


        Concepto condes = conceptoService.recuperar(codcon);
        if (condes != null) {
            listacon.add(condes);
        } else {
            logger.info("Error en " + condes);
        }

        logger.info("lstConcepto" + lista);
        logger.info("listacon" + listacon);

        model.addAttribute("lstConcepto", lista);
        model.addAttribute("xperini", perini);
        model.addAttribute("xperfin", perfin);


        return new ModelAndView("public/gladius/gestionReporte/listReportePlanillasXConceptos/listReportePlanillaxConcepto");
    }

    @RequestMapping("/listReporteNominaxPersona")
    public ModelAndView listReporteNominaxPersona(ModelMap model, HttpServletRequest request) {
        logger.info("/listReporteNominaxPersona");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        Empleado Empleado = new Empleado();
        Empleado.setIexcodcia(idCompania);
        List<Empleado> listarEmp = empleadoService.listarEmpleado(Empleado);


        String perini = request.getParameter("perini");
        String perfin = request.getParameter("perfin");
        String iexcodtra = request.getParameter("codtra");
        String codpro = request.getParameter("codpro");

        try {
            if (iexcodtra != null && !iexcodtra.isEmpty() && iexcodtra.matches("\\d+")) {

                Empleado empleado = new Empleado();
                empleado = empleadoService.recuperarCabecera(idCompania, Integer.valueOf(iexcodtra));
                if (codpro == null || codpro.trim() == "") {

                    List<PlaProPeriodo> lista = planillaService.listAllPlaPerTra(idCompania, Integer.parseInt(iexcodtra), perini, perfin);
                    model.addAttribute("Res_planAllPerTra", lista);
                    model.addAttribute("perini", perini);
                    model.addAttribute("perfin", perfin);
                    model.addAttribute("codtra", iexcodtra);
                    model.addAttribute("fichaEmp", empleado);

                } else {
                    List<PlaProPeriodo> lista = planillaService.listAllPlaPerTraPro(idCompania, Integer.parseInt(iexcodtra), Integer.parseInt(codpro), perini, perfin);
                    model.addAttribute("Res_planAllPerTra", lista);
                    model.addAttribute("perini", perini);
                    model.addAttribute("perfin", perfin);
                    model.addAttribute("codtra", iexcodtra);
                    model.addAttribute("codpro", codpro);
                    model.addAttribute("fichaEmp", empleado);

                }

            } else {
                logger.info("Error no encontrado" + iexcodtra);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }



        model.addAttribute("LstEmpleadoRes", listarEmp);
        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        return new ModelAndView("public/gladius/gestionReporte/ReportePlanillaXProceso/listReporteNominaxPersona");
    }
}
