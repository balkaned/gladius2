package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.beans.CentroCosto;
import com.balkaned.gladius.dao.AreaDao;
import com.balkaned.gladius.dao.CcostoDao;
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

@Repository("CcostoDao")
public class CcostoDaoImpl implements CcostoDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<CentroCosto> listarCentroCosto(Integer codcia, String text){

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
                "where iexcodcia="+codcia+" "  ;

        System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<CentroCosto>>() {

            public List<CentroCosto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<CentroCosto> lista = new ArrayList<CentroCosto>();

                while(rs.next()) {
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

    public CentroCosto getCentroCosto(Integer codcia, String codccosto){

        CentroCosto p=null;
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
                "where iexcodcia="+codcia+" and a.iexccosto='"+codccosto+"'"  ;

        System.out.println(sql);

        return (CentroCosto) template.query(sql, new ResultSetExtractor<CentroCosto>() {
            public CentroCosto extractData(ResultSet rs) throws SQLException, DataAccessException {
                while(rs.next()) {
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
                }
                return p;
            }
        });
    }

/*
   public Integer  getIdCentroCosto(Integer codcia ) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();
        Integer idcont =0;


        sql.append(" select coalesce(max(cast(iexccosto as integer)),0)+1 idcont  from iexccosto where iexcodcia ="+codcia+" ");

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
    public void  insertarCentroCosto(CentroCosto ccosto) throws DAOException {



          String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("Insertar cabecera");

        sql.append("  insert into iexccosto( " +
                        " iexcodcia,       iexccosto,    iexdesccosto  ,iexcodcat ," +
                        " iexusucrea,      iexfeccrea " +
                        " ) values ( " +
                        "  ? ,   ?    ,   ?   ,  ?  ,  "+
                        "  ? ,   current_date "+
                        ")  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {
                 pst.setInt(1, ccosto.getIexcodcia());
                 pst.setString(2, ccosto.getIexccosto());
                 pst.setString(3, ccosto.getIexdesccosto());
                 pst.setString(4, ccosto.getIexcodcat());
                 pst.setString(5, "1");

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
    public void  actualizarCentroCosto(CentroCosto ccosto) throws DAOException  {



          String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("actualizar cabecera");

        sql.append("  update iexccosto set" +
                        "    iexdesccosto=?  ,iexcodcat=?,  " +
                        " iexusumod =?,      iexfecmod=current_date " +
                        " where iexcodcia=?   and  iexccosto=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

                 pst.setString(1, ccosto.getIexdesccosto());
                 pst.setString(2, ccosto.getIexcodcat());
                 pst.setString(3, ccosto.getIexusumod());

                  pst.setInt(4, ccosto.getIexcodcia());
                 pst.setString(5, ccosto.getIexccosto());


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
    public void  eliminarCentroCosto(CentroCosto ccosto) throws DAOException  {



          String result = null;
        StringBuilder sql = new StringBuilder();

         System.out.println("actualizar cabecera");

        sql.append("  delete from iexccosto where iexcodcia=?   and  iexccosto=?  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {
                 pst.setInt(1, ccosto.getIexcodcia());
                 pst.setString(2, ccosto.getIexccosto());


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
