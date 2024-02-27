package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.ProcesoFormulaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("ProFoDao")
@Slf4j
public class ProcesoFormulaDaoImpl implements ProcesoFormulaDao {
    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public List<ProcesoForm> listProcesoFormula() {
        String sqlQuery = "SELECT " +
                "	procodpro, " +
                "	prodespro, " +
                "	prodescorto, " +
                "	procodregimenlab, " +
                "	progrppro, " +
                "	bolproceso, " +
                "	idtipproceso, " +
                "	bolprocesoind, " +
                "	bolprocesores, " +
                "	diasteo " +
                "FROM iexprocesos " +
                "ORDER BY procodpro ASC";

        return template.query(sqlQuery, rs -> {
            List<ProcesoForm> list = new ArrayList<>();

            while (rs.next()) {
                ProcesoForm proFo = new ProcesoForm();
                proFo.setProcodpro(rs.getInt("procodpro"));
                proFo.setProdespro(rs.getString("prodespro"));
                proFo.setProdescorto(rs.getString("prodescorto"));
                proFo.setProcodregimenlab(rs.getString("procodregimenlab"));
                proFo.setProgrppro(rs.getString("progrppro"));
                proFo.setBolproceso(rs.getString("bolproceso"));
                proFo.setIdtipproceso(rs.getString("idtipproceso"));
                proFo.setBolprocesoind(rs.getString("bolprocesoind"));
                proFo.setBolprocesores(rs.getString("bolprocesores"));
                proFo.setDiasteo(rs.getInt("diasteo"));

                list.add(proFo);
            }

            return list;
        });
    }

    @Override
    public List<FormulaXConcepto> listFormulaXConcepto() {
        String sqlQuery = "select " +
                "frm.procodpro, " +
                "frm.forcodfor, " +
                "frm.proglosa, " +
                "frm.fordesfor, " +
                "frm.forcodcon, " +
                "frm.forflgest, " +
                "frm.fororden, " +
                "frm.fortipout, " +
                "frm.forvardes, " +
                "frm.forusucrea, " +
                "frm.forfeccrea, " +
                "frm.forusumod, " +
                "frm.forfecmod, " +
                "frm.forresult, " +
                "frm.sqlprogram, " +
                "frm.grpeje, " +
                "con.coocodcon, " +
                "con.coodescon, " +
                "con.coocodforvar, " +
                "con.coodesabrev, " +
                "con.coodescripcion " +
                "from iexformula_cab frm " +
                "inner join iexconcepto con " +
                "on frm.forcodcon = con.coocodcon " +
                "where frm.procodpro = 1 " +
                "order by frm.fororden asc";

        return template.query(sqlQuery, rs -> {
            List<FormulaXConcepto> list = new ArrayList<>();

            while (rs.next()) {
                FormulaXConcepto proFo = new FormulaXConcepto();
                proFo.setFormprocodpro(rs.getString("procodpro"));
                proFo.setFormforcodfor(rs.getString("forcodfor"));
                proFo.setFormproglosa(rs.getString("proglosa"));
                proFo.setFormfordesfor(rs.getString("fordesfor"));
                proFo.setFormforcodcon(rs.getString("forcodcon"));
                proFo.setFormforflgest(rs.getString("forflgest"));
                proFo.setFormfororden(rs.getString("fororden"));
                proFo.setFormfortipout(rs.getString("fortipout"));
                proFo.setFormforvardes(rs.getString("forvardes"));
                proFo.setFormforusucrea(rs.getString("forusucrea"));
                proFo.setFormforfeccrea(rs.getString("forfeccrea"));
                proFo.setFormforusumod(rs.getString("forusumod"));
                proFo.setFormforfecmod(rs.getString("forfecmod"));
                proFo.setFormforresult(rs.getString("forresult"));
                proFo.setFormsqlprogram(rs.getString("sqlprogram"));
                proFo.setFormgrpeje(rs.getString("grpeje"));
                proFo.setConccoocodcon(rs.getString("coocodcon"));
                proFo.setConccoodescon(rs.getString("coodescon"));
                proFo.setConccoocodforvar(rs.getString("coocodforvar"));
                proFo.setConccoodesabrev(rs.getString("coodesabrev"));
                proFo.setConccoodescripcion(rs.getString("coodescripcion"));

                list.add(proFo);
            }

            return list;
        });
    }

