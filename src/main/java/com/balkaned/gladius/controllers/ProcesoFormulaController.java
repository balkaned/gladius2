package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ProcesoForm;
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
import java.util.logging.Logger;

@RestController
@Slf4j
public class ProcesoFormulaController {

	@Autowired
	ProcesoFormulaService service;
	@Autowired
	LovsService lovsService;
	@Autowired
	Sessionattributes sessionattributes;

	@RequestMapping("/listProcesoFormulas")
	public ModelAndView listConceptos(
	 ModelMap model, HttpServletRequest request) {
		log.info("/listProcesoFormulas");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		List<ProcesoForm> proFosList = service.listProcesoFormula();
		model.addAttribute("proFosList", proFosList);
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/listProcesoFormula");
	}

	@RequestMapping("/nuevoProcesoFormula")
	public ModelAndView nuevoProcesoFormula(
	 ModelMap model, HttpServletRequest request) {
		log.info("/nuevoProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		model.addAttribute("Lovs_grpplanilla", lovsService.getLovs("55", "%"));
		model.addAttribute("Lovs_reglaboral", lovsService.getLovs("33", "%"));
		return new ModelAndView("public/gladius/confPlanilla/procesosyform/nuevoProcesoFormula");
	}

	@RequestMapping("/addProcesoFormula")
	public ModelAndView addProcesoFormula(
	 ModelMap model, HttpServletRequest request) {
		log.info("/addProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

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
		log.info("/eliminarProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		service.eliminarProcesoFormula(idProceso);
		return new ModelAndView("redirect:/listProcesoFormulas");
	}
}
