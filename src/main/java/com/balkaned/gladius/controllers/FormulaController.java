package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.beans.FormulaXConcepto;
import com.balkaned.gladius.services.FormulaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoFormulaService;
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

@RestController
@Slf4j
public class FormulaController {
	@Autowired
	ProcesoFormulaService service;

	@Autowired
	FormulaService formulaService;

	@Autowired
	LovsService lovsService;

	@Autowired
	Sessionattributes sessionattributes;

	@RequestMapping("/listFormulas@{idProceso}")
	public ModelAndView listFormulas(
	 ModelMap model, HttpServletRequest request, @PathVariable("idProceso") Integer idProceso) {
		log.info("/listFormulas");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		List<FormulaXConcepto> formulaXConceptoList = service.listFormulaXConcepto();
		model.addAttribute("formulaXConceptoList", formulaXConceptoList);
		model.addAttribute("idProceso", idProceso);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/listaFormulas");
	}

	@RequestMapping("/formularCodigo@{idProceso}@{idFormula}")
	public ModelAndView formular(
	 ModelMap model, HttpServletRequest request, @PathVariable("idProceso") Integer idProceso, @PathVariable("idFormula") Integer idFormula) {
		log.info("/formularCodigo");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		model.addAttribute("idProceso",idProceso);
		model.addAttribute("idFormula",idFormula);

		FormulaPlanilla fplanilla = formulaService.getByIdProcesoIdFormula(idProceso, idFormula);
		model.addAttribute("fplanillax", fplanilla);
		model.addAttribute("Lovs_conxprod", lovsService.getConceptoxProc(idProceso));

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/formularCodigo");
	}

	@RequestMapping("/modificarFormula")
	public ModelAndView modificarFormula(ModelMap model, HttpServletRequest request){
		log.info("/modificarFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		Integer v_codpro = Integer.valueOf( request.getParameter("idprod2"));
		Integer v_codfor = Integer.valueOf( request.getParameter("idfor2"));
		String v_formula = request.getParameter("text-box");
		String desestado = request.getParameter("desestado");
		String codcon = request.getParameter("codcon");
		Integer nroorden = Integer.valueOf(request.getParameter("nroorden2").trim());
		String desglosa = request.getParameter("desglosa");
		String tipofor = request.getParameter("tipofor");
		String sqlprogram = request.getParameter("sqlprogram");
		String grpeje = request.getParameter("grpeje");

		FormulaPlanilla p = new FormulaPlanilla();
		p.setIdProceso(v_codpro);
		p.setIdFormula(v_codfor);
		p.setDesFormula(v_formula);
		p.setIdConcepto(codcon);
		p.setDesGlosa(desglosa);
		p.setNroOrden(nroorden);
		p.setTipOut(tipofor);
		p.setFlgEstado(desestado);
		p.setSqlprogram(sqlprogram);
		p.setGrpeje(grpeje);

		formulaService.actualizar(p);

		return new ModelAndView("redirect:/listFormulas@"+v_codpro);
	}
}
