package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.beans.ProcesoPlanilla;
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
public class ProcesoFormulaController {

	@Autowired
	ProcesoFormulaService procesoFormulaService;
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
		List<ProcesoForm> proFosList = procesoFormulaService.listProcesoFormula();
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
		procesoFormulaService.insertarProcesoFormula(procesoForm);
		return new ModelAndView("redirect:/listProcesoFormulas");
	}

	@RequestMapping("/eliminarProcesoFormula@{idProceso}")
	public ModelAndView eliminarProcesoFormula(
	 ModelMap model, HttpServletRequest request, @PathVariable Integer idProceso) {
		log.info("/eliminarProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		procesoFormulaService.eliminarProcesoFormula(idProceso);

		return new ModelAndView("redirect:/listProcesoFormulas");
	}

	@RequestMapping("/editarProcesoFormula@{idProceso}")
	public ModelAndView editarProcesoFormula(
			ModelMap model, HttpServletRequest request, @PathVariable Integer idProceso) {
		log.info("/editarProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		model.addAttribute("Lovs_grpplanilla", lovsService.getLovs("55", "%"));
		model.addAttribute("Lovs_reglaboral", lovsService.getLovs("33", "%"));
		model.addAttribute("pplanillax", procesoFormulaService.recuperar(idProceso));

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/editarProcesoFormula");
	}

	@RequestMapping("/modificarProcesoFormula")
	public ModelAndView modificarProcesoFormula(ModelMap model, HttpServletRequest request) {
		log.info("/modificarProcesoFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		Integer codproceso = Integer.valueOf(request.getParameter("codProceso2"));
		String proceso = request.getParameter("desProcesos");
		String procesocorto = request.getParameter("desCortoProcesos");
		String grp_planilla = request.getParameter("lov_grpplanilla");
		String reg_laboral = request.getParameter("lov_regimenlab");
		String boleta = request.getParameter("reporteBoleta");
		String tipproc = request.getParameter("tipoProceso");
		String boletaind = request.getParameter("reporteIndividual");
		String boletares = request.getParameter("reporteResumen");

		if( request.getParameter("lov_regimenlab")==null){
			reg_laboral ="1";
		}

		ProcesoPlanilla p = new ProcesoPlanilla();
		p.setIdProceso(codproceso);
		p.setDesProceso(proceso);
		p.setDesProcesoCorto(procesocorto);
		p.setIdRegLab(reg_laboral);
		p.setDesGrp(grp_planilla);
		p.setBolProceso(boleta);
		p.setIdTipProceso(tipproc);
		p.setBolProcesoind(boletaind);
		p.setBolProcesores(boletares);

		procesoFormulaService.actualizar(p);

		return new ModelAndView("redirect:/listProcesoFormulas");
	}
}
