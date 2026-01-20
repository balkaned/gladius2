package com.balkaned.gladius.vacaciones.Infrastructure;

import com.balkaned.gladius.empleado.Domain.Empleado;
import com.balkaned.gladius.vacaciones.Domain.VacacionControl;
import com.balkaned.gladius.vacaciones.Domain.VacacionProgramacion;
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
@Repository("VacacionesDao")
public class VacacionesDaoImpl implements VacacionesDao {

    private static final String CLASS_NAME = "VacacionesDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<VacacionControl> listarVacacionesCtl(Empleado empleado) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexpermesini, " +
                "iexpermesfin, " +
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexdiasgan, iexdiasgoz, iexdiasven, iexdiasper, " +
                "iexdiascom, iexdiassaldo, " +
                "iexusucrea, to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea, " +
                "iexusumod, to_char(iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacctl " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra " +
                "order by iexpermesini desc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra());

        List<VacacionControl> lsVac = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionControl.class));

        return lsVac;
    }

    public List<VacacionProgramacion> listarVacacionesPer(Empleado empleado, String perini, String perfin) {

        String sql = "select " +
                "v.iexcodcia, " +
                "v.iexcodtra, " +
                "v.iexcorrel, " +
                "to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "v.iexnrodias, v.iextipvac, d.desdet as destipvac, v.iexglosa, " +
                "v.iexpermesini, " +
                "v.iexpermesfin, " +
                "v.iexusucrea, " +
                "to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, " +
                "v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacprg v, " +
                "   ( " +
                "   select  iexkey, desdet from iexttabled where iexcodtab='56' " +
                "   ) d " +
                "where v.iexcodcia = :codcia and " +
                "v.iexcodtra = :codtra and " +
                "v.iexpermesini = :perini and " +
                "v.iexpermesfin = :perfin and " +
                "v.iextipvac = d.iexkey " +
                "order by v.iexfecini desc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", empleado.getIexcodcia())
                .addValue("codtra", empleado.getIexcodtra())
                .addValue("perini", perini)
                .addValue("perfin", perfin);

        List<VacacionProgramacion> lsVacProg = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionProgramacion.class));

        return lsVacProg;
    }

    public Integer saldotraVac(Integer codcia, Integer codtra, String perini, String perfin) {

        String sql = "select coalesce(sum(iexdiassaldo),0) as dias " +
                "from iexvacctl " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexpermesini = :perini and " +
                "iexpermesfin = :perfin ";

        SqlParameterSource namedPArameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("perini", perini)
                .addValue("perfin", perfin);

        return namedParameterJdbcTemplate.queryForObject(sql, namedPArameters, Integer.class);
    }

    public Integer validaVac(Integer codcia, Integer codtra, String fecini, String fecfin) {

        String sql = "select " +
                "sum(e.dias) dias " +
                "from ( " +
                "   select coalesce(count(iexcorrel),0) dias from iexvacprg " +
                "   where iexcodcia = :codcia and iexcodtra = :codtra " +
                "   and to_date(:fecini,'dd/mm/yyyy') >= iexfecini and " +
                "   to_date(:fecini,'dd/mm/yyyy') <= iexfecfin " +
                "	    union " +
                "	select coalesce(count(iexcorrel),0) dias from iexvacprg " +
                "   where iexcodcia = :codcia and iexcodtra = :codtra " +
                "   and to_date(:fecfin,'dd/mm/yyyy') >= iexfecini and " +
                "   to_date(:fecfin,'dd/mm/yyyy') <= iexfecfin " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexvacprg " +
                "   where iexcodcia = :codcia and iexcodtra = :codtra " +
                "   and to_date(:fecini,'dd/mm/yyyy') <= iexfecini and " +
                "   to_date(:fecfin,'dd/mm/yyyy') >= iexfecini " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexvacprg " +
                "   where iexcodcia = :codcia and iexcodtra = :codtra " +
                "   and to_date(:fecini,'dd/mm/yyyy') <= iexfecfin and " +
                "   to_date(:fecfin,'dd/mm/yyyy') >= iexfecfin " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexvacprg " +
                "   where iexcodcia = :codcia and iexcodtra = :codtra and " +
                "   to_date(:fecini,'dd/mm/yyyy') <= iexfecini and " +
                "   to_date(:fecfin,'dd/mm/yyyy') >= iexfecfin " +
                " ) e ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("fecini", fecini)
                .addValue("fecfin", fecfin);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Integer getIdVacacionPrg(VacacionProgramacion vacprg) {

        String sql = "select coalesce(max(iexcorrel),0)+1 as idex " +
                "from iexvacprg " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", vacprg.getIexcodcia())
                .addValue("codtra", vacprg.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarVacacionPrg(VacacionProgramacion vacprg) {

        String sql = "insert into iexvacprg ( " +
                "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias, " +
                "iextipvac , iexglosa, iexpermesini, iexpermesfin, iexusucrea, iexfeccrea " +
                " ) values ( " +
                "  ?, ?, ?, to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), ?, " +
                "  ?, ?, " +
                "  ?, ?, ?, current_date " +
                " ) ";

        jdbc.update(sql,
                vacprg.getIexcodcia(),
                vacprg.getIexcodtra(),
                vacprg.getIexcorrel(),
                vacprg.getIexfecini(),
                vacprg.getIexfecfin(),
                vacprg.getIexnrodias(),
                vacprg.getIextipvac(),
                vacprg.getIexglosa(),
                vacprg.getIexpermesini(),
                vacprg.getIexpermesfin(),
                vacprg.getIexusucrea()
        );
    }

    public void procesaVacacionCtl(Empleado empleado) {

        String sql = "call pl_empleado_vacsal(?,?) ";

        jdbc.update(sql,
                empleado.getIexcodcia(),
                empleado.getIexcodtra()
        );
    }


    public List<VacacionProgramacion> listaVacacionesGen(Integer codcia, String regimen, String fecini,
                                                         String fecfin, Integer codtra) {

        String sql = "select " +
                "c.iexcodcia, " +
                "c.iexcodtra, " +
                "c.iexapepat||' '|| c.iexapemat||' '|| c.iexnomtra desnomtra, " +
                "case " +
                "when iexflgest='1' then 'activo' " +
                "when iexflgest='0' then 'inactivo' " +
                "else 'inactivo' end desestado, " +
                "to_char(c.iexfecing,'DD/MM/YYYY') fecing, " +
                "c.iexnrodoc as nrodoc, " +
                "a.iextipvac, " +
                "a.iexcorrel as iexcorrel, " +
                "to_char(a.iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(a.iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "case " +
                "   when (a.iexfecini >=to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecini <= to_date(:fecfin,'DD/MM/YYYY') and " +
                "       a.iexfecfin <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then  (a.iexfecfin -  a.iexfecini) +1  " +
                "   when (a.iexfecini < to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecfin <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then (a.iexfecfin - to_date(:fecini,'DD/MM/YYYY')) +1	" +
                "	when (a.iexfecini >= to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecini <= to_date(:fecfin,'DD/MM/YYYY') and " +
                "       a.iexfecfin > to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then (to_date(:fecfin,'DD/MM/YYYY') - a.iexfecini) +1	" +
                "	when (a.iexfecini < to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecfin > to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then (to_date(:fecfin,'DD/MM/YYYY') - to_date(:fecini,'DD/MM/YYYY')) +1 " +
                "   end iexnrodias, " +
                "case " +
                "   when (a.iexfecini >=to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecini <= to_date(:fecfin,'DD/MM/YYYY') and " +
                "       a.iexfecfin <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then to_char(a.iexfecfin,'DD/MM/YYYY') " +
                "   when (a.iexfecini < to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecfin <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then to_char(a.iexfecfin,'DD/MM/YYYY') " +
                "	when (a.iexfecini >= to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecini <= to_date(:fecfin,'DD/MM/YYYY') and " +
                "       a.iexfecfin > to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then :fecfin " +
                "	  when (a.iexfecini < to_date(:fecini,'DD/MM/YYYY') and " +
                "       a.iexfecfin > to_date(:fecfin,'DD/MM/YYYY')) " +
                "	    then :fecfin " +
                "end fecfinrep, " +
                "k.des1det codcon, " +
                "k.desdet destipvac " +
                "from iexempleado c, " +
                "iexvacprg a  " +
                "full outer join ( " +
                "   select " +
                "   iexkey, desdet, des1det " +
                "   from iexttabled where iexcodtab='56' " +
                "	) k on a.iextipvac = k.iexkey " +
                "	where " +
                "	c.iexcodcia = a.iexcodcia and " +
                "	c.iexcodtra = a.iexcodtra and " +
                "	c.iexcodcia = :codcia and " +
                "   c.iexreglab = :regimen and " +
                "	( " +
                "	(a.iexfecini >= to_date(:fecini,'DD/MM/YYYY') and " +
                "   a.iexfecini <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	or " +
                "	(a.iexfecfin >= to_date(:fecini,'DD/MM/YYYY') and " +
                "   a.iexfecfin <= to_date(:fecfin,'DD/MM/YYYY')) " +
                "	or " +
                "   (a.iexfecini < to_date(:fecini,'DD/MM/YYYY') and " +
                "   a.iexfecfin > to_date(:fecfin,'DD/MM/YYYY')) " +
                "   ) ";

        if (codtra != null && codtra != 0) {
            sql = sql + " and c.iexcodtra = :codtra ";
        }

        sql = sql + " order by 3,4 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("fecini", fecini)
                .addValue("fecfin", fecfin)
                .addValue("codcia", codcia)
                .addValue("regimen", regimen)
                .addValue("codtra", codtra);

        List<VacacionProgramacion> lsVac = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionProgramacion.class));

        return lsVac;
    }

    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen) {

        String sql = "select " +
                "iexcodtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iexnomtra, " +
                "to_char(iexfecing,'dd/mm/yyyy') as fecing, " +
                "to_char(iexfecing,'dd/mm/yyyy') as iexfecing " +
                "from iexempleado " +
                "where iexcodcia = :codcia and " +
                "iexflgest = '1' and " +
                "iexreglab = :regimen " +
                "order by 2,3,4 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("regimen", regimen);

        List<Empleado> lsEmpleado = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Empleado.class));

        return lsEmpleado;
    }


    public VacacionProgramacion getVacacionPrg(VacacionProgramacion vacprg) {

        String sql = "select " +
                "v.iexcodcia, " +
                "v.iexcodtra, " +
                "v.iexcorrel, " +
                "to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "v.iexnrodias, v.iextipvac, d.desdet as destipvac, " +
                "v.iexglosa, v.iexpermesini, v.iexpermesfin, v.iexusucrea, " +
                "to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, " +
                "to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacprg v, " +
                "   ( " +
                "   select  iexkey, desdet from iexttabled where iexcodtab='56' " +
                "   ) d " +
                "where v.iexcodcia = :codcia and " +
                "v.iexcodtra = :codtra and " +
                "v.iexcorrel = :iexcorrel and " +
                "v.iextipvac = d.iexkey ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", vacprg.getIexcodcia())
                .addValue("codtra", vacprg.getIexcodtra())
                .addValue("iexcorrel", vacprg.getIexcorrel());

        VacacionProgramacion vac = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionProgramacion.class));

        return vac;
    }


    public List<VacacionControl> listaSaldoVacTra(Integer codcia, String regimen, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexpermesini, " +
                "iexpermesfin, " +
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexdiasgan, iexdiasgoz, iexdiasven, iexdiasper, " +
                "iexdiascom, iexdiassaldo, iexusucrea, " +
                "to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea, " +
                "iexusumod, to_char(iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacctl " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexdiasgan > 0 " +
                "order by iexpermesini desc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra);

        List<VacacionControl> lsVac = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionControl.class));

        return lsVac;
    }


    public void actualizarVacacionPrg(VacacionProgramacion vacprg) {

        String sql = "update iexvacprg " +
                "set iexfecini = to_date(?, 'DD/MM/YYYY'), " +
                "iexfecfin = to_date(?, 'DD/MM/YYYY'), " +
                "iexnrodias = ?, " +
                "iextipvac = ?, " +
                "iexglosa = ?, " +
                "iexpermesini = ?, " +
                "iexpermesfin = ?, " +
                "iexusucrea = ?, " +
                "iexfecmod = current_date " +
                "where iexcodcia = ? and " +
                "iexcodtra = ? and " +
                "iexcorrel = ? ";

        jdbc.update(sql,
                vacprg.getIexfecini(),
                vacprg.getIexfecfin(),
                vacprg.getIexnrodias(),
                vacprg.getIextipvac(),
                vacprg.getIexglosa(),
                vacprg.getIexpermesini(),
                vacprg.getIexpermesfin(),
                vacprg.getIexusucrea(),
                vacprg.getIexcodcia(),
                vacprg.getIexcodtra(),
                vacprg.getIexcorrel()
        );
    }

    public void eliminarVacacionPrg(VacacionProgramacion vacprg) {

        String sql = "delete from iexvacprg " +
                "where iexcodcia=? and " +
                "iexcodtra=? and " +
                "iexcorrel= ? ";

        jdbc.update(sql,
                vacprg.getIexcodcia(),
                vacprg.getIexcodtra(),
                vacprg.getIexcorrel()
        );
    }

}
