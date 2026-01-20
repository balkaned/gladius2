package com.balkaned.gladius.conceptoXProceso.Infrastructure;

import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoXProceso;
import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoxAgrup;
import com.balkaned.gladius.conceptoXProceso.Domain.ConceptoxProms;
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
@Repository("ConceptoXProcesoDao")
public class ConceptoXProcesoDaoImpl implements ConceptoXProcesoDao {

    private static final String CLASS_NAME = "ConceptoXProcesoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    @Override
    public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {

        String sql = "select " +
                "co.procodpro, " +
                "co.procodcon, " +
                "cn.coodescon, " +
                "count(b.iexcodcon) nro_asignacion " +
                "from iexproxconcepto co " +
                "left join iexctbconf b on b.iexcodcia = :xcodcia and " +
                "	b.iexcodpro = :idProceso and " +
                "	b.iexcodpro = co.procodpro  and " +
                "	b.iexcodcon = co.procodcon " +
                "left join iexconcepto cn on co.procodcon = cn.coocodcon " +
                "where co.procodpro = :idProceso and co.protipcon = :slc_grpconcepto " +
                "group by co.procodpro, co.procodcon, cn.coodescon ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("xcodcia", xcodcia)
                .addValue("idProceso", idProceso)
                .addValue("slc_grpconcepto", slc_grpconcepto);

        List<ConceptoXProceso> lsConxPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoXProceso.class));

        return lsConxPro;
    }

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto) {

        String sql = "select " +
                "procodpro, " +
                "procodcon, " +
                "coodescon," +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor nro_asignacion, " +
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
                "where procodcon = coocodcon and " +
                "procodpro = :idProceso and " +
                "trim(procodcon) = trim(:idconcepto) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idProceso", idproceso)
                .addValue("idconcepto", idconcepto);

        ConceptoXProceso conceptoXProceso = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoXProceso.class));

        return conceptoXProceso;
    }

    public void eliminar(Integer idproceso, String idconcepto) {

        String sql = "delete from iexproxconcepto " +
                "where procodpro =? and procodcon=? ";

        jdbc.update(sql, idproceso, idconcepto);
    }

    public List<ConceptoxProms> listarPromCon(Integer idproceso, String idconcepto) {

        String sql = "select " +
                "idcodpro idproceso, " +
                "idcodcon codconcepto, " +
                "idcodproaux idprocesoaux, " +
                "prodespro desprocesoaux, " +
                "idcodconaux codconceptaux, " +
                "coodescon desconceptaux " +
                "from iexproxcon_prom " +
                "inner join iexprocesos on idcodproaux = procodpro " +
                "inner join iexconcepto on coocodcon = idcodconaux " +
                "where idcodpro = :idproceso and idcodcon = trim(:idconcepto) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idproceso", idproceso)
                .addValue("idconcepto", idconcepto);

        List<ConceptoxProms> lsPromCon = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoxProms.class));

        return lsPromCon;
    }

    public List<ConceptoXProceso> listar(Integer idproceso, String text) {

        String sql = "select " +
                "procodpro, " +
                "procodcon, " +
                "coodescon, " +
                "procodconpdt, " +
                "proflgbol, " +
                "proorden, " +
                "provalor nro_asignacion, " +
                "protipcon, " +
                "prodescustom " +
                "from iexproxconcepto inner join iexconcepto on procodcon = coocodcon " +
                "where procodpro = :idproceso ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idproceso", idproceso);

        List<ConceptoXProceso> lsConxPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoXProceso.class));

        return lsConxPro;
    }

    public void insertarProm(ConceptoxProms conxproms) {

        String sql = "insert into iexproxcon_prom (idcodpro, idcodcon, idcodproaux, idcodconaux) " +
                "values (?,?,?,?) ";

        jdbc.update(sql,
                conxproms.getIdproceso(),
                conxproms.getCodconcepto(),
                conxproms.getIdprocesoaux(),
                conxproms.getCodconceptaux()
        );
    }

    public void eliminaProm(ConceptoxProms conxproms) {

        String sql = "delete from iexproxcon_prom " +
                "where idcodpro=? and trim(idcodcon)=trim(?) and idcodproaux=? " +
                "and trim(idcodconaux) = trim(?) ";

        jdbc.update(sql,
                conxproms.getIdproceso(),
                conxproms.getCodconcepto(),
                conxproms.getIdprocesoaux(),
                conxproms.getCodconceptaux()
        );
    }

    public List<ConceptoxAgrup> listarAgrupCon(Integer idproceso, String idconcepto) {

        String sql = "select " +
                "grpidpro idproceso, " +
                "grpidcon codconcepto, " +
                "grpidconaux codconceptaux, " +
                "coodescon desconceptaux " +
                "from iexproxcon_agrup " +
                "inner join iexconcepto on coocodcon = grpidconaux " +
                "where grpidpro = :idproceso and " +
                "grpidcon = trim(:idconcepto) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idproceso", idproceso)
                .addValue("idconcepto", idconcepto);

        List<ConceptoxAgrup> lsAgrupCon = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ConceptoxAgrup.class));

        return lsAgrupCon;
    }

    public void insertarAgrup(ConceptoxAgrup conxagrup) {

        String sql = "insert into iexproxcon_agrup (grpidpro, grpidcon, grpidconaux) " +
                "values (?,?,?) ";

        jdbc.update(sql,
                conxagrup.getIdproceso(),
                conxagrup.getCodconcepto(),
                conxagrup.getCodconceptaux()
        );
    }

    public void eliminaAgrup(ConceptoxAgrup conxagrup) {

        String sql = "delete from iexproxcon_agrup " +
                "where grpidpro=? and " +
                "trim(grpidcon)=trim(?) and " +
                "trim(grpidconaux) = trim(?) ";

        jdbc.update(sql,
                conxagrup.getIdproceso(),
                conxagrup.getCodconcepto(),
                conxagrup.getCodconceptaux()
        );
    }
}
