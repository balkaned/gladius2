package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.beans.ConceptoxAgrup;
import com.balkaned.gladius.beans.ConceptoxProms;
import com.balkaned.gladius.dao.ConceptoXProcesoDao;
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
import java.util.logging.Logger;

@Repository("ConceptoXProcesoDao")
@Slf4j
public class ConceptoXProcesoDaoImpl implements ConceptoXProcesoDao {
    static Logger logger = Logger.getLogger(ConceptoXProcesoDaoImpl.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {
        String sqlQuery = "select    " +
                " co.procodpro, " +
                " co.procodcon, " +
                " cn.coodescon, " +
                " count(b.iexcodcon)  nro_asignacion " +
                " from  " +
                " iexproxconcepto co " +
                " left join iexctbconf b " +
                "     on  b.iexcodcia = " + xcodcia + " and  " +
                "	     b.iexcodpro = " + idProceso + " and  " +
                "	     b.iexcodpro = co.procodpro  and  " +
                "		 b.iexcodcon = co.procodcon  " +
                " left join  iexconcepto cn on  " +
                "      co.procodcon = cn.coocodcon " +
                "  where co.procodpro = " + idProceso + "  and co.protipcon = '" + slc_grpconcepto + "' " +
                "  group by  " +
                "  co.procodpro,  " +
                " co.procodcon, " +
                "  cn.coodescon    ";
        try {
            return template.query(sqlQuery, rs -> {
                List<ConceptoXProceso> list = new java.util.ArrayList<>();
                while (rs.next()) {
                    ConceptoXProceso p = new ConceptoXProceso();
                    p.setProcodpro(rs.getInt("procodpro"));
                    p.setProcodcon(rs.getString("procodcon"));
                    p.setCoodescon(rs.getString("coodescon"));
                    p.setProvalor(rs.getDouble("nro_asignacion"));
                    list.add(p);
                }
                return list;
            });
        } catch (Exception e) {
            logger.info("Error en listarTipconCtb: " + e.getMessage());
            return null;
        }
    }

    public ConceptoXProceso recuperar(Integer idproceso, String idconcepto) {

        String sql = "select " +
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
                "from iexproxconcepto ,  iexconcepto where  procodcon =coocodcon and procodpro=" + idproceso + "  and  trim(procodcon) = trim('" + idconcepto + "') ";

        return (ConceptoXProceso) template.query(sql, new ResultSetExtractor<ConceptoXProceso>() {
            public ConceptoXProceso extractData(ResultSet rs) throws SQLException, DataAccessException {
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
            }
        });
    }

    public void eliminar(Integer idproceso, String idconcepto) {

        template.update(" delete from iexproxconcepto where procodpro =? and procodcon=? ",

                idproceso,
                idconcepto);
    }

    public List<ConceptoxProms> listarPromCon(Integer idproceso, String idconcepto) {

        String sql = " select " +
                " idcodpro, " +
                " idcodcon, " +
                " idcodproaux," +
                " prodespro," +
                " idcodconaux," +
                " coodescon " +
                " from iexproxcon_prom " +
                " inner join iexprocesos on idcodproaux = procodpro " +
                " inner join iexconcepto  on coocodcon = idcodconaux  " +
                " where idcodpro=" + idproceso + " and idcodcon= trim('" + idconcepto + "') ";

        return template.query(sql, new ResultSetExtractor<List<ConceptoxProms>>() {

            public List<ConceptoxProms> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoxProms> lista = new ArrayList<ConceptoxProms>();

                while (rs.next()) {
                    ConceptoxProms p = new ConceptoxProms();

                    p.setIdproceso(rs.getInt("idcodpro"));
                    p.setCodconcepto(rs.getString("idcodcon"));
                    p.setIdprocesoaux(rs.getInt("idcodproaux"));
                    p.setCodconceptaux(rs.getString("idcodconaux"));
                    p.setDesprocesoaux(rs.getString("prodespro"));
                    p.setDesconceptaux(rs.getString("coodescon"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ConceptoXProceso> listar(Integer idproceso, String text) {

        String sql = "select " +
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
                + "  where procodpro=" + idproceso + "  ";

        return template.query(sql, new ResultSetExtractor<List<ConceptoXProceso>>() {

            public List<ConceptoXProceso> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoXProceso> lista = new ArrayList<ConceptoXProceso>();

                while (rs.next()) {
                    ConceptoXProceso p = new ConceptoXProceso();

                    p.setProcodpro(rs.getInt("procodpro"));
                    p.setProcodcon(rs.getString("procodcon"));
                    p.setCoodescon(rs.getString("coodescon"));
                    p.setProcodconpdt(rs.getString("procodconpdt"));
                    p.setProflgbol(rs.getString("proflgbol"));
                    p.setProorden(rs.getInt("proorden"));
                    p.setProvalor(rs.getDouble("provalor"));
                    p.setProdescustom(rs.getString("prodescustom"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarProm(ConceptoxProms conxproms) {

        template.update("  insert into iexproxcon_prom( idcodpro, idcodcon, idcodproaux, idcodconaux ) values (?,?,?,?) ",

                conxproms.getIdproceso(),
                conxproms.getCodconcepto(),
                conxproms.getIdprocesoaux(),
                conxproms.getCodconceptaux());
    }

    public void eliminaProm(ConceptoxProms conxproms){

        template.update("  delete from  iexproxcon_prom where  idcodpro=? and trim(idcodcon)=trim(?)  and idcodproaux=? and trim(idcodconaux) =trim(?)  ",

        conxproms.getIdproceso(),
        conxproms.getCodconcepto(),
        conxproms.getIdprocesoaux(),
        conxproms.getCodconceptaux());
    }

    public List<ConceptoxAgrup> listarAgrupCon(Integer idproceso, String idconcepto){

        String sql = "select "+
                "grpidpro, " +
                "grpidcon, " +
                "grpidconaux," +
                "coodescon " +
                " from iexproxcon_agrup " +
                "inner join iexconcepto  on coocodcon = grpidconaux  " +
                "where grpidpro="+idproceso+" and grpidcon= trim('"+idconcepto+"')  ";

        return template.query(sql, new ResultSetExtractor<List<ConceptoxAgrup>>() {

            public List<ConceptoxAgrup> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoxAgrup> lista = new ArrayList<ConceptoxAgrup>();

                while(rs.next()) {
                    ConceptoxAgrup p = new ConceptoxAgrup();

                    p.setIdproceso(rs.getInt("grpidpro"));
                    p.setCodconcepto(rs.getString("grpidcon"));
                    p.setCodconceptaux(rs.getString("grpidconaux"));
                    p.setDesconceptaux(rs.getString("coodescon"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarAgrup(ConceptoxAgrup conxagrup){

        template.update("  insert into iexproxcon_agrup( grpidpro, grpidcon , grpidconaux ) values (?,?,?) ",

        conxagrup.getIdproceso(),
        conxagrup.getCodconcepto(),
        conxagrup.getCodconceptaux());
    }

    public void eliminaAgrup(ConceptoxAgrup conxagrup){

        template.update(" delete from  iexproxcon_agrup where  grpidpro=? and trim(grpidcon)=trim(?)  and trim(grpidconaux) =trim(?) ",

        conxagrup.getIdproceso(),
        conxagrup.getCodconcepto(),
        conxagrup.getCodconceptaux());
    }
}
