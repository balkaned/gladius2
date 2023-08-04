package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Area;
import com.balkaned.gladius.dao.AreaDao;
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

@Repository("AreaDao")
public class AreaDaoImpl implements AreaDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Area> listarArea(Integer codcia, String text){

        List<Area> lista = null;
        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodarea, " +
                "a.iexdesarea, " +
                "a.iexdesarea_descripcion, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat, " +
                "  case " +
                " WHEN length(a.iexareapadre)>0 THEN a.iexareapadre " +
                " else 'null'  end iexareapadre, " +
                "f.iexdesarea as desareapadre " +
                "from iexarea a  " +
                "full outer join iexarea f on a.iexcodcia= f.iexcodcia and  a.iexareapadre =  f.iexcodarea  " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='62' ) d  on a.iexcodcat = d.iexkey " +
                " where a.iexcodcia="+codcia+"  order by a.iexcodcia, a.iexcodarea asc  "  ;

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Area>>() {

            public List<Area> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Area> lista = new ArrayList<Area>();

                while(rs.next()) {
                    Area p = new Area();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexdesarea(rs.getString("iexdesarea"));
                    p.setIexdesarea_descripcion(rs.getString("iexdesarea_descripcion"));
                    p.setIexareapadre(rs.getString("iexareapadre"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("descodcat"));
                    p.setDesareapadre(rs.getString("desareapadre"));

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

    public Area getArea(Integer codcia, String codarea){

        Area p = null;

        String sql = " select  " +
                "a.iexcodcia, " +
                "a.iexcodarea, " +
                "a.iexdesarea, " +
                "a.iexdesarea_descripcion, " +
                "a.iexusucrea, " +
                "a.iexusumod, " +
                "a.iexfeccrea, " +
                "a.iexfecmod, " +
                "a.iexcodcat, " +
                "d.desdet as descodcat, " +
                "a.iexareapadre, " +
                "f.iexdesarea as desareapadre " +
                "from iexarea a  " +
                "full outer join iexarea f on  a.iexareapadre =  f.iexcodarea  " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='12' ) d  on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia="+codcia+"  and a.iexcodarea='"+codarea+"' "  ;

        //System.out.println(sql);

        return (Area) template.query(sql, new ResultSetExtractor<Area>() {
            public Area extractData(ResultSet rs) throws SQLException, DataAccessException {
                while(rs.next()) {
                    Area p = new Area();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexdesarea(rs.getString("iexdesarea"));
                    p.setIexdesarea_descripcion(rs.getString("iexdesarea_descripcion"));
                    p.setIexareapadre(rs.getString("iexareapadre"));
                    p.setIexcodcat(rs.getString("iexcodcat"));
                    p.setDescodcat(rs.getString("descodcat"));
                    p.setDesareapadre(rs.getString("desareapadre"));

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
    public Integer  getIdArea(Integer codcia ) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();
        Integer idcont =0;


        sql.append(" select  coalesce(max(cast(iexcodarea as integer)),0)+1 idcont from iexarea where iexcodcia ="+codcia+" ");

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
    public void  insertarArea(Area area) throws DAOException {


        String result = null;
        StringBuilder sql = new StringBuilder();

        System.out.println("Insertar cabecera");

        sql.append("  insert into iexarea( " +
                " iexcodcia,     iexcodarea,    iexdesarea,       iexdesarea_descripcion, " +
                " iexusucrea,    iexfeccrea,    iexcodcat,        iexareapadre " +
                " ) values ( " +
                "  ? ,   ?    ,   ?   ,   ?  ,"+
                "  ? ,   current_date  ,   ?   ,   ?  "+
                ")  ");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {
            pst.setInt(1, area.getIexcodcia());
            pst.setString(2, area.getIexcodarea());
            pst.setString(3, area.getIexdesarea());
            pst.setString(4, area.getIexdesarea_descripcion());


            pst.setString(5, area.getIexusucrea());
            pst.setString(6, area.getIexcodcat());
            pst.setString(7, area.getIexareapadre());

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
    public void  actualizarArea(Area area) throws DAOException {

        String result = null;
        StringBuilder sql = new StringBuilder();

        System.out.println("Insertar cabecera");

        sql.append("  update iexarea  set " +
                "     iexdesarea=?,       iexdesarea_descripcion=?, " +
                " iexusumod=?,    iexfecmod=current_date,    iexcodcat=?,        iexareapadre=? " +
                " where iexcodcia=?  and   iexcodarea = ?");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {

            pst.setString(1, area.getIexdesarea());
            pst.setString(2, area.getIexdesarea_descripcion());


            pst.setString(3, area.getIexusumod());
            pst.setString(4, area.getIexcodcat());
            pst.setString(5, area.getIexareapadre());

            pst.setInt(6, area.getIexcodcia());
            pst.setString(7, area.getIexcodarea());

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
    public void  eliminarArea(Area area) throws DAOException {


        String result = null;
        StringBuilder sql = new StringBuilder();

        System.out.println("Insertar cabecera");

        sql.append("  delete from iexarea  where iexcodcia=?  and   iexcodarea = ?");

        try (
                Connection cn = cf.getConnection();
                //PreparedStatement pst = cn.prepareStatement(sql.toString());) {
                CallableStatement pst =cn.prepareCall(sql.toString());) {


            pst.setInt(1, area.getIexcodcia());
            pst.setString(2, area.getIexcodarea());

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


    public List<Empleado> listarEmpRep(Integer codcia, String tiporep) throws DAOException {

        List<Empleado> lista = null;
        String sql = " select   " +
                "iexcodsex, " +
                "count(1)  headcount " +
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='1' " +
                "group by iexcodsex "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setIexcodsex(rs.getString("iexcodsex"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Empleado> listarEmpAfp(Integer codcia, String tiporep) throws DAOException {

        List<Empleado> lista = null;
        String sql = "  select   " +
                "iexcodafp, " +
                "count(1)  headcount " +
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='1' " +
                "group by iexcodafp "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setIexcodafp(rs.getString("iexcodafp"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;


    }
    public List<Empleado> listarEmpBan(Integer codcia, String tiporep) throws DAOException {
        List<Empleado> lista = null;
        String sql = "  select   " +
                "iexcodban_hab, " +
                "count(1)  headcount " +
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='1' " +
                "group by iexcodban_hab "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setIexcodban_hab(rs.getString("iexcodban_hab"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }
    public List<Empleado> listarEmpTipcon(Integer codcia, String tiporep) throws DAOException {
        List<Empleado> lista = null;
        String sql = "  select   " +
                "iextipcont, " +
                "count(1)  headcount " +
                "from iexempleado where iexcodcia="+codcia+" and iexflgest='1' " +
                "group by iextipcont "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setIextipcont(rs.getString("iextipcont"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }


    public List<Empleado> listarEmpRangEdad(Integer codcia, String tiporep) throws DAOException {

        List<Empleado> lista = null;
        String sql = "  select  " +
                "rango_edad, " +
                "count(1) headcount  " +
                "from (  " +
                "select " +
                "case " +
                "when anios > 0 and anios <= 20  then '<0 - 20 >'   " +
                "when anios > 20 and anios <= 25  then '<21 - 25 >'  " +
                "when anios > 25 and anios <= 30  then '<26 - 30 >'  " +
                "when anios > 30 and anios <= 35  then '<30 - 35 >'  " +
                "when anios > 35 and anios <= 40  then '<35 - 40 >'  " +
                "when anios > 40 and anios <= 50  then '<40 - 50 >'  " +
                "when anios > 50 and anios <= 60  then '<50 - 60 >' " +
                "when anios > 60 and anios <= 80  then '<60 - 80 >'  " +
                "else 'Ninguno' " +
                "end rango_edad, " +
                "1 heads " +
                "from  ( " +
                "select  " +
                "(current_date - iexfecnac)/365 as anios, " +
                "1 head " +
                "from iexempleado " +
                " where iexcodcia="+codcia+" and iexflgest='1' " +
                "	) k " +
                "	) r group by rango_edad order by 1 asc "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setRango_edad(rs.getString("rango_edad"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }


    public List<Empleado> listarEmpRangSuel(Integer codcia, String tiporep) throws DAOException {

        List<Empleado> lista = null;
        String sql = "  select  " +
                "rango_edad, " +
                "count(1) headcount  " +
                "from (  " +
                "select " +
                "case " +
                "when anios > 0 and anios <= 20  then '<0 - 20 >'   " +
                "when anios > 20 and anios <= 25  then '<21 - 25 >'  " +
                "when anios > 25 and anios <= 30  then '<26 - 30 >'  " +
                "when anios > 30 and anios <= 35  then '<30 - 35 >'  " +
                "when anios > 35 and anios <= 40  then '<35 - 40 >'  " +
                "when anios > 40 and anios <= 50  then '<40 - 50 >'  " +
                "when anios > 50 and anios <= 60  then '<50 - 60 >' " +
                "when anios > 60 and anios <= 80  then '<60 - 80 >'  " +
                "else 'Ninguno' " +
                "end rango_edad, " +
                "1 heads " +
                "from  ( " +
                "select  " +
                "(current_date - iexfecnac)/365 as anios, " +
                "1 head " +
                "from iexempleado " +
                " where iexcodcia="+codcia+" and iexflgest='1' " +
                "	) k " +
                "	) r group by rango_edad order by 1 asc "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setRango_edad(rs.getString("rango_edad"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;
    }

    public List<Empleado> listarEmpAnioIng(Integer codcia, String tiporep) throws DAOException {

        List<Empleado> lista = null;
        String sql = "   select   " +
                "to_char(iexfecing,'yyyy') as anio, " +
                "count(1) headcount " +
                "from iexempleado " +
                " where iexcodcia="+codcia+" and iexflgest='1' " +
                "group by " +
                "to_char(iexfecing,'yyyy') order by 1 asc "  ;

        System.out.println(sql);
        try (
                Connection cn = cf.getConnection();
                Statement st = cn.createStatement();


                ResultSet rs = st.executeQuery(sql);) {

            lista = new ArrayList<>();
            while (rs.next()) {
                Empleado p = new Empleado();


                p.setAnio_ing(rs.getString("anio"));
                p.setHeadcount(rs.getInt("headcount"));


                lista.add(p);

                //System.out.println("proceso: "+rs.getString("prodespro"));
            }

            st.close();
            cn.close();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } catch (NamingException ex) {
            Logger.getLogger(DAOCompaniaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lista;

    }*/
}
