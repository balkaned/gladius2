package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.AusentismoDao;
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
@Repository("AusentismoDao")
public class AusentismoDaoImpl implements AusentismoDao {

    private static final String CLASS_NAME = "AusentismoDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado) {

        String sql = "select v.iexcodcia, " +
                "v.iexcodtra, v.iexcorrel, " +
                "to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "v.iexnrodias, v.iextipaus, d.desdet as destipaus, v.iexglosa, " +
                "v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, " +
                "to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexausprg v, " +
                "  ( " +
                "  select  iexkey, desdet from iexttabled where iexcodtab='57' " +
                "  ) d " +
                "where v.iexcodcia= :iexcodcia and " +
                "v.iexcodtra= :iexcodtra and " +
                "v.iextipaus = d.iexkey ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", empleado.getIexcodcia())
                .addValue("iexcodtra", empleado.getIexcodtra());

        List<AusentismoProgramacion> lsAusentProgram = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(AusentismoProgramacion.class));

        return lsAusentProgram;
    }

    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = "select coalesce(max(iexcorrel),0)+1 as idex " +
                "from iexausprg " +
                "where iexcodcia = :iexcodcia and " +
                "iexcodtra = :iexcodtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", ausprg.getIexcodcia())
                .addValue("iexcodtra", ausprg.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin, Integer iexcorrel) {

        String sql = "select sum(e.dias) dias " +
                "from ( " +
                "   select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia= :iexcodcia and " +
                "   iexcodtra= :iexcodtra and to_date(:fecini,'dd/mm/yyyy')  >= iexfecini  and " +
                "   to_date(:fecini,'dd/mm/yyyy')  <= iexfecfin and iexcorrel <> :iexcorrel " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia= :iexcodcia and " +
                "   iexcodtra= :iexcodtra and to_date(:fecfin,'dd/mm/yyyy')  >= iexfecini  and " +
                "   to_date(:fecfin,'dd/mm/yyyy')  <= iexfecfin and iexcorrel <> :iexcorrel " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia= :iexcodcia and " +
                "   iexcodtra= :iexcodtra and to_date(:fecini,'dd/mm/yyyy')  <= iexfecini  and " +
                "   to_date(:fecfin,'dd/mm/yyyy')  >= iexfecini  and iexcorrel <> :iexcorrel " +
                "       union " +
                "   select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia= :iexcodcia and " +
                "   iexcodtra= :iexcodtra and to_date(:fecini,'dd/mm/yyyy')  <= iexfecfin  and " +
                "   to_date(:fecfin,'dd/mm/yyyy')  >= iexfecfin  and iexcorrel <> :iexcorrel " +
                "       union  " +
                "   select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia= :iexcodcia and " +
                "   iexcodtra= :iexcodtra and to_date(:fecini,'dd/mm/yyyy')  <= iexfecini  and " +
                "   to_date(:fecfin,'dd/mm/yyyy')  >= iexfecfin and iexcorrel <> :iexcorrel " +
                " ) e ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", codcia)
                .addValue("iexcodtra", codtra)
                .addValue("fecini", fecini)
                .addValue("fecfin", fecfin)
                .addValue("iexcorrel", iexcorrel);

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertarAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = "insert into iexausprg ( " +
                "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias, " +
                "iextipaus, iexglosa, iexusucrea, iexfeccrea) values ( " +
                " ?, ?, ?, to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), ?, " +
                " ?, ?, ?, current_date) ";

        jdbc.update(sql,
                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel(),
                ausprg.getIexfecini(),
                ausprg.getIexfecfin(),
                ausprg.getIexnrodias(),
                ausprg.getIextipaus(),
                ausprg.getIexglosa(),
                ausprg.getIexusucrea()
        );
    }


    public List<AusentismoProgramacion> listaAusentismoGen(Integer codcia, String regimen, String fecini,
                                                           String fecfin, Integer codtra) {

        String sql = "select " +
                "c.iexcodcia, " +
                "c.iexcodtra, " +
                "c.iexapepat ||' '|| c.iexapemat||' '|| c.iexnomtra nomtra, " +
                "case " +
                "   when iexflgest='1' then 'activo' " +
                "   when iexflgest='0' then 'inactivo' " +
                "   else 'inactivo' end desestado, " +
                "to_char(c.iexfecing,'dd/mm/yyyy') fecing, " +
                "c.iexnrodoc, " +
                "a.iextipaus, " +
                "a.iexcorrel as aus_id, " +
                "to_char(a.iexfecini,'dd/mm/yyyy') iexfecini, " +
                "to_char(a.iexfecfin,'dd/mm/yyyy') iexfecfin, " +
                "case " +
                "   when (a.iexfecini >=to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecini <= to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin <= to_date(' :fecfin ','dd/mm/yyyy')) " +
                "       then (a.iexfecfin -  a.iexfecini) +1 " +
                "   when (a.iexfecini < to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin <= to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then (a.iexfecfin -  to_date(' :fecini ','dd/mm/yyyy')) +1 " +
                "	when (a.iexfecini >= to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecini <= to_date(' :fecfin ','dd/mm/yyyy') and " +
                "       a.iexfecfin >  to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then ( to_date(':fecfin','dd/mm/yyyy') - a.iexfecini) +1 " +
                "	when (a.iexfecini < to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin >  to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then ( to_date(' :fecfin ','dd/mm/yyyy') - to_date(' :fecini ','dd/mm/yyyy')) +1 " +
                "end dias_aus, " +
                "case " +
                "   when (a.iexfecini >=to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecini <= to_date(' :fecfin ','dd/mm/yyyy') and " +
                "       a.iexfecfin <= to_date(' :fecfin ','dd/mm/yyyy'))" +
                "	    then   to_char(a.iexfecfin,'dd/mm/yyyy') " +
                "   when (a.iexfecini < to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin <= to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then  to_char(a.iexfecfin,'dd/mm/yyyy') " +
                "	when (a.iexfecini >= to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecini <= to_date(' :fecfin ','dd/mm/yyyy') and " +
                "       a.iexfecfin >  to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then ' :fecfin ' " +
                "   when (a.iexfecini < to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin >  to_date(' :fecfin ','dd/mm/yyyy')) " +
                "	    then ' :fecfin ' " +
                "end  fecfinrep, " +
                "k.des1det codcon, " +
                "k.desdet destipaus " +
                "from iexempleado c, " +
                "iexausprg a " +
                "	  full outer join ( " +
                "	  select iexkey, desdet, des1det " +
                "     from iexttabled where iexcodtab='57' " +
                "	  ) k on a.iextipaus = k.iexkey " +
                "	 where " +
                "	 c.iexcodcia = a.iexcodcia and " +
                "	 c.iexcodtra = a.iexcodtra and " +
                "	 c.iexcodcia= :codcia and c.iexreglab=' :regimen ' and " +
                "	 ( " +
                "	    (a.iexfecini >=to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecini <=to_date(' :fecfin ','dd/mm/yyyy')) " +
                "		or " +
                "	    (a.iexfecfin >=to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin <=to_date(' :fecfin ','dd/mm/yyyy')) " +
                "		or " +
                "		(a.iexfecini <to_date(' :fecini ','dd/mm/yyyy') and " +
                "       a.iexfecfin >to_date(' :fecfin ','dd/mm/yyyy')) " +
                " ) ";

        if (codtra != null && codtra.intValue() != 0) {
            sql += " and c.iexcodtra = :codtra ";
        }

        sql += " order by 3,4 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("regimen", regimen)
                .addValue("fecini", fecini)
                .addValue("fecfin", fecfin)
                .addValue("codtra", codtra);

        List<AusentismoProgramacion> lsAusen = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(AusentismoProgramacion.class));

        return lsAusen;
    }

    public AusentismoProgramacion getAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = "select v.iexcodcia, v.iexcodtra, v.iexcorrel, " +
                "to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "v.iexnrodias, v.iextipaus , d.desdet as destipaus, v.iexglosa, " +
                "v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, " +
                "v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexausprg v, " +
                " ( " +
                " select  iexkey, desdet from iexttabled where iexcodtab='57' " +
                " ) d " +
                "where " +
                "v.iexcodcia= :iexcodcia and " +
                "v.iexcodtra= :iexcodtra and " +
                "v.iexcorrel= :iexcorrel and " +
                "v.iextipaus = d.iexkey ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("iexcodcia", ausprg.getIexcodcia())
                .addValue("iexcodtra", ausprg.getIexcodtra())
                .addValue("iexcorrel", ausprg.getIexcorrel());

        AusentismoProgramacion ausen = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(AusentismoProgramacion.class));

        return ausen;
    }


    public void actualizarAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = "update iexausprg set " +
                "iexfecini=to_date(?,'DD/MM/YYYY'), iexfecfin=to_date(?,'DD/MM/YYYY'), iexnrodias=?, " +
                "iextipaus=?, iexglosa=?, iexusumod=?, iexfecmod=current_date " +
                "where iexcodcia=? and iexcodtra=? and iexcorrel=? ";

        jdbc.update(sql,
                ausprg.getIexfecini(),
                ausprg.getIexfecfin(),
                ausprg.getIexnrodias(),
                ausprg.getIextipaus(),
                ausprg.getIexglosa(),
                ausprg.getIexusumod(),
                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel()
        );
    }

    public void eliminarAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = "delete from iexausprg " +
                "where iexcodcia=? and iexcodtra=? and iexcorrel=? ";

        jdbc.update(sql,
                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel()
        );
    }
}
