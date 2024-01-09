package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.services.ConceptoXProcesoService;
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
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Slf4j
public class AsignacionContableController {
    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @Autowired
    ConceptoXProcesoService conceptoXProcesoService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listAsignacionContable@{idProceso}")
    public ModelAndView listAsignacionContable(
            ModelMap model, HttpServletRequest request, @PathVariable("idProceso") Integer idProceso) {
        log.info("/listAsignacionContable");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        String slc_grpconcepto = request.getParameter("slc_grpconcepto");
        HttpSession session = request.getSession(true);
        Integer xcodcia = (Integer) session.getAttribute("codcia");
        //ProcesoPlanilla pplanilla = procesoPlanillaService.listarPorProcodpro(idProceso);
        //request.setAttribute("pplanillax", pplanilla.getDesProceso());
        request.setAttribute("idxproceso", idProceso);
        List<ConceptoXProceso> listap = conceptoXProcesoService.listarTipconCtb(xcodcia, idProceso, slc_grpconcepto);
        request.setAttribute("LstconceptoxProcesopCtb", listap);
        request.setAttribute("slc_grpconcepto", slc_grpconcepto);
        return new ModelAndView("public/gladius/confPlanilla/procesosyform/asignacionContable/listaAsignacionContable");
    }
}
