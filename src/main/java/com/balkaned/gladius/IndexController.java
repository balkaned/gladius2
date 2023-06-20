package com.balkaned.gladius;

import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.services.UsuarioConeccionService;
import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import java.io.IOException;
import java.util.Map;

@RestController
public class IndexController {

   /* @Autowired
    UsuarioConeccionService usuarioConeccionService;*/

    @RequestMapping("/home")
    public ModelAndView home(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("public/index");
    }

}
