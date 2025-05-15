package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.ProcesoFormulaDao;
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
@Repository("ProcesoFormulaDao")
public class ProcesoFormulaDaoImpl implements ProcesoFormulaDao {

    private static final String CLASS_NAME = "ProcesoFormulaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    @Override
    public List<ProcesoForm> listProcesoFormula() {

        String sql = "select " +
                "procodpro, " +
                "prodespro, " +
                "prodescorto, " +
                "procodregimenlab, " +
                "progrppro, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores, " +
                "diasteo " +
                "from iexprocesos " +
                "order by procodpro ASC ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<ProcesoForm> lsProc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoForm.class));

        return lsProc;
    }

    @Override
    public List<FormulaXConcepto> listFormulaXConcepto(String codpro) {

        String sql = "select " +
                "frm.procodpro as formprocodpro, " +
                "frm.forcodfor as formforcodfor, " +
                "frm.proglosa as formproglosa, " +
                "frm.fordesfor as formfordesfor, " +
                "frm.forcodcon as formforcodcon, " +
                "frm.forflgest as formforflgest, " +
                "frm.fororden as formfororden, " +
                "frm.fortipout as formfortipout, " +
                "frm.forvardes as formforvardes, " +
                "frm.forusucrea as formforusucrea, " +
                "frm.forfeccrea as formforfeccrea, " +
                "frm.forusumod as formforusumod, " +
                "frm.forfecmod as formforfecmod, " +
                "frm.forresult as formforresult, " +
                "frm.sqlprogram as formsqlprogram, " +
                "frm.grpeje as formgrpeje, " +
                "con.coocodcon as formcoocodcon, " +
                "con.coodescon as formcoodescon, " +
                "con.coocodforvar as formcoocodforvar, " +
                "con.coodesabrev as formcoodesabrev, " +
                "con.coodescripcion as formcoodescripcion " +
                "from iexformula_cab frm " +
                "inner join iexconcepto con on frm.forcodcon = con.coocodcon " +
                "where frm.procodpro = :codpro " +
                "order by frm.fororden asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codpro", Integer.parseInt(codpro));

