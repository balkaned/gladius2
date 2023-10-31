package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.services.ConceptoService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Log4j2
@RestController
public class ConceptoController {
    @Autowired
    ConceptoService conceptoService;

    @RequestMapping("/listConceptos")
    public ModelAndView listConceptos(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/listConceptos");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        List<Concepto> conceptosList = conceptoService.listConceptos();

        log.info("conceptosList: " + conceptosList);

        model.addAttribute("conceptosList", conceptosList);

        return new ModelAndView("public/gladius/confPlanilla/conceptos/listConceptos");
    }

    @RequestMapping("/nuevoConcepto")
    public ModelAndView nuevoConcepto(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/nuevoConcepto");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        return new ModelAndView("public/gladius/confPlanilla/conceptos/nuevoConcepto");
    }

    @RequestMapping("/insertarConcepto")
    public ModelAndView insertarConcepto(
            ModelMap model,
            HttpServletRequest request
    ) {
        log.info("/insertarConcepto");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Concepto newConcepto = new Concepto();
        newConcepto.setCodConcepto(request.getParameter("codConcepto"));
        newConcepto.setDesConcepto(request.getParameter("desConcepto"));
        newConcepto.setDesVariable(request.getParameter("desVariable"));
        newConcepto.setDesAbreviacion(request.getParameter("desAbreviacion"));
        newConcepto.setDescripcion(request.getParameter("descripcion"));

        conceptoService.insertarConcepto(newConcepto);

        return new ModelAndView("redirect:/listConceptos");
    }

    @RequestMapping("/editarConcepto@{idParam}")
    public ModelAndView editarConcepto(
            ModelMap model,
            HttpServletRequest request,
            @PathVariable String idParam
    ) {
        log.info("/editarConcepto@{idParam}");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Concepto xConcepto = conceptoService.getById(idParam);

        model.addAttribute("xConcepto", xConcepto);

        log.info("xConcepto: " + xConcepto);

        return new ModelAndView("public/gladius/confPlanilla/conceptos/editarConcepto");
    }

    @RequestMapping("/actualizarConcepto")
    public ModelAndView actualizarConcepto(
            ModelMap model,
            HttpServletRequest request
    ) {
       log.info("/actualizarConcepto");

       String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        Concepto newConcepto = new Concepto();
        newConcepto.setCodConcepto(request.getParameter("codConcepto"));
        newConcepto.setDesConcepto(request.getParameter("desConcepto"));
        newConcepto.setDesVariable(request.getParameter("desVariable"));
        newConcepto.setDesAbreviacion(request.getParameter("desAbreviacion"));
        newConcepto.setDescripcion(request.getParameter("descripcion"));

        conceptoService.actualizarConcepto(newConcepto);

        return new ModelAndView("redirect:/listConceptos");
    }
}