    @Override
    public List<Proceso> listConcepto(String id) {
        String sqlQuery = "select a.procodcon, " +
                "b.coodescon " +
                "from iexproxconcepto a " +
                "inner join iexconcepto b " +
                "on a.procodcon = b.coocodcon " +
                "where a.procodpro = 1 " +
                "and a.protipcon = '" + id + "'";

        return template.query(sqlQuery, rs -> {
            List<Proceso> list = new ArrayList<>();

            while (rs.next()) {
                Proceso proceso = new Proceso();
                proceso.setProcodcon(rs.getString("procodcon"));
                proceso.setCoodescon(rs.getString("coodescon"));
                list.add(proceso);
            }

            return list;
        });
    }

    @Override
    public List<ConceptoXProceso> listConceptoXProceso(Integer idproceso, String tipcon) {
        String sqlQuery = "select " +
                "procodpro, " +
                "procodcon, " +
                "coodescon, " +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor, " +
                "protipcon, " +
                "prodescustom " +
                "from iexproxconcepto inner join iexconcepto on procodcon = coocodcon"
                + "  where procodpro=" + idproceso + "  and  protipcon='" + tipcon + "'";

        return template.query(sqlQuery, rs -> {
            List<ConceptoXProceso> list = new ArrayList<>();

            while (rs.next()) {
                ConceptoXProceso p = new ConceptoXProceso();
                p.setProcodpro(rs.getInt("procodpro"));
                p.setProcodcon(rs.getString("procodcon"));
                p.setCoodescon(rs.getString("coodescon"));
                p.setProcodconpdt(rs.getString("procodconpdt"));
                p.setProflgbol(rs.getString("proflgbol"));
                p.setProorden(rs.getInt("proorden"));
                p.setProvalor(rs.getDouble("provalor"));
                p.setProtipcon(rs.getString("protipcon"));
                p.setProdescustom(rs.getString("prodescustom"));
                list.add(p);
            }

            return list;
        });
    }

    @Override
    public ConceptoXProceso getConceptoXProceso(Integer idproceso, String idconcepto) {
        String sqlQuery = "select " +
                "procodpro, " +
                "procodcon,  coodescon ," +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor, " +
                "protipcon, " +
                "prodescustom, " +
                "tip_ingreso ,  " +
                "flg_pry_5ta,  " +
                "flg_des_5ta_mes,  " +
                "flg_ess_reg ,  " +
                "flg_ess_pesq ,  " +
                "flg_ess_agrac ,  " +
                "flg_ess_sctr ,  " +
                "flg_extra_solid,  " +
                "flg_fondo_art ,  " +
                "flg_apo_senati ,  " +
                "flg_onp ,  " +
                "flg_afp ,  " +
                "flg_fond_compl_jub ,  " +
                "flg_esp_pens_pesq ,  " +
                "flg_5ta   ,  " +
                "flg_ess_seg_pen ,  " +
                "flg_cont_asis_previs ,  " +
                " flg_promediable ," +
                " flg_agrupable ," +
                " nro_meses_prom_atras " +
                "from iexproxconcepto ,  iexconcepto where  procodcon =coocodcon " +
                "and procodpro=" + idproceso + "  and  trim(procodcon) = trim('" + idconcepto + "') ";

        return template.query(sqlQuery, rs -> {
            ConceptoXProceso p = new ConceptoXProceso();
            while (rs.next()) {
                p.setProcodpro(rs.getInt("procodpro"));
                p.setProcodcon(rs.getString("procodcon"));
                p.setCoodescon(rs.getString("coodescon"));
                p.setProcodconpdt(rs.getString("procodconpdt"));
                p.setProflgbol(rs.getString("proflgbol"));
                p.setProorden(rs.getInt("proorden"));
                p.setProvalor(rs.getDouble("provalor"));
                p.setProtipcon(rs.getString("protipcon"));
                p.setProdescustom(rs.getString("prodescustom"));
                p.setTip_ingreso(rs.getString("tip_ingreso"));
                p.setFlg_pry_5ta(rs.getString("flg_pry_5ta"));
                p.setFlg_des_5ta_mes(rs.getString("flg_des_5ta_mes"));
                p.setFlg_ess_reg(rs.getString("flg_ess_reg"));
                p.setFlg_ess_pesq(rs.getString("flg_ess_pesq"));
                p.setFlg_ess_agrac(rs.getString("flg_ess_agrac"));
                p.setFlg_ess_sctr(rs.getString("flg_ess_sctr"));
                p.setFlg_extra_solid(rs.getString("flg_extra_solid"));
                p.setFlg_fondo_art(rs.getString("flg_fondo_art"));
                p.setFlg_apo_senati(rs.getString("flg_apo_senati"));
                p.setFlg_onp(rs.getString("flg_onp"));
                p.setFlg_afp(rs.getString("flg_afp"));
                p.setFlg_fond_compl_jub(rs.getString("flg_fond_compl_jub"));
                p.setFlg_esp_pens_pesq(rs.getString("flg_esp_pens_pesq"));
                p.setFlg_5ta(rs.getString("flg_5ta"));
                p.setFlg_ess_seg_pen(rs.getString("flg_ess_seg_pen"));
                p.setFlg_cont_asis_previs(rs.getString("flg_cont_asis_previs"));
                p.setFlg_promediable(rs.getString("flg_promediable"));
                p.setFlg_agrupable(rs.getString("flg_agrupable"));
                p.setNro_meses_atras(rs.getInt("nro_meses_prom_atras"));
            }

            return p;
        });
    }

