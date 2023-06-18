package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.dao.UsuarioConeccionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("UsuarioConecionDao")
public class UsuarioConeccionDaoImpl implements UsuarioConeccionDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public UsuarioConeccion obtenerUsuarioConeccionById(String id) {

        String sql="select " +
                "us.iexcodusu, " +
                "us.iexdesusu, " +
                "us.iexpassw, " +
                "us.iexemail, " +
                "comp.iexcodcia, " +
                "comp.iexdescia, " +
                "comp.iexsourcedes " +
                "from iexusuario us " +
                "inner join iexusuxcia ucia on ucia.iexcodusu=us.iexcodusu " +
                "inner join iexcompania comp on comp.iexcodcia=ucia.iexcodcia " +
                "where us.iexcodusu='"+id+"'";

        return (UsuarioConeccion) template.query(sql, new ResultSetExtractor<UsuarioConeccion>() {
            public UsuarioConeccion extractData(ResultSet rs) throws SQLException, DataAccessException {
                UsuarioConeccion uc = new UsuarioConeccion();
                while(rs.next()) {
                    uc.setId_usuario(rs.getString("iexcodusu"));
                    uc.setUser(rs.getString("iexdesusu"));
                    uc.setPass(rs.getString("iexpassw"));
                    uc.setEmail(rs.getString("iexemail"));
                    uc.setCodCia(rs.getString("iexcodcia"));
                    uc.setDesCia(rs.getString("iexdescia"));
                    uc.setSourceDes(rs.getString("iexsourcedes"));
                }
                return uc;
            }
        });
    }

    @Override
    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc) {
        String sql="select * from iexusuario where iexdesusu='"+uc.getUser()+"'";

        try{
            return (UsuarioConeccion) template.query(sql, new ResultSetExtractor<UsuarioConeccion>() {
                public UsuarioConeccion extractData(ResultSet rs) throws SQLException, DataAccessException{
                    UsuarioConeccion us = new UsuarioConeccion();
                    if(rs.next()) {
                        us.setId_usuario(rs.getString("iexcodusu"));
                        us.setUser(rs.getString("iexdesusu"));
                        us.setPass(rs.getString("iexpassw"));
                    }else {
                        us.setUser("noecontrado");
                        us.setPass("noecontrado");
                    }
                    return us;
                }
            });
        }catch(DataAccessException sa){
            System.out.println("Error de base de datos entré aqui");
            uc.setUser("sinbd");
        }
        return uc;
    }
}
