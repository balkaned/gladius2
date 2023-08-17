package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    LovsService lovsService;

    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

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

    @RequestMapping("/nuevoBanco")
    public ModelAndView nuevoBanco(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoBanco");
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

        model.addAttribute("lovTipCta",lovsService.getLovs("66","%"));
        model.addAttribute("lovBancos",lovsService.getLovs("36","%"));
        model.addAttribute("lovProcesos",procesoPlanillaService.listar("%"));

        return new ModelAndView("public/gladius/organizacion/puestos/nuevoBanco");
    }

    @RequestMapping("/insertarBanco")
    public ModelAndView insertarBanco(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarBanco");
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

        Integer iexcodcia = idCompania;
        String iexpuesto = request.getParameter("iexpuesto2");
        String iexdespuesto = request.getParameter("iexdespuesto");
        String iexcodcat  = request.getParameter("iexcodcat");
        String iexusucrea   =  usuario;

        Puesto puesto = new Puesto();
        puesto.setIexcodcia(iexcodcia);
        puesto.setIexpuesto(iexpuesto);
        puesto.setIexdespuesto(iexdespuesto);
        puesto.setIexcodcat(iexcodcat);
        puesto.setIexusucrea(iexusucrea);

        //puestoService.insertarPuesto(puesto);

        return new ModelAndView("redirect:/listPuestos");
    }

}

