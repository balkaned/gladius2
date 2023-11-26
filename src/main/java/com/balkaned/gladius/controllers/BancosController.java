package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
        logger.info("/listBancos");

        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        logger.info("################### idCompania: " + idCompania);

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        List<BancoPro> bancoProList = bancoProService.listarBancoPro(idCompania, "");

        logger.info("bancoProList: " + bancoProList);

        model.addAttribute("bancoProList", bancoProList);

        return new ModelAndView("public/gladius/organizacion/bancosPro/listBancosPro");
    }

    @RequestMapping("/nuevoBanco")
    public ModelAndView nuevoBanco(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoBanco");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        model.addAttribute("lovTipCta", lovsService.getLovs("66", "%"));
        model.addAttribute("lovBancos", lovsService.getLovs("36", "%"));
        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));

        return new ModelAndView("public/gladius/organizacion/bancosPro/nuevoBancoPro");
    }

    @RequestMapping("/insertarBanco")
    public ModelAndView insertarBanco(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarBanco");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Integer iexcodcia = idCompania;
        String iexcodban = request.getParameter("iexcodban");
        String iexcodpro = request.getParameter("iexcodpro");
        String iextipcta = request.getParameter("iextipcta");
        String iexctaban = request.getParameter("iexctaban");
        String iexusucrea = usuario;

        BancoPro banpro = new BancoPro();
        banpro.setIexcodcia(iexcodcia);
        banpro.setIexcodban(iexcodban);
        banpro.setIexcodpro(Integer.parseInt(iexcodpro));
        banpro.setIextipcta(iextipcta);
        banpro.setIexctaban(iexctaban);
        banpro.setIexusucrea(iexusucrea);

        bancoProService.insertarBancoPro(banpro);

        return new ModelAndView("redirect:/listBancos");
    }

    @RequestMapping("/editarBancoPro@{idBanco}@{idCodPro}")
    public ModelAndView editarBancoPro(ModelMap model, HttpServletRequest request
            , @PathVariable String idBanco
            , @PathVariable String idCodPro) {
        logger.info("/editarBancoPro");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        model.addAttribute("idBanco", idBanco);
        model.addAttribute("idCodPro", idCodPro);
        model.addAttribute("lovTipCta", lovsService.getLovs("66", "%"));
        model.addAttribute("lovBancos", lovsService.getLovs("36", "%"));
        model.addAttribute("lovProcesos", procesoPlanillaService.listar("%"));
        model.addAttribute("xCcontable", bancoProService.getBancoPro(idCompania, Integer.valueOf(idCodPro), idBanco));

        return new ModelAndView("public/gladius/organizacion/bancosPro/editarBancoPro");
    }

    @RequestMapping("/modificarBanco")
    public ModelAndView modificarBanco(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarBanco");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        Integer iexcodcia = idCompania;
        String iexcodban = request.getParameter("iexcodban");
        String iexcodpro = request.getParameter("iexcodpro");
        String iextipcta = request.getParameter("iextipcta");
        String iexctaban = request.getParameter("iexctaban");
        String iexusucrea = usuario;

        BancoPro banpro = new BancoPro();
        banpro.setIexcodcia(iexcodcia);
        banpro.setIexcodban(iexcodban);
        banpro.setIexcodpro(Integer.parseInt(iexcodpro));
        banpro.setIextipcta(iextipcta);
        banpro.setIexctaban(iexctaban);
        banpro.setIexusucrea(iexusucrea);

        bancoProService.actualizarBancoPro(banpro);

        return new ModelAndView("redirect:/listBancos");
    }

    @RequestMapping("/eliminarBancoPro@{idBanco}@{idCodPro}")
    public ModelAndView eliminarBancoPro(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idBanco,
                                         @PathVariable String idCodPro) {
        logger.info("/eliminarBancoPro");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
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

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        BancoPro banpro = new BancoPro();
        banpro.setIexcodcia(idCompania);
        banpro.setIexcodban(idBanco);
        banpro.setIexcodpro(Integer.valueOf(idCodPro));

        bancoProService.eliminarBancoPro(banpro);

        return new ModelAndView("redirect:/listBancos");
    }

}

