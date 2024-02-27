package com.balkaned.gladius.controllers;


import com.balkaned.gladius.services.*;
import com.balkaned.gladius.servicesImpl.Sessionattributes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@RestController
@Slf4j
public class GestionAfpNet {
    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Autowired
    PlanillaService planillaService;
    @Autowired
    Sessionattributes sessionattributes;

    @RequestMapping("/gestionAfp")
    public ModelAndView gestionAfp(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        log.info("/gestionAfp");

        String user = (String) request.getSession().getAttribute("user");
        log.info("user:" + user);
        if (user == null || user.equals("") || user.equals("null")) {
            log.info("Ingreso a user null");
            return new ModelAndView("redirect:/login2");
        }

        sessionattributes.getVariablesSession(model, request);
        Integer idCompania = (Integer) request.getSession().getAttribute("idCompania");


        String v_idproceso = "";
        String v_idperiodo = "";
        Integer v_codcia = idCompania;
        v_idperiodo = request.getParameter("permes");
        String file2 = request.getParameter("file");


        planillaService.AfpNetExe(v_codcia, v_idperiodo);

        log.info("v_codcia " + v_codcia);
        log.info("v_idperiodo " + v_idperiodo);
        log.info("v_file2 " + file2);

        model.addAttribute("permes", v_idperiodo);
        model.addAttribute("P_PERMES", v_idperiodo);

        return new ModelAndView("public/gladius/gestionProceso/afpNet/gestionAfp");
    }

}
