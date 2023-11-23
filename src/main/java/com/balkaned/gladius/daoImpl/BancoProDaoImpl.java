package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.BancoPro;
import com.balkaned.gladius.dao.AreaDao;
import com.balkaned.gladius.dao.BancoProDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
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

@Repository("BancoProDao")
public class BancoProDaoImpl implements BancoProDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<BancoPro> listarBancoPro(Integer codcia, String text) {

        List<BancoPro> lista = null;
        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodban, " +
                "c.desdet as desban, " +
                "a.iexcodpro, " +
                "f.prodespro, " +
                "a.iextipcta, " +
                "d.desdet as destipcta, " +
                "a.iexctaban, " +
                "a.iexusucrea, " +
                "a.iexfeccrea, " +
                "a.iexusumod, " +
                "a.iexfecmod " +
                "from iexprobancos a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='36' ) c  on a.iexcodban = c.iexkey " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='66' ) d  on a.iextipcta = d.iexkey " +
                "full outer join iexprocesos f on a.iexcodpro =f.procodpro  " +
                "where iexcodcia=" + codcia + "  ";

        return template.query(sql, new ResultSetExtractor<List<BancoPro>>() {

            public List<BancoPro> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<BancoPro> lista = new ArrayList<BancoPro>();

                while (rs.next()) {
                    BancoPro p = new BancoPro();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodban(rs.getString("iexcodban"));
                    p.setDesban(rs.getString("desban"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDespro(rs.getString("prodespro"));
                    p.setIextipcta(rs.getString("iextipcta"));
                    p.setDestipcta(rs.getString("destipcta"));
                    p.setIexctaban(rs.getString("iexctaban"));

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

    public BancoPro getBancoPro(Integer codcia, Integer codpro, String banco) {

        BancoPro p = null;
        List<BancoPro> lista = null;
        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodban, " +
                "c.desdet as desban, " +
                "a.iexcodpro, " +
                "f.prodespro, " +
                "a.iextipcta, " +
                "d.desdet as destipcta, " +
                "a.iexctaban, " +
                "a.iexusucrea, " +
                "a.iexfeccrea, " +
                "a.iexusumod, " +
                "a.iexfecmod " +
                "from iexprobancos a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='36' ) c  on a.iexcodban = c.iexkey " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='66' ) d  on a.iextipcta = d.iexkey " +
                "full outer join iexprocesos f on a.iexcodpro =f.procodpro  " +
                "where iexcodcia=" + codcia + "  ";

        return (BancoPro) template.query(sql, new ResultSetExtractor<BancoPro>() {
            public BancoPro extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    BancoPro p = new BancoPro();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodban(rs.getString("iexcodban"));
                    p.setDesban(rs.getString("desban"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDespro(rs.getString("prodespro"));
                    p.setIextipcta(rs.getString("iextipcta"));
                    p.setDestipcta(rs.getString("destipcta"));
                    p.setIexctaban(rs.getString("iexctaban"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public void insertarBancoPro(BancoPro bancopro) {

        template.update("  insert into iexprobancos( " +
                        " iexcodcia,      iexcodban,     iexcodpro,      iextipcta,  " +
                        " iexctaban,      iexusucrea,    iexfeccrea " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,  ?  , " +
                        "  ? ,  ? ,  current_date " +
                        ")  ",

                bancopro.getIexcodcia(),
                bancopro.getIexcodban(),
                bancopro.getIexcodpro(),
                bancopro.getIextipcta(),
                bancopro.getIexctaban(),
                bancopro.getIexusucrea());
    }

}
