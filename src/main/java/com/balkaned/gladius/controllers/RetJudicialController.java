package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.RetencionJudicial;
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
public class RetJudicialController {
    static Logger logger = Logger.getLogger(RetJudicialController.class.getName());
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    RetJudicialService retencionJudService;

    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/retencionJud@{idTrab}")
    public ModelAndView retencionJud(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/retencionJud");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrabXXXXX: " + idTrab);

        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);
        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("LstRetencionDet", retencionJudService.listarRetencionJudicial(emp));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/retencionJudicial/retencionJud");
    }

    @RequestMapping("/nuevaRetencion@{idTrab}")
    public ModelAndView nuevaRetencion(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        logger.info("/nuevaRetencion");

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("lovTipretj", lovsService.getLovs("58", "%"));
        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/retencionJudicial/nuevaRetencion");
    }

    @RequestMapping("/insertarRetencion")
    public ModelAndView insertarRetencion(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarRetencion");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer codcorrel = 0;
        String iexcodcia = request.getParameter("iexcodcia");
        String iexcodtra = request.getParameter("iexcodtra");

        RetencionJudicial ausprg = new RetencionJudicial();
        ausprg.setIexcodcia(Integer.valueOf(iexcodcia));
        ausprg.setIexcodtra(Integer.valueOf(iexcodtra));

        codcorrel = retencionJudService.getIdRetencionJudicial(ausprg);

        if (codcorrel > 0) {
            ausprg.setIexcorrel(codcorrel);
            ausprg.setIextipretjud(request.getParameter("iextipretjud"));
            ausprg.setIexresolucion(request.getParameter("iexresolucion"));
            ausprg.setIexcodpro(Integer.parseInt(request.getParameter("iexcodpro")));
            ausprg.setIexfecini(request.getParameter("iexfecini"));
            ausprg.setIexfecfin(request.getParameter("iexfecfin"));
            ausprg.setIexpordesct(Double.parseDouble(request.getParameter("iexpordesct")));
            ausprg.setIeximpfijo(Double.parseDouble(request.getParameter("ieximpfijo")));
            ausprg.setIexusucrea("1");

            retencionJudService.insertarRetencionJudicial(ausprg);
        }

        return new ModelAndView("redirect:/retencionJud@" + iexcodtra);
    }

}

