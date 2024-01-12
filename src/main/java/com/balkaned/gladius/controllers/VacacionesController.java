package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionProgramacion;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import com.balkaned.gladius.utils.CapitalizarCadena;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class VacacionesController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    LovsService lovsService;

    @Autowired
    VacacionesService vacacionesService;

    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/vacaciones@{idTrab}")
    public ModelAndView vacaciones(ModelMap model, HttpServletRequest request, @PathVariable String idTrab) {
        log.info("/vacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());

        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }

        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        empleado.setIexcodcia(idCompania);
        empleado.setIexcodtra(Integer.valueOf(idTrab));

        model.addAttribute("LstVacacionesCtl", vacacionesService.listarVacacionesCtl(empleado));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/listVacaciones");
    }

    @RequestMapping("/verDetalleVac@{idTrab}@{perMesIni}@{perMesFin}")
    public ModelAndView nuevoSueldoFijo(ModelMap model, HttpServletRequest request,
                                        @PathVariable String idTrab,
                                        @PathVariable String perMesIni,
                                        @PathVariable String perMesFin) {
        log.info("/verDetalleVac");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);
        model.addAttribute("perini", perMesIni);
        model.addAttribute("perfin", perMesFin);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);
        model.addAttribute("LstVacacionesPer", vacacionesService.listarVacacionesPer(emp, perMesIni, perMesFin));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/verDetalleVac");
    }

    @RequestMapping("/nuevasVacacionesValidacion@{idTrab}@{perMesIni}@{perMesFin}")
    public ModelAndView nuevasVacacionesValidacion(ModelMap model, HttpServletRequest request,
                                                   @PathVariable String idTrab,
                                                   @PathVariable String perMesIni,
                                                   @PathVariable String perMesFin) {
        log.info("/nuevasVacacionesValidacion");

        Integer idempleado = 0;

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);
        model.addAttribute("perini", perMesIni);
        model.addAttribute("perfin", perMesFin);

        Integer saldo = 0;
        saldo = vacacionesService.saldotraVac(idCompania, Integer.valueOf(idTrab), perMesIni, perMesFin);
        log.info("Saldo=" + saldo);

        if (saldo > 0) {
            return new ModelAndView("redirect:/nuevasVacacionesIns@{idTrab}@{perMesIni}@{perMesFin}@" + saldo);
        } else {
            model.addAttribute("msgErrorSaldoVacId", "ERVAC01");
            model.addAttribute("msgErrorSaldoVac", "No cuenta con saldo de dias para programar vacaciones");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/verDetalleVac");
    }


    @RequestMapping("/nuevasVacacionesIns@{idTrab}@{perini}@{perfin}@{saldo}")
    public ModelAndView nuevasVacacionesIns(ModelMap model, HttpServletRequest request,
                                            @PathVariable String idTrab,
                                            @PathVariable String perini,
                                            @PathVariable String perfin,
                                            @PathVariable String saldo) {
        log.info("/nuevasVacacionesIns");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);

        Empleado empleado = new Empleado();
        model.addAttribute("empleado", empleado);

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);
        model.addAttribute("saldo", saldo);
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));
        model.addAttribute("perini", perini);
        model.addAttribute("perfin", perfin);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/nuevasVacacionesIns");
    }

    @RequestMapping("/insertarVacaciones")
    public ModelAndView insertarVacaciones(ModelMap model, HttpServletRequest request) {
        log.info("/insertarVacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String iexcodtra = request.getParameter("iexcodtra");

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.parseInt(iexcodtra));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        String perini2 = request.getParameter("iexpermesini");
        String perfin2 = request.getParameter("iexpermesfin");
        String saldo = request.getParameter("saldo");
        String iexcodcia = String.valueOf(idCompania);
        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");

        String Msg_form_global = "";
        String resultado = "Error";

        Integer codcorrel = 0;
        Empleado empleado = new Empleado();
        Integer validador = 0;

        validador = vacacionesService.validaVac(Integer.valueOf(iexcodcia), Integer.valueOf(iexcodtra), iexfecini, iexfecfin);

        if (validador == 0) {
            VacacionProgramacion vacprg = new VacacionProgramacion();
            vacprg.setIexcodcia(Integer.valueOf(iexcodcia));
            vacprg.setIexcodtra(Integer.valueOf(iexcodtra));

            codcorrel = vacacionesService.getIdVacacionPrg(vacprg);
            log.info("codcorrelVAc: " + codcorrel);

            if (codcorrel > 0) {

                String iexpermesini2 = perini2;

                log.info("iexpermesini2: " + iexpermesini2);
                Integer mesfinal = Integer.valueOf(iexpermesini2) + 1;

                vacprg.setIexcorrel(codcorrel);
                vacprg.setIextipvac(request.getParameter("iextipvac"));
                vacprg.setIexfecini(iexfecini);
                vacprg.setIexfecfin(iexfecfin);
                vacprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
                vacprg.setIexglosa(request.getParameter("iexglosa"));
                vacprg.setIexusucrea("1");
                vacprg.setIexpermesini(iexpermesini2);
                vacprg.setIexpermesfin(String.valueOf(mesfinal));

                vacacionesService.insertarVacacionPrg(vacprg);
                Msg_form_global = "OK";
            }
        } else {

            Msg_form_global = "Error";
        }

        resultado = Msg_form_global;

        if (resultado.equals("OK")) {
            Empleado emp2 = new Empleado();
            emp2.setIexcodtra(Integer.valueOf(iexcodtra));
            emp2.setIexcodcia(Integer.valueOf(iexcodcia));

            vacacionesService.procesaVacacionCtl(emp2);
            return new ModelAndView("redirect:/verDetalleVac@" + iexcodtra + "@" + perini2 + "@" + perfin2);

        } else {
            model.addAttribute("saldo", saldo);
            model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));
            model.addAttribute("perini", perini2);
            model.addAttribute("perfin", perfin2);
            model.addAttribute("idTrab", iexcodtra);
            model.addAttribute("msg", "Las fechas se cruzan con programaciones anteriores");
        }

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/nuevasVacacionesIns");
    }

    @RequestMapping("/actualizarVacEmpl@{idTrab}")
    public ModelAndView actualizarVacEmpl(ModelMap model, HttpServletRequest request,
                                          @PathVariable String idTrab) {
        log.info("/actualizarVacEmpl");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        Empleado emp = empleadoService.recuperarCabecera(idCompania, Integer.valueOf(idTrab));
        model.addAttribute("emp", emp);
        model.addAttribute("nombrecompl", emp.getNomCompactoUpper());
        model.addAttribute("direccion", emp.getDireccion1());
        model.addAttribute("telefono", emp.getIexnrotelf());
        model.addAttribute("email", emp.getIexemail());
        model.addAttribute("nrodoc", emp.getIexnrodoc());
        model.addAttribute("puesto", emp.getDespuesto());
        model.addAttribute("fechaMod", emp.getIexfeccmod());
        model.addAttribute("estado", emp.getIexflgest());
        model.addAttribute("idComp", idCompania);
        model.addAttribute("iexlogo", emp.getIexlogo());
        model.addAttribute("urlLogo", urlLogo);

        String sexo;
        log.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        log.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        Empleado emp2 = new Empleado();
        emp2.setIexcodtra(Integer.valueOf(idTrab));
        emp2.setIexcodcia(idCompania);

        vacacionesService.procesaVacacionCtl(emp2);
        model.addAttribute("LstVacacionesCtl", vacacionesService.listarVacacionesCtl(emp2));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/listVacaciones");
    }

    @RequestMapping("/gestionTiempoListVacaciones")
    public ModelAndView gestionTiempoListVacaciones(ModelMap model, HttpServletRequest request) {
        log.info("/gestionTiempoListVacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String estado = request.getParameter("slc_estado");
        String reglab = request.getParameter("iexcodreg");
        String regimen = request.getParameter("iexcodreg");
        String codtra = request.getParameter("iexcodtra");
        String fecini = request.getParameter("fecini");
        String fecfin = request.getParameter("fecfin");

        //CapitalizarCadena capitalizarCadena = new CapitalizarCadena();
        //String fechaFormatterini = capitalizarCadena.fechaFormatter(fecini);
        //String fechaFormatterfin = capitalizarCadena.fechaFormatter(fecfin);
        String fechaFormatterini=fecini;
        String fechaFormatterfin=fecfin;

        Integer xcodtra = 0;
        if (codtra == null || codtra.isEmpty()) {
            xcodtra = 0;
        } else {
            try {
                xcodtra = Integer.parseInt(codtra);
            } catch (NumberFormatException e) {
                xcodtra = 0;
            }
        }

        log.info("fecini" + fecini);
        log.info("fecfin" + fecfin);
        log.info("P_FECINI " + fechaFormatterini);
        log.info("P_FECFIN " + fechaFormatterfin);
        log.info("P_REGLAB " + reglab);
        log.info("P_FLGEST " + estado);

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        if (fecini != null && fecfin != null) {
            model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(idCompania, regimen, fecini, fecfin, xcodtra));
        }

        model.addAttribute("fecini", fecini);
        model.addAttribute("fecfin", fecfin);
        model.addAttribute("iexcodreg", regimen);
        model.addAttribute("P_FECINI", fechaFormatterini);
        model.addAttribute("P_FECFIN", fechaFormatterfin);
        model.addAttribute("P_REGLAB", reglab);
        model.addAttribute("P_FLGEST", estado);

        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/gestionTiempoListVacaciones");
    }

    @RequestMapping("/nuevoGestionVacaciones")
    public ModelAndView nuevoGestionVacaciones(ModelMap model, HttpServletRequest request) {
        log.info("/nuevoGestionVacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/nuevoGestionVacaciones");
    }

    @RequestMapping("/insertGestionVacaciones")
    public ModelAndView insertGestionVacaciones(ModelMap model, HttpServletRequest request) {
        log.info("/insertGestionVacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String iexcodtra = request.getParameter("iexcodtra");
        String Msg_form_global = "";
        String resultado = "Error";
        Integer codcorrel = 0;
        Empleado empleado = new Empleado();
        Integer validador = 0;

        validador = vacacionesService.validaVac(empleado.getIexcodcia(), empleado.getIexcodtra(), request.getParameter("iexfecini"), request.getParameter("iexfecfin"));

        if (validador == 0) {
            VacacionProgramacion vacprg = new VacacionProgramacion();
            vacprg.setIexcodcia(idCompania);
            vacprg.setIexcodtra(Integer.parseInt(iexcodtra));

            codcorrel = vacacionesService.getIdVacacionPrg(vacprg);
            if (codcorrel > 0) {
                vacprg.setIexcorrel(codcorrel);
                vacprg.setIextipvac(request.getParameter("iextipvac"));
                vacprg.setIexfecini(request.getParameter("iexfecini"));
                vacprg.setIexfecfin(request.getParameter("iexfecfin"));
                vacprg.setIexusucrea("1");
                vacprg.setIexpermesini(request.getParameter("iexpervac"));
                String iexpervacString = request.getParameter("iexpervac");
                String iexnrodias = request.getParameter("iexnrodias");
                if (iexpervacString != null && !iexpervacString.isEmpty()) {
                    Integer iexpervac = Integer.parseInt(iexpervacString);
                    vacprg.setIexpermesfin(String.valueOf(iexpervac + 1));
                } else {
                    log.info("Error iexpervacString" + iexpervacString);
                }
                if (iexnrodias != null && !iexnrodias.isEmpty()) {
                    vacprg.setIexnrodias(Double.parseDouble(iexnrodias));
                } else {
                    log.info("Error iexnrodias" + iexnrodias);
                }

                vacacionesService.insertarVacacionPrg(vacprg);
                Msg_form_global = "OK";

                Empleado empleado2 = empleadoService.recuperarCabecera(vacprg.getIexcodcia(), vacprg.getIexcodtra());

                model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(vacprg.getIexcodcia(), request.getParameter("iexcodreg"), empleado2.getIexfecing(), vacprg.getIexfecfin(), vacprg.getIexcodtra()));
                model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(vacprg.getIexcodcia(), request.getParameter("iexcodreg")));
                model.addAttribute("iexcodreg", request.getParameter("iexcodreg"));
                model.addAttribute("iexcodtra", vacprg.getIexcodtra());
                model.addAttribute("fecini", empleado2.getIexfecing());
                model.addAttribute("fecfin", vacprg.getIexfecfin());

            }
        } else {
            log.info("Error en la validación de vacaciones: validador != 0");
            Msg_form_global = "Error en la validación de vacaciones";
        }

        return new ModelAndView("redirect:/gestionTiempoListVacaciones");
    }


    @RequestMapping("/editarGestionVacaciones@{idTrab}@{iexcorrel}")
    public ModelAndView editarGestionVacaciones(ModelMap model, HttpServletRequest request, @PathVariable String idTrab,
                                                @PathVariable String iexcorrel) {
        log.info("/editarGestionVacaciones@{idTrab}@{iexcorrel}s");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        log.info("idTrab: " + idTrab);
        model.addAttribute("idTrab", idTrab);
        log.info("iexcorrel: " + iexcorrel);
        model.addAttribute("iexcorrel", iexcorrel);

        log.info("idCompaniaXXXX: " + idCompania);
        log.info("idTraXXXXXb: " + idTrab);
        log.info("iexcorrel: " + iexcorrel);

        VacacionProgramacion vacprg = new VacacionProgramacion();
        vacprg.setIexcodcia(idCompania);
        vacprg.setIexcodtra(Integer.valueOf(idTrab));
        vacprg.setIexcorrel(Integer.valueOf(iexcorrel));

        VacacionProgramacion vacprg2 = new VacacionProgramacion();

        vacprg2 = vacacionesService.getVacacionPrg(vacprg);

        Integer saldo2 = 0;

        Empleado empleado2 = new Empleado();
        empleado2 = empleadoService.recuperarCabecera(vacprg.getIexcodcia(), vacprg.getIexcodtra());
        vacacionesService.procesaVacacionCtl(empleado2);
        saldo2 = vacacionesService.saldotraVac(vacprg.getIexcodcia(), vacprg.getIexcodtra(), vacprg.getIexpermesini(), vacprg.getIexpermesfin());

        model.addAttribute("xVacacionesPrg", vacprg2);
        model.addAttribute("iexcodreg", empleado2.getIexreglab());
        model.addAttribute("xSaldo", saldo2 + vacprg2.getIexnrodias());
        model.addAttribute("LstPervac", vacacionesService.listaSaldoVacTra(idCompania, empleado2.getIexreglab(), vacprg2.getIexcodtra()));
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado2.getIexreglab()));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));
        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/actualizarGestionVacaciones");
    }


    @RequestMapping("/actualizarGestionVacaciones")
    public ModelAndView actualizarGestionVacaciones(ModelMap model, HttpServletRequest request) {
        log.info("/actualizarGestionVacaciones");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        String codtra = request.getParameter("iexcodtra");
        String correl = request.getParameter("iexcorrel");


        VacacionProgramacion vacprg = new VacacionProgramacion();
        Empleado empleado2 = new Empleado();
        empleado2 = empleadoService.recuperarCabecera(vacprg.getIexcodcia(), vacprg.getIexcodtra());

        vacprg.setIexcodcia(idCompania);
        vacprg.setIexcodtra(Integer.valueOf(codtra));
        vacprg.setIexcorrel(Integer.valueOf(correl));

        vacprg.setIextipvac(request.getParameter("iextipvac"));
        vacprg.setIexfecini(request.getParameter("iexfecini"));
        vacprg.setIexfecfin(request.getParameter("iexfecfin"));
        vacprg.setIexnrodias(Double.parseDouble(request.getParameter("iexnrodias")));
        vacprg.setIexusucrea("1");
        vacprg.setIexpermesini(request.getParameter("iexpervac"));
        vacprg.setIexpermesfin(String.valueOf(Integer.parseInt(request.getParameter("iexpervac")) + 1));

        vacacionesService.actualizarVacacionPrg(vacprg);

        if (empleado2.getIexfecing() != null && request.getParameter("iexfecfin") != null) {
            model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(idCompania, empleado2.getIexreglab(), empleado2.getIexfecing(), request.getParameter("iexfecfin"), empleado2.getIexcodtra()));
        }
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado2.getIexreglab()));
        model.addAttribute("iexcodtra", empleado2.getIexcodtra());
        model.addAttribute("iexcodreg", empleado2.getIexreglab());
        model.addAttribute("fecini", empleado2.getIexfecing());
        model.addAttribute("fecfin", request.getParameter("iexfecfin"));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));

        return new ModelAndView("redirect:/gestionTiempoListVacaciones");
    }


    @RequestMapping("/fechaformatter")
    public ModelAndView fechaformatter(ModelMap model, HttpServletRequest request) {
        log.info("/fechaformatter");

        String user = (String) request.getSession().getAttribute("user");
        if (user == null || user.equals("") || user.equals("null")) {return new ModelAndView("redirect:/login2");}

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");

        String fecini = request.getParameter("fecini");
        String fecfin = request.getParameter("fecfin");


        CapitalizarCadena capitalizarCadena = new CapitalizarCadena();
        String fechaFormatterini = capitalizarCadena.fechaFormatter(fecini);
        String fechaFormatterfin = capitalizarCadena.fechaFormatter(fecfin);

        model.addAttribute("feciniFomrtter", fechaFormatterini);
        model.addAttribute("fecfinFomrtter", fechaFormatterfin);


        log.info("feciniFomrtter" + fechaFormatterini);
        log.info("fecfinFomrtter" + fechaFormatterfin);

        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/gestionTiempoListVacaciones");
    }

}

