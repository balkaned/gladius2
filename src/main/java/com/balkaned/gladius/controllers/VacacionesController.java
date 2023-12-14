package com.balkaned.gladius.controllers;

import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.VacacionProgramacion;
import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@RestController
public class VacacionesController {
    static Logger logger = Logger.getLogger(VacacionesController.class.getName());
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
        logger.info("/vacaciones");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
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
        logger.info("/verDetalleVac");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("LstVacacionesPer", vacacionesService.listarVacacionesPer(emp, perMesIni, perMesFin));

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/verDetalleVac");
    }

    @RequestMapping("/nuevasVacacionesValidacion@{idTrab}@{perMesIni}@{perMesFin}")
    public ModelAndView nuevasVacacionesValidacion(ModelMap model, HttpServletRequest request,
                                                   @PathVariable String idTrab,
                                                   @PathVariable String perMesIni,
                                                   @PathVariable String perMesFin) {
        logger.info("/nuevasVacacionesValidacion");

        Integer idempleado = 0;

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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("perini", perMesIni);
        model.addAttribute("perfin", perMesFin);

        Integer saldo = 0;
        saldo = vacacionesService.saldotraVac(idCompania, Integer.valueOf(idTrab), perMesIni, perMesFin);
        logger.info("Saldo=" + saldo);

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
        logger.info("/nuevasVacacionesIns");

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idTrab: " + idTrab);
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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
        model.addAttribute("sexo", sexo);

        model.addAttribute("saldo", saldo);
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));
        model.addAttribute("perini", perini);
        model.addAttribute("perfin", perfin);

        return new ModelAndView("public/gladius/organizacion/gestionEmpleado/vacaciones/nuevasVacacionesIns");
    }

    @RequestMapping("/insertarVacaciones")
    public ModelAndView insertarVacaciones(ModelMap model, HttpServletRequest request) {
        logger.info("/insertarVacaciones");

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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
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
            logger.info("codcorrelVAc: " + codcorrel);

            if (codcorrel > 0) {

                String iexpermesini2 = perini2;

                logger.info("iexpermesini2: " + iexpermesini2);
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
        logger.info("/actualizarVacEmpl");

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
        logger.info("emp.getIexcodsex(): " + emp.getIexcodsex());
        if (emp.getIexcodsex() == null) {
            sexo = "NA";
        } else {
            sexo = emp.getIexcodsex();
        }
        logger.info("sexo: " + sexo);
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
        logger.info("/gestionTiempoListVacaciones");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);


        String regimen = request.getParameter("iexcodreg");
        String codtra = request.getParameter("iexcodtra");
        String fecini = request.getParameter("fecini");
        String fecfin = request.getParameter("fecfin");

        model.addAttribute("fecini", fecini);
        model.addAttribute("fecfin", fecfin);
        model.addAttribute("iexcodreg", regimen);

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

        logger.info("fecini" + fecini);
        logger.info("fecfin" + fecfin);

        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        if (fecini != null && fecfin != null) {
            model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(idCompania, regimen, fecini, fecfin, xcodtra));
        }
        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/gestionTiempoListVacaciones");
    }

    @RequestMapping("/nuevoGestionVacaciones")
    public ModelAndView nuevoGestionVacaciones(ModelMap model, HttpServletRequest request) {
        logger.info("/nuevoGestionVacaciones");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }
        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);


        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/nuevoGestionVacaciones");
    }


    @RequestMapping("/insertGestionVacaciones")
    public ModelAndView insertGestionVacaciones(ModelMap model, HttpServletRequest request) {
        logger.info("/insertGestionVacaciones");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }

        String Msg_form_global = "";
        Integer idempleado = 0;

        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);

        String iexfecini = request.getParameter("iexfecini");
        String iexfecfin = request.getParameter("iexfecfin");

        Integer codcorrel = 0;
        Empleado empleado = new Empleado();
        Integer validador = 0;
        if (iexfecini != null && iexfecfin != null) {
            validador = vacacionesService.validaVac(empleado.getIexcodcia(), empleado.getIexcodtra(), iexfecini, iexfecfin);
        }
        if (validador == 0) {
            VacacionProgramacion vacprg = new VacacionProgramacion();
            vacprg.setIexcodcia(idCompania);
            idempleado = empleadoService.obtieneIdEmpleado(empleado);
            if (idempleado > 0) {
                vacprg.setIexcodtra(idempleado);
            } else {
                idempleado = 0;
            }
            codcorrel = vacacionesService.getIdVacacionPrg(vacprg);

            logger.info("codcorrelVAc: " + codcorrel);
            logger.info("idCompania: " + idCompania);
            logger.info("validador: " + validador);
            logger.info("iexfecini: " + request.getParameter("iexfecini"));
            logger.info("iexfecini: " + request.getParameter("iexfecfin"));
            logger.info("Obtiene Id empleado :" + idempleado);
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
                    Msg_form_global = "Error" + iexpervacString;
                }
                if (iexnrodias != null && !iexnrodias.isEmpty()) {
                    vacprg.setIexnrodias(Double.parseDouble(iexnrodias));
                } else {
                    Msg_form_global = "Error" + iexnrodias;
                }
                vacacionesService.insertarVacacionPrg(vacprg);
                Msg_form_global = "OK";

                Empleado empleado2 = new Empleado();
                empleado2 = empleadoService.recuperarCabecera(vacprg.getIexcodcia(), vacprg.getIexcodtra());
                if (iexfecini != null && iexfecfin != null) {
                    model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(vacprg.getIexcodcia(), request.getParameter("iexcodreg"), iexfecini, iexfecfin, vacprg.getIexcodtra()));
                    ;
                }
                model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(vacprg.getIexcodcia(), request.getParameter("iexcodreg")));
                model.addAttribute("iexcodtra", vacprg.getIexcodtra());
                model.addAttribute("iexcodreg", request.getParameter("iexcodreg"));
                model.addAttribute("fecini", empleado2.getIexfecing());
                model.addAttribute("fecfin", vacprg.getIexfecfin());
            }
        } else {

            Msg_form_global = "Error";
        }

        return new ModelAndView("redirect:/gestionTiempoListVacaciones");
    }


    @RequestMapping("/editarGestionVacaciones@{idTrab}@{iexcorrel}")
    public ModelAndView editarGestionVacaciones(ModelMap model, HttpServletRequest request, @PathVariable String idTrab,
                                                @PathVariable String iexcorrel) {
        logger.info("/editarGestionVacaciones@{idTrab}@{iexcorrel}s");
        String user = (String) request.getSession().getAttribute("user");

        if (request.getSession().getAttribute("user") == null) {
            return new ModelAndView("redirect:/login2");
        }
        String usuario = (String) request.getSession().getAttribute("user");
        String idusuario = (String) request.getSession().getAttribute("idUser");
        String email = (String) request.getSession().getAttribute("email");
        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
        String rucComp = (String) request.getSession().getAttribute("ruccomp");
        String urlLogo = (String) request.getSession().getAttribute("urlLogo");

        logger.info("idCompaniaXXXX: " + idCompania);
        logger.info("idTraXXXXXb: " + idTrab);
        logger.info("iexcorrel: " + iexcorrel);
        model.addAttribute("usuario", usuario);
        model.addAttribute("idusuario", idusuario);
        model.addAttribute("email", email);
        model.addAttribute("firstCharacter", firstCharacter);
        model.addAttribute("nombreComp", nombreComp);
        model.addAttribute("rucComp", rucComp);
        model.addAttribute("idComp", idCompania);
        model.addAttribute("urlLogo", urlLogo);
        model.addAttribute("idTrab", idTrab);


        VacacionProgramacion vacprg = new VacacionProgramacion();
        vacprg.setIexcodcia(idCompania);
        vacprg.setIexcodtra(Integer.valueOf(idTrab));
        vacprg.setIexcorrel(Integer.valueOf(iexcorrel));

        Integer saldo2 = 0;
        Empleado empleado2 = new Empleado();
        empleado2 = empleadoService.recuperarCabecera(vacprg.getIexcodcia(), vacprg.getIexcodtra());
        vacacionesService.procesaVacacionCtl(empleado2);
        saldo2 = vacacionesService.saldotraVac(vacprg.getIexcodcia(), vacprg.getIexcodtra(), vacprg.getIexpermesini(), vacprg.getIexpermesfin());

        model.addAttribute("iexcodreg", empleado2.getIexreglab());

        Double iexnrodias = vacprg.getIexnrodias();
        if (iexnrodias != null) {
            model.addAttribute("xSaldo", saldo2 + iexnrodias);
        } else {
            model.addAttribute("xSaldo Null", saldo2);
        }
        model.addAttribute("LstPervac", vacacionesService.listaSaldoVacTra(idCompania, empleado2.getIexreglab(), vacprg.getIexcodtra()));
        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado2.getIexreglab()));
        model.addAttribute("xVacacionesPrg", vacacionesService.getVacacionPrg(vacprg));
        model.addAttribute("Lovs_regimen", lovsService.getRegimenProc());
        model.addAttribute("lovTipvaca", lovsService.getLovs("56", "%"));

        return new ModelAndView("public/gladius/gestionTiempo/vacaciones/actualizarGestionVacaciones");
    }


