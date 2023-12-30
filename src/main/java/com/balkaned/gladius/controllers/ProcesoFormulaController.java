package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoFormulaService;
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
		model.addAttribute("Lovs_grpplanilla", lovsService.getLovs("55" , "%"));
		model.addAttribute("Lovs_reglaboral", lovsService.getLovs("33" , "%"));
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

	@RequestMapping("/listConceptoXProceso@{accion}@{codigo}")
	public ModelAndView listConcepto(
	 ModelMap model, HttpServletRequest request, @PathVariable String accion, @PathVariable String codigo
	) {
		logger.info("/listConceptoXProceso");
		sessionattributes.getVariablesSession(model, request);

		if (Objects.equals(accion, "QRY") && Objects.nonNull(codigo)) {
			List<ConceptoXProceso> conceptoXProcesoList = service.listConceptoXProceso(codigo);
			model.addAttribute("slc_grpconcepto", codigo);
			model.addAttribute("conceptoXProcesoList", conceptoXProcesoList);
		} else {
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
		logger.info("idProceso: " + idProceso);
		logger.info("idConcepto: " + idConcepto);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/conceptoxproceso/nuevoConceptoXProceso");
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
