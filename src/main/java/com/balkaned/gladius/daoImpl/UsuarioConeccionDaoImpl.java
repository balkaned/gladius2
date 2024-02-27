package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.dao.UsuarioConeccionDao;
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

@Repository("UsuarioConecionDao")
@Slf4j
public class UsuarioConeccionDaoImpl implements UsuarioConeccionDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public UsuarioConeccion obtenerUsuarioConeccionById(String id) {

        String sql = "select " +
                "us.iexcodusu, " +
                "us.iexdesusu, " +
                "us.iexpassw, " +
                "us.iexemail, " +
                "comp.iexcodcia, " +
                "comp.iexdescia, " +
                "comp.iexsourcedes, " +
                "comp.iexnroruc " +
                "from iexusuario us " +
                "inner join iexusuxcia ucia on ucia.iexcodusu=us.iexcodusu " +
                "inner join iexcompania comp on comp.iexcodcia=ucia.iexcodcia " +
                "where us.iexcodusu='" + id + "'";

        return (UsuarioConeccion) template.query(sql, new ResultSetExtractor<UsuarioConeccion>() {
            public UsuarioConeccion extractData(ResultSet rs) throws SQLException, DataAccessException {
                UsuarioConeccion uc = new UsuarioConeccion();
                while (rs.next()) {
                    uc.setId_usuario(rs.getString("iexcodusu"));
                    uc.setUser(rs.getString("iexdesusu"));
                    uc.setPass(rs.getString("iexpassw"));
                    uc.setEmail(rs.getString("iexemail"));
                    uc.setCodCia(rs.getString("iexcodcia"));
                    uc.setDesCia(rs.getString("iexdescia"));
                    uc.setSourceDes(rs.getString("iexsourcedes"));
                    uc.setRuccia(rs.getString("iexnroruc"));
                }
                return uc;
            }
        });
    }

    @Override
    public UsuarioConeccion obtenerUsuarioConeccionByName(UsuarioConeccion uc) {
        String sql = "select * from iexusuario where iexdesusu='" + uc.getUser() + "' ";

        try {
            return (UsuarioConeccion) template.query(sql, new ResultSetExtractor<UsuarioConeccion>() {
                public UsuarioConeccion extractData(ResultSet rs) throws SQLException, DataAccessException {
                    UsuarioConeccion us = new UsuarioConeccion();
                    while (rs.next()) {
                        us.setId_usuario(rs.getString("iexcodusu"));
                        us.setUser(rs.getString("iexdesusu"));
                        us.setPass(rs.getString("iexpassw"));
                    }
                    return us;
                }
            });
        } catch (DataAccessException sa) {
            log.info("Error de base de datos entré aqui");
            uc.setUser("sinbd");
        }
        return uc;
    }

    public List<Compania> listarCompaniasBycodUsu(String idUser) {

        String sql = "select " +
                "us.iexcodusu, " +
                "us.iexdesusu, " +
                "cp.iexcodcia, " +
                "cp.iexdescia, " +
                "cp.iexnroruc, " +
                "cp.iexdireccion, " +
                "cp.iexreplogo, " +
                "cp.iexschema," +
                "cp.iexflgsource " +
                "from iexcompania cp " +
                "inner join iexusuxcia uc on uc.iexcodcia=cp.iexcodcia " +
                "inner join iexusuario us on us.iexcodusu=uc.iexcodusu " +
                "where us.iexcodusu='" + idUser + "' ";
        return template.query(sql, new ResultSetExtractor<List<Compania>>() {

            public List<Compania> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Compania> list = new ArrayList<Compania>();

                while (rs.next()) {
                    Compania comp = new Compania();
                    comp.setId_usuario(rs.getString("iexcodusu"));
                    comp.setId_companias(rs.getString("iexcodcia"));
                    comp.setNombre(rs.getString("iexdescia"));
                    comp.setRuc(rs.getString("iexnroruc"));
                    comp.setDireccion(rs.getString("iexdireccion"));
                    comp.setUrlLogo(rs.getString("iexreplogo"));
                    comp.setSchema(rs.getString("iexschema"));
                    comp.setIexflgsource(rs.getString("iexflgsource"));

                    list.add(comp);
                }
                return list;
            }
        });
    }

}
