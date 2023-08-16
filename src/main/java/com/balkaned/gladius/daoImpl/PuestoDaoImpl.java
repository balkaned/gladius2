package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Puesto;
import com.balkaned.gladius.dao.PuestoDao;
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

@Repository("PuestoDao")
public class PuestoDaoImpl implements PuestoDao {
    static Logger logger = Logger.getLogger(IndexController.class.getName());
    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Puesto> listarPuesto(Integer codcia, String text){

        List<Puesto> lista = null;

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexpuesto, " +
                "a.iexdespuesto, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as desdet " +
                "from iexpuesto a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='63' ) d  on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia="+codcia+"  "  ;

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Puesto>>() {

            public List<Puesto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Puesto> lista = new ArrayList<Puesto>();

                while(rs.next()) {
                    Puesto p = new Puesto();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexpuesto(rs.getString("iexpuesto"));
                    p.setIexdespuesto(rs.getString("iexdespuesto"));
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

    public Puesto getPuesto(Integer codcia, String codpuesto){

        List<Puesto> lista = null;

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexpuesto, " +
                "a.iexdespuesto, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as desdet " +
                "from iexpuesto a " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='63' ) d  on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia="+codcia+"  and a.iexpuesto='"+codpuesto+"' "  ;

        return (Puesto) template.query(sql, new ResultSetExtractor<Puesto>() {
            public Puesto extractData(ResultSet rs) throws SQLException, DataAccessException {
                Puesto p = new Puesto();
                while(rs.next()) {
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexpuesto(rs.getString("iexpuesto"));
                    p.setIexdespuesto(rs.getString("iexdespuesto"));
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

    public Integer getIdPuesto(Integer codcia){

        final Integer[] idcont = {0};

        String sql=" select  coalesce(max(cast(iexpuesto as integer)),0)+1  idcont from iexpuesto where iexcodcia ="+codcia;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idcont[0] = Integer.valueOf(rs.getString("idcont"));
                }
                return idcont[0];
            }
        });
    }

    public void insertarPuesto(Puesto puesto){

        template.update("  insert into iexpuesto( " +
                " iexcodcia,       iexpuesto,    iexdespuesto  ,iexcodcat ," +
                " iexusucrea,      iexfeccrea " +
                " ) values ( " +
                "  ? ,   ?    ,   ?   , ?, "+
                "  ? ,   current_date "+
                ")  ",

            puesto.getIexcodcia(),
            puesto.getIexpuesto(),
            puesto.getIexdespuesto(),
            puesto.getIexcodcat(),
            puesto.getIexusucrea());

    }

/*


    public void  actualizarPuesto(Puesto puesto) throws DAOException  {

        String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  update iexpuesto  set " +
                        "     iexdespuesto =?  ,iexcodcat=? ," +
                        " iexusucrea =?,      iexfeccrea=current_date  " +
                        " where iexcodcia=?  and  iexpuesto =?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

                 pst.setString(1, puesto.getIexdespuesto());
                 pst.setString(2, puesto.getIexcodcat());
                 pst.setString(3, "1");

                  pst.setInt(4, puesto.getIexcodcia());
                 pst.setString(5, puesto.getIexpuesto());

             System.out.println(sql);

            pst.execute();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
             System.out.println("Error en insertar cabecera"+e.getMessage());
         } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void  eliminarPuesto(Puesto puesto) throws DAOException  {

         String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  delete from  iexpuesto  where iexcodcia=?  and  iexpuesto =?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

                 pst.setInt(1, puesto.getIexcodcia());
                 pst.setString(2, puesto.getIexpuesto());


             System.out.println(sql);

            pst.execute();
            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
             System.out.println("Error en insertar cabecera"+e.getMessage());
         } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

    }*/
}
