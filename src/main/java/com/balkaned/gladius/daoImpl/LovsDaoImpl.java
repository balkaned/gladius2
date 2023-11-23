package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.AreaDao;
import com.balkaned.gladius.dao.LovsDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
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

@Repository("LovsDao")
public class LovsDaoImpl implements LovsDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Lovs> getLovs(String id_table, String text) {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                "from iexttabled where iexcodtab='" + id_table + "'  and '%'||desdet||'%' like '%'||'" + text + "'||'%' ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<RegimenLaboral> getRegimenLab() {

        List<RegimenLaboral> lista = null;
        String sql = " select " +
                "codregimen, " +
                "desregimen " +
                "from iexregimenlab ";

        return template.query(sql, new ResultSetExtractor<List<RegimenLaboral>>() {

            public List<RegimenLaboral> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<RegimenLaboral> lista = new ArrayList<RegimenLaboral>();

                while (rs.next()) {
                    RegimenLaboral p = new RegimenLaboral();

                    p.setIdRegimenLab(rs.getInt("codregimen"));
                    p.setDesRegimenLab(rs.getString("desregimen"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Concepto> getConceptoxProc(Integer id_proc) {

        List<Concepto> lista = null;
        String sql = " select  " +
                " PROCODPRO, " +
                " COOCODCON, " +
                " COODESCON, " +
                " COOCODFORVAR, " +
                " COODESABREV, " +
                " COODESCRIPCION  " +
                " from iexproxconcepto "
                + "   inner join iexconcepto on  procodcon=coocodcon " +
                " where " +
                " procodpro=" + id_proc + " ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Concepto>>() {

            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Concepto> lista = new ArrayList<Concepto>();

                while (rs.next()) {
                    Concepto p = new Concepto();

                    p.setIdProceso(rs.getInt("PROCODPRO"));
                    p.setCodConcepto(rs.getString("COOCODCON"));
                    p.setDesVariable(rs.getString("COOCODFORVAR"));
                    p.setDesConcepto(rs.getString("COODESCON"));
                    p.setDesAbreviacion(rs.getString("COODESABREV"));
                    p.setDescripcion(rs.getString("COODESCRIPCION"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Area> getAreaCia(Integer id_cia) {

        List<Area> lista = null;
        String sql = " select iexcodcia, iexcodarea, iexdesarea from iexarea where iexcodcia=" + id_cia + "";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Area>>() {

            public List<Area> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Area> lista = new ArrayList<Area>();

                while (rs.next()) {
                    Area p = new Area();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexdesarea(rs.getString("iexdesarea"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Puesto> getPuestoCia(Integer id_cia) {

        List<Puesto> lista = null;
        String sql = " select  iexcodcia, iexpuesto, iexdespuesto from iexpuesto  where iexcodcia=" + id_cia + "";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Puesto>>() {

            public List<Puesto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Puesto> lista = new ArrayList<Puesto>();

                while (rs.next()) {
                    Puesto p = new Puesto();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexpuesto(rs.getString("iexpuesto"));
                    p.setIexdespuesto(rs.getString("iexdespuesto"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<CentroCosto> getCCostoCia(Integer id_cia) {

        List<CentroCosto> lista = null;
        String sql = " select iexcodcia, iexccosto, iexdesccosto from iexccosto where iexcodcia=" + id_cia + "";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<CentroCosto>>() {

            public List<CentroCosto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<CentroCosto> lista = new ArrayList<CentroCosto>();

                while (rs.next()) {
                    CentroCosto p = new CentroCosto();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexccosto(rs.getString("iexccosto"));
                    p.setIexdesccosto(rs.getString("iexdesccosto"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Local> getUbicacionCia(Integer id_cia) {

        List<Local> lista = null;
        String sql = " select iexcodcia, iexubicod, iexubides from iexubicacion where iexcodcia=" + id_cia + "";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Local>>() {

            public List<Local> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Local> lista = new ArrayList<Local>();

                while (rs.next()) {
                    Local p = new Local();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexubicod(rs.getString("iexubicod"));
                    p.setIexubides(rs.getString("iexubides"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Ubigeo> getUbigeo(String text_buscar) {

        List<Ubigeo> lista = null;
        String sql = " SELECT " +
                "d.iddep, d.desdep," +
                "p.idprov,  p.desprov, " +
                "iexkey iddistri, desdet desdistri FROM IEXTTABLED f , " +
                "(SELECT iexkey iddep, desdet desdep FROM IEXTTABLED WHERE IEXCODTAB='48') d," +
                "(SELECT iexkey idprov , desdet desprov  FROM IEXTTABLED WHERE IEXCODTAB='49' ) p " +
                "WHERE " +
                "substring(f.iexkey,1,2) = d.iddep   and " +
                "substring(f.iexkey,1,4) = p.idprov and " +
                "IEXCODTAB='28' and '%'||d.iddep||'%'||p.idprov||'%'||iexkey||'%'||d.desdep||'%'||p.desprov||'%'||desdet||'%' like '%'||'" + text_buscar + "'||'%' ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Ubigeo>>() {

            public List<Ubigeo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Ubigeo> lista = new ArrayList<Ubigeo>();

                while (rs.next()) {
                    Ubigeo p = new Ubigeo();

                    p.setIddepartamento(rs.getString("iddep"));
                    p.setDesdepartamento(rs.getString("desdep"));
                    p.setIdprovincia(rs.getString("idprov"));
                    p.setDesprovincia(rs.getString("desprov"));
                    p.setIddistrito(rs.getString("iddistri"));
                    p.setDesdistrito(rs.getString("desdistri"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Lovs> getRegimenProc() {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                " from iexttabled where iexcodtab='33' and  iexkey in (  " +
                "  select procodregimenlab from iexprocesos " +
                ")";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ProcesoPlanilla> getProxRegimen(String regimen) {

        List<ProcesoPlanilla> lista = null;
        String sql = " select " +
                " procodpro, " +
                " prodespro " +
                " from iexprocesos where procodregimenlab='" + regimen + "' order by 2 asc ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<ProcesoPlanilla>>() {

            public List<ProcesoPlanilla> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPlanilla> lista = new ArrayList<ProcesoPlanilla>();

                while (rs.next()) {
                    ProcesoPlanilla p = new ProcesoPlanilla();

                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setDesProceso(rs.getString("prodespro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ProcesoPeriodo> getPerxproc(Integer codcia, String proceso) {

        List<ProcesoPeriodo> lista = null;
        //String sql = " select iexnroper, iexpermes, iexfecini, iexfecfin from iexproperiodo where iexcodcia="+codcia+" and iexcodpro="+proceso+" and flgestado not in ('3') order by iexnroper asc  ";
        String sql = " select iexnroper, iexpermes, iexfecini, iexfecfin from iexproperiodo where iexcodcia=" + codcia + " and iexcodpro=" + proceso + "   order by iexnroper asc  ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<ProcesoPeriodo>>() {

            public List<ProcesoPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPeriodo> lista = new ArrayList<ProcesoPeriodo>();

                while (rs.next()) {
                    ProcesoPeriodo p = new ProcesoPeriodo();

                    p.setIexnroper(rs.getString("iexnroper"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Lovs> getRegimenProcGrppla(String grppla) {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                " from iexttabled where iexcodtab='33' and  iexkey in (  " +
                "  select procodregimenlab from iexprocesos where progrppro ='" + grppla + "' " +
                ")";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ProcesoPlanilla> getProxRegimenGrppla(String regimen, String grppla) {

        List<ProcesoPlanilla> lista = null;
        String sql = " select " +
                " procodpro, " +
                " prodespro " +
                " from iexprocesos where procodregimenlab='" + regimen + "' and progrppro='" + grppla + "' order by 2 asc ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<ProcesoPlanilla>>() {

            public List<ProcesoPlanilla> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPlanilla> lista = new ArrayList<ProcesoPlanilla>();

                while (rs.next()) {
                    ProcesoPlanilla p = new ProcesoPlanilla();

                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setDesProceso(rs.getString("prodespro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Concepto> getConceptoLov() {

        List<Concepto> lista = null;
        String sql = " select  " +
                " COOCODCON, " +
                " COODESCON, " +
                " COOCODFORVAR, " +
                " COODESABREV, " +
                " COODESCRIPCION  " +
                " from  iexconcepto  ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Concepto>>() {

            public List<Concepto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Concepto> lista = new ArrayList<Concepto>();

                while (rs.next()) {
                    Concepto p = new Concepto();

                    p.setCodConcepto(rs.getString("COOCODCON"));
                    p.setDesVariable(rs.getString("COOCODFORVAR"));
                    p.setDesConcepto(rs.getString("COODESCON"));
                    p.setDesAbreviacion(rs.getString("COODESABREV"));
                    p.setDescripcion(rs.getString("COODESCRIPCION"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Lovs> getLovsDept(String id_table, String id_pais) {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                "from iexttabled where iexcodtab='48'  and des1det='" + id_pais + "' ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Lovs> getLovsProv(String id_table, String id_dept) {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                "from iexttabled where iexcodtab='49'  and des1det='" + id_dept + "' ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Lovs> getLovsDist(String id_table, String id_prov) {

        List<Lovs> lista = null;
        String sql = " select " +
                "iexkey cod, " +
                "trim(substring(desdet,1,40)) des " +
                "from iexttabled where iexcodtab='28'  and des2det='" + id_prov + "' ";

        //System.out.println(sql);

        return template.query(sql, new ResultSetExtractor<List<Lovs>>() {

            public List<Lovs> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Lovs> lista = new ArrayList<Lovs>();

                while (rs.next()) {
                    Lovs p = new Lovs();

                    p.setIdLov(rs.getString("cod"));
                    p.setDesLov(rs.getString("des"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<VacacionControl> getSaldoVacTra(Integer codcia, Integer codtra, String pervac) {

        List<VacacionControl> lista = null;
        String sql = " select  " +
                "iexcodcia, iexcodtra, iexpermesini, iexpermesfin,   iexdiassaldo " +
                "from iexvacctl " +
                "where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and  iexpermesini = '" + pervac + "' ";
        return template.query(sql, new ResultSetExtractor<List<VacacionControl>>() {

            public List<VacacionControl> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<VacacionControl> lista = new ArrayList<VacacionControl>();

                while (rs.next()) {
                    VacacionControl p = new VacacionControl();

                    p.setIexpermesini(rs.getString("iexpermesini"));
                    p.setIexpermesfin(rs.getString("iexpermesfin"));
                    p.setIexdiassaldo(rs.getDouble("iexdiassaldo"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }


    public List<VacacionControl> listaSaldoVacTra(Integer codcia, String regimen, Integer codtra) {

        List<VacacionControl> lista = null;
        String sql = " select  " +
                "iexcodcia, iexcodtra, iexpermesini, iexpermesfin, to_char(iexfecini,'DD/MM/YYYY') as iexfecini, to_char(iexfecfin,'DD/MM/YYYY') as iexfecfin,  " +
                "iexdiasgan, iexdiasgoz, iexdiasven, iexdiasper, iexdiascom, iexdiassaldo, " +
                "iexusucrea, to_char(iexfeccrea,'DD/MM/YYYY') as iexfeccrea,  " +
                "iexusumod, to_char(iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from iexvacctl " +
                "where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and iexdiasgan>0 order by iexpermesini desc  ";
        return template.query(sql, new ResultSetExtractor<List<VacacionControl>>() {

            public List<VacacionControl> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<VacacionControl> lista = new ArrayList<VacacionControl>();

                while (rs.next()) {
                    VacacionControl p = new VacacionControl();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexpermesini(rs.getString("iexpermesini"));
                    p.setIexpermesfin(rs.getString("iexpermesfin"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexdiasgan(rs.getDouble("iexdiasgan"));
                    p.setIexdiassaldo(rs.getDouble("iexdiassaldo"));
                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Empleado> listaTrabajadoresReg(Integer codcia, String regimen) {

        List<Empleado> lista = null;
        String sql = " select  " +
                "iexcodtra, " +
                "iexapepat, iexapemat, iexnomtra, " +
                " to_char(iexfecing,'dd/mm/yyyy') as fecing " +
                "from iexempleado where iexcodcia=" + codcia + " and iexflgest='1' and iexreglab='" + regimen + "' order by 2,3,4 asc ";

        return template.query(sql, new ResultSetExtractor<List<Empleado>>() {

            public List<Empleado> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Empleado> lista = new ArrayList<Empleado>();

                while (rs.next()) {
                    Empleado p = new Empleado();

                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexapepat(rs.getString("iexapepat"));
                    p.setIexapemat(rs.getString("iexapemat"));
                    p.setIexnomtra(rs.getString("iexnomtra"));
                    p.setIexfecing(rs.getString("fecing"));
                    lista.add(p);
                }
                return lista;
            }
        });
    }
}