        List<FormulaXConcepto> lsForm = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(FormulaXConcepto.class));

        return lsForm;
    }

    @Override
    public List<Proceso> listConcepto(String id) {

        String sql = "select " +
                "a.procodcon, " +
                "b.coodescon " +
                "from iexproxconcepto a " +
                "inner join iexconcepto b " +
                "on a.procodcon = b.coocodcon " +
                "where a.procodpro = 1 " +
                "and a.protipcon = :id ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        List<Proceso> lsProc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Proceso.class));

        return lsProc;
    }

    @Override
    public List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon) {

        String sql = "select " +
                "procodpro, " +
                "procodcon, " +
                "coodescon, " +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor as nro_asignacion, " +
                "protipcon, " +
                "prodescustom " +
                "from iexproxconcepto inner join iexconcepto on procodcon = coocodcon " +
                "where procodpro = :idproceso and protipcon = :tipcon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idproceso", idproceso)
                .addValue("tipcon", tipcon);

        List<ConceptoXProceso> lsConcept = namedParameterJdbcTemplate.query(sql,
                namedParameters, BeanPropertyRowMapper.newInstance(ConceptoXProceso.class));

        return lsConcept;
    }

    @Override
    public ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto) {

        String sql = "select " +
                "procodpro, " +
                "procodcon, " +
                "coodescon, " +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor as nro_asignacion, " +
                "protipcon, " +
                "prodescustom, " +
                "tip_ingreso, " +
                "flg_pry_5ta, " +
                "flg_des_5ta_mes, " +
                "flg_ess_reg, " +
                "flg_ess_pesq, " +
                "flg_ess_agrac, " +
                "flg_ess_sctr, " +
                "flg_extra_solid, " +
                "flg_fondo_art, " +
                "flg_apo_senati, " +
                "flg_onp, " +
                "flg_afp, " +
                "flg_fond_compl_jub, " +
                "flg_esp_pens_pesq, " +
                "flg_5ta, " +
                "flg_ess_seg_pen, " +
                "flg_cont_asis_previs, " +
                "flg_promediable, " +
                "flg_agrupable, " +
                "nro_meses_prom_atras " +
                "from iexproxconcepto, iexconcepto " +
                "where procodcon = coocodcon " +
                "and procodpro = :idproceso and " +
                "trim(procodcon) = trim(:idconcepto) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idproceso", idproceso)
                .addValue("idconcepto", idconcepto);

        ConceptoXProceso concept = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoXProceso.class));

        return concept;
    }

    public void insertar(ConceptoXProceso cproceso) {

        String sql = "insert into iexproxconcepto(procodpro, " +
                "procodcon, procodconpdt, proflgbol, proorden, " +
                "provalor,protipcon,prodescustom, " +
                "tip_ingreso, flg_pry_5ta, flg_des_5ta_mes, flg_ess_reg, flg_ess_pesq, flg_ess_agrac, " +
                "flg_ess_sctr, flg_extra_solid, flg_fondo_art, flg_apo_senati, flg_onp, flg_afp, " +
                "flg_fond_compl_jub, flg_esp_pens_pesq, flg_5ta, flg_ess_seg_pen, flg_cont_asis_previs, " +
                "flg_promediable, flg_agrupable, nro_meses_prom_atras " +
                " ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                cproceso.getProcodpro(),
                cproceso.getProcodcon(),
                cproceso.getProcodconpdt(),
                cproceso.getProflgbol(),
                cproceso.getProorden(),
                cproceso.getNro_asignacion(),
                cproceso.getProtipcon(),
                cproceso.getProdescustom(),
                cproceso.getTip_ingreso(),
                cproceso.getFlg_pry_5ta(),
                cproceso.getFlg_des_5ta_mes(),
                cproceso.getFlg_ess_reg(),
                cproceso.getFlg_ess_pesq(),
                cproceso.getFlg_ess_agrac(),
                cproceso.getFlg_ess_sctr(),
                cproceso.getFlg_extra_solid(),
                cproceso.getFlg_fondo_art(),
                cproceso.getFlg_apo_senati(),
                cproceso.getFlg_onp(),
                cproceso.getFlg_afp(),
                cproceso.getFlg_fond_compl_jub(),
                cproceso.getFlg_esp_pens_pesq(),
                cproceso.getFlg_5ta(),
                cproceso.getFlg_ess_seg_pen(),
                cproceso.getFlg_cont_asis_previs(),
                cproceso.getFlg_promediable(),
                cproceso.getFlg_agrupable(),
                cproceso.getNro_meses_prom_atras()
        );
    }

    public void actualizar(ConceptoXProceso cproceso) {

        String sql = "update iexproxconcepto set procodconpdt = ?, " +
                "proflgbol = ?, proorden = ?, " +
                "provalor = ?, protipcon = ?, " +
                "prodescustom = ? , " +
                "tip_ingreso = ?, " +
                "flg_pry_5ta = ?, " +
                "flg_des_5ta_mes = ?, " +
                "flg_ess_reg = ?, " +
                "flg_ess_pesq = ?, " +
                "flg_ess_agrac = ?, " +
                "flg_ess_sctr = ?, " +
                "flg_extra_solid = ?, " +
                "flg_fondo_art = ?, " +
                "flg_apo_senati  = ?, " +
                "flg_onp = ?, " +
                "flg_afp = ?, " +
                "flg_fond_compl_jub = ?, " +
                "flg_esp_pens_pesq = ?, " +
                "flg_5ta  = ?, " +
                "flg_ess_seg_pen = ?, " +
                "flg_cont_asis_previs= ?, " +
                "flg_promediable = ?, " +
                "flg_agrupable = ?, " +
                "nro_meses_prom_atras = ? " +
                "where procodpro = ? and " +
                "trim(procodcon) = trim(?) ";

        jdbc.update(sql,
                cproceso.getProcodconpdt(),
                cproceso.getProflgbol(),
                cproceso.getProorden(),
                cproceso.getNro_asignacion(),
                cproceso.getProtipcon(),
                cproceso.getProdescustom(),
                cproceso.getTip_ingreso(),
                cproceso.getFlg_pry_5ta(),
                cproceso.getFlg_des_5ta_mes(),
                cproceso.getFlg_ess_reg(),
                cproceso.getFlg_ess_pesq(),
                cproceso.getFlg_ess_agrac(),
                cproceso.getFlg_ess_sctr(),
                cproceso.getFlg_extra_solid(),
                cproceso.getFlg_fondo_art(),
                cproceso.getFlg_apo_senati(),
                cproceso.getFlg_onp(),
                cproceso.getFlg_afp(),
                cproceso.getFlg_fond_compl_jub(),
                cproceso.getFlg_esp_pens_pesq(),
                cproceso.getFlg_5ta(),
                cproceso.getFlg_ess_seg_pen(),
                cproceso.getFlg_cont_asis_previs(),
                cproceso.getFlg_promediable(),
                cproceso.getFlg_agrupable(),
                cproceso.getNro_meses_prom_atras(),
                cproceso.getProcodpro(),
                cproceso.getProcodcon()
        );
    }


    @Override
    public void insertarProcesoFormula(ProcesoForm proFo) {

        String sql = "call pl_gestion_procesos(?,?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                0,
                proFo.getProdespro(),
                proFo.getProdescorto(),
                proFo.getProcodregimenlab(),
                proFo.getProgrppro(),
                "1",
                proFo.getBolproceso(),
                proFo.getIdtipproceso(),
                proFo.getBolprocesoind(),
                proFo.getBolprocesores()
        );
    }

    @Override
    public void eliminarProcesoFormula(Integer id) {

        String sql = "call pl_gestion_procesos(?,'','','0','','3','','','','') ";

        jdbc.update(sql,
                id
        );
    }

    public ProcesoPlanilla recuperar(Integer id) {

        String sql = "select " +
                "procodpro as idProceso, " +
                "prodespro as desProceso, " +
                "prodescorto as desProcesoCorto, " +
                "procodregimenlab as idRegLab, " +
                "t.desdet as desRegLab, " +
                "progrppro as desGrp, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores " +
                "from iexprocesos p, " +
                "   (select  iexkey, desdet from  iexttabled where iexcodtab='33') t " +
                "where p.procodregimenlab = t.iexkey and " +
                "procodpro = :id " +
                "order by 1 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id", id);

        ProcesoPlanilla proc = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPlanilla.class));

        return proc;
    }

    public void actualizar(ProcesoPlanilla pplanilla) {

        String sql = "call pl_gestion_procesos(?,?,?,?,?,?,?,? ,?,?) ";

        jdbc.update(sql,
                pplanilla.getIdProceso(),
                pplanilla.getDesProceso(),
                pplanilla.getDesProcesoCorto(),
                pplanilla.getIdRegLab(),
                pplanilla.getDesGrp(),
                "2",
                pplanilla.getBolProceso(),
                pplanilla.getIdTipProceso(),
                pplanilla.getBolProcesoind(),
                pplanilla.getBolProcesores()
        );
    }
}
