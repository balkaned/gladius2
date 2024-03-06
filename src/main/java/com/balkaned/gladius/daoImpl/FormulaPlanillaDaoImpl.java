package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.FormulaPlanillaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository("FormulaPlanillaDao")
@Slf4j
public class FormulaPlanillaDaoImpl implements FormulaPlanillaDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<FormulaPlanilla> listar(String text) {

        String sql = "select " +
                "a.procodpro," +
                "a.forcodfor," +
                "a.proglosa," +
                "a.fordesfor," +
                "a.forcodcon," +
                "c.coodescon," +
                "c.coocodforvar," +
                "a.FORFLGEST, " +
                "a.FORORDEN, " +
                "a.FORTIPOUT fortipout," +
                "a.FORVARDES," +
                "a.FORUSUCREA," +
                "a.FORFECCREA," +
                "a.FORUSUMOD," +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a " +
                "       inner join iexconcepto c on  a.forcodcon= c.coocodcon  " +
                "where  " +
                "a.procodpro=" + text + "  " +
                "order by a.fororden asc     ";

        return template.query(sql, new ResultSetExtractor<List<FormulaPlanilla>>() {

            public List<FormulaPlanilla> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<FormulaPlanilla> lista = new ArrayList<FormulaPlanilla>();

                while (rs.next()) {
                    FormulaPlanilla p = new FormulaPlanilla();

                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setIdFormula(rs.getInt("forcodfor"));
                    p.setIdConcepto(rs.getString("forcodcon"));
                    p.setDesConcepto(rs.getString("coodescon"));
                    p.setDesGlosa(rs.getString("proglosa"));
                    p.setDesFormula(rs.getString("fordesfor"));
                    p.setNroOrden(rs.getInt("fororden"));
                    p.setDesVar(rs.getString("FORVARDES"));
                    p.setFlgEstado(rs.getString("FORFLGEST"));
                    p.setCooforVar(rs.getString("coocodforvar"));
                    p.setGrpeje(rs.getString("grpeje"));
                    p.setSqlprogram(rs.getString("sqlprogram"));
                    p.setTipOut(rs.getString("fortipout"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public String getListVars(Integer idprod, String script) {
        //Analiza el body de la formuación y detecta la lista de variables y las debe colocar en una lista o un arreglo

        //String string = "$VARIABLE$; $VARIABLE2$; $VARIABLE3$;";
        String string = script;
        String variable_inicio = "";
        Integer res = 0;

        String variable_sql = "";
        String variable_sql2 = "";
        Integer counter = 0;

        //Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
        Pattern pattern = Pattern.compile("(\\$)(.*?)(\\$)");
        Matcher matcher = pattern.matcher(string);
        log.info(string);

        List<String> listMatches = new ArrayList<String>();

        while (matcher.find()) {
            listMatches.add(matcher.group(2));
        }

        List<String> listVariable = new ArrayList<String>();
        res = 0;

        for (String s : listMatches) {
            if (variable_inicio.indexOf("$" + s + "$") == -1) {
                //System.out.println("$"+s+"$=1");
                variable_inicio = variable_inicio + " var $" + s + "$=0;";
                listVariable.add("$" + s + "$");
                variable_sql = variable_sql + "'$" + s + "$',";
                counter++;
            }
        }

        variable_sql = "( " + variable_sql + "'')";
        /// hacer la consulta en base de datos y obtener la lista de conceptos con su atributo de que si es grupo o no es grupo de concepto.
        // Ordena las variables y las coloca en un String
        /// consultar de base de datos

        variable_sql2 = obtenerVariableSql2(idprod,variable_sql);
        variable_sql2 = "( " + variable_sql2 + "'')";
        // ------
        //return variable_inicio;
        return variable_sql2;
    }

    public String obtenerVariableSql2(Integer idprod, String variable_sql) {

        final String[] variable_sql2 = {""};

        String sql = "select  " +
                "coocodforvar, " +
                "flg_agrupable " +
                "from iexproxconcepto, iexconcepto " +
                "where " +
                "procodcon=coocodcon and " +
                "procodpro=" + idprod + " and " +
                "trim(coocodforvar) in " + variable_sql;

        return (String) template.query(sql, new ResultSetExtractor<String>() {
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    variable_sql2[0] = variable_sql2[0] + "'" + rs.getString("coocodforvar") + "',";
                }
                return variable_sql2[0];
            }
        });
    }

    public List<ConceptoXProceso> obtenerListVariables_glb(Integer idprod, String script){
        // Analiza el body de la formuación y detecta la lista de variables y las debe colocar en una lista o un arreglo

        //String string = "$VARIABLE$; $VARIABLE2$; $VARIABLE3$;";
        String string =  script;
        String variable_inicio="";
        Integer res=0;

        String variable_sql="";
        Integer counter=0;

        //Pattern pattern = Pattern.compile("(\\[)(.*?)(\\])");
        Pattern pattern = Pattern.compile("(\\$)(.*?)(\\$)");
        Matcher matcher = pattern.matcher(string);
        //    System.out.println(string);
        List<String> listMatches = new ArrayList<String>();

        while(matcher.find()) {
            listMatches.add(matcher.group(2));
        }

        List<String> listVariable = new ArrayList<String>();
        res=0;

        for(String s : listMatches) {
            if (variable_inicio.indexOf("$"+s+"$")==-1)  {
                //System.out.println("$"+s+"$=1");
                variable_inicio=variable_inicio+" var $"+s+"$=0;";
                listVariable.add("$"+s+"$");
                variable_sql=variable_sql+"'$"+s+"$',";
                counter++;
            }
        }

        variable_sql ="( "+variable_sql+"'')" ;

        List<ConceptoXProceso> listVariable2 = obtenerListVariablesConc(idprod,variable_sql);

        return listVariable2;
    }

    public List<ConceptoXProceso> obtenerListVariablesConc(Integer idprod,String variable_sql){

        String sql = "select  " +
                "coocodforvar, " +
                "flg_agrupable , procodcon " +
                "from iexproxconcepto, iexconcepto " +
                "where " +
                "procodcon=coocodcon and " +
                "procodpro="+idprod+" and flg_agrupable ='1' and " +
                "trim(coocodforvar) in "+variable_sql;

        return template.query(sql, new ResultSetExtractor<List<ConceptoXProceso>>() {

            public List<ConceptoXProceso> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoXProceso> lista = new ArrayList<ConceptoXProceso>();

                while(rs.next()) {
                    ConceptoXProceso cp = new ConceptoXProceso();

                    cp.setConvar(rs.getString("coocodforvar"));
                    cp.setFlg_agrupable(rs.getString("flg_agrupable"));
                    cp.setProcodcon(rs.getString("procodcon"));

                    lista.add(cp);
                }
                return lista;
            }
        });
    }

    public Double realEjecucion(String v_script_dec, String v_script_ini , String v_script_body){
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

        vformula= v_script_dec+" "+v_script_ini+" "+v_script_body+" result2.setValue($resultado$); "+" result3.setValue($salto$); " ;

        engine.put("result2", result2);
        engine.put("result3", result3);
        //String script = "3 + 4; result.setValue(1);";

        try {
            engine.eval(vformula);
            //  resultado="1";
        } catch (ScriptException ex) {
            //Logger.getLogger(DAOFormulaPlanillaImpl.class.getName()).log(Level.SEVERE, null, ex);
//          resultado="0";
            log.info(ex.getMessage());
        }

        Double returnedValue = result2.getValue();   // Resultado final
        Double returnedValue2 = result3.getValue();  //
        //ebebeb       System.out.println("Formula : {  "+vformula+" } " );
        //ebebeb       System.out.println("Valor final= " + returnedValue+" , Salto="+returnedValue2 );

        return returnedValue;
    }
}

