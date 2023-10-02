package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Opciones;
import com.balkaned.gladius.beans.ParametrosGen;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class OpcionesController {
    static Logger logger = Logger.getLogger(OpcionesController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;
    @Autowired
    CompaniaService companiaService;
    @Autowired
    LovsService lovsService;
    @Autowired
    OpcionService opcionService;
    @Autowired
    SeccionService seccionService;

    @RequestMapping("/listOpciones")
    public ModelAndView listOpciones(ModelMap model, HttpServletRequest request) {
        logger.info("/listOpciones");

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

        model.addAttribute("LstOpciones",opcionService.listarOpciones());

        return new ModelAndView("public/gladius/configuracion/opciones/listOpciones");
    }

    @RequestMapping("/nuevaOpcion")
    public ModelAndView nuevaOpcion(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaOpcion");
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

        model.addAttribute("lovSeccion",seccionService.listarSeccion());

        return new ModelAndView("public/gladius/configuracion/opciones/nuevaOpcion");
    }

    @RequestMapping("/insertarOpcion")
    public ModelAndView insertarOpcion(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarOpcion");
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

        Integer idopc = opcionService.getIdOpciones();
        Opciones opc =  new Opciones();
        opc.setIexcodopc(idopc);
        opc.setIexdesopc(request.getParameter("iexdesopc"));
        opc.setIexflgest(request.getParameter("iexflgest"));
        opc.setIexcodsec(Integer.parseInt(request.getParameter("iexcodsec")));
        opc.setIexurlopc(request.getParameter("iexurlopc"));
        opc.setIexurlimg(request.getParameter("iexurlimg"));
        opc.setIexaction(request.getParameter("iexaction"));
        opc.setIexcodapps(request.getParameter("iexcodapps"));
        opc.setIexdescripcion(request.getParameter("iexdescripcion"));

        opcionService.insertarOpciones(opc);

        return new ModelAndView("redirect:/listOpciones");
    }

}

