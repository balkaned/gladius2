package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Opciones;
import com.balkaned.gladius.dao.OpcionDao;
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

@Repository("OpcionDao")
public class OpcionDaoImpl implements OpcionDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Opciones> listarOpciones(){

        String sql = " select  " +
                "o.iexcodopc, o.iexdesopc, o.iexurlopc, o.iexurlimg, " +
                "o.iexflgest, o.iexcodsec,  " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction,  " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys "  ;
        return template.query(sql, new ResultSetExtractor<List<Opciones>>() {

            public List<Opciones> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Opciones> lista = new ArrayList<Opciones>();

                while(rs.next()) {
                    Opciones p = new Opciones();

                    p.setIexcodopc(rs.getInt("iexcodopc"));
                    p.setIexdesopc(rs.getString("iexdesopc"));
                    p.setIexurlopc(rs.getString("iexurlopc"));
                    p.setIexurlimg(rs.getString("iexurlimg"));
                    p.setIexflgest(rs.getString("iexflgest"));
                    p.setIexcodsec(rs.getInt("iexcodsec"));
                    p.setDessec(rs.getString("iexdessec"));
                    p.setDessys(rs.getString("iexdessys"));
                    p.setIexdescripcion(rs.getString("iexdescripcion"));
                    p.setIexcodapps(rs.getString("iexcodapps"));
                    p.setIexaction(rs.getString("iexaction"));
                    p.setIexusucre(rs.getString("iexusucre"));
                    p.setIexfeccre(rs.getString("iexfeccre"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getIdOpciones(){

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcodopc),0)+1 as idex from iexopciones  " ;

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertarOpciones(Opciones opc){

        template.update("  insert into iexopciones( " +
                " iexcodopc, iexdesopc, iexurlopc, iexurlimg, " +
                " iexflgest, iexcodsec, iexdescripcion, iexcodapps, iexaction,   " +
                " iexusucre, iexfeccre   " +
                " ) values ( " +
                "  ? ,      ?  ,       ? ,      ?,         "+
                "  ? ,      ?  ,       ?,       ?,      ?,   "+
                "  ? ,      current_date   "+
                ")  ",

                opc.getIexcodopc(),
                opc.getIexdesopc(),
                opc.getIexurlopc(),
                opc.getIexurlimg(),
                opc.getIexflgest(),
                opc.getIexcodsec(),
                opc.getIexdescripcion(),
                opc.getIexcodapps(),
                opc.getIexaction(),
                opc.getIexusucre());
    }

    public Opciones getOpciones(Integer codopc) {

        String sql=" select  " +
                "o.iexcodopc, o.iexdesopc, o.iexurlopc, o.iexurlimg, " +
                "o.iexflgest, o.iexcodsec,  " +
                "e.iexdessec, " +
                "s.iexdessys, " +
                "o.iexdescripcion, o.iexcodapps, o.iexaction,  " +
                "o.iexusucre, o.iexfeccre, o.iexusumod, o.iexfecmod " +
                "from iexopciones o " +
                "full outer join iexseccion e  on e.iexcodsec = o.iexcodsec " +
                "full outer join iexsystemas s on e.iexcodsys = s.iexcodsys   where o.iexcodopc="+codopc+"  "   ;

        return (Opciones) template.query(sql, new ResultSetExtractor<Opciones>() {
            public Opciones extractData(ResultSet rs) throws SQLException, DataAccessException{
                Opciones p = new Opciones();
                while(rs.next()) {
                    p.setIexcodopc(rs.getInt("iexcodopc"));
                    p.setIexdesopc(rs.getString("iexdesopc"));
                    p.setIexurlopc(rs.getString("iexurlopc"));
                    p.setIexurlimg(rs.getString("iexurlimg"));
                    p.setIexflgest(rs.getString("iexflgest"));
                    p.setIexcodsec(rs.getInt("iexcodsec"));
                    p.setDessec(rs.getString("iexdessec"));
                    p.setDessys(rs.getString("iexdessys"));
                    p.setIexdescripcion(rs.getString("iexdescripcion"));
                    p.setIexcodapps(rs.getString("iexcodapps"));
                    p.setIexaction(rs.getString("iexaction"));
                    p.setIexusucre(rs.getString("iexusucre"));
                    p.setIexfeccre(rs.getString("iexfeccre"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));
                }
                return p;
            }
        });
    }

    public void actualizarOpciones(Opciones opc){

        template.update("  update iexopciones set " +
                        "  iexdesopc =? , iexurlopc =?, iexurlimg =?, " +
                        " iexflgest =?, iexcodsec =?, iexdescripcion =?, iexcodapps =? , iexaction =?,   " +
                        " iexusumod =?, iexfeccre = current_date  where  iexcodopc   =  ? ",

                        opc.getIexdesopc(),
                        opc.getIexurlopc(),
                        opc.getIexurlimg(),
                        opc.getIexflgest(),
                        opc.getIexcodsec(),
                        opc.getIexdescripcion(),
                        opc.getIexcodapps(),
                        opc.getIexaction(),
                        opc.getIexusumod(),
                        opc.getIexcodopc());
    }

}
