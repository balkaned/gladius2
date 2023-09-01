package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.beans.RegimenLaboral;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.PuestoService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class LovsController {
    static Logger logger = Logger.getLogger(LovsController.class.getName());
    @Autowired
    PuestoService puestoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @RequestMapping(value="/getlovsPROXCON",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsPROXCON(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getlovsPROXCON");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String iexcodreg=request.getParameter("iexcodreg");

        List<ProcesoPlanilla> listProRegimen=lovsService.getProxRegimen(iexcodreg);

        String json = new Gson().toJson(listProRegimen);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value="/getlovsPERX",method= {RequestMethod.POST,RequestMethod.GET})
    public ModelAndView getlovsPERX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        logger.info("/getlovsPERX");
        String user = (String) request.getSession().getAttribute("user");

        if(request.getSession().getAttribute("user")==null) {
            return new ModelAndView("redirect:/login2");
        }

        String proceso = request.getParameter("iexcodpro");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        List<ProcesoPeriodo> listProPeriodo=lovsService.getPerxproc(idCompania,proceso);

        String json = new Gson().toJson(listProPeriodo);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

}

