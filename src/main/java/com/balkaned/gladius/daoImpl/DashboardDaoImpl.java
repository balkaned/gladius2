package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.DashboardDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository("DashboardDao")
@Slf4j
public class DashboardDaoImpl implements DashboardDao {

    private static final String CLASS_NAME = "DashboardDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Cumpleanos> traerListaDeCumpleañosPorMes(Integer codcia) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexlogo, " +
                "e.iexcodsex as sexo, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexfecnac, " +
                "to_char(e.iexfecnac, 'dd/MM/yyyy') as iexfecnacFormat, " +
                "to_char(e.iexfecnac, 'MM') as mes, " +
                "to_char(CURRENT_DATE, 'Month') as mesActual, " +
                "date_part('year', CURRENT_DATE) - date_part('year', e.iexfecnac) as edad " +
                "from iexempleado e " +
                "where to_char(e.iexfecnac, 'MM') = to_char(CURRENT_DATE, 'MM') " +
                "and e.iexcodcia = :iexcodcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia);

        List<Cumpleanos> lsCumpl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Cumpleanos.class));

        return lsCumpl;
    }

    public List<Ingresantes> traerListaDeIngresantesPorMes(Integer codcia) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexfecing, " +
                "to_char(e.iexfecing, 'dd/mm/yyyy') as iexfecingchar, " +
                "to_char(e.iexfecing, 'MM') as mes, " +
                "to_char(e.iexfecing, 'yyyy') as anio, " +
                "to_char(CURRENT_DATE, 'MM') as mes_actual, " +
                "to_char(CURRENT_DATE, 'yyyy') as anio_actual, " +
                "to_char(CURRENT_DATE, 'Month') as mes_actual " +
                "from iexempleado e " +
                "where to_char(e.iexfecing, 'MM')=to_char(CURRENT_DATE, 'MM') " +
                "and to_char(e.iexfecing, 'yyyy')=to_char(CURRENT_DATE, 'yyyy') " +
                "and e.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Ingresantes> lsIngr = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Ingresantes.class));

        return lsIngr;
    }

    public List<Retirados> traerListaDeRetiradosPorMes(Integer codcia) {

        String sql = "select " +
                "e.iexcodtra, " +
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "e.iexfecret, " +
                "to_char(e.iexfecret, 'dd/mm/yyyy') as iexfecretchar, " +
                "to_char(e.iexfecret, 'MM') as mes, " +
                "to_char(e.iexfecret, 'yyyy') as anio, " +
                "to_char(CURRENT_DATE, 'MM') as mes_actual, " +
                "to_char(CURRENT_DATE, 'yyyy') as anio_actual, " +
                "to_char(CURRENT_DATE, 'Month') as mes_actual " +
                "from iexempleado e " +
                "where to_char(e.iexfecret, 'MM')=to_char(CURRENT_DATE, 'MM') " +
                "and to_char(e.iexfecret, 'yyyy')=to_char(CURRENT_DATE, 'yyyy') " +
                "and e.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<Retirados> lsRetirados = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Retirados.class));

        return lsRetirados;
    }

    public Integer getCantidadEmpl(Integer codcia) {

        String sql = "select count(*) as cantidad " +
                "from iexempleado e " +
                "where e.iexflgest='1' " +
                "and e.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    @Override
    public List<DashboardCcosto> obtenerDatosDashboardCcosto(Integer codcia) {

        String sql = "select " +
                "cc.iexccosto, " +
                "cc.iexdesccosto, " +
                "   (select " +
                "   count(e1.iexcodtra) " +
                "   from iexempleado e1 " +
                "   inner join iexccosto cc1 on cc1.iexccosto=e1.iexccosto " +
                "   where e1.iexcodcia = :codcia " +
                "   and cc1.iexcodcia = :codcia " +
                "   and e1.iexflgest='1' " +
                "   and e1.iexccosto=cc.iexccosto) as cantidad " +
                "from iexccosto cc " +
                "where cc.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardCcosto> lsDashCcosto = namedParameterJdbcTemplate.query(sql,
                namedParameters, BeanPropertyRowMapper.newInstance(DashboardCcosto.class));

        return lsDashCcosto;
    }


    public Integer getCantidadAreas(Integer codcia) {

        String sql = "select count(*) as cantidad " +
                "from iexarea a " +
                "where a.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Integer getCantidadBancos(Integer codcia) {

        String sql = "select count(*) as cantidad " +
                "from iexprobancos b " +
                "where b.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public DashboardSexoPie obtenerDashboardPieSexo(Integer codcia) {

        String sql = "select " +
                "(select " +
                "count(e1.iexcodsex) " +
                "from iexempleado e1 " +
                "where e1.iexcodcia = :codcia and e1.iexflgest='1' and e1.iexcodsex='M') as cantidad_m, " +
                "(select " +
                "count(e2.iexcodsex) " +
                "from iexempleado e2 " +
                "where e2.iexcodcia = :codcia and e2.iexflgest='1' and e2.iexcodsex='F') as cantidad_f, " +
                "(select " +
                "count(e3.iexcodsex) " +
                "from iexempleado e3 " +
                "where e3.iexcodcia = :codcia and e3.iexflgest='1' and e3.iexcodsex='MA') as cantidad_ma, " +
                "(select " +
                "count(e4.iexcodsex) " +
                "from iexempleado e4 " +
                "where e4.iexcodcia = :codcia and e4.iexflgest='1') as cantidad_total " +
                "from iexempleado e " +
                "group by cantidad_m ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        try {
            return namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                    BeanPropertyRowMapper.newInstance(DashboardSexoPie.class));
        } catch (IncorrectResultSizeDataAccessException ex) {
            log.info(CLASS_NAME + "obtenerDashBoardPieSexo: No se encontraron resultados.");
            return null;
        }
    }

    public List<DashboardAreaBar> obtenerDatosDashboardArea(Integer codcia) {

        String sql = "select " +
                "ar.iexcodarea as codarea, " +
                "ar.iexdesarea as desarea, " +
                "   (select " +
                "   count(e.iexcodtra) " +
                "   from iexempleado e " +
                "   inner join iexarea ar1 on ar1.iexcodarea=e.iexarea " +
                "   where e.iexcodcia = :codcia " +
                "   and e.iexflgest='1' " +
                "   and ar1.iexcodcia = :codcia " +
                "   and e.iexarea=ar.iexcodarea) as cantidad " +
                "from iexarea ar " +
                "where ar.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardAreaBar> lsDashAr = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardAreaBar.class));

        return lsDashAr;
    }

    public List<DashboardFondosBar> obtenerDatosDashboardFodos(Integer codcia) {

        String sql = "select " +
                "tb1.iexcodtab as codtab, " +
                "tb1.desdet, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexttabled tb1 on tb1.iexkey = e1.iexcodafp " +
                "where e1.iexcodcia = :codcia " +
                "and tb1.iexcodtab='11' " +
                "and e1.iexflgest='1' " +
                "and e1.iexcodafp=tb1.iexkey " +
                "group by tb1.iexcodtab,tb1.desdet ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardFondosBar> lsDashFond = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardFondosBar.class));

        return lsDashFond;
    }

    public List<DashboardBancosPie> obtenerDatosDashboardBancos(Integer codcia) {

        String sql = "select " +
                "tb1.iexcodtab as codtab, " +
                "tb1.desdet, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexttabled tb1 on tb1.iexkey = e1.iexcodban_hab " +
                "where e1.iexcodcia = :codcia " +
                "and tb1.iexcodtab = '36' " +
                "and e1.iexflgest = '1' " +
                "and e1.iexcodban_hab = tb1.iexkey " +
                "group by tb1.iexcodtab,tb1.desdet ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardBancosPie> lsDashBancP = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardBancosPie.class));

        return lsDashBancP;
    }

    public List<DashboardCcosto> obtenerDatosDashboardCCosto(Integer codcia) {

        String sql = "select " +
                "cc.iexccosto, " +
                "cc.iexdesccosto, " +
                "   (select " +
                "   count(e1.iexcodtra) " +
                "   from iexempleado e1 " +
                "   inner join iexccosto cc1 on cc1.iexccosto = e1.iexccosto " +
                "   where e1.iexcodcia = :codcia " +
                "   and cc1.iexcodcia = :codcia " +
                "   and e1.iexflgest = '1' " +
                "   and e1.iexccosto = cc.iexccosto) as cantidad " +
                "from iexccosto cc " +
                "where cc.iexcodcia = :codcia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardCcosto> lsDashCc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardCcosto.class));

        return lsDashCc;
    }

    public List<DashboardPuestos> obtenerDatosDashboardPuestos(Integer codcia) {

        String sql = "select " +
                "ps.iexpuesto, " +
                "ps.iexdespuesto, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexpuesto ps on ps.iexpuesto = e1.iexpuesto " +
                "where e1.iexcodcia = :codcia " +
                "and e1.iexflgest = '1' " +
                "and ps.iexcodcia = :codcia " +
                "group by ps.iexpuesto,ps.iexdespuesto ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardPuestos> lsDashP = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardPuestos.class));

        return lsDashP;
    }

    public List<DashboardLocal> obtenerDatosDashboardLocales(Integer codcia) {

        String sql = "select " +
                "u.iexubicod, " +
                "u.iexubides, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexubicacion u on u.iexubicod = e1.iexubilocal " +
                "where e1.iexcodcia = :codcia " +
                "and e1.iexflgest = '1' " +
                "and u.iexcodcia = :codcia " +
                "group by u.iexubicod, u.iexubides ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia);

        List<DashboardLocal> lsDashLocal = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DashboardLocal.class));

        return lsDashLocal;
    }
}
