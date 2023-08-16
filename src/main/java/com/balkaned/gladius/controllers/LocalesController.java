package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.LocalService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @RequestMapping("/listLocales")
    public ModelAndView listLocales(ModelMap model, HttpServletRequest request) {

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("################### idCompania: "+idCompania);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        List<Local> localesList=localService.listarLocales(idCompania,"");

        logger.info("localesList: "+localesList);

        model.addAttribute("localesList",localesList);

        return new ModelAndView("public/gladius/organizacion/locales/listLocales");
    }

}

