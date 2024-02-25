package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.beans.Main;
import com.balkaned.gladius.dao.FormulaDao;
import com.balkaned.gladius.services.FormulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class FormulaServiceImpl implements FormulaService {
    @Autowired
    FormulaDao dao;

    @Override
    public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula) {
        return dao.getByIdProcesoIdFormula(idprod, idformula);
    }

    public void actualizar(FormulaPlanilla fplanilla) {

        dao.actualizar(fplanilla);
    }

    public void insertar(FormulaPlanilla fplanilla) {

        dao.insertar(fplanilla);
    }

    public void eliminar(Integer idprod, Integer idfor) {

        dao.eliminar(idprod, idfor);
    }

    public FormulaPlanilla recuperar(Integer idprod, Integer idformula){

        return dao.recuperar(idprod,idformula);
    }

    public String obtenerVariables(String script) {
        // Analiza el body de la formuación y detecta la lista de variables y las debe colocar en una lista o un arreglo

        //String string = "$VARIABLE$; $VARIABLE2$; $VARIABLE3$;";
        String string =  script;
        String variable_inicio="";
        Integer res=0;

        //Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
        Pattern pattern = Pattern.compile("(\\$)(.*?)(\\$)");
        Matcher matcher = pattern.matcher(string);
        System.out.println(string);
        List<String> listMatches = new ArrayList<String>();

        while(matcher.find()) {
            listMatches.add(matcher.group(2));
        }

        res=0;

        for(String s : listMatches){
            if (variable_inicio.indexOf("$"+s+"$")==-1)  {
                //System.out.println("$"+s+"$=1");
                variable_inicio=variable_inicio+" var $"+s+"$=1;";
            }
        }
        // Ordena las variables y las coloca en un String

        return variable_inicio;
    }

    public String testEjecucion(String script) {
        // Si los proceso previos se han desarrolldo correctamente
        // Se rocede a Ejecutar el sscript completo para ver si la formula es correcta.
        // Si es correcta la formula retornara la formula concatenada.

        String target;
        String v_dias;

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object result = null;
        String vformula;
        String resultado="";

        Main result2 = new Main();
        Main result3 = new Main();

        vformula= script+" result2.setValue($resultado$); "+" result3.setValue($salto$); " ;

        engine.put("result2", result2);
        engine.put("result3", result3);
        //String script = "3 + 4; result.setValue(1);";

        try {
            engine.eval(vformula);
            resultado="1";
        } catch (Exception ex) {
            log.info("Error: "+ex.getMessage());
            resultado="0";
        }

        Double returnedValue = result2.getValue();   // Resultado final
        Double returnedValue2 = result3.getValue();  //

        log.info("Formula : {  "+vformula+" } " );
        log.info("Valor final= " + returnedValue+" , Salto="+returnedValue2 );

        return resultado;
    }

    public void grabaVariableResultado(Integer idprod, Integer idformula, String Variable, String resultado){
        dao.grabaVariableResultado(idprod,idformula,Variable,resultado);
    }
}
