package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.FormulaPlanillaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
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

@Slf4j
@Repository("FormulaPlanillaDao")
public class FormulaPlanillaDaoImpl implements FormulaPlanillaDao {

    private static final String CLASS_NAME = "FormulaPLanillaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<FormulaPlanilla> listar(String text) {

        String sql = "select " +
                "a.procodpro as idProceso, " +
                "a.forcodfor as idFormula, " +
                "a.proglosa as desGlosa, " +
                "a.fordesfor as desFormula, " +
                "a.forcodcon as idConcepto, " +
                "c.coodescon as desConcepto, " +
                "c.coocodforvar as cooforVar," +
                "a.FORFLGEST as flgEstado, " +
                "a.FORORDEN as nroOrden, " +
                "a.FORTIPOUT as tipOut, " +
                "a.FORVARDES as desVar, " +
                "a.FORUSUCREA, " +
                "a.FORFECCREA, " +
                "a.FORUSUMOD, " +
                "a.FORFECMOD, " +
                "a.sqlprogram, " +
                "a.grpeje " +
                "from iexformula_cab a " +
                "inner join iexconcepto c on a.forcodcon = c.coocodcon " +
                "where a.procodpro = :text " +
                "order by a.fororden asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text", Integer.parseInt(text));

        try {
            List<FormulaPlanilla> lsFormPlanilla = namedParameterJdbcTemplate.query(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(FormulaPlanilla.class));

            return lsFormPlanilla;
        } catch (NullPointerException ex) {
            log.info("No se encontraron resultados");
            return null;
        }
    }

    public String getListVars(Integer idprod, String script) {

        String string = script;
        String variable_inicio = "";
        Integer res = 0;

        String variable_sql = "";
        String variable_sql2 = "";
        Integer count = 0;

        Pattern pattern = Pattern.compile("(\\$)(.*?)(\\$)");
        Matcher matcher = pattern.matcher(string);
        //log.info(string);

        List<String> listMatches = new ArrayList<>();

        while (matcher.find()) {
            listMatches.add(matcher.group(2));
        }

        List<String> listVariable = new ArrayList<String>();
        res = 0;

        for (String s : listMatches) {
            if (variable_inicio.indexOf("$" + s + "$") == -1) {
                variable_inicio = variable_inicio + " var $" + s + "$=0;";
                listVariable.add("$" + s + "$");
                variable_sql = variable_sql + "'$" + s + "$',";
                count++;
            }
        }

        variable_sql = "( " + variable_sql + "'')";
        /* Hacer la consulta en base de datos y obtener la lista de conceptos con su atributo de que si es
           grupo o no es grupo de concepto.
           Ordena las variables y las coloca en un String
           Consultar de base de datos */

        variable_sql2 = obtenerVariableSql2(idprod, variable_sql);
        variable_sql2 = "( " + variable_sql2 + "'')";

        return variable_sql2;
    }

    public String obtenerVariableSql2(Integer idprod, String variable_sql) {

        final String[] variable_sql2 = {""};

        String sql = "select " +
                "coocodforvar, " +
                "flg_agrupable " +
                "from iexproxconcepto, iexconcepto " +
                "where procodcon=coocodcon and " +
                "procodpro = " + idprod + " and " +
                "trim(coocodforvar) in " + variable_sql;

        return (String) jdbc.query(sql, new ResultSetExtractor<String>() {
            public String extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    variable_sql2[0] = variable_sql2[0] + "'" + rs.getString("coocodforvar") + "',";
                }
                return variable_sql2[0];
            }
        });
    }

    public List<ConceptoXProceso> obtenerListVariables_glb(Integer idprod, String script) {

        String string = script;
        String variable_inicio = "";
        Integer res = 0;

        String variable_sql = "";
        Integer count = 0;

        Pattern pattern = Pattern.compile("(\\$)(.*?)(\\$)");
        Matcher matcher = pattern.matcher(string);

        List<String> listMatches = new ArrayList<>();

        while (matcher.find()) {
            listMatches.add(matcher.group(2));
        }

        List<String> listVariable = new ArrayList<>();
        res = 0;

        for (String s : listMatches) {
            if (variable_inicio.indexOf("$" + s + "$") == -1) {
                variable_inicio = variable_inicio + " var $" + s + "$=0;";
                listVariable.add("$" + s + "$");
                variable_sql = variable_sql + "'$" + s + "$',";
                count++;
            }
        }

        variable_sql = "( " + variable_sql + "'')";

        //log.info("variable_sql: {} ", variable_sql);

        List<ConceptoXProceso> listVariable2 = obtenerListVariablesConc(idprod, variable_sql);

        return listVariable2;
    }

    public List<ConceptoXProceso> obtenerListVariablesConc(Integer idprod, String variable_sql) {

        String sql = "select " +
                "coocodforvar, " +
                "flg_agrupable, " +
                "procodcon " +
                "from iexproxconcepto, iexconcepto " +
                "where procodcon = coocodcon and " +
                "procodpro = " + idprod + " and " +
                "flg_agrupable = '1' and " +
                "trim(coocodforvar) in " + variable_sql + " ";

        return jdbc.query(sql, new ResultSetExtractor<List<ConceptoXProceso>>() {

            public List<ConceptoXProceso> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoXProceso> lista = new ArrayList<ConceptoXProceso>();

                while (rs.next()) {
                    ConceptoXProceso p = new ConceptoXProceso();

                    p.setCoocodforvar(rs.getString("coocodforvar"));
                    p.setFlg_agrupable(rs.getString("flg_agrupable"));
                    p.setProcodcon(rs.getString("procodcon"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Double realEjecucion(String v_script_dec, String v_script_ini, String v_script_body) {

        /* Si los proceso previos se han desarrolldo correctamente
           Se procede a Ejecutar el sscript completo para ver si la fórmula es correcta.
           Si es correcta la fórmula retornará la fórmula concatenada. */

        String target;
        String v_dias;

        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("JavaScript");

        Object result = null;
        String vformula;
        String resultado = "";

        Main result2 = new Main();
        Main result3 = new Main();

        vformula = v_script_dec + " " + v_script_ini + " " + v_script_body + " result2.setValue($resultado$); "
                + " result3.setValue($salto$); ";
        //log.info("vformula: {} ", vformula);

        engine.put("result2", result2);
        engine.put("result3", result3);

        try {
            engine.eval(vformula);
        } catch (ScriptException ex) {
            log.info(ex.getMessage());
        }

        Double returnedValue = result2.getValue();
        Double returnedValue2 = result3.getValue();

        return returnedValue;
    }
}

