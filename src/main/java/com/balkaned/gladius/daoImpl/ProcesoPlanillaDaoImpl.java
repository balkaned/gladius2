package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Slf4j
@Repository("ProcesoPlanillaDao")
public class ProcesoPlanillaDaoImpl implements ProcesoPlanillaDao {

    private static final String CLASS_NAME = "ProcesoPlanillaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<ProcesoPlanilla> listar(String text) {

        String sql = "select " +
                "procodpro as idProceso, " +
                "prodespro as desProceso, " +
                "prodescorto as desProcesoCorto, " +
                "procodregimenlab as idRegLab, " +
                "procodregimenlab as desRegLab, " +
                "progrppro as desGrp, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores " +
                "from iexprocesos p " +
                "where prodespro like :text " +
                "order by procodpro asc ";

        String finalText = "%" + text + "%";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text", finalText);

        List<ProcesoPlanilla> lsProcesoPlan = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPlanilla.class));

        return lsProcesoPlan;
    }

    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper) {

        String sql = "select e.iexcodcia, e.iexctbper_id, e.iexnroasiento, e.iexcodpro, " +
                "p.prodespro as desproceso, e.iexnroper, e.iexpermes, e.iextcmb, e.iexcodmon, d.desdet codmon_des, " +
                "e.iexcodmon_ext, d2.desdet codmon_ext_des, e.tot_cre_na, e.tot_deb_na, e.tot_cre_me, " +
                "e.tot_deb_me, e.iexglosacab " +
                "from iexctbpercab e " +
                "inner join iexprocesos p on e.iexcodpro = p.procodpro " +
                "left join iexttabled d on d.iexcodtab='52' and d.iexkey = e.iexcodmon " +
                "left join iexttabled d2 on d.iexcodtab='52' and d2.iexkey = e.iexcodmon_ext " +
                "where e.iexcodcia = :codcia and " +
                "e.iexcodpro = :codpro and " +
                "e.iexpermes = :nroper ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codpro", codpro)
                .addValue("nroper", nroper);

        List<AsientoContableCab> lsAsient = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(AsientoContableCab.class));

        return lsAsient;
    }

    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes) {

        String sql = "select " +
                "e.iexcodcia, " +
                "case " +
                "when substring(e.iexpermes,5,2) ='01' then 'Enero' " +
                "when substring(e.iexpermes,5,2) ='02' then 'Febrero' " +
                "when substring(e.iexpermes,5,2) ='03' then 'Marzo' " +
                "when substring(e.iexpermes,5,2) ='04' then 'Abril' " +
                "when substring(e.iexpermes,5,2) ='05' then 'Mayo' " +
                "when substring(e.iexpermes,5,2) ='06' then 'Junio' " +
                "when substring(e.iexpermes,5,2) ='07' then 'Julio' " +
                "when substring(e.iexpermes,5,2) ='08' then 'Agosto' " +
                "when substring(e.iexpermes,5,2) ='09' then 'Setiembre' " +
                "when substring(e.iexpermes,5,2) ='10' then 'Octubre' " +
                "when substring(e.iexpermes,5,2) ='11' then 'Noviembre' " +
                "when substring(e.iexpermes,5,2) ='12' then 'Diciembre' " +
                "else " +
                "'Sin mes' " +
                "end desmes, " +
                "substring(e.iexpermes,5,2) as permes, " +
                "p.procodregimenlab as codregimen, " +
                "e.iexpermes, " +
                "e.iexanio, " +
                "e.iexnroper, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "e.timerfecini, " +
                "e.timerfecfin, " +
                "e.iexfecpago, " +
                "e.flgestado, " +
                "p.progrppro as desgrppla, " +
                "p.procodpro iexcodpro, " +
                "p.prodespro as desproceso, " +
                "case " +
                "when flgestado ='1' then 'Creado' " +
                "when flgestado ='2' then 'Procesado' " +
                "when flgestado ='3' then 'Cerrado' " +
                "else " +
                "'sin estado' " +
                "end desestado " +
                "from iexprocesos p, iexproperiodo e " +
                "where p.procodpro = e.iexcodpro and " +
                "e.iexcodcia = :codcia and " +
                "iexpermes like :permes and " +
                "procodregimenlab = :regpla " +
                "order by e.iexpermes, p.progrppro, e.iexnroper asc ";

        //String finalPermes = "'%" + permes + "%'";
        String finalPermes = "'" + permes + "'";
        String finalRegpla = "'" + regpla + "'";

        log.info("codcia: {} ", codcia);
        log.info("permes: {} ", permes);
        log.info("finalPermes: {} ", finalPermes);
        log.info("regpla: {} ", regpla);
        log.info("finalRegpla: {} ", finalRegpla);

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("permes", permes)
                .addValue("regpla", regpla);

        List<ProcesoPeriodo> lsProc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPeriodo.class));

        log.info("lsProc: {} ", lsProc);
        //log.info("lsProc: {} ", lsProc.get(0).getDesgrppla());

        return lsProc;
    }

    public void insertarProper(ProcesoPeriodo pperiodo) {

        String sql = "insert into iexproperiodo(iexcodcia, iexcodpro, iexnroper, iexpermes, " +
                "iexfecini, iexfecfin, timerfecini, timerfecfin, iexfecpago, flgestado, iexfecope, " +
                "iexanio , iexfeccerti) values (?,?,?,?,to_date(?,'DD/MM/YYYY'), " +
                "to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), " +
                "to_date(?,'DD/MM/YYYY'), ?, current_date,?, to_date(?,'DD/MM/YYYY')) ";

        jdbc.update(sql,
                pperiodo.getIexcodcia(),
                pperiodo.getIexcodpro(),
                pperiodo.getIexnroper(),
                pperiodo.getIexpermes(),
                pperiodo.getIexfecini(),
                pperiodo.getIexfecfin(),
                pperiodo.getTimerfecini(),
                pperiodo.getTimerfecfin(),
                pperiodo.getIexfecpago(),
                "1",
                pperiodo.getIexanio(),
                pperiodo.getIexfeccerti()
        );
    }

    @Override
    public ProcesoPeriodo recuperarPeriodo2(Integer codcia, Integer idproceso, String periodo) {

        String sql = "select " +
                "e.iexcodcia, " +
                "e.iexcodpro, " +
                "p.prodespro as desproceso, " +
                "t.desdet as desregimen, " +
                "e.iexnroper, " +
                "e.iexpermes, " +
                "to_char(e.iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(e.iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "TO_CHAR(e.timerfecini,'DD/MM/YYYY') timerfecini, " +
                "TO_CHAR(e.timerfecfin,'DD/MM/YYYY') timerfecfin, " +
                "TO_CHAR(e.iexfecpago,'DD/MM/YYYY') iexfecpago, " +
                "to_char(e.timeini_iniciar,'yy-mm-dd hh24:mi:ss') timerfecini_iniciar, " +
                "to_char(e.timefin_iniciar,'yy-mm-dd hh24:mi:ss') timerfecfin_iniciar, " +
                "timenroimp as timerimp_iniciar, " +
                "to_char(e.timeini_proc,'yy-mm-dd hh24:mi:ss') timerfecini_proc, " +
                "to_char(e.timefin_proc,'yy-mm-dd hh24:mi:ss') timerfecfin_proc, " +
                "timenroimp_proc as timerimp_proc, " +
                "e.flgestado, " +
                "   case e.flgestado " +
                "   when  '1' Then 'Iniciado' " +
                "   when  '2' Then 'Procesado' " +
                "   when  '3' Then 'Cerrado' " +
                "   when  '0' Then 'Creado' " +
                "   end desestado, " +
                "e.iexfecope, e.iexanio, e.utiltotal, " +
                "p.progrppro as desgrppla, to_char(e.iexfeccerti,'DD/MM/YYYY') iexfeccerti, " +
                "p.procodregimenlab as codregimen, e.iextcmb as tcmb" +
                "from iexproperiodo e, iexprocesos p, (  " +
                " select  iexkey, desdet from  iexttabled where iexcodtab='33' ) t " +
                "where e.iexcodpro =  p.procodpro and " +
                "p.procodregimenlab = t.iexkey and " +
                "e.iexcodcia = :codcia and " +
                "e.iexcodpro = :idproceso and " +
                "e.iexnroper = :periodo ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idproceso", idproceso)
                .addValue("periodo", periodo);

        ProcesoPeriodo proc = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPeriodo.class));

        return proc;
    }

    public void actualizarProper(ProcesoPeriodo pperiodo) {

        String sql = "update iexproperiodo set iexpermes=?, iexfecini=TO_DATE(?,'DD/MM/YYYY'), " +
                "iexfecfin=TO_DATE(?,'DD/MM/YYYY'), timerfecini=TO_DATE(?,'DD/MM/YYYY'), " +
                "timerfecfin=TO_DATE(?,'DD/MM/YYYY'), iexfecpago=TO_DATE(?,'DD/MM/YYYY'), flgestado=?, " +
                "iexfecope=current_date, iexanio=?, iexfeccerti=TO_DATE(?,'DD/MM/YYYY') " +
                "where iexcodcia=? and " +
                "iexcodpro=? and " +
                "iexnroper=? ";

        jdbc.update(sql,
                pperiodo.getIexpermes(),
                pperiodo.getIexfecini(),
                pperiodo.getIexfecfin(),
                pperiodo.getTimerfecini(),
                pperiodo.getTimerfecfin(),
                pperiodo.getIexfecpago(),
                pperiodo.getFlgestado(),
                pperiodo.getIexanio(),
                pperiodo.getIexfeccerti(),
                pperiodo.getIexcodcia(),
                pperiodo.getIexcodpro(),
                pperiodo.getIexnroper()
        );
    }

    public ProcesoPlanillaxCia recuperar_reporte(Integer codcia, Integer codpro) {

        String sql = "select procodpro, " +
                "bolproceso, " +
                "bolproindividual, " +
                "bolproresumen, " +
                "rep_parameter, " +
                "rep_ingresos, " +
                "rep_descuentos, " +
                "rep_aportes " +
                "from iexprocesosxcia " +
                "where procodcia = :codcia and " +
                "procodpro = :codpro ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codpro", codpro);

        ProcesoPlanillaxCia proc = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPlanillaxCia.class));

        return proc;
    }

    public List<ConceptoxProcesoxTra> listarPlaNroper(Integer codcia, String perini, String perfin, String codcon) {

        String sql = "select d.iexcodcia, " +
                "d.iexcodpro as procodpro, " +
                "p.prodespro as despro, " +
                " d.iexnroper, " +
                "d.procodcon, " +
                "c.coodescon, " +
                "count(1) cantidad, " +
                "sum(provalor) provalo " +
                "from iexpropertra_nomina d, iexconcepto c, iexprocesos p " +
                "where d.procodcon = c.coocodcon and " +
                "d.iexcodcia = :codcia and " +
                "d.iexcodpro = p.procodpro and " +
                "d.procodcon in (:codcon) and " +
                "d.iexnroper >= :perini and " +
                "d.iexnroper <= :perfin and " +
                "provalor <> 0 " +
                "group by d.iexcodcia, d.iexcodpro, p.prodespro, d.iexnroper, d.procodcon, c.coodescon " +
                "order by d.iexcodcia, d.iexnroper, d.iexcodpro, d.procodcon, c.coodescon asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codcon", codcon)
                .addValue("perini", perini)
                .addValue("perfin", perfin);

        List<ConceptoxProcesoxTra> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoxProcesoxTra.class));

        return lsConcept;
    }
}
