package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.CuentaContable;
import com.balkaned.gladius.beans.Lovs;
import com.balkaned.gladius.services.CuentasContablesService;
import com.balkaned.gladius.services.LovsService;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Slf4j
public class CuentasContablesController {
	@Autowired
	CuentasContablesService service;

	@Autowired
	LovsService lovsService;

	@Autowired
	Sessionattributes sessionattributes;

	@RequestMapping("/listarCuentasContables")
	public ModelAndView listarCuentasContables(
	 ModelMap model, HttpServletRequest request
	) {
		log.info("/listarCuentasContables");

		String user = (String) request.getSession().getAttribute("user");
		log.info("user:"+user);
		if (user == null || user.equals("") || user.equals("null")) {
			log.info("Ingreso a user null");
			return new ModelAndView("redirect:/login2");
		}

		sessionattributes.getVariablesSession(model, request);
		List<CuentaContable> cuentasContablesList = service.listarCuentasContables();
		model.addAttribute("cuentasContablesList", cuentasContablesList);
		return new ModelAndView("public/gladius/confPlanilla/cuentasContables/listarCuentasContables");
	}

	@RequestMapping("/insertarCuentasContables")
	public ModelAndView insertarCuentasContables(
	 ModelMap model, HttpServletRequest request
	) {
		log.info("/insertarCuentasContables");

		String user = (String) request.getSession().getAttribute("user");
		log.info("user:"+user);
		if (user == null || user.equals("") || user.equals("null")) {
			log.info("Ingreso a user null");
			return new ModelAndView("redirect:/login2");
		}

		sessionattributes.getVariablesSession(model, request);
		List<Lovs> lovConcepto = lovsService.getLovsCContables();
		model.addAttribute("lovConcepto", lovConcepto);
		return new ModelAndView("public/gladius/confPlanilla/cuentasContables/nuevaCuentaContable");
	}

	@RequestMapping("/addCuentaContable")
	public ModelAndView addCuentaContable(
	 ModelMap model, HttpServletRequest request
	) {
		log.info("/addCuentaContable");

		String user = (String) request.getSession().getAttribute("user");
		log.info("user:"+user);
		if (user == null || user.equals("") || user.equals("null")) {
			log.info("Ingreso a user null");
			return new ModelAndView("redirect:/login2");
		}

		sessionattributes.getVariablesSession(model, request);
		Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
		CuentaContable cuentaContable = new CuentaContable();
		cuentaContable.setIexccodcta(request.getParameter("iexccodcta"));
		cuentaContable.setIexdescta(request.getParameter("iexdescta"));
		cuentaContable.setDesdet(request.getParameter("desdet"));
		service.insertarCuentaContable(cuentaContable, idCompania);
		return new ModelAndView("redirect:/listarCuentasContables");
	}
}
