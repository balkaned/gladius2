package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoFormulaService;
import com.balkaned.gladius.services.ProcesoPlanillaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
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
public class ProcesoFormulaController {

	@Autowired
	ProcesoFormulaService service;

	@Autowired
	LovsService lovsService;

	@Autowired
	ProcesoPlanillaService procesoPlanillaService;

	@Autowired
	Sessionattributes sessionattributes;

	static Logger logger = Logger.getLogger(AreaController.class.getName());

	// Sección de Procesos y Fórmulas

	@RequestMapping("/listProcesoFormulas")
	public ModelAndView listConceptos(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/listProcesoFormulas");
		sessionattributes.getVariablesSession(model, request);
		List<ProcesoForm> proFosList = service.listProcesoFormula();
		model.addAttribute("proFosList", proFosList);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/listProcesoFormula");
	}

	@RequestMapping("/nuevoProcesoFormula")
	public ModelAndView nuevoProcesoFormula(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/nuevoProcesoFormula");
		sessionattributes.getVariablesSession(model, request);
		model.addAttribute("Lovs_grpplanilla", lovsService.getLovs("55", "%"));
		model.addAttribute("Lovs_reglaboral", lovsService.getLovs("33", "%"));
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/nuevoProcesoFormula");
	}

	@RequestMapping("/addProcesoFormula")
	public ModelAndView addProcesoFormula(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/addProcesoFormula");
		sessionattributes.getVariablesSession(model, request);
		ProcesoForm procesoForm = new ProcesoForm();
		procesoForm.setProdespro(request.getParameter("desProcesos"));
		procesoForm.setProdescorto(request.getParameter("desCortoProcesos"));
		procesoForm.setProcodregimenlab(request.getParameter("lov_regimenlab"));
		procesoForm.setProgrppro(request.getParameter("lov_grpplanilla"));
		procesoForm.setBolproceso(request.getParameter("reporteBoleta"));
		procesoForm.setBolprocesoind(request.getParameter("reporteIndividual"));
		procesoForm.setBolprocesores(request.getParameter("reporteResumen"));
		procesoForm.setIdtipproceso(request.getParameter("tipoProceso"));
		service.insertarProcesoFormula(procesoForm);
		return new ModelAndView("redirect:/listProcesoFormulas");
	}

	@RequestMapping("/eliminarProcesoFormula@{idProceso}")
	public ModelAndView eliminarProcesoFormula(
	 ModelMap model, HttpServletRequest request, @PathVariable Integer idProceso
	) {
		logger.info("/eliminarProcesoFormula");
		sessionattributes.getVariablesSession(model, request);
		service.eliminarProcesoFormula(idProceso);
		return new ModelAndView("redirect:/listProcesoFormulas");
	}

	// Sección de Conceptos X Proceso

	@RequestMapping("/listConceptoXProceso@{proceso}@{codigo}")
	public ModelAndView listConcepto(
	 ModelMap model, HttpServletRequest request, @PathVariable String proceso, @PathVariable String codigo
	) {
		logger.info("/listConceptoXProceso");
		sessionattributes.getVariablesSession(model, request);

		if (Objects.nonNull(proceso)) {
			ProcesoPlanilla procesoPlanilla = procesoPlanillaService.listarPorProcodpro(Integer.parseInt(proceso));
			model.addAttribute("slc_proceso", proceso);
			model.addAttribute("pplanillax", procesoPlanilla.getDesProceso());
		}

		if (Objects.nonNull(codigo)) {
			List<ConceptoXProceso> conceptoXProcesoList = service.listConceptoXProceso(codigo);
			model.addAttribute("slc_grpconcepto", codigo);
			model.addAttribute("conceptoXProcesoList", conceptoXProcesoList);
		}
		else {
			model.addAttribute("slc_grpconcepto", '0');
		}

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/listConceptoXProceso");
	}

	@RequestMapping("/editarConceptoXProceso@{idProceso}@{idConcepto}")
	public ModelAndView editarConceptoXProceso(
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso, @PathVariable String idConcepto
	) {
		sessionattributes.getVariablesSession(model, request);
		logger.info("/editarConceptoXProceso");
		if (Objects.nonNull(idProceso)) {
			ProcesoPlanilla procesoPlanilla = procesoPlanillaService.listarPorProcodpro(Integer.parseInt(idProceso));
			model.addAttribute("slc_proceso", idProceso);
			model.addAttribute("pplanillax", procesoPlanilla.getDesProceso());
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
	 ModelMap model, HttpServletRequest request, @PathVariable String idProceso, @PathVariable String procodcon
	) {
		logger.info("/editConceptoXProceso");
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
			logger.info("ConceptoXProceso: " + p);
			service.editarConceptoXProceso(p);
		}
		catch (Exception e) {
			logger.info("Error: " + e.getMessage());
		}
		return new ModelAndView("redirect:/listConceptoXProceso@" + idProceso + "@");
	}

	// Sección de Fórmulas

	@RequestMapping("/listFormulas")
	public ModelAndView listFormulas(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/listFormulas");
		sessionattributes.getVariablesSession(model, request);
		List<FormulaXConcepto> formulaXConceptoList = service.listFormulaXConcepto();
		model.addAttribute("formulaXConceptoList", formulaXConceptoList);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/listaFormulas");
	}

	@RequestMapping("/formularCodigo")
	public ModelAndView formular(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/formularCodigo");
		sessionattributes.getVariablesSession(model, request);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/formularCodigo");
	}
}
