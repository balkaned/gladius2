package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.beans.TTablaCabecera;
import com.balkaned.gladius.beans.TTablaDetalle;
import com.balkaned.gladius.dao.TtableDao;
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

@Repository("TtableDao")
@Slf4j
public class TtableDaoImpl implements TtableDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<TTablaCabecera> listarTTablac(String text) {

        String sql = "select " +
                "iexcodtab, " +
                "iexdestab," +
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
                "iexlblval13," +
                "iexlblval14, " +
                "iexlblval15," +
                "iexlblval16," +
                "iexlblflg9," +
                "iexlblflg10, " +
                "iexlblflg11," +
                "iexlblflg12, " +
                "iexlblflg13, " +
                "iexlblflg14, " +
                "iexlblflg15, " +
                "iexlblflg16 " +
                "from iexttablec where '%'||iexcodtab||'%'||iexdestab||'%' like '%" + text + "%'  order by iexcodtab asc ";

        return template.query(sql, new ResultSetExtractor<List<TTablaCabecera>>() {

            public List<TTablaCabecera> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<TTablaCabecera> lista = new ArrayList<TTablaCabecera>();

                while (rs.next()) {
                    TTablaCabecera p = new TTablaCabecera();

                    p.setIexcodtab(rs.getString("iexcodtab"));
                    p.setIexdestab(rs.getString("iexdestab"));
                    p.setIexlbl1(rs.getString("iexlbl1"));
                    p.setIexlbl2(rs.getString("iexlbl2"));
                    p.setIexlbl3(rs.getString("iexlbl3"));
                    p.setIexlbl4(rs.getString("iexlbl4"));
                    p.setIexlbl5(rs.getString("iexlbl5"));
                    p.setIexlbl6(rs.getString("iexlbl6"));
                    p.setIexlbl7(rs.getString("iexlbl7"));
                    p.setIexlbl8(rs.getString("iexlbl8"));
                    p.setIexlblflg1(rs.getString("iexlblflg1"));
                    p.setIexlblflg2(rs.getString("iexlblflg2"));
                    p.setIexlblflg3(rs.getString("iexlblflg3"));
                    p.setIexlblflg4(rs.getString("iexlblflg4"));
                    p.setIexlblflg5(rs.getString("iexlblflg5"));
                    p.setIexlblflg6(rs.getString("iexlblflg6"));
                    p.setIexlblflg7(rs.getString("iexlblflg7"));
                    p.setIexlblflg8(rs.getString("iexlblflg8"));
                    p.setIexlblval9(rs.getString("iexlblval9"));
                    p.setIexlblval10(rs.getString("iexlblval10"));
                    p.setIexlblval11(rs.getString("iexlblval11"));
                    p.setIexlblval12(rs.getString("iexlblval12"));
                    p.setIexlblval13(rs.getString("iexlblval13"));
                    p.setIexlblval14(rs.getString("iexlblval14"));
                    p.setIexlblval15(rs.getString("iexlblval15"));
                    p.setIexlblval16(rs.getString("iexlblval16"));
                    p.setIexlblflg9(rs.getString("iexlblflg9"));
                    p.setIexlblflg10(rs.getString("iexlblflg10"));
                    p.setIexlblflg11(rs.getString("iexlblflg11"));
                    p.setIexlblflg12(rs.getString("iexlblflg12"));
                    p.setIexlblflg13(rs.getString("iexlblflg13"));
                    p.setIexlblflg14(rs.getString("iexlblflg14"));
                    p.setIexlblflg15(rs.getString("iexlblflg15"));
                    p.setIexlblflg16(rs.getString("iexlblflg16"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarTtablac(TTablaCabecera ttc) {

        template.update(" insert into iexttablec (   " +
                        " iexcodtab,iexdestab, " +
                        " iexlbl1, iexlbl2,iexlbl3, " +
                        " iexlbl4, iexlbl5, iexlbl6, " +
                        " iexlbl7, iexlbl8, iexlblflg1, " +
                        " iexlblflg2, iexlblflg3, iexlblflg4, " +
                        " iexlblflg5, iexlblflg6, iexlblflg7, " +
                        " iexlblflg8, iexlblval9, iexlblval10, " +
                        " iexlblval11, iexlblval12, iexlblval13, " +
                        " iexlblval14, iexlblval15, iexlblval16, " +
                        " iexlblflg9,  iexlblflg10,  iexlblflg11, " +
                        " iexlblflg12, iexlblflg13, iexlblflg14," +
                        "iexlblflg15, iexlblflg16) values ( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",

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
                ttc.getIexlblflg16());
    }

    public TTablaCabecera recuperarTTablac(String idttabla) {

        String sql = "select " +
                "iexcodtab, " +
                "iexdestab," +
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
                "iexlblval13," +
                "iexlblval14, " +
                "iexlblval15," +
                "iexlblval16," +
                "iexlblflg9," +
                "iexlblflg10, " +
                "iexlblflg11," +
                "iexlblflg12, " +
                "iexlblflg13, " +
                "iexlblflg14, " +
                "iexlblflg15, " +
                "iexlblflg16 " +
                "from iexttablec where iexcodtab='" + idttabla + "' ";

        return (TTablaCabecera) template.query(sql, new ResultSetExtractor<TTablaCabecera>() {
            public TTablaCabecera extractData(ResultSet rs) throws SQLException, DataAccessException {
                TTablaCabecera p = new TTablaCabecera();
                while (rs.next()) {
                    p.setIexcodtab(rs.getString("iexcodtab"));
                    p.setIexdestab(rs.getString("iexdestab"));
                    p.setIexlbl1(rs.getString("iexlbl1"));
                    p.setIexlbl2(rs.getString("iexlbl2"));
                    p.setIexlbl3(rs.getString("iexlbl3"));
                    p.setIexlbl4(rs.getString("iexlbl4"));
                    p.setIexlbl5(rs.getString("iexlbl5"));
                    p.setIexlbl6(rs.getString("iexlbl6"));
                    p.setIexlbl7(rs.getString("iexlbl7"));
                    p.setIexlbl8(rs.getString("iexlbl8"));
                    p.setIexlblflg1(rs.getString("iexlblflg1"));
                    p.setIexlblflg2(rs.getString("iexlblflg2"));
                    p.setIexlblflg3(rs.getString("iexlblflg3"));
                    p.setIexlblflg4(rs.getString("iexlblflg4"));
                    p.setIexlblflg5(rs.getString("iexlblflg5"));
                    p.setIexlblflg6(rs.getString("iexlblflg6"));
                    p.setIexlblflg7(rs.getString("iexlblflg7"));
                    p.setIexlblflg8(rs.getString("iexlblflg8"));
                    p.setIexlblval9(rs.getString("iexlblval9"));
                    p.setIexlblval10(rs.getString("iexlblval10"));
                    p.setIexlblval11(rs.getString("iexlblval11"));
                    p.setIexlblval12(rs.getString("iexlblval12"));
                    p.setIexlblval13(rs.getString("iexlblval13"));
                    p.setIexlblval14(rs.getString("iexlblval14"));
                    p.setIexlblval15(rs.getString("iexlblval15"));
                    p.setIexlblval16(rs.getString("iexlblval16"));
                    p.setIexlblflg9(rs.getString("iexlblflg9"));
                    p.setIexlblflg10(rs.getString("iexlblflg10"));
                    p.setIexlblflg11(rs.getString("iexlblflg11"));
                    p.setIexlblflg12(rs.getString("iexlblflg12"));
                    p.setIexlblflg13(rs.getString("iexlblflg13"));
                    p.setIexlblflg14(rs.getString("iexlblflg14"));
                    p.setIexlblflg15(rs.getString("iexlblflg15"));
                    p.setIexlblflg16(rs.getString("iexlblflg16"));
                }
                return p;
            }
        });
    }

    public void actualizarTTablac(TTablaCabecera ttc) {

        template.update(" update iexttablec set   " +
                        " iexdestab=?, " +
                        " iexlbl1 =?, iexlbl2 =?,iexlbl3 =?, " +
                        " iexlbl4 =?, iexlbl5 =?, iexlbl6 =?, " +
                        " iexlbl7 =?, iexlbl8=?, iexlblflg1 =?, " +
                        " iexlblflg2=?, iexlblflg3=?, iexlblflg4=?, " +
                        " iexlblflg5=?, iexlblflg6=?, iexlblflg7=?, " +
                        " iexlblflg8=?, iexlblval9=?, iexlblval10=?, " +
                        " iexlblval11=?, iexlblval12=?, iexlblval13=?, " +
                        " iexlblval14=?, iexlblval15=?, iexlblval16=?, " +
                        " iexlblflg9=?,  iexlblflg10=?,  iexlblflg11=?, " +
                        " iexlblflg12=?, iexlblflg13=?, iexlblflg14=?," +
                        "iexlblflg15=?, iexlblflg16=?  where iexcodtab = ? ",

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
                ttc.getIexcodtab());

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
                "from  " +
                "iexttabled where iexcodtab='" + idttabla + "' ";

        return template.query(sql, new ResultSetExtractor<List<TTablaDetalle>>() {

            public List<TTablaDetalle> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<TTablaDetalle> lista = new ArrayList<TTablaDetalle>();

                while (rs.next()) {
                    TTablaDetalle p = new TTablaDetalle();

                    p.setIexcodtab(rs.getString("iexcodtab"));
                    p.setIexkey(rs.getString("iexkey"));
                    p.setDesdet(rs.getString("desdet"));
                    p.setDes1det(rs.getString("des1det"));
                    p.setDes2det(rs.getString("des2det"));
                    p.setDes3det(rs.getString("des3det"));
                    p.setDes4det(rs.getString("des4det"));
                    p.setDes5det(rs.getString("des5det"));
                    p.setDes6det(rs.getString("des6det"));
                    p.setDes7det(rs.getString("des7det"));
                    p.setDes8det(rs.getString("des8det"));
                    p.setVal9det(rs.getDouble("val9det"));
                    p.setVal10det(rs.getDouble("val10det"));
                    p.setVal11det(rs.getDouble("val11det"));
                    p.setVal12det(rs.getDouble("val12det"));
                    p.setVal13det(rs.getDouble("val13det"));
                    p.setVal14det(rs.getDouble("val14det"));
                    p.setVal15det(rs.getDouble("val15det"));
                    p.setVal16det(rs.getDouble("val16det"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void actualizarTTablad(TTablaDetalle ttd) {

        template.update(" update  iexttabled  set  " +
                        "desdet=?," +
                        "des1det=?," +
                        "des2det=?," +
                        "des3det=?," +
                        "des4det=?," +
                        "des5det=?," +
                        "des6det=?," +
                        "des7det=?," +
                        "des8det=?," +
                        "val9det=?," +
                        "val10det=?," +
                        "val11det=?," +
                        "val12det=?," +
                        "val13det=?," +
                        "val14det=?," +
                        "val15det=?," +
                        "val16det=?  where iexcodtab=?  and iexkey=?  ",

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
                ttd.getIexkey());

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
                "from  " +
                "iexttabled where iexcodtab='" + idttabla + "' and iexkey='" + idttabladet + "' ";

        return (TTablaDetalle) template.query(sql, new ResultSetExtractor<TTablaDetalle>() {
            public TTablaDetalle extractData(ResultSet rs) throws SQLException, DataAccessException {
                TTablaDetalle p = new TTablaDetalle();
                while (rs.next()) {
                    p.setIexcodtab(rs.getString("iexcodtab"));
                    p.setIexkey(rs.getString("iexkey"));
                    p.setDesdet(rs.getString("desdet"));
                    p.setDes1det(rs.getString("des1det"));
                    p.setDes2det(rs.getString("des2det"));
                    p.setDes3det(rs.getString("des3det"));
                    p.setDes4det(rs.getString("des4det"));
                    p.setDes5det(rs.getString("des5det"));
                    p.setDes6det(rs.getString("des6det"));
                    p.setDes7det(rs.getString("des7det"));
                    p.setDes8det(rs.getString("des8det"));
                    p.setVal9det(rs.getDouble("val9det"));
                    p.setVal10det(rs.getDouble("val10det"));
                    p.setVal11det(rs.getDouble("val11det"));
                    p.setVal12det(rs.getDouble("val12det"));
                    p.setVal13det(rs.getDouble("val13det"));
                    p.setVal14det(rs.getDouble("val14det"));
                    p.setVal15det(rs.getDouble("val15det"));
                    p.setVal16det(rs.getDouble("val16det"));
                }
                return p;
            }
        });
    }

    public void eliminarTTablac(String idttabla) {

        template.update(" delete from iexttablec where  iexcodtab = ? ",
                idttabla);
    }

    public void eliminarTTablad(String idttabla) {

        template.update(" delete from iexttabled where  iexcodtab = ? ",
                idttabla);
    }

    public void eliminarTTablade(String idttabla, String idttabladet) {

        template.update(" delete from iexttabled where  iexcodtab = ? and iexkey=? ",
                idttabla,
                idttabladet);
    }

}
