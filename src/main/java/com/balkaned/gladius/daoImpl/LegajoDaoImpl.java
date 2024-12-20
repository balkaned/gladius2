package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.FileImageLegajo;
import com.balkaned.gladius.models.Grpfile;
import com.balkaned.gladius.dao.LegajoDao;
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

@Slf4j
@Repository("LegajoDao")
public class LegajoDaoImpl implements LegajoDao {

    private static final String CLASS_NAME = "LegajoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile) {

        String sql = "select " +
                "i.iexcodcia, " +
                "i.iexcodtra, " +
                "grp.iexkey idgrp, " +
                "grp.desdet as desgrangrupo, " +
                "i.iexcodgrpfile,  " +
                "i.iexdesgrpfile, " +
                "e.iexcodimage, " +
                "e.iexurlimage, " +
                "e.iexdesimage, " +
                "e.iexestado, " +
                "i.iexgrpfile " +
                "from iexgrpfile i " +
                "inner join ( select  iexkey, desdet " +
                "from iexttabled where iexcodtab='91') grp on i.iexgrpfile = grp.iexkey " +
                "left outer join  iexfileimage e on i.iexcodcia = e.iexcodcia and i.iexcodgrpfile = e.iexcodgrpfile " +
                "where i.iexcodcia = :codcia and " +
                "i.iexcodtra = :codtra ";

        if (!grpfile.equals("%")) {
            sql = sql + " and  i.iexgrpfile=':grpfile' ";
        }

        sql = sql + " order by grp.iexkey, i.iexcodgrpfile asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra);

        List<Grpfile> lsGrpFile = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Grpfile.class));

        return lsGrpFile;
    }

    public Integer obtieneIdGrpFile(Grpfile grpfile) {

        final Integer[] idfinal = {0};

        String sql = "SELECT coalesce(max(iexcodgrpfile),0)+1 idcont " +
                "FROM iexgrpfile WHERE IEXCODCIA = :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", grpfile.getIexcodcia());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarGrpFile(Grpfile grpfile) {

        String sql = "insert into iexgrpfile( " +
                "iexcodcia, " +
                "iexcodgrpfile, " +
                "iexcodtra, " +
                "iexgrpfile, " +
                "iexdesgrpfile, " +
                "iexestado, " +
                "iexusucrea, " +
                "iexfeccrea) values " +
                " ( ?,?,?,?,?,?,?, CURRENT_TIMESTAMP) ";

        jdbc.update(sql,
                grpfile.getIexcodcia(),
                grpfile.getIexcodgrpfile(),
                grpfile.getIexcodtra(),
                grpfile.getIexgrpfile(),
                grpfile.getIexdesgrpfile(),
                grpfile.getIexestado(),
                grpfile.getIexusucrea()
        );
    }

    public void insertarImage(FileImageLegajo fileImageLegajo) {

        String sql = "insert into iexfileimage (iexcodcia, iexcodgrpfile, iexcodimage, " +
                "iexurlimage, iexdesimage, iexestado , iexusucrea, iexfeccrea) values " +
                " ( ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP) ";

        jdbc.update(sql,
                fileImageLegajo.getIexcodcia(),
                fileImageLegajo.getIexcodgrpfile(),
                fileImageLegajo.getIexcodimage(),
                fileImageLegajo.getIexurlimage(),
                fileImageLegajo.getIexdesimage(),
                fileImageLegajo.getIexestado(),
                fileImageLegajo.getIexusucrea()
        );
    }

    public Integer obtieneIdImage(Integer codcia, Integer idgrpfile) {

        final Integer[] idfinal = {0};

        String sql = "SELECT coalesce(max(iexcodimage),0)+1 idcont " +
                "FROM iexfileimage WHERE IEXCODCIA = :codcia and iexcodgrpfile = :idgrpfile ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idgrpfile", idgrpfile);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Grpfile getGrpfile(Integer codcia, Integer idgrpfile) {

        String sql = "select " +
                "i.iexcodcia, " +
                "i.iexcodtra, " +
                "i.iexcodgrpfile, " +
                "i.iexdesgrpfile, " +
                "i.iexgrpfile, " +
                "i.iexestado, " +
                "i.iexusucrea, " +
                "i.iexfeccrea, " +
                "i.iexusumod, " +
                "i.iexfecmod " +
                "from iexgrpfile i " +
                "where i.iexcodcia = :codcia and " +
                "i.iexcodgrpfile = :idgrpfile ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("idgrpfile", idgrpfile);

        Grpfile grpfile = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Grpfile.class));

        return grpfile;
    }

    public void actualizarGrpFile(Grpfile grpfile) {

        String sql = "update iexgrpfile set " +
                "iexgrpfile = ?, " +
                "iexdesgrpfile = ?, " +
                "iexusumod = ?, iexestado = ?, " +
                "iexfecmod = CURRENT_TIMESTAMP where iexcodcia = ? and " +
                "iexcodgrpfile = ? and iexcodtra = ? ";

        jdbc.update(sql,
                grpfile.getIexgrpfile(),
                grpfile.getIexdesgrpfile(),
                grpfile.getIexusumod(),
                grpfile.getIexestado(),
                grpfile.getIexcodcia(),
                grpfile.getIexcodgrpfile(),
                grpfile.getIexcodtra()
        );
    }

    public void eliminarGrpFile(Grpfile grpfile) {

        String sql = "delete from iexgrpfile where iexcodcia=? and " +
                "iexcodgrpfile = ? and iexcodtra = ? ";

        jdbc.update(sql,
                grpfile.getIexcodcia(),
                grpfile.getIexcodgrpfile(),
                grpfile.getIexcodtra()
        );
    }

    public void eliminarImage(FileImageLegajo fileImageLegajo) {

        String sql = "delete from iexfileimage " +
                "where iexcodcia =? and iexcodgrpfile =? and iexcodimage =? ";

        jdbc.update(sql,
                fileImageLegajo.getIexcodcia(),
                fileImageLegajo.getIexcodgrpfile(),
                fileImageLegajo.getIexcodimage()
        );
    }

    public void aprobarDocumento(FileImageLegajo fileImageLegajo) {

        String sql = "update iexfileimage set iexestado=?, iexusuaprob=?, iexfecaprob=? " +
                "where iexcodcia=? and iexcodgrpfile=? and iexcodimage=? ";

        jdbc.update(sql,
                fileImageLegajo.getIexestado(),
                fileImageLegajo.getIexusuaprob(),
                fileImageLegajo.getIexfecaprob(),
                fileImageLegajo.getIexcodcia(),
                fileImageLegajo.getIexcodgrpfile(),
                fileImageLegajo.getIexcodimage()
        );
    }
}
