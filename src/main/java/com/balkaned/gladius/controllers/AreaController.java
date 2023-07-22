package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.services.AreaService;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.EmpleadoService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class AreaController {
    static Logger logger = Logger.getLogger(AreaController.class.getName());
    @Autowired
    AreaService areaService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @RequestMapping("/listAreas")
    public ModelAndView empleadosList(ModelMap model, HttpServletRequest request) {

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

        List<Area> areasList=areaService.listarArea(idCompania,"");

        logger.info("areasList: "+areasList);

        model.addAttribute("areasList",areasList);

        return new ModelAndView("public/gladius/organizacion/areas/listAreas");
    }

}
