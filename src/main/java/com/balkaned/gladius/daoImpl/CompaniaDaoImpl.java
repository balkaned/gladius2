package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Ciaxcon;
import com.balkaned.gladius.models.Compania;
import com.balkaned.gladius.dao.CompaniaDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.List;


@Repository("CompaniaDao")
@Slf4j
public class CompaniaDaoImpl implements CompaniaDao {

    private static final String CLASS_NAME = "CompaniaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public Compania getCompaniaAll(Integer codcia) {

        String sql = "select " +
                "c.iexcodcia idCodcia, " +
                "c.iexdescia descCia, " +
                "c.iexnroruc nroRuc, " +
                "c.iexdescorto descCiaCorto, " +
                "c.iexdireccion direccionCia, " +
                "c.iexnrotelf nroTelfCia, " +
                "c.iexcodact idActividadCia, " +
                "d.desdet desActividadCia, " +
                "c.iexrepnombre nombreRepresentante, " +
                "c.iexrepcargo desCargoRep, " +
                "c.iexrepdocid nroDocuRep, " +
                "c.iexreplogo urllogo, " +
                "c.iexusucre usuCrea, " +
                "c.iexfeccre fecCrea, " +
                "c.iexusumod usuMod, " +
                "c.iexfecmod fecMod, " +
                "c.iexurlfileserver iexurlfileserver, " +
                "c.iexurlfilereport iexurlfilereport, " +
                "c.iexurlfileimg iexurlfileimg, " +
                "c.iexflgsource, " +
                "c.iexususource, " +
                "c.iexpasssource, " +
                "c.iexportsource, " +
                "c.iexsourcedes, " +
                "c.iexregiondes, " +
                "c.iexdesobservacion, " +
                "c.iexschema schema " +
                "from iexcompania c " +
                "full outer join ( select iexkey, desdet from iexttabled where iexcodtab='1' ) d " +
                "on c.iexcodact = d.iexkey  where c.iexcodcia = :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        Compania com = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Compania.class));

        return com;

