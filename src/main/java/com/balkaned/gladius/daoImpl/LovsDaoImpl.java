package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.LovsDao;
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
@Repository("LovsDao")
public class LovsDaoImpl implements LovsDao {

    private static final String CLASS_NAME = "LovsDao";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private JdbcTemplate jdbc;

    @Autowired
    public void setDataSource(DataSource datasource) {
        jdbc = new JdbcTemplate(datasource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(datasource);
    }

    public List<Lovs> getLovs(String id_table, String text) {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,100)) desLov " +
                "from iexttabled " +
                "where iexcodtab = :id_table and " +
                "'%'||desdet||'%' like '%'||:text||'%' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_table", id_table)
                .addValue("text", text);

        List<Lovs> lsLov = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLov;
    }

    public List<RegimenLaboral> getRegimenLab() {

        String sql = "select " +
                "codregimen as idRegimenLab, " +
                "desregimen as desRegimenLab " +
                "from iexregimenlab ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<RegimenLaboral> lsReg = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(RegimenLaboral.class));

        return lsReg;
    }

    public List<Concepto> getConceptoxProc(Integer id_proc) {

        String sql = "select " +
                "PROCODPRO as idProceso, " +
                "COOCODCON as codConcepto, " +
                "COODESCON as desConcepto, " +
                "COOCODFORVAR as desVariable, " +
                "COODESABREV as desAbreviacion, " +
                "COODESCRIPCION as descripcion " +
                "from iexproxconcepto " +
                "inner join iexconcepto on procodcon = coocodcon " +
                "where procodpro = :id_proc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_proc", id_proc);

        List<Concepto> lsConcepto = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcepto;
    }

    public List<Area> getAreaCia(Integer id_cia) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodarea, " +
                "iexdesarea " +
                "from iexarea " +
                "where iexcodcia = :id_cia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_cia", id_cia);

        List<Area> lsArea = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Area.class));

        return lsArea;
    }

    public List<Puesto> getPuestoCia(Integer id_cia) {

        String sql = "select " +
                "iexcodcia, " +
                "iexpuesto, " +
                "iexdespuesto " +
                "from iexpuesto " +
                "where iexcodcia = :id_cia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_cia", id_cia);

        List<Puesto> lsPuesto = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Puesto.class));

        return lsPuesto;
    }

    public List<CentroCosto> getCCostoCia(Integer id_cia) {

        String sql = "select " +
                "iexcodcia, " +
                "iexccosto, " +
                "iexdesccosto " +
                "from iexccosto " +
                "where iexcodcia = :id_cia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_cia", id_cia);

        List<CentroCosto> lsCentroC = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(CentroCosto.class));

        return lsCentroC;
    }

    public List<Local> getUbicacionCia(Integer id_cia) {

        String sql = "select " +
                "iexcodcia, " +
                "iexubicod, " +
                "iexubides " +
                "from iexubicacion " +
                "where iexcodcia = :id_cia ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_cia", id_cia);

        List<Local> lsLocal = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Local.class));

        return lsLocal;
    }

    public List<Ubigeo> getUbigeo(String text_buscar) {

        String sql = "select " +
                "d.iddep as iddepartamento, " +
                "d.desdep as desdepartamento, " +
                "p.idprov as idprovincia, " +
                "p.desprov, " +
                "iexkey iddistrito, " +
                "desdet desdistrito " +
                "from iexttabled f, " +
                "   (SELECT iexkey iddep, desdet desdep FROM IEXTTABLED WHERE IEXCODTAB='48') d, " +
                "   (SELECT iexkey idprov , desdet desprov FROM IEXTTABLED WHERE IEXCODTAB='49') p " +
                "where substring(f.iexkey,1,2) = d.iddep and " +
                "substring(f.iexkey,1,4) = p.idprov and " +
                "iexcodtab = '28' and " +
                "'%'||d.iddep||'%'||p.idprov||'%'||iexkey||'%'||d.desdep||'%'||p.desprov||'%'||desdet||'%' " +
                "like '%'||:text_buscar||'%' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("text_buscar", text_buscar);

        List<Ubigeo> lsUbigeo = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Ubigeo.class));

        return lsUbigeo;
    }

    public List<Lovs> getRegimenProc() {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab = '33' and " +
                "iexkey in (select procodregimenlab from iexprocesos) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Lovs> lovs = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lovs;
    }

    public List<ProcesoPlanilla> getProxRegimen(String regimen) {

        String sql = "select " +
                "procodpro as idProceso, " +
                "prodespro as desProceso " +
                "from iexprocesos " +
                "where procodregimenlab = :regimen " +
                "order by 2 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("regimen", regimen);

        List<ProcesoPlanilla> lsPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPlanilla.class));

        return lsPro;
    }

    public List<ProcesoPeriodo> getPerxproc(Integer codcia, String proceso) {

        String sql = "select " +
                "iexnroper, " +
                "iexpermes, " +
                "iexfecini, " +
                "iexfecfin " +
                "from iexproperiodo " +
                "where iexcodcia = :codcia and " +
                "iexcodpro = :proceso " +
                "order by iexnroper asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("proceso", Integer.parseInt(proceso));

        List<ProcesoPeriodo> lsPro = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPeriodo.class));

        return lsPro;
    }

    public List<Lovs> getRegimenProcGrppla(String grppla) {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab = '33' and iexkey in ( " +
                "  select procodregimenlab from iexprocesos where progrppro = :grppla " +
                " ) ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("grppla", grppla);


        List<Lovs> lsLovs = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLovs;
    }

    public List<ProcesoPlanilla> getProxRegimenGrppla(String regimen, String grppla) {

        String sql = "select " +
                "procodpro as idProceso, " +
                "prodespro as desProceso " +
                "from iexprocesos " +
                "where procodregimenlab = :regimen and " +
                "progrppro = :grppla " +
                "order by 2 asc ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("regimen", regimen)
                .addValue("grppla", grppla);

        List<ProcesoPlanilla> lsProc = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(ProcesoPlanilla.class));

        return lsProc;
    }

    public List<Concepto> getConceptoLov() {

        String sql = "select " +
                "COOCODCON as codConcepto, " +
                "COODESCON as desConcepto, " +
                "COOCODFORVAR as desVariable, " +
                "COODESABREV as desAbreviacion, " +
                "COODESCRIPCION as descripcion " +
                "from iexconcepto ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Concepto> lsConcept = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Concepto.class));

        return lsConcept;
    }

    public List<Lovs> getLovsDept(String id_table, String id_pais) {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab = '48' and " +
                "des1det = :id_pais ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_pais", id_pais);

        List<Lovs> lsLovs = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLovs;
    }

    public List<Lovs> getLovsProv(String id_table, String id_dept) {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab = '49' and " +
                "des1det = :id_dept ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("id_dept", id_dept);

        List<Lovs> lsLovs = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLovs;
    }

    public List<Lovs> getLovsDist(String id_table, String id_prov) {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab='28' and " +
                "des2det = :id_prov ";

        SqlParameterSource namedParemeters = new MapSqlParameterSource()
                .addValue("id_prov", id_prov);

        List<Lovs> lsLovs = namedParameterJdbcTemplate.query(sql, namedParemeters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLovs;
    }

    public List<VacacionControl> getSaldoVacTra(Integer codcia, Integer codtra, String pervac) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexpermesini, " +
                "iexpermesfin, " +
                "iexdiassaldo " +
                "from iexvacctl " +
                "where iexcodcia = :codcia and " +
                "iexcodtra = :codtra and " +
                "iexpermesini = :pervac ";

        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("codcia", codcia)
                .addValue("codtra", codtra)
                .addValue("pervac", pervac);

        List<VacacionControl> lsVacControl = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(VacacionControl.class));

        return lsVacControl;
    }


    public List<VacacionControl> listaSaldoVacTra(Integer codcia, String regimen, Integer codtra) {

        String sql = "select " +
                "iexcodcia, " +
                "iexcodtra, " +
                "iexpermesini, " +
                "iexpermesfin, " +
                "to_char(iexfecini,'DD/MM/YYYY') as iexfecini, " +
                "to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin, " +
                "iexdiasgan, iexdiasgoz, iexdiasven, " +
                "iexdiasper, iexdiascom, iexdiassaldo, " +
                "iexusucrea, to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea, " +
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

    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen) {

        String sql = "select " +
                "iexcodtra, " +
                "iexapepat, " +
                "iexapemat, " +
                "iexnomtra, " +
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

    public List<Lovs> getLovsCContables() {

        String sql = "select " +
                "iexkey idLov, " +
                "trim(substring(desdet,1,40)) desLov " +
                "from iexttabled " +
                "where iexcodtab = '65' ";

        SqlParameterSource namedParameters = new MapSqlParameterSource();

        List<Lovs> lsLovs = namedParameterJdbcTemplate.query(sql, namedParameters,
                BeanPropertyRowMapper.newInstance(Lovs.class));

        return lsLovs;
    }
}
