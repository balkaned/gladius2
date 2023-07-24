package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.services.AreaService;
import com.balkaned.gladius.services.BancoProService;
import com.balkaned.gladius.services.CompaniaService;
import com.balkaned.gladius.services.UsuarioConeccionService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.logging.Logger;

@RestController
public class BancosController {
    static Logger logger = Logger.getLogger(BancosController.class.getName());
    @Autowired
    BancoProService bancoProService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @RequestMapping("/listBancos")
    public ModelAndView listBancos(ModelMap model, HttpServletRequest request) {

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

        List<BancoPro> bancoProList=bancoProService.listarBancoPro(idCompania,"");

        logger.info("bancoProList: "+bancoProList);

        model.addAttribute("bancoProList",bancoProList);

        return new ModelAndView("public/gladius/organizacion/bancosPro/listBancosPro");
    }

}

