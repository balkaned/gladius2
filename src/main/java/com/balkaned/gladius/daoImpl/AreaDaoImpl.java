package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.Area;
import com.balkaned.gladius.dao.AreaDao;
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
@Repository("AreaDao")
public class AreaDaoImpl implements AreaDao {

    private static final String CLASS_NAME = "AreaDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Area> listarArea(Integer codcia, String text) {

        String sql = "select " +
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
                " case " +
                " WHEN length(a.iexareapadre) > 0 THEN a.iexareapadre " +
                " else 'null'  end iexareapadre, " +
                "f.iexdesarea as desareapadre " +
                "from iexarea a " +
                "full outer join iexarea f on a.iexcodcia= f.iexcodcia and  a.iexareapadre = f.iexcodarea " +
                "full outer join (select  iexkey, desdet from iexttabled where iexcodtab='62' ) d on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia= :iexcodcia " +
                "order by a.iexcodcia, a.iexcodarea asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        List<Area> lsArea = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Area.class));

        return lsArea;
    }

    public Area getArea(Integer codcia, String codarea) {

        String sql = "select " +
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
                "from iexarea a " +
                "full outer join iexarea f on a.iexareapadre = f.iexcodarea " +
                "full outer join (select iexkey, desdet from iexttabled where iexcodtab='12') d on a.iexcodcat = d.iexkey " +
                "where a.iexcodcia= :iexcodcia and a.iexcodarea= :iexcodarea ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("iexcodarea", codarea);

        List<Area> lsArea = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Area.class));

        return lsArea.get(0);
    }

    public Integer getIdArea(Integer codcia) {

        String sql = "select coalesce(max(cast(iexcodarea as integer)),0)+1 idcont " +
                "from iexarea " +
                "where iexcodcia = :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarArea(Area area) {

        String sql = "insert into iexarea ( " +
                " iexcodcia, iexcodarea, iexdesarea, iexdesarea_descripcion, " +
                " iexusucrea, iexfeccrea, iexcodcat, iexareapadre " +
                " ) values ( " +
                "  ?, ?, ?, ?, " +
                "  ?, current_date, ?, ? " +
                " ) ";

        jdbc.update(sql,
                area.getIexcodcia(),
                area.getIexcodarea(),
                area.getIexdesarea(),
                area.getIexdesarea_descripcion(),
                area.getIexusucrea(),
                area.getIexcodcat(),
                area.getIexareapadre()
        );
    }

    public void actualizarArea(Area area) {

        String sql = "update iexarea set " +
                "iexdesarea=?, iexdesarea_descripcion=?, " +
                "iexusumod=?, iexfecmod=current_date, iexcodcat=?, iexareapadre=? " +
                "where iexcodcia=? and " +
                "iexcodarea = ? ";

        jdbc.update(sql,
                area.getIexdesarea(),
                area.getIexdesarea_descripcion(),
                area.getIexusumod(),
                area.getIexcodcat(),
                area.getIexareapadre(),
                area.getIexcodcia(),
                area.getIexcodarea()
        );
    }

    public void eliminarArea(Area area) {

        String sql = "delete from iexarea " +
                "where iexcodcia=? and iexcodarea = ? ";

        jdbc.update(sql,
                area.getIexcodcia(),
                area.getIexcodarea()
        );
    }
}