    @Override
    public void insertarConceptoXProceso(ConceptoXProceso cxp) {

        String sqlQuery = "  insert into iexproxconcepto(procodpro,procodcon,procodconpdt,proflgbol,proorden,provalor,protipcon,prodescustom, " +
                " tip_ingreso , flg_pry_5ta ,  flg_des_5ta_mes ,   flg_ess_reg , flg_ess_pesq ,  flg_ess_agrac,  flg_ess_sctr, flg_extra_solid , flg_fondo_art ,  flg_apo_senati , flg_onp, flg_afp,  flg_fond_compl_jub,   flg_esp_pens_pesq , flg_5ta ,  flg_ess_seg_pen , flg_cont_asis_previs , flg_promediable, flg_agrupable, nro_meses_prom_atras " +
                " ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        try {
            template.update(sqlQuery, cxp.getProcodpro(), cxp.getProcodcon(), cxp.getProcodconpdt(), cxp.getProflgbol(),
                    cxp.getProorden(), cxp.getProvalor()
                    , cxp.getProtipcon(), cxp.getProdescustom(), cxp.getTip_ingreso(), cxp.getFlg_pry_5ta(),
                    cxp.getFlg_des_5ta_mes(), cxp.getFlg_ess_reg(), cxp.getFlg_ess_pesq(), cxp.getFlg_ess_agrac(),
                    cxp.getFlg_ess_sctr(), cxp.getFlg_extra_solid(), cxp.getFlg_fondo_art(), cxp.getFlg_apo_senati(),
                    cxp.getFlg_onp(), cxp.getFlg_afp(), cxp.getFlg_fond_compl_jub(), cxp.getFlg_esp_pens_pesq(),
                    cxp.getFlg_5ta(), cxp.getFlg_ess_seg_pen(), cxp.getFlg_cont_asis_previs(), cxp.getFlg_promediable(),
                    cxp.getFlg_agrupable(), cxp.getNro_meses_atras());
        } catch (DataAccessException e) {
            log.error("Error al insertar proceso: " + e.getMessage());
        }
    }

    @Override
    public void editarConceptoXProceso(ConceptoXProceso cxp) {

        String sqlQuery = "  update  iexproxconcepto set procodconpdt = ?, proflgbol = ?, proorden = ?, provalor = ?, protipcon = ?, prodescustom = ? , "
                + "tip_ingreso = ?, " +
                "flg_pry_5ta = ?, " +
                " flg_des_5ta_mes = ?, " +
                " flg_ess_reg = ?, " +
                " flg_ess_pesq = ?, " +
                " flg_ess_agrac = ?, " +
                " flg_ess_sctr = ?, " +
                " flg_extra_solid = ?, " +
                " flg_fondo_art = ?, " +
                " flg_apo_senati  = ?, " +
                "  flg_onp = ?, " +
                " flg_afp = ?, " +
                " flg_fond_compl_jub = ?, " +
                " flg_esp_pens_pesq = ?, " +
                " flg_5ta  = ? , " +
                " flg_ess_seg_pen = ?, " +
                " flg_cont_asis_previs= ? ," +
                " flg_promediable = ? ," +
                " flg_agrupable = ? ," +
                " nro_meses_prom_atras = ? " +
                "where procodpro = ? and trim(procodcon)=trim(?) ";

        try {
            template.update(sqlQuery, cxp.getProcodconpdt(), cxp.getProflgbol(), cxp.getProorden(), cxp.getProvalor()
                    , cxp.getProtipcon(), cxp.getProdescustom(), cxp.getTip_ingreso(), cxp.getFlg_pry_5ta(),
                    cxp.getFlg_des_5ta_mes(), cxp.getFlg_ess_reg(), cxp.getFlg_ess_pesq(), cxp.getFlg_ess_agrac(),
                    cxp.getFlg_ess_sctr(), cxp.getFlg_extra_solid(), cxp.getFlg_fondo_art(), cxp.getFlg_apo_senati(),
                    cxp.getFlg_onp(), cxp.getFlg_afp(), cxp.getFlg_fond_compl_jub(), cxp.getFlg_esp_pens_pesq(),
                    cxp.getFlg_5ta(), cxp.getFlg_ess_seg_pen(), cxp.getFlg_cont_asis_previs(), cxp.getFlg_promediable(),
                    cxp.getFlg_agrupable(), cxp.getNro_meses_atras(), cxp.getProcodpro(), cxp.getProcodcon());
        } catch (DataAccessException e) {
            log.error("Error al editar proceso: " + e.getMessage());
        }
    }

    @Override
    public void insertarProcesoFormula(ProcesoForm proFo) {

        String sqlQuery = "call pl_gestion_procesos(?,?,?,?,?,?,?,?,?,?)";

        try {
            template.update(
                    sqlQuery,
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
        } catch (DataAccessException e) {
            log.error("Error al insertar proceso: " + e.getMessage());
        }
    }

    @Override
    public void eliminarProcesoFormula(Integer id) {

        String sqlQuery = "call pl_gestion_procesos(?,'','','0','','3','','','','')";

        try {
            template.update(
                    sqlQuery,
                    id
            );
        } catch (DataAccessException e) {
            log.error("Error al eliminar proceso: " + e.getMessage());
        }
    }

    public ProcesoPlanilla recuperar(Integer id) {

        String sql = "select " +
                "procodpro, " +
                "prodespro, " +
                "prodescorto, " +
                "procodregimenlab, " +
                " procodregimenlab desregimen, " +
                "progrppro, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores " +
                "from iexprocesos p  " +
                "where " +
                " procodpro=" + id + " order by 1 asc ";

        return (ProcesoPlanilla) template.query(sql, new ResultSetExtractor<ProcesoPlanilla>() {
            public ProcesoPlanilla extractData(ResultSet rs) throws SQLException, DataAccessException {
                ProcesoPlanilla p = new ProcesoPlanilla();
                while (rs.next()) {
                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setDesProceso(rs.getString("prodespro"));
                    p.setDesProcesoCorto(rs.getString("prodescorto"));
                    p.setIdRegLab(rs.getString("procodregimenlab"));
                    p.setDesRegLab(rs.getString("desregimen"));
                    p.setDesGrp(rs.getString("progrppro"));
                    p.setBolProceso(rs.getString("bolproceso"));
                    p.setIdTipProceso(rs.getString("idtipproceso"));
                    p.setBolProcesoind(rs.getString("bolprocesoind"));
                    p.setBolProcesores(rs.getString("bolprocesores"));
                }
                return p;
            }
        });
    }

    public void actualizar(ProcesoPlanilla pplanilla) {

        template.update("call pl_gestion_procesos(?,?,?,?,?,?,?,? ,?,?) ",

                pplanilla.getIdProceso(),
                pplanilla.getDesProceso(),
                pplanilla.getDesProcesoCorto(),
                pplanilla.getIdRegLab(),
                pplanilla.getDesGrp(),
                "2",
                pplanilla.getBolProceso(),
                pplanilla.getIdTipProceso(),
                pplanilla.getBolProcesoind(),
                pplanilla.getBolProcesores());
    }
}
