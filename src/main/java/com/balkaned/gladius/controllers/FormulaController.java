package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.services.FormulaService;
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
import java.util.logging.Logger;

@RestController
public class FormulaController {
	@Autowired
	ProcesoFormulaService service;

	@Autowired
	FormulaService formulaService;

	@Autowired
	LovsService lovsService;

	@Autowired
	Sessionattributes sessionattributes;

	static Logger logger = Logger.getLogger(FormulaController.class.getName());

	@RequestMapping("/listFormulas@{idProceso}")
	public ModelAndView listFormulas(
	 ModelMap model, HttpServletRequest request, @PathVariable("idProceso") Integer idProceso
	) {
		logger.info("/listFormulas");
		sessionattributes.getVariablesSession(model, request);
		List<FormulaXConcepto> formulaXConceptoList = service.listFormulaXConcepto();
		model.addAttribute("formulaXConceptoList", formulaXConceptoList);
		model.addAttribute("idProceso", idProceso);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/listaFormulas");
	}

	@RequestMapping("/formularCodigo@{idProceso}@{idFormula}")
	public ModelAndView formular(
	 ModelMap model, HttpServletRequest request, @PathVariable("idProceso") Integer idProceso, @PathVariable("idFormula") Integer idFormula
	) {
		logger.info("/formularCodigo");
		sessionattributes.getVariablesSession(model, request);
		FormulaPlanilla fplanilla = formulaService.getByIdProcesoIdFormula(idProceso, idFormula);
		request.setAttribute("fplanillax", fplanilla);
		request.setAttribute("Lovs_conxprod", lovsService.getConceptoxProc(idProceso));
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/formularCodigo");
	}
}
