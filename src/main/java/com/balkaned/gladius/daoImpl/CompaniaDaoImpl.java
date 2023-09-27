package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Compania;
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

    public void logoCompania(Compania com){

        template.update("update  iexcompania set   iexreplogo=?  where iexcodcia = ? ",

        com.getUrlLogo(),
        com.getIdCodcia());
    }

    public List<Compania> listarTodo(){

        String sql =  "select " +
                "c.iexcodcia    codcia, " +
                "c.iexdescia    descia, " +
                "c.iexnroruc    nroruc, " +
                "c.iexdescorto  descorto, " +
                "c.iexdireccion direccion," +
                "c.iexnrotelf   telefono, " +
                "c.iexcodact    codactividad, " +
                "c.iexrepnombre nombreRepresentante, " +
                "c.iexrepcargo  cargoRepresentante, " +
                "c.iexrepdocid  nrodocRepresentante, " +
                "c.iexreplogo   urllogo " +
                "from iexcompania c ";
        return template.query(sql, new ResultSetExtractor<List<Compania>>() {
            public List<Compania> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Compania> lista = new ArrayList<Compania>();

                while(rs.next()) {
                    Compania cia = new Compania();

                    cia.setIdCodcia(rs.getInt("codcia"));
                    cia.setDescCia(rs.getString("descia"));
                    cia.setDescCiaCorto(rs.getString("descorto"));
                    cia.setNroRuc(rs.getString("nroruc"));

                    lista.add(cia);
                }
                return lista;
            }
        });
    }

    public void insertarCompania(Compania com){

        template.update("  insert into  iexcompania ( " +
                        "iexcodcia,        iexdescia,               iexnroruc,              iexdescorto, " +
                        "iexdireccion,     iexnrotelf,              iexcodact,              iexrepnombre, " +
                        "iexrepcargo,      iexrepdocid,             iexreplogo,             iexusucre, " +
                        "iexfeccre,        iexurlfileserver,        iexurlfilereport " +
                        " ) values ( " +
                        "  ? ,        ?    ,     ?   ,        ?  ,"+
                        "  ? ,        ?    ,     ?   ,        ?  ,"+
                        "  ? ,        ?    ,     ?   ,        ?  ,"+
                        " current_date  ,   ?   ,   ?  "+
                        ")  ",

                com.getIdCodcia(),
                com.getDescCia(),
                com.getNroRuc(),
                com.getDescCiaCorto(),
                com.getDireccionCia(),
                com.getNroTelfCia(),
                com.getIdActividadCia(),
                com.getNomRepesentante(),
                com.getDesCargoRep(),
                com.getNroDocuRep(),
                com.getUrlLogo(),
                com.getUsuCrea(),
                com.getIexurlfileserver(),
                com.getIexurlfilereport());
    }

}
