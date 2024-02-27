package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.beans.CentroCosto;
import com.balkaned.gladius.dao.CcostoDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
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


@Repository("CcostoDao")
@Slf4j
public class CcostoDaoImpl implements CcostoDao {


    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<CentroCosto> listarCentroCosto(Integer codcia, String text) {

        List<CentroCosto> lista = null;
        String sql = "select   " +
                "a.iexcodcia, " +
                "a.iexccosto, " +
                "a.iexdesccosto, " +
                "a.iexcodcat, " +
                "d.desdet , " +
                "a.iexusucrea , " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from " +
                "iexccosto  a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='64' ) d  on a.iexcodcat = d.iexkey " +
                "where iexcodcia=" + codcia + " ";

        return template.query(sql, new ResultSetExtractor<List<CentroCosto>>() {

            public List<CentroCosto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<CentroCosto> lista = new ArrayList<CentroCosto>();

                while (rs.next()) {
                    CentroCosto p = new CentroCosto();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexccosto(rs.getString("iexccosto"));
                    p.setIexdesccosto(rs.getString("iexdesccosto"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("desdet"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public CentroCosto getCentroCosto(Integer codcia, String codccosto) {

        String sql = "select   " +
                "a.iexcodcia, " +
                "a.iexccosto, " +
                "a.iexdesccosto, " +
                "a.iexcodcat, " +
                "d.desdet , " +
                "a.iexusucrea , " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod " +
                "from " +
                "iexccosto  a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='64' ) d  on a.iexcodcat = d.iexkey " +
                "where iexcodcia=" + codcia + " and a.iexccosto='" + codccosto + "'";

        return (CentroCosto) template.query(sql, new ResultSetExtractor<CentroCosto>() {
            public CentroCosto extractData(ResultSet rs) throws SQLException, DataAccessException {
                CentroCosto p = new CentroCosto();
                while (rs.next()) {
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexccosto(rs.getString("iexccosto"));
                    p.setIexdesccosto(rs.getString("iexdesccosto"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("desdet"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public Integer getIdCentroCosto(Integer codcia) {

        final Integer[] idcont = {0};

        String sql = " select coalesce(max(cast(iexccosto as integer)),0)+1 idcont  from iexccosto where iexcodcia =" + codcia;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idcont[0] = Integer.valueOf(rs.getString("idcont"));
                }
                return idcont[0];
            }
        });
    }

    public void insertarCentroCosto(CentroCosto ccosto) {

        StringBuilder sql = new StringBuilder();

        template.update("  insert into iexccosto( " +
                        " iexcodcia,       iexccosto,    iexdesccosto  ,iexcodcat ," +
                        " iexusucrea,      iexfeccrea " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,  ?  ,  " +
                        "  ? ,   current_date " +
                        ")  ",

                ccosto.getIexcodcia(),
                ccosto.getIexccosto(),
                ccosto.getIexdesccosto(),
                ccosto.getIexcodcat(),
                "1");
    }

    public void actualizarCentroCosto(CentroCosto ccosto) {

        template.update("  update iexccosto set" +
                        "    iexdesccosto=?  ,iexcodcat=?,  " +
                        " iexusumod =?,      iexfecmod=current_date " +
                        " where iexcodcia=?   and  iexccosto=?  ",

                ccosto.getIexdesccosto(),
                ccosto.getIexcodcat(),
                ccosto.getIexusumod(),
                ccosto.getIexcodcia(),
                ccosto.getIexccosto());

    }

    public void eliminarCentroCosto(CentroCosto ccosto) {

        template.update("  delete from iexccosto where iexcodcia=?   and  iexccosto=?  ",

        ccosto.getIexcodcia(),
        ccosto.getIexccosto());

    }
}
