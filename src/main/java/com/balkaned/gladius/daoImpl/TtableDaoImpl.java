package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.TTablaCabecera;
import com.balkaned.gladius.models.TTablaDetalle;
import com.balkaned.gladius.dao.TtableDao;
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
@Repository("TtableDao")
public class TtableDaoImpl implements TtableDao {


    private static final String CLASS_NAME = "TtableDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<TTablaCabecera> listarTTablac(String text) {

        String sql = "select " +
                "iexcodtab, " +
                "iexdestab, " +
                "iexlbl1, " +
                "iexlbl2, " +
                "iexlbl3, " +
                "iexlbl4, " +
                "iexlbl5, " +
                "iexlbl6, " +
                "iexlbl7, " +
                "iexlbl8, " +
                "iexlblflg1, " +
                "iexlblflg2, " +
                "iexlblflg3, " +
                "iexlblflg4, " +
                "iexlblflg5, " +
                "iexlblflg6, " +
                "iexlblflg7, " +
                "iexlblflg8, " +
                "iexlblval9, " +
                "iexlblval10, " +
                "iexlblval11, " +
                "iexlblval12, " +
                "iexlblval13, " +
                "iexlblval14, " +
                "iexlblval15, " +
                "iexlblval16, " +
                "iexlblflg9, " +
                "iexlblflg10, " +
                "iexlblflg11, " +
                "iexlblflg12, " +
                "iexlblflg13, " +
                "iexlblflg14, " +
                "iexlblflg15, " +
                "iexlblflg16 " +
                "from iexttablec " +
                /*"where '%'||iexcodtab||'%'||iexdestab||'%' like :text " +*/
                "order by iexcodtab asc ";

        log.info("text: {} ", text);

        String finalText = "'%" + text + "%'";

        log.info("finalText: {} ", finalText);

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text", finalText);

