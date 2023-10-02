package com.balkaned.gladius.controllers;


import com.balkaned.gladius.beans.Seccion;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class SeccionesController {
    static Logger logger = Logger.getLogger(SeccionesController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    SeccionService seccionService;

    @Autowired
    SistemaService sistemaService;

    @RequestMapping("/listSecciones")
    public ModelAndView listSecciones(ModelMap model, HttpServletRequest request) {
        logger.info("/listSecciones");

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

        logger.info("################### idCompania: "+idCompania);

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);
        model.addAttribute("email",email);
        model.addAttribute("firstCharacter",firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp",rucComp);
        model.addAttribute("idComp",idCompania);
        model.addAttribute("urlLogo",urlLogo);

        model.addAttribute("LstSeccion",seccionService.listarSeccion());

        return new ModelAndView("public/gladius/configuracion/secciones/listSecciones");
    }

    @RequestMapping("/nuevaSeccion")
    public ModelAndView nuevaSeccion(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaSeccion");
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

        model.addAttribute("lovSys",sistemaService.listarSistemas());

        return new ModelAndView("public/gladius/configuracion/secciones/nuevaSeccion");
    }

    @RequestMapping("/insertarSeccion")
    public ModelAndView insertarSeccion(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarSeccion");
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

        Seccion sec = new Seccion();
        Integer idsec= seccionService.getIdSeccion();
        sec.setIexcodsec(idsec);
        sec.setIexdessec(request.getParameter("iexdessec"));
        sec.setIexcodsys(Integer.parseInt(request.getParameter("iexcodsys")));
        sec.setIexordsec(Integer.parseInt(request.getParameter("iexordsec")));
        sec.setIexsecimg(request.getParameter("iexsecimg"));
        sec.setIexsecurl(request.getParameter("iexsecurl"));
        sec.setIexsecobs(request.getParameter("iexsecobs"));
        sec.setIexactiondef(request.getParameter("iexactiondef"));

        seccionService.insertarSeccion(sec);

        return new ModelAndView("redirect:/listSecciones");
    }

}

