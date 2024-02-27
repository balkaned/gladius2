package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.ConceptoService;
import com.balkaned.gladius.services.ConceptoXProcesoService;
import com.balkaned.gladius.services.ProcesoFormulaService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import com.balkaned.gladius.utils.CapitalizarCadena;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@Slf4j
public class ConceptoXProcesoController {
    @Autowired
    Sessionattributes sessionattributes;

    @Autowired
    ProcesoFormulaService procesoFormulaService;

    @Autowired
    ConceptoService conceptoService;

    @Autowired
    ConceptoXProcesoService conceptoXProcesoService;

    @Autowired
    ProcesoPlanillaService procesoPlanillaService;

    @RequestMapping("/listConceptoXProceso@{codproceso}")
    public ModelAndView listConcepto(ModelMap model, HttpServletRequest request,
                                     @PathVariable String codproceso) {
        log.info("/listConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        model.addAttribute("proceso", codproceso);

        ProcesoPlanilla pro = procesoFormulaService.recuperar(Integer.valueOf(codproceso));
        CapitalizarCadena cap = new CapitalizarCadena();
        String desproceso2 = cap.letras(pro.getDesProceso());
        model.addAttribute("desproceso", desproceso2);

        log.info("proceso: " + codproceso);

        return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/listConceptoXProceso");
    }

    @RequestMapping("/buscarConceptoProceso")
    public ModelAndView buscarConceptoProceso(ModelMap model, HttpServletRequest request) {
        log.info("/buscarConceptoProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        String codproceso = request.getParameter("proceso");
        String slc_grpconcepto = request.getParameter("slc_grpconcepto");

        model.addAttribute("proceso", codproceso);
        model.addAttribute("slc_grpconcepto", slc_grpconcepto);

        ProcesoPlanilla pro = procesoFormulaService.recuperar(Integer.valueOf(codproceso));
        CapitalizarCadena cap = new CapitalizarCadena();
        String desproceso2 = cap.letras(pro.getDesProceso());
        model.addAttribute("desproceso", desproceso2);

        model.addAttribute("conceptoXProcesoList", procesoFormulaService.listConceptoXProceso(Integer.valueOf(codproceso), slc_grpconcepto));

        return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/listConceptoXProceso");
    }

    @RequestMapping(value = "/getConceptoXprocesoFormula", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getConceptoXprocesoFormula(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/getConceptoXprocesoFormula");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        String proceso = request.getParameter("proceso");
        String select_concepto = request.getParameter("select_concepto");
        log.info("proceso:" + proceso);
        log.info("select_concepto:" + select_concepto);

        List<ConceptoXProceso> conceptoXProcesoList = procesoFormulaService.listConceptoXProceso(Integer.valueOf(proceso), select_concepto);
        log.info("lista: " + conceptoXProcesoList.size());

        String json = new Gson().toJson(conceptoXProcesoList);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping("/nuevoConceptoXProceso@{idProceso}")
    public ModelAndView nuevoConceptoXProceso(
            ModelMap model, HttpServletRequest request, @PathVariable String idProceso) {
        log.info("/nuevoConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        if (Objects.nonNull(idProceso)) {
            model.addAttribute("idxproceso", idProceso);
            List<Concepto> lista = conceptoService.listarConceptoIns(Integer.parseInt(idProceso));
            model.addAttribute("LstConceptoIns", lista);
        }
        return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/nuevoConceptoXProceso");
    }

    @RequestMapping("/addConceptoXProceso@{idProceso}")
    public ModelAndView addConceptoXProceso(
            ModelMap model, HttpServletRequest request, @PathVariable String idProceso
    ) {
        log.info("/addConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        try {
            Integer codproceso = Integer.valueOf(idProceso);
            String codcon = request.getParameter("idconcepto");
            String tip_con = request.getParameter("tip_concepto");
            String codcon_pdt = request.getParameter("id_concept_pdt");
            String flg_bol = request.getParameter("flg_boleta");
            String des_custom = request.getParameter("des_custom");
            String tip_ingreso = request.getParameter("tip_ingreso");
            String flg_pry_5ta = request.getParameter("flg_pry_5ta");
            String flg_des_5ta_mes = request.getParameter("flg_des_5ta_mes");
            String flg_ess_reg = request.getParameter("flg_ess_reg");
            String flg_ess_pesq = request.getParameter("flg_ess_pesq");
            String flg_ess_agrac = request.getParameter("flg_ess_agrac");
            String flg_ess_sctr = request.getParameter("flg_ess_sctr");
            String flg_extra_solid = request.getParameter("flg_extra_solid");
            String flg_fondo_art = request.getParameter("flg_fondo_art");
            String flg_apo_senati = request.getParameter("flg_apo_senati");
            String flg_onp = request.getParameter("flg_onp");
            String flg_afp = request.getParameter("flg_afp");
            String flg_fond_compl_jub = request.getParameter("flg_fond_compl_jub");
            String flg_esp_pens_pesq = request.getParameter("flg_esp_pens_pesq");
            String flg_5ta = request.getParameter("flg_5ta");
            String flg_promediable = request.getParameter("flg_promediable");
            String flg_agrupable = request.getParameter("flg_agrupable");
            int nro_meses_atras;
            if (request.getParameter("nro_meses_atras") == null) {
                nro_meses_atras = 0;
            } else {
                nro_meses_atras = Integer.parseInt(request.getParameter("nro_meses_atras"));
            }
            ConceptoXProceso p = new ConceptoXProceso();
            p.setProcodpro(codproceso);
            p.setProcodcon(codcon);
            p.setProcodconpdt(codcon_pdt);
            p.setProflgbol(flg_bol);
            if (request.getParameter("id_orden_bol") == null) {
                p.setProorden(0);
            } else {
                p.setProorden(Integer.valueOf(request.getParameter("id_orden_bol")));
            }
            if (request.getParameter("valor_bol") == null) {
                p.setProvalor(0.0);
            } else {
                p.setProvalor(Double.valueOf(request.getParameter("valor_bol")));
            }
            p.setProtipcon(tip_con);
            p.setProdescustom(des_custom);
            p.setTip_ingreso(tip_ingreso);
            p.setFlg_pry_5ta(flg_pry_5ta);
            p.setFlg_des_5ta_mes(flg_des_5ta_mes);
            p.setFlg_ess_reg(flg_ess_reg);
            p.setFlg_ess_pesq(flg_ess_pesq);
            p.setFlg_ess_agrac(flg_ess_agrac);
            p.setFlg_ess_sctr(flg_ess_sctr);
            p.setFlg_extra_solid(flg_extra_solid);
            p.setFlg_fondo_art(flg_fondo_art);
            p.setFlg_apo_senati(flg_apo_senati);
            p.setFlg_onp(flg_onp);
            p.setFlg_afp(flg_afp);
            p.setFlg_fond_compl_jub(flg_fond_compl_jub);
            p.setFlg_esp_pens_pesq(flg_esp_pens_pesq);
            p.setFlg_5ta(flg_5ta);
            p.setFlg_promediable(flg_promediable);
            p.setFlg_agrupable(flg_agrupable);
            p.setNro_meses_atras(nro_meses_atras);
            log.info("ConceptoXProceso: " + p);

            procesoFormulaService.insertarConceptoXProceso(p);
        } catch (Exception e) {
            log.info("Error: " + e.getMessage());
        }
        return new ModelAndView("redirect:/listConceptoXProceso@" + idProceso);
    }

    @RequestMapping("/editarConceptoXProceso@{idProceso}@{idConcepto}")
    public ModelAndView editarConceptoXProceso(ModelMap model, HttpServletRequest request,
                                               @PathVariable String idProceso,
                                               @PathVariable String idConcepto) {
        log.info("/editarConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        model.addAttribute("proceso", idProceso);
        model.addAttribute("LstConceptoIns", conceptoService.listarConceptoIns(Integer.parseInt(idProceso)));
        model.addAttribute("slc_grpconcepto", idConcepto);
        model.addAttribute("proconceptox", conceptoXProcesoService.recuperar(Integer.valueOf(idProceso), idConcepto));

        //Modal Coneptos Promediables
        List<ProcesoPlanilla> listap = procesoPlanillaService.listar("%");
        List<ConceptoxProms> listab = conceptoXProcesoService.listarPromCon(Integer.valueOf(idProceso), idConcepto);

        model.addAttribute("idproceso", idProceso);
        model.addAttribute("idconcepto", idConcepto);
        model.addAttribute("LstPromProceso", listap);
        model.addAttribute("LstconceptoxProcesod", listab);

        //Modal Grupo Conceptos
        model.addAttribute("listaConAgrp",conceptoXProcesoService.listar(Integer.valueOf(idProceso),"%"));
        model.addAttribute("listTblAgrpConc",conceptoXProcesoService.listarAgrupCon(Integer.valueOf(idProceso),idConcepto));

        return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/editarConceptoXProceso");
    }

    @RequestMapping("/modificarConceptoXProceso")
    public ModelAndView modificarConceptoXProceso(ModelMap model, HttpServletRequest request) {
        log.info("/modificarConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        Integer codproceso = Integer.valueOf(request.getParameter("idproceso"));
        String idconcept = request.getParameter("id_concept");
        String codcon_pdt = request.getParameter("id_concepto_pdt");
        String tip_con = request.getParameter("tip_concepto");
        String flg_bol = request.getParameter("flg_boleta");
        String orden = request.getParameter("id_orden_bol");
        String valor = request.getParameter("valor_bol");
        String des_custom = request.getParameter("des_custom");
        String tip_ingreso = request.getParameter("tip_ingreso");
        String flg_pry_5ta = request.getParameter("flg_pry_5ta");
        String flg_des_5ta_mes = request.getParameter("flg_des_5ta_mes");
        String flg_ess_reg = request.getParameter("flg_ess_reg");
        String flg_ess_pesq = request.getParameter("flg_ess_pesq");
        String flg_ess_agrac = request.getParameter("flg_ess_agrac");
        String flg_ess_sctr = request.getParameter("flg_ess_sctr");
        String flg_extra_solid = request.getParameter("flg_extra_solid");
        String flg_fondo_art = request.getParameter("flg_fondo_art");
        String flg_apo_senati = request.getParameter("flg_apo_senati");
        String flg_onp = request.getParameter("flg_onp");
        String flg_afp = request.getParameter("flg_afp");
        String flg_fond_compl_jub = request.getParameter("flg_fond_compl_jub");
        String flg_esp_pens_pesq = request.getParameter("flg_esp_pens_pesq");
        String flg_5ta = request.getParameter("flg_5ta");
        String flg_ess_seg_pen = request.getParameter("flg_ess_seg_pen");
        String flg_cont_asis_previs = request.getParameter("flg_cont_asis_previs");
        String flg_promediable = request.getParameter("flg_promediable");
        String flg_agrupable = request.getParameter("flg_agrupable");

        int nro_meses_atras;

        if (request.getParameter("nro_meses_atras") == null) {
            nro_meses_atras = 0;
        } else {
            nro_meses_atras = Integer.valueOf(request.getParameter("nro_meses_atras"));
        }

        ConceptoXProceso p = new ConceptoXProceso();
        p.setProcodpro(codproceso);
        p.setProcodcon(idconcept);
        p.setProcodconpdt(codcon_pdt);
        p.setProflgbol(flg_bol);
        p.setProorden(Integer.valueOf(orden));
        p.setProvalor(Double.valueOf(valor));
        p.setProtipcon(tip_con);
        p.setProdescustom(des_custom);
        p.setTip_ingreso(tip_ingreso);
        p.setFlg_pry_5ta(flg_pry_5ta);
        p.setFlg_des_5ta_mes(flg_des_5ta_mes);
        p.setFlg_ess_reg(flg_ess_reg);
        p.setFlg_ess_pesq(flg_ess_pesq);
        p.setFlg_ess_agrac(flg_ess_agrac);
        p.setFlg_ess_sctr(flg_ess_sctr);
        p.setFlg_extra_solid(flg_extra_solid);
        p.setFlg_fondo_art(flg_fondo_art);
        p.setFlg_apo_senati(flg_apo_senati);
        p.setFlg_onp(flg_onp);
        p.setFlg_afp(flg_afp);
        p.setFlg_fond_compl_jub(flg_fond_compl_jub);
        p.setFlg_esp_pens_pesq(flg_esp_pens_pesq);
        p.setFlg_5ta(flg_5ta);
        p.setFlg_ess_seg_pen(flg_ess_seg_pen);
        p.setFlg_cont_asis_previs(flg_cont_asis_previs);
        p.setFlg_promediable(flg_promediable);
        p.setFlg_agrupable(flg_agrupable);
        p.setNro_meses_atras(nro_meses_atras);

        log.info("ConceptoXProceso: " + p);

        procesoFormulaService.editarConceptoXProceso(p);

        return new ModelAndView("redirect:/listConceptoXProceso@" + codproceso);
    }

    @RequestMapping("/deleteConceptoXProceso@{idProceso}@{idConcepto}")
    public ModelAndView deleteConceptoXProceso(ModelMap model, HttpServletRequest request,
                                               @PathVariable String idProceso,
                                               @PathVariable String idConcepto) {
        log.info("/deleteConceptoXProceso");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);

        conceptoXProcesoService.eliminar(Integer.valueOf(idProceso), idConcepto);

        return new ModelAndView("redirect:/listConceptoXProceso@" + idProceso);
    }

    @RequestMapping(value = "/getConceptoxProcesoPromediable", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView getConceptoxProcesoPromediable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/getConceptoxProcesoPromediable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        String codproceso = request.getParameter("codproceso");

        List<ConceptoXProceso> listcp = conceptoXProcesoService.listar(Integer.valueOf(codproceso), "%");

        String json = new Gson().toJson(listcp);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value = "/addConceptoPromediable", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addConceptoPromediable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/addConceptoPromediable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer codprocesoaux = Integer.valueOf(request.getParameter("codprocesoaux"));
        String codconaux = request.getParameter("codconceptoaux");
        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codcon = request.getParameter("codconcepto");

        ConceptoxProms p = new ConceptoxProms();
        p.setIdproceso(codproceso);
        p.setCodconcepto(codcon);
        p.setIdprocesoaux(codprocesoaux);
        p.setCodconceptaux(codconaux);

        conceptoXProcesoService.insertarProm(p);

        return null;
    }

    @RequestMapping(value = "/actualizarTblConceptoxProcesoPromediable", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView actualizarTblConceptoxProcesoPromediable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/actualizarTblConceptoxProcesoPromediable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codconcepto = request.getParameter("codconcepto");

        List<ConceptoxProms> listabactTbl = conceptoXProcesoService.listarPromCon(Integer.valueOf(codproceso), codconcepto);

        String json = new Gson().toJson(listabactTbl);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value = "/deleteConceptoPromediable", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteConceptoPromediable(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/deleteConceptoPromediable");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer codprocesoaux = Integer.valueOf(request.getParameter("codprocesoaux"));
        String codconaux = request.getParameter("codconceptoaux");
        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codcon = request.getParameter("codconcepto");

        ConceptoxProms p = new ConceptoxProms();
        p.setIdproceso(codproceso);
        p.setCodconcepto(codcon);
        p.setIdprocesoaux(codprocesoaux);
        p.setCodconceptaux(codconaux);

        conceptoXProcesoService.eliminaProm(p);

        return null;
    }

    @RequestMapping(value = "/addConceptoAgrup", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView addConceptoAgrup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/addConceptoAgrup");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }


        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codconcepto = request.getParameter("codconcepto");
        String codconceptoaux = request.getParameter("codconceptoaux");

        ConceptoxAgrup p = new ConceptoxAgrup();
        p.setIdproceso(codproceso);
        p.setCodconcepto(codconcepto);
        p.setCodconceptaux(codconceptoaux);

        conceptoXProcesoService.insertarAgrup(p);

        return null;
    }

    @RequestMapping(value = "/actualizarTblConceptoAgrup", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView actualizarTblConceptoAgrup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/actualizarTblConceptoAgrup");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codconcepto = request.getParameter("codconcepto");

        List<ConceptoxAgrup> lstAgrup = conceptoXProcesoService.listarAgrupCon(Integer.valueOf(codproceso),codconcepto);

        String json = new Gson().toJson(lstAgrup);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);

        return null;
    }

    @RequestMapping(value = "/deleteConceptoAgrup", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView deleteConceptoAgrup(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("/deleteConceptoAgrup");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {
            return new ModelAndView("redirect:/login2");
        }

        Integer codproceso = Integer.valueOf(request.getParameter("codproceso"));
        String codconcepto = request.getParameter("codconcepto");
        String codconaux = request.getParameter("codconceptoaux");

        ConceptoxAgrup p = new ConceptoxAgrup();
        p.setIdproceso(codproceso);
        p.setCodconcepto(codconcepto);
        p.setCodconceptaux(codconaux);

        conceptoXProcesoService.eliminaAgrup(p);

        return null;
    }
}
