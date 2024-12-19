package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.models.DerechoHabiente;
import com.balkaned.gladius.dao.DerechoHabientesDao;
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

@Repository("DerechoHabientesDao")
@Slf4j
public class DerechoHabientesDaoImpl implements DerechoHabientesDao {

    private static final String CLASS_NAME = "DerechoHabientesDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<DerechoHabiente> listar(Integer codcia, Integer codtra) {

        String sql = " select " +
                "d.iexcodcia, " +
                "d.iexcodtra, " +
                "d.iexcoddep, " +
                "d.iextipnroiddep, " +
                "j.desdet destipnroiddep, " +
                "d.iexnroiddep, " +
                "d.iexpaisemisor, " +
                "to_char(d.iexfecnac,'dd/mm/yyyy') iexfecnac, " +
                "d.iexapepatdep, " +
                "d.iexapematdep, " +
                "d.iexnomdep, " +
                "d.iexsexo, " +
                "d.iextipvinculo, " +
                "g.desdet as destipvinculo, " +
                "d.iextipdocacredit, " +
                "d.iexnrodocacredit, " +
                "d.iexmesconcep, " +
                "d.iextipvia_dom1, " +
                "d.iexnomvia_dom1, " +
                "d.iexnrovia_dom1, " +
                "d.iexdeptin_dom1, " +
                "d.iexinterior_dom1, " +
                "d.iexmanzana_dom1, " +
                "d.iexlote_dom1, " +
                "d.iexkilometro_dom1, " +
                "d.iexblock_dom1, " +
                "d.iexetapa_dom1, " +
                "d.iextipzona_dom1, " +
                "d.iexnomzona_dom1,  " +
                "d.iexreferencia_dom1, " +
                "d.iexubigeo_dom1, " +
                "d.iextipvia_dom2, " +
                "d.iexnomvia_dom2, " +
                "d.iexnrovia_dom2, " +
                "d.iexdeptin_dom2, " +
                "d.iexinterior_dom2, " +
                "d.iexmanzana_dom2, " +
                "d.iexlote_dom2, " +
                "d.iexkilometro_dom2, " +
                "d.iexblock_dom2, " +
                "d.iexetapa_dom2, " +
                "d.iextipzona_dom2, " +
                "d.iexnomzona_dom2, " +
                "d.iexreferencia_dom2, " +
                "d.iexubigeo_dom2, " +
                "d.iexcenasis, " +
                "d.iexcodlar, " +
                "d.iexnrotelf, " +
                "d.iexemail , " +
                "  d.iexnacion_origen1, " +
                "    d.iexdepart_origen1 , " +
                "    d.iexprovin_origen1 , " +
                "    d.iexnacion_origen2 , " +
                "    d.iexdepart_origen2 , " +
                "    d.iexprovin_origen2 " +
                "from iexempderhab d, iexempleado p , (select  iexkey, desdet from iexttabled where iexcodtab='19') g,  " +
                "(select  iexkey, desdet from iexttabled where iexcodtab='3') j " +
                " where " +
                "d.iexcodcia = p.iexcodcia and  " +
                "d.iexcodtra = p.iexcodtra  and " +
                "d.iextipvinculo = g.iexkey and " +
                "d.iextipnroiddep = j.iexkey  and " +
                "d.iexcodcia = :codcia " +
                "d.iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra);

