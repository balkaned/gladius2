package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.services.FormulaService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.services.ProcesoFormulaService;
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

	@Autowired
	ProcesoFormulaService procesoFormulaService;

	@RequestMapping("/listFormulas@{idProceso}")
	public ModelAndView listFormulas(ModelMap model, HttpServletRequest request,
									 @PathVariable Integer idProceso) {
		log.info("/listFormulas");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);
		List<FormulaXConcepto> formulaXConceptoList = service.listFormulaXConcepto();
		model.addAttribute("formulaXConceptoList", formulaXConceptoList);
		model.addAttribute("idProceso", idProceso);

		ProcesoPlanilla pro=procesoFormulaService.recuperar(Integer.valueOf(idProceso));
		CapitalizarCadena cap= new CapitalizarCadena();
		String desproceso2=cap.letras(pro.getDesProceso());
		model.addAttribute("desproceso",desproceso2);

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

	@RequestMapping("/nuevaFormula@{idProceso}")
	public ModelAndView nuevaFormula(ModelMap model, HttpServletRequest request,
									 @PathVariable("idProceso") Integer idProceso) {
		log.info("/nuevaFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		model.addAttribute("idProceso",idProceso);
		model.addAttribute("Lovs_conxprod", lovsService.getConceptoxProc(idProceso));

		return new ModelAndView("public/gladius/confPlanilla/procesosyform/formulas/nuevaFormula");
	}

	@RequestMapping("/insertarNuevaFormula")
	public ModelAndView insertarNuevaFormula(ModelMap model, HttpServletRequest request){
		log.info("/insertarNuevaFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		Integer idprod = Integer.valueOf(request.getParameter("idprod2"));
		Integer nroorden = Integer.valueOf(request.getParameter("nroorden"));
		String desglosa = request.getParameter("desglosa");
		String desformula = request.getParameter("text-box");
		String codcon = request.getParameter("codcon");
		String desestado = request.getParameter("desestado");
		String tipofor = request.getParameter("tipofor");
		//String vardes = request.getParameter("vardes");
		String sqlprogram = request.getParameter("sqlprogram");
		String grpeje = request.getParameter("grpeje");

		FormulaPlanilla p = new FormulaPlanilla();
		p.setIdProceso(idprod);
		p.setDesGlosa(desglosa);
		p.setDesFormula(desformula);
		p.setIdConcepto(codcon);
		p.setFlgEstado(desestado);
		p.setNroOrden(nroorden);
		p.setTipOut(tipofor);
		p.setDesVar("var");
		p.setSqlprogram(sqlprogram);
		p.setGrpeje(grpeje);

		formulaService.insertar(p);

		return new ModelAndView("redirect:/listFormulas@"+idprod);
	}

	@RequestMapping("/deleteFormula@{idProceso}@{idForm}")
	public ModelAndView deleteFormula(ModelMap model, HttpServletRequest request,
									 @PathVariable("idProceso") Integer idProceso,
									  @PathVariable("idForm") Integer idForm) {
		log.info("/deleteFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		sessionattributes.getVariablesSession(model, request);

		formulaService.eliminar(idProceso,idForm);

		return new ModelAndView("redirect:/listFormulas@"+idProceso);
	}

	@RequestMapping("/compilarFormula@{idProceso}@{idForm}")
	public ModelAndView compilarFormula(ModelMap model, HttpServletRequest request,
									  @PathVariable("idProceso") Integer idProceso,
									  @PathVariable("idForm") Integer idForm) {
		log.info("/compilarFormula");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

		//sessionattributes.getVariablesSession(model, request);

		String v_result ="1";
		String v_get_result=" $resultado$ ; $salto$ ";
		String v_result_message ;
		String v_script;
		String v_script2;
		String v_all_script="";

		Integer v_codpro = idProceso;
		Integer v_codfor = idForm;

		FormulaPlanilla fplanilla = formulaService.recuperar(idProceso,idForm);

		if(fplanilla.getTipOut().equals("1") || fplanilla.getTipOut().equals("3")){
			// Parsear la  variables
			v_script= formulaService.obtenerVariables(fplanilla.getDesFormula());

			// Inicializar variables modo prueba

			// Unificar cabecera y el detalle
			v_all_script=  v_script+" var $resultado$; var $salto$;  "+fplanilla.getDesFormula();

			// Test de Ejecución del script
			v_result=formulaService.testEjecucion(v_all_script);

			// Capturar si la ejecución es correcta
			// Si es corecta cambiar el estado a 3 de compilado

			if (v_result.equals("1") ) {
				v_result_message="Correcto" ;
				// Insertar Variable inicial y resultado
				formulaService.grabaVariableResultado(v_codpro,v_codfor,v_script,v_get_result);
			}else if (v_result.equals("0") ){
				v_result_message="Incorrecto" ;
			}

			// Si no es correcta colocar 9 como error al compilar.
			v_result="";
			v_script="";

			model.addAttribute("codpro",v_codpro );
		}else{
			formulaService.grabaVariableResultado(v_codpro, v_codfor, "", "");
		}

		return new ModelAndView("redirect:/listFormulas@"+idProceso);
	}

	@RequestMapping(value = "/traducirFormulaAjax", method = {RequestMethod.POST, RequestMethod.GET})
	public ModelAndView traducirFormulaAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		log.info("/traducirFormulaAjax");

		String user = (String) request.getSession().getAttribute("user");
		if (user == null || user.equals("") || user.equals("null")) {
			return new ModelAndView("redirect:/login2");
		}

		List<Concepto> lsconcept = lovsService.getConceptoLov();

		String json = new Gson().toJson(lsconcept);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(json);

		return null;
	}

}
