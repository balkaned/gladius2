package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.beans.Proceso;
import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.services.ProcesoFormulaService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.logging.Logger;

@RestController
public class ProcesoFormulaController {

	@Autowired
	ProcesoFormulaService service;

	@Autowired
	Sessionattributes sessionattributes;

	static Logger logger = Logger.getLogger(AreaController.class.getName());

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

	@RequestMapping("/listConcepto")
	public ModelAndView listConcepto(
	 ModelMap model, HttpServletRequest request
	) {
		logger.info("/listConcepto");
		sessionattributes.getVariablesSession(model, request);
		List<Proceso> procesoList = service.listProceso("0");
		model.addAttribute("procesoList", procesoList);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/listConcepto");
	}

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