        List<TTablaCabecera> lsTable = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(TTablaCabecera.class));

        log.info("lsTable: {} ", lsTable);

        return lsTable;
    }

    public void insertarTtablac(TTablaCabecera ttc) {

        String sql = "insert into iexttablec ( " +
                "iexcodtab,iexdestab, " +
                "iexlbl1, iexlbl2, iexlbl3, " +
                "iexlbl4, iexlbl5, iexlbl6, " +
                "iexlbl7, iexlbl8, iexlblflg1, " +
                "iexlblflg2, iexlblflg3, iexlblflg4, " +
                "iexlblflg5, iexlblflg6, iexlblflg7, " +
                "iexlblflg8, iexlblval9, iexlblval10, " +
                "iexlblval11, iexlblval12, iexlblval13, " +
                "iexlblval14, iexlblval15, iexlblval16, " +
                "iexlblflg9,  iexlblflg10,  iexlblflg11, " +
                "iexlblflg12, iexlblflg13, iexlblflg14, " +
                "iexlblflg15, iexlblflg16) values " +
                "( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                ttc.getIexcodtab(),
                ttc.getIexdestab(),
                ttc.getIexlbl1(),
                ttc.getIexlbl2(),
                ttc.getIexlbl3(),
                ttc.getIexlbl4(),
                ttc.getIexlbl5(),
                ttc.getIexlbl6(),
                ttc.getIexlbl7(),
                ttc.getIexlbl8(),
                ttc.getIexlblflg1(),
                ttc.getIexlblflg2(),
                ttc.getIexlblflg3(),
                ttc.getIexlblflg4(),
                ttc.getIexlblflg5(),
                ttc.getIexlblflg6(),
                ttc.getIexlblflg7(),
                ttc.getIexlblflg8(),
                ttc.getIexlblval9(),
                ttc.getIexlblval10(),
                ttc.getIexlblval11(),
                ttc.getIexlblval12(),
                ttc.getIexlblval13(),
                ttc.getIexlblval14(),
                ttc.getIexlblval15(),
                ttc.getIexlblval16(),
                ttc.getIexlblflg9(),
                ttc.getIexlblflg10(),
                ttc.getIexlblflg11(),
                ttc.getIexlblflg12(),
                ttc.getIexlblflg13(),
                ttc.getIexlblflg14(),
                ttc.getIexlblflg15(),
                ttc.getIexlblflg16()
        );
    }

    public TTablaCabecera recuperarTTablac(String idttabla) {

        String sql = "select " +
                "iexcodtab, " +
                "iexdestab, " +
                "iexlbl1, " +
                "iexlbl2, " +
                "iexlbl3, " +
                "iexlbl4, " +
                "iexlbl5, " +
                "iexlbl6, " +
                "iexlbl7, " +
                "iexlbl8, " +
                "iexlblflg1, " +
                "iexlblflg2, " +
                "iexlblflg3, " +
                "iexlblflg4, " +
                "iexlblflg5, " +
                "iexlblflg6, " +
                "iexlblflg7, " +
                "iexlblflg8, " +
                "iexlblval9, " +
                "iexlblval10, " +
                "iexlblval11, " +
                "iexlblval12, " +
                "iexlblval13, " +
                "iexlblval14, " +
                "iexlblval15, " +
                "iexlblval16, " +
                "iexlblflg9, " +
                "iexlblflg10, " +
                "iexlblflg11, " +
                "iexlblflg12, " +
                "iexlblflg13, " +
                "iexlblflg14, " +
                "iexlblflg15, " +
                "iexlblflg16 " +
                "from iexttablec " +
                "where iexcodtab = :idttabla ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idttabla", idttabla);

        TTablaCabecera ttcab = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(TTablaCabecera.class));

        return ttcab;
    }

    public void actualizarTTablac(TTablaCabecera ttc) {

        String sql = "update iexttablec set " +
                "iexdestab=?, " +
                "iexlbl1 =?, iexlbl2 =?, iexlbl3 =?, " +
                "iexlbl4 =?, iexlbl5 =?, iexlbl6 =?, " +
                "iexlbl7 =?, iexlbl8=?, iexlblflg1 =?, " +
                "iexlblflg2=?, iexlblflg3=?, iexlblflg4=?, " +
                "iexlblflg5=?, iexlblflg6=?, iexlblflg7=?, " +
                "iexlblflg8=?, iexlblval9=?, iexlblval10=?, " +
                "iexlblval11=?, iexlblval12=?, iexlblval13=?, " +
                "iexlblval14=?, iexlblval15=?, iexlblval16=?, " +
                "iexlblflg9=?,  iexlblflg10=?,  iexlblflg11=?, " +
                "iexlblflg12=?, iexlblflg13=?, iexlblflg14=?, " +
                "iexlblflg15=?, iexlblflg16=? " +
                "where iexcodtab = ? ";

        jdbc.update(sql,
                ttc.getIexdestab(),
                ttc.getIexlbl1(),
                ttc.getIexlbl2(),
                ttc.getIexlbl3(),
                ttc.getIexlbl4(),
                ttc.getIexlbl5(),
                ttc.getIexlbl6(),
                ttc.getIexlbl7(),
                ttc.getIexlbl8(),
                ttc.getIexlblflg1(),
                ttc.getIexlblflg2(),
                ttc.getIexlblflg3(),
                ttc.getIexlblflg4(),
                ttc.getIexlblflg5(),
                ttc.getIexlblflg6(),
                ttc.getIexlblflg7(),
                ttc.getIexlblflg8(),
                ttc.getIexlblval9(),
                ttc.getIexlblval10(),
                ttc.getIexlblval11(),
                ttc.getIexlblval12(),
                ttc.getIexlblval13(),
                ttc.getIexlblval14(),
                ttc.getIexlblval15(),
                ttc.getIexlblval16(),
                ttc.getIexlblflg9(),
                ttc.getIexlblflg10(),
                ttc.getIexlblflg11(),
                ttc.getIexlblflg12(),
                ttc.getIexlblflg13(),
                ttc.getIexlblflg14(),
                ttc.getIexlblflg15(),
                ttc.getIexlblflg16(),
                ttc.getIexcodtab()
        );
    }

    public List<TTablaDetalle> listarTTablad(String idttabla) {

        String sql = "select " +
                "iexcodtab, " +
                "iexkey, " +
                "desdet, " +
                "des1det," +
                "des2det, " +
                "des3det, " +
                "des4det, " +
                "des5det, " +
                "des6det, " +
                "des7det, " +
                "des8det, " +
                "val9det, " +
                "val10det, " +
                "val11det," +
                "val12det, " +
                "val13det, " +
                "val14det, " +
                "val15det, " +
                "val16det " +
                "from iexttabled " +
                "where iexcodtab = :idttabla ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idttabla", idttabla);

        List<TTablaDetalle> lsTTable = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(TTablaDetalle.class));

        return lsTTable;
    }

    public void actualizarTTablad(TTablaDetalle ttd) {

        String sql = "update iexttabled set " +
                "desdet=?, " +
                "des1det=?, " +
                "des2det=?, " +
                "des3det=?, " +
                "des4det=?, " +
                "des5det=?, " +
                "des6det=?, " +
                "des7det=?, " +
                "des8det=?, " +
                "val9det=?, " +
                "val10det=?, " +
                "val11det=?, " +
                "val12det=?, " +
                "val13det=?, " +
                "val14det=?, " +
                "val15det=?, " +
                "val16det=? " +
                "where iexcodtab=? and " +
                "iexkey=? ";

        jdbc.update(sql,
                ttd.getDesdet(),
                ttd.getDes1det(),
                ttd.getDes2det(),
                ttd.getDes3det(),
                ttd.getDes4det(),
                ttd.getDes5det(),
                ttd.getDes6det(),
                ttd.getDes7det(),
                ttd.getDes8det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getIexcodtab(),
                ttd.getIexkey()
        );
    }

    public TTablaDetalle recuperarTTablad(String idttabla, String idttabladet) {

        String sql = "select " +
                "iexcodtab, " +
                "iexkey, " +
                "desdet, " +
                "des1det," +
                "des2det, " +
                "des3det, " +
                "des4det, " +
                "des5det, " +
                "des6det, " +
                "des7det, " +
                "des8det, " +
                "val9det, " +
                "val10det, " +
                "val11det," +
                "val12det, " +
                "val13det, " +
                "val14det, " +
                "val15det, " +
                "val16det " +
                "from iexttabled " +
                "where iexcodtab = :idttabla and " +
                "iexkey = :idttabladet ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("idttabla", idttabla)
                .addValue("idttabladet", idttabladet);

        TTablaDetalle ttabledet = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(TTablaDetalle.class));

        return ttabledet;
    }

    public void eliminarTTablac(String idttabla) {

        String sql = "delete from iexttablec " +
                "where iexcodtab = ? ";

        jdbc.update(sql,
                idttabla
        );
    }

    public void eliminarTTablad(String idttabla) {

        String sql = "delete from iexttabled " +
                "where iexcodtab = ? ";

        jdbc.update(sql,
                idttabla
        );
    }

    public void eliminarTTablade(String idttabla, String idttabladet) {

        String sql = "delete from iexttabled " +
                "where iexcodtab = ? and " +
                "iexkey=? ";

        jdbc.update(sql,
                idttabla,
                idttabladet
        );
    }

    public void insertarTtablad(TTablaDetalle ttd) {

        String sql = "insert into iexttabled ( " +
                "iexcodtab, " +
                "iexkey, " +
                "desdet, " +
                "des1det, " +
                "des2det, " +
                "des3det, " +
                "des4det, " +
                "des5det, " +
                "des6det, " +
                "des7det, " +
                "des8det, " +
                "val9det, " +
                "val10det, " +
                "val11det, " +
                "val12det, " +
                "val13det, " +
                "val14det, " +
                "val15det, " +
                "val16det ) " +
                "values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

        jdbc.update(sql,
                ttd.getIexcodtab(),
                ttd.getIexkey(),
                ttd.getDesdet(),
                ttd.getDes1det(),
                ttd.getDes2det(),
                ttd.getDes3det(),
                ttd.getDes4det(),
                ttd.getDes5det(),
                ttd.getDes6det(),
                ttd.getDes7det(),
                ttd.getDes8det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det(),
                ttd.getVal9det()
        );
    }

}