        /*return (Compania) template.query(sql, new ResultSetExtractor<Compania>() {
            public Compania extractData(ResultSet rs) throws SQLException, DataAccessException {
                Compania cia = new Compania();
                while (rs.next()) {
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
        });*/
    }

    public void logoCompania(Compania com) {

        String sql = "update iexcompania set iexreplogo=? " +
                "where iexcodcia = ? ";

        jdbc.update(sql,
                com.getUrlLogo(),
                com.getIdCodcia());
    }

    public List<Compania> listarTodo() {

        String sql = "select " +
                "c.iexcodcia idCodcia, " +
                "c.iexdescia descCia, " +
                "c.iexnroruc nroRuc, " +
                "c.iexdescorto descCiaCorto, " +
                "c.iexdireccion direccion, " +
                "c.iexnrotelf telefono, " +
                "c.iexcodact codactividad, " +
                "c.iexrepnombre nombreRepresentante, " +
                "c.iexrepcargo cargoRepresentante, " +
                "c.iexrepdocid nrodocRepresentante, " +
                "c.iexreplogo urllogo " +
                "from iexcompania c ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Compania> lsCom = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Compania.class));

        return lsCom;
    }

    public void insertarCompania(Compania com) {

        String sql = "insert into iexcompania ( " +
                "iexcodcia, iexdescia, iexnroruc, iexdescorto, " +
                "iexdireccion, iexnrotelf, iexcodact, iexrepnombre, " +
                "iexrepcargo, iexrepdocid, iexreplogo, iexusucre, " +
                "iexfeccre, iexurlfileserver, iexurlfilereport " +
                " ) values ( " +
                " ?, ?, ?, ?, " +
                " ?, ?, ?, ?, " +
                " ?, ?, ?, ?, " +
                " current_date, ?, ? " +
                ") ";

        jdbc.update(sql,
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

    public Compania getCompania(Integer codcia) {

        String sql = "select " +
                "c.iexcodcia idCodcia, " +
                "c.iexdescia descCia, " +
                "c.iexnroruc nroRuc, " +
                "c.iexdescorto descCiaCorto, " +
                "c.iexdireccion direccion, " +
                "c.iexnrotelf nroTelfCia, " +
                "c.iexcodact idActividadCia, " +
                " d.desdet desActividadCia, " +
                "c.iexrepnombre nombreRepresentante, " +
                "c.iexrepcargo desCargoRep, " +
                "c.iexrepdocid nroDocuRep, " +
                "c.iexreplogo urllogo, " +
                "c.iexusucre usuCrea, " +
                "c.iexfeccre fecCrea, " +
                "c.iexusumod usuMod, " +
                "c.iexfecmod fecMod, " +
                "c.iexurlfilereport iexurlfilereport, " +
                "c.iexflgsource, " +
                "c.iexurlfileserver, " +
                "c.iexususource, " +
                "c.iexpasssource, " +
                "c.iexsourcedes, " +
                "c.iexregiondes, " +
                "c.iexportsource " +
                "from iexcompania c " +
                "full outer join ( select  iexkey, desdet from iexttabled where iexcodtab='1' ) d " +
                "on c.iexcodact = d.iexkey where c.iexcodcia= :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        Compania com = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Compania.class));

        return com;

        /*return (Compania) template.query(sql, new ResultSetExtractor<Compania>() {
            public Compania extractData(ResultSet rs) throws SQLException, DataAccessException {
                Compania cia = new Compania();
                while (rs.next()) {
                    cia.setIdCodcia(rs.getInt("codcia"));
                    cia.setDescCia(rs.getString("descia"));
                    cia.setNroRuc(rs.getString("nroruc"));

                    cia.setDescCiaCorto(rs.getString("descorto"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    cia.setDescCiaCorto(cap.letras(cia.getDescCiaCorto()));

                    cia.setDireccionCia(rs.getString("direccion"));
                    CapitalizarCadena cap2 = new CapitalizarCadena();
                    cia.setDireccionCia(cap2.letras(cia.getDireccionCia()));

                    cia.setNroTelfCia(rs.getString("telefono"));
                    cia.setIdActividadCia(rs.getString("codactividad"));
                    cia.setDesActividadCia(rs.getString("desdet"));

                    cia.setNomRepesentante(rs.getString("nombreRepresentante"));
                    CapitalizarCadena cap3 = new CapitalizarCadena();
                    cia.setNomRepesentante(cap3.letras(cia.getNomRepesentante()));

                    cia.setDesCargoRep(rs.getString("cargoRepresentante"));
                    CapitalizarCadena cap4 = new CapitalizarCadena();
                    cia.setDesCargoRep(cap4.letras(cia.getDesCargoRep()));

                    cia.setNroDocuRep(rs.getString("nrodocRepresentante"));
                    cia.setUrlLogo(rs.getString("urllogo"));
                    cia.setUsuCrea(rs.getString("iexusucre"));
                    cia.setUsuMod(rs.getString("iexusumod"));
                    cia.setFecCrea(rs.getString("iexfeccre"));
                    cia.setFecMod(rs.getString("iexfecmod"));
                    cia.setIexurlfilereport(rs.getString("iexurlfilereport"));

                    cia.setIexflgsource(rs.getString("iexflgsource"));
                    cia.setIexurlfileserver(rs.getString("iexurlfileserver"));
                    cia.setIexususource(rs.getString("iexususource"));
                    cia.setIexpasssource(rs.getString("iexpasssource"));
                    cia.setIexsourcedes(rs.getString("iexsourcedes"));
                    cia.setIexregiondes(rs.getString("iexregiondes"));
                    cia.setIexportsource(rs.getString("iexportsource"));
                    log.info("urlLogo: " + cia.getUrlLogo());
                }
                return cia;
            }
        });*/
    }

    public List<Ciaxcon> listarCiaxcon(Integer codcia, String flgtipreg) {

        String sql = "select " +
                "c.iexcodcia, c.iexcodcon, d.coodescon iexdescon, c.iextipreg " +
                "from iexciaxcon c, iexconcepto d " +
                "where c.iexcodcon = d.coocodcon and " +
                "c.iexcodcia = :iexcodcia and c.iextipreg = :flgtipreg ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("flgtipreg", flgtipreg);

        List<Ciaxcon> lsCiaXcon = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Ciaxcon.class));

        return lsCiaXcon;
    }

    public void actualizarCompania(Compania com) {

        String sql = "update iexcompania set " +
                "iexdescia=?, iexnroruc=?, iexdescorto=?, " +
                "iexdireccion=?, iexnrotelf=?, iexcodact=?, iexrepnombre=?, " +
                "iexrepcargo=?, iexrepdocid=?, iexreplogo=?, iexusumod=?, " +
                "iexfecmod=current_date, iexurlfileserver=?, iexurlfilereport=?, " +
                "iexflgsource=?, iexususource=?, iexpasssource=?, " +
                "iexsourcedes=?, iexregiondes=?, iexportsource=? " +
                "where iexcodcia = ? ";

        jdbc.update(sql,
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
                com.getUsuMod(),
                com.getIexurlfileserver(),
                com.getIexurlfilereport(),
                com.getIexflgsource(),
                com.getIexususource(),
                com.getIexpasssource(),
                com.getIexsourcedes(),
                com.getIexregiondes(),
                com.getIexportsource(),
                com.getIdCodcia());
    }

    public void insertarCiaxcon(Integer codcia, String codcon, String tipreg) {

        String sql = "insert into iexciaxcon ( " +
                "iexcodcia, iexcodcon, iexflgest, iexdefval, iextipreg " +
                " ) values ( " +
                "  ? ,  ? ,  ? ,  ?  , ? " +
                ")  ";

        jdbc.update(sql,
                codcia,
                codcon,
                "1",
                0.0,
                tipreg);
    }

    public void deleteCiaxcon(Integer codcia, String codcon) {

        String sql = "delete from iexciaxcon " +
                "where iexcodcia=? and  iexcodcon=? ";

        jdbc.update(sql,
                codcia,
                codcon);
    }

    public void eliminarCompania(Compania com) {

        String sql = "delete from iexcompania " +
                "where iexcodcia = ? ";

        jdbc.update(sql,
                com.getIdCodcia());
    }

}
