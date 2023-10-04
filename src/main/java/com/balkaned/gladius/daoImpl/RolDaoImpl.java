package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Role;
import com.balkaned.gladius.dao.RolDao;
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

@Repository("RolDao")
public class RolDaoImpl implements RolDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<Role> listarRoles(){

        String sql =  "select " +
                "c.iexcodrol    codrol, " +
                "c.iexdesrol    desrol " +
                "from iexroles c ";

        return template.query(sql, new ResultSetExtractor<List<Role>>() {

            public List<Role> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Role> lista = new ArrayList<Role>();

                while(rs.next()) {
                    Role rol = new Role();

                    rol.setIdRole(rs.getInt("codrol"));
                    rol.setDesRole(rs.getString("desrol"));

                    lista.add(rol);
                }
                return lista;
            }
        });
    }

    public void insertarRole(Role rol){

        template.update("  insert into iexroles( " +
                        " iexcodrol,       iexdesrol, iexflgest " +
                        " ) values ( " +
                        "  ? ,   ?  ,  ?   "+
                        ")  ",

                        rol.getIdRole(),
                        rol.getDesRole(),
                        rol.getFlgest());
    }

}