//    @RequestMapping("/actualizarGestionVacaciones")
//    public ModelAndView actualizarGestionVacaciones(ModelMap model, HttpServletRequest request) {
//        logger.info("/actualizarGestionVacaciones");
//        String user = (String) request.getSession().getAttribute("user");
//
//        if (request.getSession().getAttribute("user") == null) {
//            return new ModelAndView("redirect:/login2");
//        }
//        String usuario = (String) request.getSession().getAttribute("user");
//        String idusuario = (String) request.getSession().getAttribute("idUser");
//        String email = (String) request.getSession().getAttribute("email");
//        String firstCharacter = (String) request.getSession().getAttribute("firstCharacter");
//        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");
//        String nombreComp = (String) request.getSession().getAttribute("nombrecomp");
//        String rucComp = (String) request.getSession().getAttribute("ruccomp");
//        String urlLogo = (String) request.getSession().getAttribute("urlLogo");
//
//        model.addAttribute("usuario", usuario);
//        model.addAttribute("idusuario", idusuario);
//        model.addAttribute("email", email);
//        model.addAttribute("firstCharacter", firstCharacter);
//        model.addAttribute("nombreComp", nombreComp);
//        model.addAttribute("rucComp", rucComp);
//        model.addAttribute("idComp", idCompania);
//        model.addAttribute("urlLogo", urlLogo);
//
//        String Msg_form_global = "";
//        String resultado = "Error";
//
//        String iexcodtra = request.getParameter("iexcodtra");
//        String correl = request.getParameter("iexcorrel");
//
//        Integer xcodtra = 0;
//        try {
//            if (iexcodtra != null && !iexcodtra.trim().isEmpty()) {
//                xcodtra = Integer.parseInt(iexcodtra.trim());
//            }
//        } catch (NumberFormatException e) {
//            logger.info("Error al convertir el código de trabajador: " + e);
//            Msg_form_global = "Error al convertir el código de trabajador a número.";
//            resultado = "Error";
//        }
//        VacacionProgramacion vacprg = new VacacionProgramacion();
//
//        Empleado empleado2 = new Empleado();
//        empleado2 = empleadoService.recuperarCabecera(idCompania, xcodtra);
//
//        vacprg.setIexcodcia(idCompania);
//        vacprg.setIexcodtra(xcodtra);
//        if (correl != null && !correl.isEmpty()) {
//            try {
//                int correlInt = Integer.parseInt(correl);
//                vacprg.setIexcorrel(correlInt);
//            } catch (NumberFormatException e) {
//                logger.info("Error al convertir el código de trabajador: " + e);
//                Msg_form_global = "Error al convertir el código de trabajador a número.";
//                resultado = "Error";
//            }
//        } else {
//            resultado = "Error";
//        }
//        vacprg.setIextipvac(request.getParameter("iextipvac"));
//        vacprg.setIexfecini(request.getParameter("iexfecini"));
//        vacprg.setIexfecfin(request.getParameter("iexfecfin"));
//        try {
//            String iexnrodiasString = request.getParameter("iexnrodias");
//            if (iexnrodiasString != null && !iexnrodiasString.trim().isEmpty()) {
//                double iexnrodias = Double.parseDouble(iexnrodiasString);
//                vacprg.setIexnrodias(iexnrodias);
//            } else {
//                logger.info("iexnrodiasString is null or empty: " + iexnrodiasString);
//            }
//        } catch (NumberFormatException e) {
//            logger.info("Number format exception for iexnrodiasString: " + e.getMessage());
//        }
//        vacprg.setIexusucrea("1");
//        vacprg.setIexpermesini(request.getParameter("iexpervac"));
//        try {
//            int experVac = Integer.parseInt(request.getParameter("iexpervac"));
//            vacprg.setIexpermesfin(String.valueOf(experVac + 1));
//        } catch (NumberFormatException e) {
//            logger.info("error experVac" + e);
//        }
//
//        vacacionesService.actualizarVacacionPrg(vacprg);
//
//        model.addAttribute("LstVacacionesView", vacacionesService.listaVacacionesGen(idCompania, empleado2.getIexreglab(), empleado2.getIexfecing(), request.getParameter("iexfecfin"), empleado2.getIexcodtra()));
//        model.addAttribute("LstTrabajadorReg", vacacionesService.listaTrabajadoresReg(idCompania, empleado2.getIexreglab()));
//        model.addAttribute("iexcodtra", empleado2.getIexcodtra());
//        model.addAttribute("iexcodreg", empleado2.getIexreglab());
//        model.addAttribute("fecini", empleado2.getIexfecing());
//        model.addAttribute("fecfin", request.getParameter("iexfecfin"));
//
//        return new ModelAndView("redirect:/gestionTiempoListVacaciones");
//    }


}