        List<DerechoHabiente> lsDerHab = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DerechoHabiente.class));

        return lsDerHab;
    }

    public Integer validarDerhabiente(DerechoHabiente derhab) {

        String sql = "select " +
                "d.iexcodcia, " +
                "d.iexcodtra, " +
                "d.iexcoddep, " +
                "d.iextipnroiddep, " +
                "j.desdet destipnroiddep, " +
                "d.iexnroiddep, " +
                "d.iexpaisemisor, " +
                "d.iexfecnac, " +
                "d.iexapepatdep, " +
                "d.iexapematdep, " +
                "d.iexnomdep " +
                "from iexempderhab d, iexempleado p , (select  iexkey, desdet from iexttabled where iexcodtab='19') g,  " +
                "(select  iexkey, desdet from iexttabled where iexcodtab='3') j " +
                "where " +
                "d.iexcodcia = p.iexcodcia and  " +
                "d.iexcodtra = p.iexcodtra  and " +
                "d.iextipvinculo = g.iexkey and " +
                "d.iextipnroiddep = j.iexkey  and " +
                "d.iexcodcia = :codcia and " +
                "d.iexcodtra = :codtra and " +
                "d.iextipnroiddep = ':iextipnroiddep' and " +
                "d.iexnroiddep = ':iexnroiddep' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", derhab.getIexcodcia())
                .addValue("codtra", derhab.getIexcodtra())
                .addValue("iextipnroiddep", derhab.getIextipnroiddep())
                .addValue("iexnroiddep", derhab.getIexnroiddep());

        List<DerechoHabiente> lsDerHab = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DerechoHabiente.class));

        return lsDerHab.size();
    }

    public Integer getIdDerechoHab(DerechoHabiente derhab) {

        final Integer[] idfinal = {0};

        String sql = "select coalesce(max(iexcoddep),0)+1 as idex " +
                "from iexempderhab " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", derhab.getIexcodcia())
                .addValue("codtra", derhab.getIexcodtra());

        return namedParameterJdbcTemplate.queryForObject(sql, namedParameters, Integer.class);
    }

    public void insertar(DerechoHabiente derhab) {

        String sql = "insert into iexempderhab( " +
                " iexcodcia,           iexcodtra,         	iexcoddep,         iextipnroiddep,      iexnroiddep, " +
                " iexpaisemisor,       iexfecnac,         	iexapepatdep,      iexapematdep,        iexnomdep, " +
                " iexsexo,             iextipvinculo,     	iextipdocacredit,  iexnrodocacredit,    iexmesconcep, " +
                " iextipvia_dom1,      iexnomvia_dom1,    	iexnrovia_dom1, " +
                " iexdeptin_dom1,      iexinterior_dom1,  	iexmanzana_dom1, " +
                " iexlote_dom1,        iexkilometro_dom1, 	iexblock_dom1, " +
                " iexetapa_dom1,       iextipzona_dom1,   	iexnomzona_dom1, " +
                " iexreferencia_dom1,  iexubigeo_dom1, " +
                " iextipvia_dom2,      iexnomvia_dom2,        iexnrovia_dom2,    iexdeptin_dom2, " +
                " iexinterior_dom2,    iexmanzana_dom2,       iexlote_dom2,      iexkilometro_dom2, " +
                " iexblock_dom2,       iexetapa_dom2,         iextipzona_dom2,   iexnomzona_dom2, " +
                " iexreferencia_dom2,  iexubigeo_dom2,         " +
                " iexcenasis,          iexcodlar,             iexnrotelf,        iexemail , " +
                " iexnacion_origen1, " +
                " iexdepart_origen1, " +
                " iexprovin_origen1, " +
                " iexnacion_origen2, " +
                " iexdepart_origen2, " +
                " iexprovin_origen2 " +
                " ) values " +
                " (   ? ,  ? , ?,   ?,   ?,  " +
                " ? ,  to_date(?,'DD/MM/YYYY'),  ?,   ?,   ?,  " +
                " ? ,  ?,  ?,   ?,   ?,   " +
                " ?,   ?,  ?  , " +
                " ?,   ?,  ?  , " +
                " ?,   ?,  ?  , " +
                " ?,   ?,  ?  , " +
                " ?,   ?,            " +
                " ?,   ?,  ?  , ? ,  " +
                " ?,   ?,  ?  , ? ,  " +
                " ?,   ?,  ?  , ? ,  " +
                " ?,   ?,             " +
                " ?,   ?,  ?  , ? ,   " +
                " ?, ?, ?, ?, ?, ? ) ";

        jdbc.update(sql,
                derhab.getIexcodcia(),
                derhab.getIexcodtra(),
                derhab.getIexcoddep(),
                derhab.getIextipnroiddep(),
                derhab.getIexnroiddep(),
                derhab.getIexpaisemisor(),
                derhab.getIexfecnac(),
                derhab.getIexapepatdep(),
                derhab.getIexapematdep(),
                derhab.getIexnomdep(),
                derhab.getIexsexo(),
                derhab.getIextipvinculo(),
                derhab.getIextipdocacredit(),
                derhab.getIexnrodocacredit(),
                derhab.getIexmesconcep(),
                derhab.getIextipvia_dom1(),
                derhab.getIexnomvia_dom1(),
                derhab.getIexnrovia_dom1(),
                derhab.getIexdeptin_dom1(),
                derhab.getIexinterior_dom1(),
                derhab.getIexmanzana_dom1(),
                derhab.getIexlote_dom1(),
                derhab.getIexkilometro_dom1(),
                derhab.getIexblock_dom1(),
                derhab.getIexetapa_dom1(),
                derhab.getIextipzona_dom1(),
                derhab.getIexnomzona_dom1(),
                derhab.getIexreferencia_dom1(),
                derhab.getIexubigeo_dom1(),
                derhab.getIextipvia_dom2(),
                derhab.getIexnomvia_dom2(),
                derhab.getIexnrovia_dom2(),
                derhab.getIexdeptin_dom2(),
                derhab.getIexinterior_dom2(),
                derhab.getIexmanzana_dom2(),
                derhab.getIexlote_dom2(),
                derhab.getIexkilometro_dom2(),
                derhab.getIexblock_dom2(),
                derhab.getIexetapa_dom2(),
                derhab.getIextipzona_dom2(),
                derhab.getIexnomzona_dom2(),
                derhab.getIexreferencia_dom2(),
                derhab.getIexubigeo_dom2(),
                derhab.getIexcenasis(),
                derhab.getIexcodlar(),
                derhab.getIexnrotelf(),
                derhab.getIexemail(),
                derhab.getIexnacion_origen1(),
                derhab.getIexdepart_origen1(),
                derhab.getIexprovin_origen1(),
                derhab.getIexnacion_origen2(),
                derhab.getIexdepart_origen2(),
                derhab.getIexprovin_origen2());
    }

    public DerechoHabiente recuperar(DerechoHabiente derhab) {

        String sql = "select " +
                "d.iexcodcia, " +
                "d.iexcodtra, " +
                "d.iexcoddep, " +
                "d.iextipnroiddep, " +
                "j.desdet destipnroiddep, " +
                "d.iexnroiddep, " +
                "d.iexpaisemisor, " +
                "to_char(d.iexfecnac,'DD/MM/YYYY') iexfecnac, " +
                "d.iexapepatdep, " +
                "d.iexapematdep, " +
                "d.iexnomdep, " +
                "d.iexsexo, " +
                "d.iextipvinculo, " +
                "g.desdet as destipvinculo, " +
                "d.iextipdocacredit, " +
                "d.iexnrodocacredit, " +
                "d.iexmesconcep, " +
                "d.iextipvia_dom1, " +
                "d.iexnomvia_dom1, " +
                "d.iexnrovia_dom1, " +
                "d.iexdeptin_dom1, " +
                "d.iexinterior_dom1, " +
                "d.iexmanzana_dom1, " +
                "d.iexlote_dom1, " +
                "d.iexkilometro_dom1, " +
                "d.iexblock_dom1, " +
                "d.iexetapa_dom1, " +
                "d.iextipzona_dom1, " +
                "d.iexnomzona_dom1,  " +
                "d.iexreferencia_dom1, " +
                "d.iexubigeo_dom1, " +
                "d.iextipvia_dom2, " +
                "d.iexnomvia_dom2, " +
                "d.iexnrovia_dom2, " +
                "d.iexdeptin_dom2, " +
                "d.iexinterior_dom2, " +
                "d.iexmanzana_dom2, " +
                "d.iexlote_dom2, " +
                "d.iexkilometro_dom2, " +
                "d.iexblock_dom2, " +
                "d.iexetapa_dom2, " +
                "d.iextipzona_dom2, " +
                "d.iexnomzona_dom2, " +
                "d.iexreferencia_dom2, " +
                "d.iexubigeo_dom2, " +
                "d.iexcenasis, " +
                "d.iexcodlar, " +
                "d.iexnrotelf, " +
                "d.iexemail, " +
                "d.iexnacion_origen1, " +
                "d.iexdepart_origen1, " +
                "d.iexprovin_origen1, " +
                "d.iexnacion_origen2, " +
                "d.iexdepart_origen2, " +
                "d.iexprovin_origen2 " +
                "from iexempderhab d, iexempleado p, (select  iexkey, desdet from iexttabled where iexcodtab='19') g, " +
                "(select  iexkey, desdet from iexttabled where iexcodtab='3') j " +
                "where " +
                "d.iexcodcia = p.iexcodcia and " +
                "d.iexcodtra = p.iexcodtra  and " +
                "d.iextipvinculo = g.iexkey and " +
                "d.iextipnroiddep = j.iexkey  and " +
                "d.iexcodcia = :codcia and " +
                "d.iexcodtra = :codtra and " +
                "d.iexcoddep = :coddep ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", derhab.getIexcodcia())
                .addValue("codtra", derhab.getIexcodtra())
                .addValue("coddep", derhab.getIexcoddep());

        DerechoHabiente derHab = namedParameterJdbcTemplate.queryForObject(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(DerechoHabiente.class));

        return derHab;
    }

    public void actualizar(DerechoHabiente derhab) {

        String sql = "update iexempderhab set " +
                " iextipnroiddep =?,     iexnroiddep =?, " +
                " iexpaisemisor =?,      iexfecnac =to_date(?,'DD/MM/YYYY'),         	iexapepatdep =?,      iexapematdep =?,        iexnomdep =?, " +
                " iexsexo=?,             iextipvinculo=?,     	iextipdocacredit=?,  iexnrodocacredit=?,    iexmesconcep=?, " +
                " iextipvia_dom1=?,      iexnomvia_dom1=?,    	iexnrovia_dom1=?, " +
                " iexdeptin_dom1=?,      iexinterior_dom1=?,  	iexmanzana_dom1=?, " +
                " iexlote_dom1=?,        iexkilometro_dom1=?, 	iexblock_dom1=?, " +
                " iexetapa_dom1=?,       iextipzona_dom1=?,   	iexnomzona_dom1=?, " +
                " iexreferencia_dom1=?,  iexubigeo_dom1=?, " +
                " iextipvia_dom2=?,      iexnomvia_dom2=?,        iexnrovia_dom2=?,    iexdeptin_dom2=?, " +
                " iexinterior_dom2=?,    iexmanzana_dom2=?,       iexlote_dom2=?,      iexkilometro_dom2=?, " +
                " iexblock_dom2=?,       iexetapa_dom2=?,         iextipzona_dom2=?,   iexnomzona_dom2=?, " +
                " iexreferencia_dom2=?,  iexubigeo_dom2=?,         " +
                " iexcenasis=?,          iexcodlar=?,             iexnrotelf=?,        iexemail=?  , " +
                " iexnacion_origen1 =?, " +
                " iexdepart_origen1 =?, " +
                " iexprovin_origen1 =?, " +
                " iexnacion_origen2 =?, " +
                " iexdepart_origen2 =?, " +
                " iexprovin_origen2 =? " +
                " where iexcodcia=? and iexcodtra=? and iexcoddep=? ";

        jdbc.update(sql,
                derhab.getIextipnroiddep(),
                derhab.getIexnroiddep(),
                derhab.getIexpaisemisor(),
                derhab.getIexfecnac(),
                derhab.getIexapepatdep(),
                derhab.getIexapematdep(),
                derhab.getIexnomdep(),
                derhab.getIexsexo(),
                derhab.getIextipvinculo(),
                derhab.getIextipdocacredit(),
                derhab.getIexnrodocacredit(),
                derhab.getIexmesconcep(),
                derhab.getIextipvia_dom1(),
                derhab.getIexnomvia_dom1(),
                derhab.getIexnrovia_dom1(),
                derhab.getIexdeptin_dom1(),
                derhab.getIexinterior_dom1(),
                derhab.getIexmanzana_dom1(),
                derhab.getIexlote_dom1(),
                derhab.getIexkilometro_dom1(),
                derhab.getIexblock_dom1(),
                derhab.getIexetapa_dom1(),
                derhab.getIextipzona_dom1(),
                derhab.getIexnomzona_dom1(),
                derhab.getIexreferencia_dom1(),
                derhab.getIexubigeo_dom1(),
                derhab.getIextipvia_dom2(),
                derhab.getIexnomvia_dom2(),
                derhab.getIexnrovia_dom2(),
                derhab.getIexdeptin_dom2(),
                derhab.getIexinterior_dom2(),
                derhab.getIexmanzana_dom2(),
                derhab.getIexlote_dom2(),
                derhab.getIexkilometro_dom2(),
                derhab.getIexblock_dom2(),
                derhab.getIexetapa_dom2(),
                derhab.getIextipzona_dom2(),
                derhab.getIexnomzona_dom2(),
                derhab.getIexreferencia_dom2(),
                derhab.getIexubigeo_dom2(),
                derhab.getIexcenasis(),
                derhab.getIexcodlar(),
                derhab.getIexnrotelf(),
                derhab.getIexemail(),
                derhab.getIexnacion_origen1(),
                derhab.getIexdepart_origen1(),
                derhab.getIexprovin_origen1(),
                derhab.getIexnacion_origen2(),
                derhab.getIexdepart_origen2(),
                derhab.getIexprovin_origen2(),
                derhab.getIexcodcia(),
                derhab.getIexcodtra(),
                derhab.getIexcoddep());
    }

    public void eliminar(DerechoHabiente derhab) {

        String sql = "delete from iexempderhab " +
                "where iexcodcia=? and iexcodtra=? and iexcoddep=? ";

        jdbc.update(sql,
                derhab.getIexcodcia(),
                derhab.getIexcodtra(),
                derhab.getIexcoddep());
    }

}
