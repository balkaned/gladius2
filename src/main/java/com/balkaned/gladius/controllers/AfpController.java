package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Afp;
import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.services.AfpService;
import com.balkaned.gladius.services.AreaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class AfpController {

    @Autowired
    LovsService lovsService;

    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    AfpService afpService;

    @RequestMapping("/listAfp")
    public ModelAndView listAfp(ModelMap model, HttpServletRequest request) {
        log.info("/listAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String periodo = request.getParameter("idperiodo");

        if(periodo==null || periodo==""){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            periodo= sdf.format(date);
        }else{
            periodo= request.getParameter("idperiodo");
        }

        log.info("periodo:"+periodo);

        model.addAttribute("LstAfpPer",afpService.listar(periodo));
        model.addAttribute("periodo",periodo);

        return new ModelAndView("public/gladius/gestionDePlanilla/afp/listAfp");
    }

    @RequestMapping("/buscarAfps")
    public ModelAndView buscarAfps(ModelMap model, HttpServletRequest request) {
        log.info("/buscarAfps");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String periodo = request.getParameter("idperiodo");

        if(periodo==null || periodo==""){
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            periodo= sdf.format(date);
        }else{
            periodo= request.getParameter("idperiodo");
        }

        log.info("periodo:"+periodo);

        model.addAttribute("LstAfpPer",afpService.listar(periodo));
        model.addAttribute("periodo",periodo);

        return new ModelAndView("public/gladius/gestionDePlanilla/afp/listAfp");
    }

    @RequestMapping("/buscarAfpsDesdePlanillas@{iexcodreg}@{iexcodpro}@{iexperiodo}")
    public ModelAndView buscarAfpsDesdePlanillas(ModelMap model, HttpServletRequest request,
                                                @PathVariable String iexcodreg,
                                                @PathVariable String iexcodpro,
                                                @PathVariable String iexperiodo) {
        log.info("/buscarAfpsDesdePlanillas");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        log.info("periodo:"+iexperiodo);

        model.addAttribute("LstAfpPer",afpService.listar(iexperiodo));
        model.addAttribute("periodo",iexperiodo);
        model.addAttribute("iexcodreg",iexcodreg);
        model.addAttribute("iexcodpro",iexcodpro);

        return new ModelAndView("public/gladius/gestionDePlanilla/afp/listAfpDesdePlanillas");
    }

    @RequestMapping("/nuevaAfp")
    public ModelAndView nuevaAfp(ModelMap model, HttpServletRequest request) {
        log.info("/nuevaAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("lovAfp",lovsService.getLovs("11","%"));

        return new ModelAndView("public/gladius/gestionDePlanilla/afp/nuevaAfp");
    }

    @RequestMapping("/insertarAfp")
    public ModelAndView insertarAfp(ModelMap model, HttpServletRequest request) {
        log.info("/insertarAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexpermes  = request.getParameter("idperiodo");
        String iexcodafp = request.getParameter("iexcodafp");
        String iexdesafp = request.getParameter("iexdesafp");
        Double iexcomis_fija = Double.parseDouble(request.getParameter("iexcomis_fija"));
        Double iexcomis_sflu = Double.parseDouble(request.getParameter("iexcomis_sflu"));
        Double iexcomis_sflu_mix = Double.parseDouble(request.getParameter("iexcomis_sflu_mix"));
        Double iexcomis_anual_mix = Double.parseDouble(request.getParameter("iexcomis_anual_mix"));
        Double iexprima_seguro = Double.parseDouble(request.getParameter("iexprima_seguro"));
        Double iexaporte_oblig = Double.parseDouble(request.getParameter("iexaporte_oblig"));
        Double iexremmax_asegu = Double.parseDouble(request.getParameter("iexremmax_asegu"));
        Double iexcomis_onp =Double.parseDouble(request.getParameter("iexcomis_onp"));

        Afp f = new Afp();
        f.setIexpermes(iexpermes);
        f.setIexcodafp(iexcodafp);
        f.setIexcomis_anual_mix(iexcomis_anual_mix);
        f.setIexcomis_fija(iexcomis_fija);
        f.setIexcomis_sflu(iexcomis_sflu);
        f.setIexcomis_sflu_mix(iexcomis_sflu_mix);
        f.setIexprima_seguro(iexprima_seguro);
        f.setIexaporte_oblig(iexaporte_oblig);
        f.setIexremmax_asegu(iexremmax_asegu);
        f.setIexcomis_onp(iexcomis_onp);

        afpService.insertar(f);

        return new ModelAndView("redirect:/listAfp");
    }

    @RequestMapping("/editarAfp@{idafp}@{periodo}")
    public ModelAndView editarAfp(ModelMap model, HttpServletRequest request,
                                  @PathVariable String idafp,
                                  @PathVariable String periodo) {
        log.info("/editarAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Afp f = new Afp();
        f.setIexpermes(periodo);
        f.setIexcodafp(idafp);

        model.addAttribute("LstAfpPerCur",afpService.recuperar(f));
        model.addAttribute("lovAfp",lovsService.getLovs("11","%"));

        return new ModelAndView("public/gladius/gestionDePlanilla/afp/editarAfp");
    }

    @RequestMapping("/modificarAfp")
    public ModelAndView modificarAfp(ModelMap model, HttpServletRequest request) {
        log.info("/modificarAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);

        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexpermes  = request.getParameter("idperiodo");
        String iexcodafp = request.getParameter("iexcodafp");
        String iexdesafp = request.getParameter("iexdesafp");
        Double iexcomis_fija = Double.parseDouble(request.getParameter("iexcomis_fija"));
        Double iexcomis_sflu = Double.parseDouble(request.getParameter("iexcomis_sflu"));
        Double iexcomis_sflu_mix = Double.parseDouble(request.getParameter("iexcomis_sflu_mix"));
        Double iexcomis_anual_mix = Double.parseDouble(request.getParameter("iexcomis_anual_mix"));
        Double iexprima_seguro = Double.parseDouble(request.getParameter("iexprima_seguro"));
        Double iexaporte_oblig = Double.parseDouble(request.getParameter("iexaporte_oblig"));
        Double iexremmax_asegu = Double.parseDouble(request.getParameter("iexremmax_asegu"));
        Double iexcomis_onp =Double.parseDouble(request.getParameter("iexcomis_onp"));

        Afp f = new Afp();
        f.setIexpermes(iexpermes);
        f.setIexcodafp(iexcodafp);
        f.setIexcomis_anual_mix(iexcomis_anual_mix);
        f.setIexcomis_fija(iexcomis_fija);
        f.setIexcomis_sflu(iexcomis_sflu);
        f.setIexcomis_sflu_mix(iexcomis_sflu_mix);
        f.setIexprima_seguro(iexprima_seguro);
        f.setIexaporte_oblig(iexaporte_oblig);
        f.setIexremmax_asegu(iexremmax_asegu);
        f.setIexcomis_onp(iexcomis_onp);

        afpService.actualizar(f);

        return new ModelAndView("redirect:/listAfp");
    }

    @RequestMapping("/deleteAfp@{idafp}@{periodo}")
    public ModelAndView deleteAfp(ModelMap model, HttpServletRequest request,
                                  @PathVariable String idafp,
                                  @PathVariable String periodo) {
        log.info("/deleteAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        Afp f = new Afp();
        f.setIexpermes(periodo);
        f.setIexcodafp(idafp);

        afpService.eliminar(f);

        return new ModelAndView("redirect:/listAfp");
    }

    @RequestMapping("/copiarPeriodoAfp")
    public ModelAndView copiarPeriodoAfp(ModelMap model, HttpServletRequest request) {
        log.info("/copiarPeriodoAfp");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexpermes  = request.getParameter("idPeriodoCopy1");
        String iexpermes2  = request.getParameter("idPeriodoCopy2");

        afpService.insertarDuplicado(iexpermes,iexpermes2);

        return new ModelAndView("redirect:/listAfp");
    }
}

