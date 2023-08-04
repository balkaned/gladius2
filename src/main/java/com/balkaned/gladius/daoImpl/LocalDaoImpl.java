package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Local;
import com.balkaned.gladius.dao.LocalDao;
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

@Repository("LocalDao")
public class LocalDaoImpl implements LocalDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Local> listarLocales(Integer codcia, String text){

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod "+
                "from iexubicacion a  where a.iexcodcia="+codcia+"  "  ;

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Local>>() {

            public List<Local> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Local> lista = new ArrayList<Local>();

                while(rs.next()) {
                    Local p = new Local();
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexubicod(rs.getString("iexubicod"));
                    p.setIexubides(rs.getString("iexubides"));

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

    public Local getLocales(Integer codcia, String codubicacion){

        Local p = null;

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexubicod, " +
                "a.iexubides, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod "+
                "from iexubicacion a  where a.iexcodcia="+codcia+" and iexubicod ='"+codubicacion+"'  "  ;

        //System.out.println(sql);

        return (Local) template.query(sql, new ResultSetExtractor<Local>() {
            public Local extractData(ResultSet rs) throws SQLException, DataAccessException {
                Local p = new Local();
                while(rs.next()) {
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexubicod(rs.getString("iexubicod"));
                    p.setIexubides(rs.getString("iexubides"));

                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

/*

    public Integer  getIdUbicaion(Integer codcia ) throws DAOException {

      String result = null;
        StringBuilder sql = new StringBuilder();
        Integer idcont =0;


        sql.append(" select  coalesce(max(cast(iexubicod as integer)),0)+1  idcont  from iexubicacion  where iexcodcia ="+codcia+" ");

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                PreparedStatement pst = cn.prepareStatement(sql.toString());
                ResultSet rs = pst.executeQuery();
                ) {
              // Statement st = cn.createStatement();
                if (rs.next()) {
              idcont= Integer.valueOf(rs.getString("idcont"));
                }


            pst.close();
            cn.close();

        } catch (SQLException e) {
            result = e.getMessage();
            idcont=-1;
             System.out.println(e.getMessage());
         } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }



        return idcont ;
    }
    public void  insertarUbicacion(Ubicacion ubic) throws DAOException {

         String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  insert into iexubicacion( " +
                        " iexcodcia,     iexubicod,    iexubides,       " +
                        " iexusucrea,    iexfeccrea  " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,  "+
                        "  ? ,   current_date   "+
                        ")  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {
                 pst.setInt(1,  ubic.getIexcodcia());
                 pst.setString(2,  ubic.getIexubicod());
                 pst.setString(3,  ubic.getIexubides());
                 pst.setString(4, ubic.getIexusucrea());


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
    public void  actualizarUbicaion(Ubicacion ubic) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  update iexubicacion  set " +
                        "     iexubides=?,      " +
                        " iexusumod=?,    iexfecmod=current_date " +
                        " where iexcodcia=?  and   iexubicod = ?");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

                 pst.setString(1, ubic.getIexubides());
                 pst.setString(2, ubic.getIexusumod());
                 pst.setInt(3,  ubic.getIexcodcia());
                 pst.setString(4,  ubic.getIexubicod());



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
    public void  eliminarUbicacion(Ubicacion ubic) throws DAOException {

         String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  delete from iexubicacion where iexcodcia=?  and   iexubicod = ?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {


                 pst.setInt(1,  ubic.getIexcodcia());
                 pst.setString(2,  ubic.getIexubicod());



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
