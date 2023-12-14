package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.LocalService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
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
public class LocalesController {
    static Logger logger = Logger.getLogger(LocalesController.class.getName());
    @Autowired
    LocalService localService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/listLocales")
    public ModelAndView listLocales(ModelMap model, HttpServletRequest request) {
        logger.info("/listLocales");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<Local> localesList = localService.listarLocales(idCompania, "");
        logger.info("localesList: " + localesList);
        model.addAttribute("localesList", localesList);

        return new ModelAndView("public/gladius/organizacion/locales/listLocales");
    }

    @RequestMapping("/nuevoLocal")
    public ModelAndView nuevoLocal(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoLocal");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idx", localService.getIdUbicaion(idCompania));

        return new ModelAndView("public/gladius/organizacion/locales/nuevoLocal");
    }

    @RequestMapping("/insertarLocal")
    public ModelAndView insertarLocal(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarLocal");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexcodubi = request.getParameter("iexubicod2");
        String iexdesubi = request.getParameter("iexubides");
        String iexusucrea = usuario;

        Local ubic = new Local();
        ubic.setIexcodcia(iexcodcia);
        ubic.setIexubicod(iexcodubi);
        ubic.setIexubides(iexdesubi);
        ubic.setIexusucrea(iexusucrea);

        localService.insertarUbicacion(ubic);

        return new ModelAndView("redirect:/listLocales");
    }

    @RequestMapping("/editarLocal@{idLocal}")
    public ModelAndView editarLocal(ModelMap model, HttpServletRequest request,
                                    @PathVariable String idLocal) {
        logger.info("/editarLocal");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("idLocal", idLocal);
        model.addAttribute("xUbicacion", localService.getLocales(idCompania, idLocal));

        return new ModelAndView("public/gladius/organizacion/locales/editarLocal");
    }

    @RequestMapping("/modificarLocal")
    public ModelAndView modificarLocal(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarLocal");

        sessionattributes.getVariablesSession(model, request);
        String usuario = (String) request.getSession().getAttribute("user");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Integer iexcodcia = idCompania;
        String iexcodubi = request.getParameter("iexubicod2");
        String iexdesubi = request.getParameter("iexubides");
        String iexusucrea = usuario;

        Local ubic = new Local();
        ubic.setIexcodcia(iexcodcia);
        ubic.setIexubicod(iexcodubi);
        ubic.setIexubides(iexdesubi);
        ubic.setIexusucrea(iexusucrea);

        localService.actualizarUbicaion(ubic);

        return new ModelAndView("redirect:/listLocales");
    }

    @RequestMapping("/eliminarLocal@{idLocal}")
    public ModelAndView eliminarLocal(ModelMap model, HttpServletRequest request, @PathVariable String idLocal) {
        logger.info("/eliminarLocal");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Local ubic = new Local();
        ubic.setIexcodcia(idCompania);
        ubic.setIexubicod(idLocal);

        localService.eliminarUbicacion(ubic);

        return new ModelAndView("redirect:/listLocales");
    }

}

