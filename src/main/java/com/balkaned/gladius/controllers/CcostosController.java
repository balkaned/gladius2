package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.CentroCosto;
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
public class CcostosController {
    static Logger logger = Logger.getLogger(CcostosController.class.getName());
    @Autowired
    CcostoService ccostoService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @RequestMapping("/listCcostos")
    public ModelAndView litsCcostos(ModelMap model, HttpServletRequest request) {

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

        List<CentroCosto> ccostosList=ccostoService.listarCentroCosto(idCompania,"");

        logger.info("ccostosList: "+ccostosList);

        model.addAttribute("ccostosList",ccostosList);

        return new ModelAndView("public/gladius/organizacion/ccostos/listCcostos");
    }

    @RequestMapping("/nuevoCcosto")
    public ModelAndView nuevoCcosto(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoCcosto");

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

        //model.addAttribute("idx",puestoService.getIdPuesto(idCompania));
        //model.addAttribute("lovCatPuesto",lovsService.getLovs("63","%"));
        model.addAttribute("lovCatCencos",lovsService.getLovs("64","%"));
        model.addAttribute("idx",ccostoService.getIdCentroCosto(idCompania));

        return new ModelAndView("public/gladius/organizacion/ccostos/nuevoCcosto");
    }

    @RequestMapping("/insertarCcosto")
    public ModelAndView insertarCcosto(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarCcosto");
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
        String iexccosto = request.getParameter("iexccosto2");
        String iexdesccosto = request.getParameter("iexdesccosto");
        String iexcodcat  = request.getParameter("iexcodcat");
        String iexusucrea   =  usuario;

        CentroCosto  cencos = new CentroCosto();
        cencos.setIexcodcia(iexcodcia);
        cencos.setIexccosto(iexccosto);
        cencos.setIexdesccosto(iexdesccosto);
        cencos.setIexcodcat(iexcodcat);
        cencos.setIexusucrea(iexusucrea);

        ccostoService.insertarCentroCosto(cencos);

        return new ModelAndView("redirect:/listCcostos");
    }

}

