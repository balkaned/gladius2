package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class GestionTtableController {
    static Logger logger = Logger.getLogger(GestionTtableController.class.getName());

    @Autowired
    UsuarioConeccionService usuarioConeccionService;
    @Autowired
    CompaniaService companiaService;
    @Autowired
    LovsService lovsService;
    @Autowired
    TtableService ttableService;

    @RequestMapping("/listTablasGen")
    public ModelAndView listTablasGen(ModelMap model, HttpServletRequest request) {
        logger.info("/listTablasGen");

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

        model.addAttribute("LstTTablac", ttableService.listarTTablac("%"));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/listTablasGen");
    }

    @RequestMapping("/nuevaTablaGen")
    public ModelAndView nuevaTablaGen(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevaTablaGen");
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

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/nuevaTablaGen");
    }

    @RequestMapping("/insertarNuevaTblGen")
    public ModelAndView insertarNuevaTblGen(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarNuevaTblGen");
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

        String iexcodtab = request.getParameter("iexcodtab");
        String iexdestab = request.getParameter("iexdestab");
        String iexlbl1 = request.getParameter("iexlbl1");
        String iexlbl2 = request.getParameter("iexlbl2");
        String iexlbl3 = request.getParameter("iexlbl3");
        String iexlbl4 = request.getParameter("iexlbl4");
        String iexlbl5 = request.getParameter("iexlbl5");
        String iexlbl6 = request.getParameter("iexlbl6");
        String iexlbl7 = request.getParameter("iexlbl7");
        String iexlbl8 = request.getParameter("iexlbl8");
        String iexlblflg1 = request.getParameter("iexlblflg1");
        String iexlblflg2 = request.getParameter("iexlblflg2");
        String iexlblflg3 = request.getParameter("iexlblflg3");
        String iexlblflg4 = request.getParameter("iexlblflg4");
        String iexlblflg5 = request.getParameter("iexlblflg5");
        String iexlblflg6 = request.getParameter("iexlblflg6");
        String iexlblflg7 = request.getParameter("iexlblflg7");
        String iexlblflg8 = request.getParameter("iexlblflg8");
        String iexlblval9 = request.getParameter("iexlblval9");
        String iexlblval10 = request.getParameter("iexlblval10");
        String iexlblval11 = request.getParameter("iexlblval11");
        String iexlblval12 = request.getParameter("iexlblval12");
        String iexlblval13 = request.getParameter("iexlblval13");
        String iexlblval14 = request.getParameter("iexlblval14");
        String iexlblval15 = request.getParameter("iexlblval15");
        String iexlblval16 = request.getParameter("iexlblval16");
        String iexlblflg9 = request.getParameter("iexlblflg9");
        String iexlblflg10 = request.getParameter("iexlblflg10");
        String iexlblflg11 = request.getParameter("iexlblflg11");
        String iexlblflg12 = request.getParameter("iexlblflg12");
        String iexlblflg13 = request.getParameter("iexlblflg13");
        String iexlblflg14 = request.getParameter("iexlblflg14");
        String iexlblflg15 = request.getParameter("iexlblflg15");
        String iexlblflg16 = request.getParameter("iexlblflg16");

        TTablaCabecera p = new TTablaCabecera();
        p.setIexcodtab(iexcodtab);
        p.setIexdestab(iexdestab);
        p.setIexlbl1(iexlbl1);
        p.setIexlbl2(iexlbl2);
        p.setIexlbl3(iexlbl3);
        p.setIexlbl4(iexlbl4);
        p.setIexlbl5(iexlbl5);
        p.setIexlbl6(iexlbl6);
        p.setIexlbl7(iexlbl7);
        p.setIexlbl8(iexlbl8);
        p.setIexlblflg1(iexlblflg1);
        p.setIexlblflg2(iexlblflg2);
        p.setIexlblflg3(iexlblflg3);
        p.setIexlblflg4(iexlblflg4);
        p.setIexlblflg5(iexlblflg5);
        p.setIexlblflg6(iexlblflg6);
        p.setIexlblflg7(iexlblflg7);
        p.setIexlblflg8(iexlblflg8);
        p.setIexlblval9(iexlblval9);
        p.setIexlblval10(iexlblval10);
        p.setIexlblval11(iexlblval11);
        p.setIexlblval12(iexlblval12);
        p.setIexlblval13(iexlblval13);
        p.setIexlblval14(iexlblval14);
        p.setIexlblval15(iexlblval15);
        p.setIexlblval16(iexlblval16);
        p.setIexlblflg9(iexlblflg9);
        p.setIexlblflg10(iexlblflg10);
        p.setIexlblflg11(iexlblflg11);
        p.setIexlblflg12(iexlblflg12);
        p.setIexlblflg13(iexlblflg13);
        p.setIexlblflg14(iexlblflg14);
        p.setIexlblflg15(iexlblflg15);
        p.setIexlblflg16(iexlblflg16);

        ttableService.insertarTtablac(p);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/editarTblGen@{idTbl}")
    public ModelAndView editarTblGen(ModelMap model, HttpServletRequest request, @PathVariable String idTbl) {
        logger.info("/editarTblGen");

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

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("ttablacx", ttableService.recuperarTTablac(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/editarTablaGen");
    }

    @RequestMapping("/modificarTblGen")
    public ModelAndView modificarTblGen(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarTblGen");
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

        String iexcodrol = request.getParameter("iexcodrol2");
        String iexdesrol = request.getParameter("iexdesrol");
        String iexlbl1 = request.getParameter("iexlbl1");
        String iexlbl2 = request.getParameter("iexlbl2");
        String iexlbl3 = request.getParameter("iexlbl3");
        String iexlbl4 = request.getParameter("iexlbl4");
        String iexlbl5 = request.getParameter("iexlbl5");
        String iexlbl6 = request.getParameter("iexlbl6");
        String iexlbl7 = request.getParameter("iexlbl7");
        String iexlbl8 = request.getParameter("iexlbl8");
        String iexlblflg1 = request.getParameter("iexlblflg1");
        String iexlblflg2 = request.getParameter("iexlblflg2");
        String iexlblflg3 = request.getParameter("iexlblflg3");
        String iexlblflg4 = request.getParameter("iexlblflg4");
        String iexlblflg5 = request.getParameter("iexlblflg5");
        String iexlblflg6 = request.getParameter("iexlblflg6");
        String iexlblflg7 = request.getParameter("iexlblflg7");
        String iexlblflg8 = request.getParameter("iexlblflg8");
        String iexlblval9 = request.getParameter("iexlblval9");
        String iexlblval10 = request.getParameter("iexlblval10");
        String iexlblval11 = request.getParameter("iexlblval11");
        String iexlblval12 = request.getParameter("iexlblval12");
        String iexlblval13 = request.getParameter("iexlblval13");
        String iexlblval14 = request.getParameter("iexlblval14");
        String iexlblval15 = request.getParameter("iexlblval15");
        String iexlblval16 = request.getParameter("iexlblval16");
        String iexlblflg9 = request.getParameter("iexlblflg9");
        String iexlblflg10 = request.getParameter("iexlblflg10");
        String iexlblflg11 = request.getParameter("iexlblflg11");
        String iexlblflg12 = request.getParameter("iexlblflg12");
        String iexlblflg13 = request.getParameter("iexlblflg13");
        String iexlblflg14 = request.getParameter("iexlblflg14");
        String iexlblflg15 = request.getParameter("iexlblflg15");
        String iexlblflg16 = request.getParameter("iexlblflg16");

        TTablaCabecera p = new TTablaCabecera();
        p.setIexcodtab(iexcodrol);
        p.setIexdestab(iexdesrol);
        p.setIexlbl1(iexlbl1);
        p.setIexlbl2(iexlbl2);
        p.setIexlbl3(iexlbl3);
        p.setIexlbl4(iexlbl4);
        p.setIexlbl5(iexlbl5);
        p.setIexlbl6(iexlbl6);
        p.setIexlbl7(iexlbl7);
        p.setIexlbl8(iexlbl8);
        p.setIexlblflg1(iexlblflg1);
        p.setIexlblflg2(iexlblflg2);
        p.setIexlblflg3(iexlblflg3);
        p.setIexlblflg4(iexlblflg4);
        p.setIexlblflg5(iexlblflg5);
        p.setIexlblflg6(iexlblflg6);
        p.setIexlblflg7(iexlblflg7);
        p.setIexlblflg8(iexlblflg8);
        p.setIexlblval9(iexlblval9);
        p.setIexlblval10(iexlblval10);
        p.setIexlblval11(iexlblval11);
        p.setIexlblval12(iexlblval12);
        p.setIexlblval13(iexlblval13);
        p.setIexlblval14(iexlblval14);
        p.setIexlblval15(iexlblval15);
        p.setIexlblval16(iexlblval16);
        p.setIexlblflg9(iexlblflg9);
        p.setIexlblflg10(iexlblflg10);
        p.setIexlblflg11(iexlblflg11);
        p.setIexlblflg12(iexlblflg12);
        p.setIexlblflg13(iexlblflg13);
        p.setIexlblflg14(iexlblflg14);
        p.setIexlblflg15(iexlblflg15);
        p.setIexlblflg16(iexlblflg16);

        ttableService.actualizarTTablac(p);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/verDetalleTblGen@{idTbl}")
    public ModelAndView verDetalleTblGen(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTbl) {
        logger.info("/verDetalleTblGen");
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
        model.addAttribute("idTbl", idTbl);

        model.addAttribute("idTbl", idTbl);
        request.getSession().setAttribute("ttablaclbl", ttableService.recuperarTTablac(idTbl));
        model.addAttribute("LstTTablad", ttableService.listarTTablad(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/verDetalleTablasGen");
    }

    @RequestMapping("/modificarTblGenDetalle")
    public ModelAndView modificarTblGenDetalle(ModelMap model, HttpServletRequest request) {
        logger.info("/modificarTblGenDetalle");
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

        String iexcodtab = request.getParameter("iexcodtab2");
        String iexkey = request.getParameter("iexkey");
        String desdet = request.getParameter("desdet");
        logger.info("iexcodtab: "+iexcodtab);
        logger.info("iexkey: "+iexkey);
        logger.info("desdet: "+desdet);

        String des1det = request.getParameter("des1det");
        String des2det = request.getParameter("des2det");
        String des3det = request.getParameter("des3det");
        String des4det = request.getParameter("des4det");
        String des5det = request.getParameter("des5det");
        String des6det = request.getParameter("des6det");
        String des7det = request.getParameter("des7det");
        String des8det = request.getParameter("des8det");
        String val9det = request.getParameter("val9det");
        String val10det = request.getParameter("val10det");
        String val11det = request.getParameter("val11det");
        String val12det = request.getParameter("val12det");
        String val13det = request.getParameter("val13det");
        String val14det = request.getParameter("val14det");
        String val15det = request.getParameter("val15det");
        String val16det = request.getParameter("val16det");

        TTablaDetalle p = new TTablaDetalle();
        p.setIexcodtab(iexcodtab);
        p.setIexkey(iexkey);
        p.setDesdet(desdet);

        if (request.getParameter("des1det") == null) {
            p.setDes1det("");
        } else {
            p.setDes1det(des1det);
        }

        if (request.getParameter("des2det") == null) {
            p.setDes2det("");
        } else {
            p.setDes2det(des2det);
        }

        if (request.getParameter("des3det") == null) {
            p.setDes3det("");
        } else {
            p.setDes3det(des3det);
        }
        if (request.getParameter("des4det") == null) {
            p.setDes4det("");
        } else {
            p.setDes4det(des4det);
        }

        if (request.getParameter("des5det") == null) {
            p.setDes5det("");
        } else {
            p.setDes5det(des5det);
        }

        if (request.getParameter("des6det") == null) {
            p.setDes6det("");
        } else {
            p.setDes6det(des6det);
        }

        if (request.getParameter("des7det") == null) {
            p.setDes7det("");
        } else {
            p.setDes7det(des7det);
        }

        if (request.getParameter("des8det") == null) {
            p.setDes8det("");
        } else {
            p.setDes8det(des8det);
        }

        if (request.getParameter("val9det") == null || request.getParameter("val9det") == "") {
            p.setVal9det(0.0);
        } else {
            p.setVal9det(Double.parseDouble(val9det));
        }

        if (request.getParameter("val10det") == null || request.getParameter("val10det") == "") {
            p.setVal10det(0.0);
        } else {
            p.setVal10det(Double.parseDouble(val10det));
        }

        if (request.getParameter("val11det") == null || request.getParameter("val11det") == "") {
            p.setVal11det(0.0);
        } else {
            p.setVal11det(Double.parseDouble(val11det));
        }

        if (request.getParameter("val12det") == null || request.getParameter("val12det") == "") {
            p.setVal12det(0.0);
        } else {
            p.setVal12det(Double.parseDouble(val12det));
        }

        if (request.getParameter("val13det") == null || request.getParameter("val13det") == "") {
            p.setVal13det(0.0);
        } else {
            p.setVal13det(Double.parseDouble(val13det));
        }
        if (request.getParameter("val14det") == null || request.getParameter("val14det") == "") {
            p.setVal14det(0.0);
        } else {
            p.setVal14det(Double.parseDouble(val14det));
        }
        if (request.getParameter("val15det") == null || request.getParameter("val15det") == "") {
            p.setVal15det(0.0);
        } else {
            p.setVal15det(Double.parseDouble(val15det));
        }

        if (request.getParameter("val16det") == null || request.getParameter("val16det") == "") {
            p.setVal16det(0.0);
        } else {
            p.setVal16det(Double.parseDouble(val16det));
        }

        ttableService.actualizarTTablad(p);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/curDetalleTblGen@{idTbl}@{idKey}")
    public ModelAndView verDetalleTblGen(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTbl, @PathVariable String idKey) {
        logger.info("/curDetalleTblGen");
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
        model.addAttribute("idTbl", idTbl);

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("idKey", idKey);

        TTablaDetalle ttabladxx = ttableService.recuperarTTablad(idTbl,idKey);
        model.addAttribute("ttabladxx", ttabladxx);

        request.getSession().setAttribute("ttablaclbl", ttableService.recuperarTTablac(idTbl));
        model.addAttribute("LstTTablad", ttableService.listarTTablad(idTbl));

        return new ModelAndView("public/gladius/configuracion/tablasGenericas/verDetalleTablasGen");
    }

    @RequestMapping("/deleteTablaGen@{idTbl}")
    public ModelAndView deleteTablaGen(ModelMap model, HttpServletRequest request, @PathVariable String idTbl) {
        logger.info("/deleteTablaGen");
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

        ttableService.eliminarTTablac(idTbl);
        ttableService.eliminarTTablad(idTbl);

        return new ModelAndView("redirect:/listTablasGen");
    }

    @RequestMapping("/deletecurDetalleTblGen@{idTbl}@{idKey}")
    public ModelAndView deletecurDetalleTblGen(ModelMap model, HttpServletRequest request,
                                         @PathVariable String idTbl,
                                         @PathVariable String idKey) {
        logger.info("/deletecurDetalleTblGen");
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
        model.addAttribute("idTbl", idTbl);

        model.addAttribute("idTbl", idTbl);
        model.addAttribute("idKey", idKey);

        ttableService.eliminarTTablade(idTbl,idKey);

        return new ModelAndView("redirect:/verDetalleTblGen@"+idTbl);
    }

}

