package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.services.ConceptoService;
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
import java.util.logging.Logger;

@RestController
@Slf4j
public class ConceptoController {
    @Autowired
    ConceptoService conceptoService;

    @Autowired
    Sessionattributes sessionattributes;

    private static final String basePath = "public/gladius/confPlanilla/conceptos";
    private static final String listConceptos = "/listConceptos";
    private static final String nuevoConcepto = "/nuevoConcepto";
    private static final String insertarConcepto = "/insertarConcepto";
    private static final String editarConcepto = "/editarConcepto";
    private static final String actualizarConcepto = "/actualizarConcepto";

    @RequestMapping(listConceptos)
    public ModelAndView listConceptos(
            ModelMap model,
            HttpServletRequest request) {
        log.info(listConceptos);

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        List<Concepto> conceptosList = conceptoService.listConceptos();
        model.addAttribute("conceptosList", conceptosList);
        return new ModelAndView(basePath + listConceptos);
    }

    @RequestMapping(nuevoConcepto)
    public ModelAndView nuevoConcepto(
            ModelMap model,
            HttpServletRequest request) {
        log.info(nuevoConcepto);

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        return new ModelAndView(basePath + nuevoConcepto);
    }

    @RequestMapping(insertarConcepto)
    public ModelAndView insertarConcepto(
            ModelMap model,
            HttpServletRequest request) {
        log.info(insertarConcepto);

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        conceptoService.insertarConcepto(getConcepto(request));
        return new ModelAndView("redirect:" + listConceptos);
    }

    @RequestMapping(editarConcepto + "@{idParam}")
    public ModelAndView editarConcepto(
            ModelMap model,
            HttpServletRequest request,
            @PathVariable String idParam) {
        log.info(editarConcepto + "@{idParam}");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Concepto xConcepto = conceptoService.getById(idParam);
        model.addAttribute("xConcepto", xConcepto);
        return new ModelAndView(basePath + editarConcepto);
    }

    @RequestMapping(actualizarConcepto)
    public ModelAndView actualizarConcepto(
            ModelMap model,
            HttpServletRequest request) {
        log.info(actualizarConcepto);

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        conceptoService.actualizarConcepto(getConcepto(request));
        return new ModelAndView("redirect:" + listConceptos);
    }

    private static Concepto getConcepto(HttpServletRequest request) {
        return Concepto.builder().idProceso(Integer.parseInt(request.getParameter("idProceso")))
                .codConcepto(request.getParameter("codConcepto")).desConcepto(request.getParameter("desConcepto"))
                .desVariable(request.getParameter("desVariable")).descripcion(request.getParameter("descripcion"))
                .desAbreviacion(request.getParameter("desAbreviacion")).build();
    }
}
