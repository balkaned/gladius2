package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Concepto;
import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.services.ConceptoService;
import com.balkaned.gladius.services.ProcesoFormulaService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@RestController
@Slf4j
public class ConceptoXProcesoController {
	@Autowired
	Sessionattributes sessionattributes;

	@Autowired
	ProcesoFormulaService service;

	@Autowired
	ProcesoPlanillaService procesoPlanillaService;

	@Autowired
	ConceptoService conceptoService;

	@RequestMapping("/listConceptoXProceso@{proceso}@{codigo}")
	public ModelAndView listConcepto(
	 ModelMap model, HttpServletRequest request, @PathVariable String proceso, @PathVariable String codigo) {
		log.info("/listConceptoXProceso");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		if (proceso != null) {
			//ProcesoPlanilla procesoPlanilla = procesoPlanillaService.listarPorProcodpro(Integer.parseInt(proceso));
			model.addAttribute("slc_proceso", proceso);
			//model.addAttribute("pplanillax", procesoPlanilla.getDesProceso());
		}

		if (codigo != null && !codigo.isEmpty()) {
			List<ConceptoXProceso> conceptoXProcesoList = service.listConceptoXProceso(Integer.valueOf(proceso), codigo);
			model.addAttribute("slc_grpconcepto", codigo);
			model.addAttribute("conceptoXProcesoList", conceptoXProcesoList);
		}
		else {
			model.addAttribute("slc_grpconcepto", '0');
		}

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/listConceptoXProceso");
	}

	@RequestMapping("/nuevoConceptoXProceso@{idProceso}")
	public ModelAndView nuevoConceptoXProceso(
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso) {
		log.info("/nuevoConceptoXProceso");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		if (Objects.nonNull(idProceso)) {
			model.addAttribute("idxproceso", idProceso);
			List<Concepto> lista = conceptoService.listarConceptoIns(Integer.parseInt(idProceso));
			request.setAttribute("LstConceptoIns", lista);
		}
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/nuevoConceptoXProceso");
	}

	@RequestMapping("/addConceptoXProceso@{idProceso}")
	public ModelAndView addConceptoXProceso(
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso
	) {
		log.info("/addConceptoXProceso");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
			}
			else {
				nro_meses_atras = Integer.parseInt(request.getParameter("nro_meses_atras"));
			}
			ConceptoXProceso p = new ConceptoXProceso();
			p.setProcodpro(codproceso);
			p.setProcodcon(codcon);
			p.setProcodconpdt(codcon_pdt);
			p.setProflgbol(flg_bol);
			if (request.getParameter("id_orden_bol") == null) {
				p.setProorden(0);
			}
			else {
				p.setProorden(Integer.valueOf(request.getParameter("id_orden_bol")));
			}
			if (request.getParameter("valor_bol") == null) {
				p.setProvalor(0.0);
			}
			else {
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
			service.insertarConceptoXProceso(p);
		}
		catch (Exception e) {
			log.info("Error: " + e.getMessage());
		}
		return new ModelAndView("redirect:/listConceptoXProceso@" + idProceso + "@");
	}

	@RequestMapping("/editarConceptoXProceso@{idProceso}@{idConcepto}")
	public ModelAndView editarConceptoXProceso(
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso, @PathVariable String idConcepto) {
		log.info("/editarConceptoXProceso");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		if (Objects.nonNull(idProceso)) {
			//ProcesoPlanilla procesoPlanilla = procesoPlanillaService.listarPorProcodpro(Integer.parseInt(idProceso));
			model.addAttribute("slc_proceso", idProceso);
			//model.addAttribute("pplanillax", procesoPlanilla.getDesProceso());
		}

		if (Objects.nonNull(idConcepto)) {
			model.addAttribute("slc_grpconcepto", idConcepto);
			ConceptoXProceso conceptoXProceso = service.getConceptoXProceso(Integer.valueOf(idProceso), idConcepto);
			model.addAttribute("proconceptox", conceptoXProceso);
		}

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/editarConceptoXProceso");
	}

	@RequestMapping("/editConceptoXProceso@{idProceso}@{procodcon}")
	public ModelAndView editConceptoXProceso(
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso, @PathVariable String procodcon) {
		log.info("/editConceptoXProceso");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		try {
			Integer codproceso = Integer.valueOf(request.getParameter("idproceso"));
			String tip_con = request.getParameter("tip_concepto");
			String codcon_pdt = request.getParameter("id_concept_pdt");
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
			}
			else {
				nro_meses_atras = Integer.valueOf(request.getParameter("nro_meses_atras"));
			}
			ConceptoXProceso p = new ConceptoXProceso();
			p.setProcodpro(codproceso);
			p.setProcodcon(procodcon);
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
			service.editarConceptoXProceso(p);
		}
		catch (Exception e) {
			log.info("Error: " + e.getMessage());
		}
		return new ModelAndView("redirect:/listConceptoXProceso@" + idProceso + "@");
	}
}
