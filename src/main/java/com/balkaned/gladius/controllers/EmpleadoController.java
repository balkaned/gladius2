package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.UsuarioConeccionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class EmpleadoController {
    static Logger logger = Logger.getLogger(EmpleadoController.class.getName());
    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @RequestMapping("/empleadosList@{idComp}@{idUser}")
    public ModelAndView empleadosList(ModelMap model, HttpServletRequest request,@PathVariable String idComp,@PathVariable String idUser) {

        logger.info("idComp: "+idComp);
        logger.info("idUser: "+idUser);

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");

        model.addAttribute("usuario",usuario);
        model.addAttribute("idusuario",idusuario);

        return new ModelAndView("public/gladius/empleadosList");
    }
}

