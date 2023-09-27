package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Compania;
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
public class CompaniasController {
    static Logger logger = Logger.getLogger(CompaniasController.class.getName());
    @Autowired
    ParametroService parametroService;

    @Autowired
    UsuarioConeccionService usuarioConeccionService;

    @Autowired
    CompaniaService companiaService;

    @Autowired
    LovsService lovsService;

    @Autowired
    ConceptoService conceptoService;

    @RequestMapping("/listCompanias")
    public ModelAndView listCompanias(ModelMap model, HttpServletRequest request) {
        logger.info("/listCompanias");

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

        model.addAttribute("LstCompania",companiaService.listarTodo());

        return new ModelAndView("public/gladius/configuracion/companias/listCompanias");
    }

    @RequestMapping("/nuevaCompania")
    public ModelAndView nuevaCompania(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaCompania");
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

        model.addAttribute("lovTipAct",lovsService.getLovs("1","%"));

        return new ModelAndView("public/gladius/configuracion/companias/nuevaCompania");
    }

    @RequestMapping("/insertarCompania")
    public ModelAndView insertarCompania(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarCompania");
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

        Compania cia =  new Compania();

        cia.setIdCodcia(Integer.parseInt(request.getParameter("iexcodcia")));
        cia.setDescCia(request.getParameter("iexdescia"));
        cia.setDescCiaCorto(request.getParameter("iexdescorto"));
        cia.setNroRuc(request.getParameter("iexnroruc"));
        cia.setDireccionCia(request.getParameter("iexdireccion"));
        cia.setNroTelfCia(request.getParameter("iexnrotelf"));
        cia.setIdActividadCia(request.getParameter("iexcodact"));
        cia.setNomRepesentante(request.getParameter("iexrepnombre"));
        cia.setDesCargoRep(request.getParameter("iexrepcargo"));
        cia.setNroDocuRep(request.getParameter("iexrepdocid"));
        cia.setUrlLogo(request.getParameter("iexreplogo"));
        cia.setUsuCrea(usuario);
        cia.setIexurlfileserver(request.getParameter("iexurlfileserver"));
        cia.setIexurlfilereport(request.getParameter("iexurlfilereport"));

        companiaService.insertarCompania(cia);

        return new ModelAndView("redirect:/listCompanias");
    }



}

