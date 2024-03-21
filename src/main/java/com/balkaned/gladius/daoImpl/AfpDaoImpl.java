package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.Afp;
import com.balkaned.gladius.dao.AfpDao;
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

@Repository("AfpDao")
@Slf4j
public class AfpDaoImpl implements AfpDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Afp> listar(String text) {

        String sql = " select " +
                "iexpermes," +
                "iexcodafp," +
                "desafp," +
                "iexcomis_fija," +
                "iexcomis_sflu," +
                "iexcomis_sflu_mix," +
                "iexcomis_anual_mix," +
                "iexprima_seguro," +
                "iexaporte_oblig," +
                "iexremmax_asegu," +
                "iexcomis_onp " +
                "from iexafponpper c , (" +
                "select " +
                "  iexkey codafp, desdet desafp " +
                "from iexttabled where iexcodtab='11' " +
                ") d where " +
                " c.iexcodafp= d.codafp and iexpermes='"+text+"' ";

        return template.query(sql, new ResultSetExtractor<List<Afp>>() {

            public List<Afp> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Afp> lista = new ArrayList<Afp>();

                while (rs.next()) {
                    Afp p = new Afp();

                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIexcodafp(rs.getString("iexcodafp"));
                    p.setIexdesafp(rs.getString("desafp"));
                    p.setIexcomis_fija(rs.getDouble("iexcomis_fija"));
                    p.setIexcomis_sflu(rs.getDouble("iexcomis_sflu"));
                    p.setIexcomis_sflu_mix(rs.getDouble("iexcomis_sflu_mix"));
                    p.setIexcomis_anual_mix(rs.getDouble("iexcomis_anual_mix"));
                    p.setIexprima_seguro(rs.getDouble("iexprima_seguro"));
                    p.setIexaporte_oblig(rs.getDouble("iexaporte_oblig"));
                    p.setIexremmax_asegu(rs.getDouble("iexremmax_asegu"));
                    p.setIexcomis_onp(rs.getDouble("iexcomis_onp"));

                    lista.add(p);
                }

                return lista;
            }
        });
    }

    public void insertar(Afp afp){

        template.update(" insert into iexafponpper (" +
                        "iexpermes," +
                        "iexcodafp," +
                        "iexcomis_fija," +
                        "iexcomis_sflu," +
                        "iexcomis_sflu_mix," +
                        "iexcomis_anual_mix," +
                        "iexprima_seguro," +
                        "iexaporte_oblig," +
                        "iexremmax_asegu," +
                        "iexcomis_onp " +
                        ") values ( ?,?,?,?,?,?,?,?,?,?  ) ",

                afp.getIexpermes(),
                afp.getIexcodafp(),
                afp.getIexcomis_fija(),
                afp.getIexcomis_sflu(),
                afp.getIexcomis_sflu_mix(),
                afp.getIexcomis_anual_mix(),
                afp.getIexprima_seguro(),
                afp.getIexaporte_oblig(),
                afp.getIexremmax_asegu(),
                afp.getIexcomis_onp());
    }

    public Afp recuperar(Afp afp) {

        String sql=" select " +
                "iexpermes," +
                "iexcodafp," +
                "desafp," +
                "iexcomis_fija," +
                "iexcomis_sflu," +
                "iexcomis_sflu_mix," +
                "iexcomis_anual_mix," +
                "iexprima_seguro," +
                "iexaporte_oblig," +
                "iexremmax_asegu," +
                "iexcomis_onp " +
                "from iexafponpper c , (" +
                "select " +
                "  iexkey codafp, desdet desafp " +
                "  from iexttabled where iexcodtab='11' " +
                ") d where " +
                " c.iexcodafp= d.codafp and iexpermes='"+afp.getIexpermes()+"'  and c.iexcodafp='"+afp.getIexcodafp()+"' ";

        return (Afp) template.query(sql, new ResultSetExtractor<Afp>() {
            public Afp extractData(ResultSet rs) throws SQLException, DataAccessException{
                Afp p = new Afp();

                while(rs.next()) {
                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIexcodafp(rs.getString("iexcodafp"));
                    p.setIexdesafp(rs.getString("desafp"));
                    p.setIexcomis_fija(rs.getDouble("iexcomis_fija"));
                    p.setIexcomis_sflu(rs.getDouble("iexcomis_sflu"));
                    p.setIexcomis_sflu_mix(rs.getDouble("iexcomis_sflu_mix"));
                    p.setIexcomis_anual_mix(rs.getDouble("iexcomis_anual_mix"));
                    p.setIexprima_seguro(rs.getDouble("iexprima_seguro"));
                    p.setIexaporte_oblig(rs.getDouble("iexaporte_oblig"));
                    p.setIexremmax_asegu(rs.getDouble("iexremmax_asegu"));
                    p.setIexcomis_onp(rs.getDouble("iexcomis_onp"));
                }
                return p;
            }
        });
    }

    public void actualizar(Afp afp){

        template.update(" update  iexafponpper  set " +
                        "iexcomis_fija=?," +
                        "iexcomis_sflu=?," +
                        "iexcomis_sflu_mix=?," +
                        "iexcomis_anual_mix=?," +
                        "iexprima_seguro =? ," +
                        "iexaporte_oblig=? ," +
                        "iexremmax_asegu =?," +
                        "iexcomis_onp=? " +
                        " where iexpermes=?  and iexcodafp=?  ",

                afp.getIexcomis_fija(),
                afp.getIexcomis_sflu(),
                afp.getIexcomis_sflu_mix(),
                afp.getIexcomis_anual_mix(),
                afp.getIexprima_seguro(),
                afp.getIexaporte_oblig(),
                afp.getIexremmax_asegu(),
                afp.getIexcomis_onp(),
                afp.getIexpermes(),
                afp.getIexcodafp());
    }

    public void eliminar(Afp afp){

        template.update(" delete from  iexafponpper where " +
                        " iexpermes=? and  " +
                        " iexcodafp=? ",

        afp.getIexpermes(),
        afp.getIexcodafp());
    }

    public void insertarDuplicado(String perini, String perfin2){

        template.update(" call pl_replica_afp(?,?) ",

                perini,
                perfin2);
    }

}
