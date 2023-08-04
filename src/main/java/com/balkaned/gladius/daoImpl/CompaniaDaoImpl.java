package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Compania;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuarioConeccion;
import com.balkaned.gladius.dao.CompaniaDao;
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

@Repository("CompaniaDao")
public class CompaniaDaoImpl implements CompaniaDao {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public Compania getCompaniaAll(Integer codcia){

        String sql =  "select " +
                "c.iexcodcia    codcia, " +
                "c.iexdescia    descia, " +
                "c.iexnroruc    nroruc, " +
                "c.iexdescorto  descorto, " +
                "c.iexdireccion direccion," +
                "c.iexnrotelf   telefono, " +
                "c.iexcodact    codactividad, " +
                " d.desdet,  "+
                "c.iexrepnombre nombreRepresentante, " +
                "c.iexrepcargo  cargoRepresentante, " +
                "c.iexrepdocid  nrodocRepresentante, " +
                "c.iexreplogo   urllogo, " +
                "c.iexusucre, "+
                "c.iexfeccre, "+
                "c.iexusumod, "+
                "c.iexfecmod, "+
                "c.iexurlfileserver, "+
                "c.iexurlfilereport  ,"+
                " c.iexurlfileimg , "+
                " c.iexflgsource , "+
                " c.iexususource , "+
                " c.iexpasssource , "+
                " c.iexportsource, "+
                " c.iexsourcedes , "+
                " c.iexregiondes , "+
                " c.iexdesobservacion," +
                " c.iexschema "+
                "from iexcompania c  " +
                " full outer join ( select  iexkey, desdet from iexttabled where iexcodtab='1' )  d on c.iexcodact = d.iexkey  where c.iexcodcia="+codcia+" ";

        //logger.info(sql);
        return (Compania) template.query(sql, new ResultSetExtractor<Compania>() {
            public Compania extractData(ResultSet rs) throws SQLException, DataAccessException {
                Compania cia = new Compania();
                while(rs.next()) {
                    cia.setIdCodcia(rs.getInt("codcia"));
                    cia.setDescCia(rs.getString("descia"));
                    cia.setNroRuc(rs.getString("nroruc"));
                    cia.setDescCiaCorto(rs.getString("descorto"));
                    cia.setDireccionCia(rs.getString("direccion"));
                    cia.setNroTelfCia(rs.getString("telefono"));
                    cia.setIdActividadCia(rs.getString("codactividad"));
                    cia.setDesActividadCia(rs.getString("desdet"));
                    cia.setNomRepesentante(rs.getString("nombreRepresentante"));
                    cia.setDesCargoRep(rs.getString("cargoRepresentante"));
                    cia.setNroDocuRep(rs.getString("nrodocRepresentante"));
                    cia.setUrlLogo(rs.getString("urllogo"));
                    cia.setUsuCrea(rs.getString("iexusucre"));
                    cia.setUsuMod(rs.getString("iexusumod"));
                    cia.setFecCrea(rs.getString("iexfeccre"));
                    cia.setFecMod(rs.getString("iexfecmod"));
                    cia.setIexurlfileserver(rs.getString("iexurlfileserver"));
                    cia.setIexurlfilereport(rs.getString("iexurlfilereport"));

                    cia.setIexurlfileimg(rs.getString("iexurlfileimg"));
                    cia.setUrlflgsource(rs.getString("iexflgsource"));
                    cia.setIexususource(rs.getString("iexususource"));
                    cia.setIexpasssource(rs.getString("iexpasssource"));
                    cia.setIexsourcedes(rs.getString("iexsourcedes"));
                    cia.setIexportsource(rs.getString("iexportsource"));
                    cia.setIexregiondes(rs.getString("iexregiondes"));
                    cia.setIexdesobservacion(rs.getString("iexdesobservacion"));
                    cia.setSchema(rs.getString("iexschema"));
                }
                return cia;
            }
        });
    }
}
