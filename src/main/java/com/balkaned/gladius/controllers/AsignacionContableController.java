package com.balkaned.gladius.controllers;

import com.balkaned.gladius.services.ConceptoXProcesoService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class AsignacionContableController {

    @Autowired
    ConceptoXProcesoService conceptoXProcesoService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listAsignacionContable@{idProceso}")
    public ModelAndView listAsignacionContable(ModelMap model, HttpServletRequest request,
                                               @PathVariable Integer idProceso) {
        log.info("/listAsignacionContable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) { return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String slc_grpconcepto = request.getParameter("slc_grpconcepto");

        model.addAttribute("idxproceso", idProceso);
        model.addAttribute("LstconceptoxProcesopCtb", conceptoXProcesoService.listarTipconCtb(idCompania, idProceso, slc_grpconcepto));
        model.addAttribute("slc_grpconcepto", slc_grpconcepto);

        return new ModelAndView("public/gladius/confPlanilla/procesosyform/asignacionContable/listaAsignacionContable");
    }
}
