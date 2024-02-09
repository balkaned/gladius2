package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.DashboardDao;
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

@Repository("DashboardDao")
public class DashboardDaoImpl implements DashboardDao {
    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<Cumpleanos> traerListaDeCumpleañosPorMes(Integer codcia){

        String sql = "select " +
                "e.iexcodtra, "+
                "e.iexnomtra, " +
                "e.iexapepat, " +
                "e.iexapemat, " +
                "to_char(e.iexfecnac, 'dd Month, yyyy') as iexfecnac, " +
                "to_char(e.iexfecnac, 'MM') as mes, " +
                "to_char(CURRENT_DATE, 'Month') as mes_actual, " +
                "date_part('year', CURRENT_DATE) - date_part('year', e.iexfecnac) as edad " +
                "from iexempleado e " +
                "where to_char(e.iexfecnac, 'MM')=to_char(CURRENT_DATE, 'MM') "+
                "and e.iexcodcia="+codcia+" ";
        return template.query(sql, new ResultSetExtractor<List<Cumpleanos>>() {

            public List<Cumpleanos> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Cumpleanos> lista = new ArrayList<Cumpleanos>();

                while(rs.next()) {
                    Cumpleanos p = new Cumpleanos();

                    p.setIexcodtra(rs.getInt("iexcodtra"));

                    p.setIexnomtra(rs.getString("iexnomtra"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setIexnomtra(cap.letras(p.getIexnomtra()));

                    p.setIexapepat(rs.getString("iexapepat"));
                    CapitalizarCadena cap2 = new CapitalizarCadena();
                    p.setIexapepat(cap2.letras(p.getIexapepat()));

                    p.setIexapemat(rs.getString("iexapemat"));
                    CapitalizarCadena cap3 = new CapitalizarCadena();
                    p.setIexapemat(cap3.letras(p.getIexapemat()));

                    p.setIexfecnac(rs.getString("iexfecnac"));
                    p.setMesActual(rs.getString("mes_actual"));
                    p.setEdad(rs.getInt("edad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Ingresantes> traerListaDeIngresantesPorMes(Integer codcia){

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
                "and to_char(e.iexfecing, 'yyyy')=to_char(CURRENT_DATE, 'yyyy') "+
                "and e.iexcodcia="+codcia+" ";
                //"and to_char(e.iexfecing, 'yyyy')='2018' ";
        return template.query(sql, new ResultSetExtractor<List<Ingresantes>>() {

            public List<Ingresantes> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Ingresantes> lista = new ArrayList<Ingresantes>();

                while(rs.next()) {
                    Ingresantes p = new Ingresantes();

                    p.setIexcodtra(rs.getInt("iexcodtra"));

                    p.setIexnomtra(rs.getString("iexnomtra"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setIexnomtra(cap.letras(p.getIexnomtra()));

                    p.setIexapepat(rs.getString("iexapepat"));
                    CapitalizarCadena cap2 = new CapitalizarCadena();
                    p.setIexapepat(cap2.letras(p.getIexapepat()));

                    p.setIexapemat(rs.getString("iexapemat"));
                    CapitalizarCadena cap3 = new CapitalizarCadena();
                    p.setIexapemat(cap3.letras(p.getIexapemat()));

                    p.setIexfecing(rs.getString("iexfecingchar"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<Retirados> traerListaDeRetiradosPorMes(Integer codcia){

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
                "and e.iexcodcia="+codcia+" ";
                //"and to_char(e.iexfecret, 'yyyy')='2021' ";
        return template.query(sql, new ResultSetExtractor<List<Retirados>>() {

            public List<Retirados> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<Retirados> lista = new ArrayList<Retirados>();

                while(rs.next()) {
                    Retirados p = new Retirados();

                    p.setIexcodtra(rs.getInt("iexcodtra"));

                    p.setIexnomtra(rs.getString("iexnomtra"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setIexnomtra(cap.letras(p.getIexnomtra()));

                    p.setIexapepat(rs.getString("iexapepat"));
                    CapitalizarCadena cap2 = new CapitalizarCadena();
                    p.setIexapepat(cap2.letras(p.getIexapepat()));

                    p.setIexapemat(rs.getString("iexapemat"));
                    CapitalizarCadena cap3 = new CapitalizarCadena();
                    p.setIexapemat(cap3.letras(p.getIexapemat()));

                    p.setIexfecret(rs.getString("iexfecretchar"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getCantidadEmpl(Integer codcia){

        final Integer[] cant = {0};

        String sql = "select count(*) as cantidad " +
                "from iexempleado e " +
                "where e.iexflgest='1' " +
                "and e.iexcodcia="+codcia+" ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    cant[0] =rs.getInt("cantidad");
                }
                return cant[0];
            }
        });
    }

    @Override
    public List<DashboardCcosto> obtenerDatosDashboardCcosto(Integer codcia) {
        String sql="select " +
                "cc.iexccosto, " +
                "cc.iexdesccosto, " +
                "   (select " +
                "   count(e1.iexcodtra) " +
                "   from iexempleado e1 " +
                "   inner join iexccosto cc1 on cc1.iexccosto=e1.iexccosto " +
                "   where e1.iexcodcia="+codcia+" " +
                "   and cc1.iexcodcia="+codcia+" " +
                "   and e1.iexflgest='1' " +
                "   and e1.iexccosto=cc.iexccosto) as cantidad " +
                "from iexccosto cc " +
                "where cc.iexcodcia="+codcia+" ";

        return template.query(sql, new ResultSetExtractor<List<DashboardCcosto>>() {
            public List<DashboardCcosto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DashboardCcosto> lista = new ArrayList<DashboardCcosto>();

                while(rs.next()) {
                    DashboardCcosto p = new DashboardCcosto();

                    p.setIexccosto(rs.getInt("iexccosto"));

                    p.setIexdesccosto(rs.getString("iexdesccosto"));
                    CapitalizarCadena cap= new CapitalizarCadena();
                    p.setIexdesccosto(cap.letras(p.getIexdesccosto()));

                    p.setCantidad(rs.getInt("cantidad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }


    public Integer getCantidadAreas(Integer codcia){

        final Integer[] cant = {0};

        String sql = "select count(*) as cantidad " +
                "from iexarea a " +
                "where a.iexcodcia="+codcia+" ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    cant[0] =rs.getInt("cantidad");
                }
                return cant[0];
            }
        });
    }

    public Integer getCantidadBancos(Integer codcia){

        final Integer[] cant = {0};

        String sql = "select count(*) as cantidad " +
                "from iexprobancos b " +
                "where b.iexcodcia="+codcia+" ";

        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    cant[0] =rs.getInt("cantidad");
                }
                return cant[0];
            }
        });
    }

    public DashboardSexoPie obtenerDashboardPieSexo(Integer codcia) {

        String sql = "select " +
                "(select " +
                "count(e1.iexcodsex) " +
                "from iexempleado e1 " +
                "where e1.iexcodcia="+codcia+" and e1.iexflgest='1' and e1.iexcodsex='M') as cantidad_m, " +
                "(select " +
                "count(e2.iexcodsex) " +
                "from iexempleado e2 " +
                "where e2.iexcodcia="+codcia+" and e2.iexflgest='1' and e2.iexcodsex='F') as cantidad_f, " +
                "(select " +
                "count(e3.iexcodsex) " +
                "from iexempleado e3 " +
                "where e3.iexcodcia="+codcia+" and e3.iexflgest='1' and e3.iexcodsex='MA') as cantidad_ma, " +
                "(select " +
                "count(e4.iexcodsex) " +
                "from iexempleado e4 " +
                "where e4.iexcodcia="+codcia+" and e4.iexflgest='1') as cantidad_total " +
                "from iexempleado e " +
                "group by cantidad_m ";

        return (DashboardSexoPie) template.query(sql, new ResultSetExtractor<DashboardSexoPie>() {
            public DashboardSexoPie extractData(ResultSet rs) throws SQLException, DataAccessException{
                DashboardSexoPie p = new DashboardSexoPie();
                while(rs.next()) {
                    p.setCantidad_m(rs.getInt("cantidad_m"));
                    p.setCantidad_f(rs.getInt("cantidad_f"));
                    p.setCantidad_ma(rs.getInt("cantidad_ma"));
                    p.setCantidad_total(rs.getInt("cantidad_total"));
                }
                return p;
            }
        });
    }

    public List<DashboardAreaBar> obtenerDatosDashboardArea(Integer codcia) {

        String sql = "select " +
                "ar.iexcodarea, " +
                "ar.iexdesarea, " +
                "   (select " +
                "   count(e.iexcodtra) " +
                "   from iexempleado e " +
                "   inner join iexarea ar1 on ar1.iexcodarea=e.iexarea " +
                "   where e.iexcodcia="+codcia+" " +
                "   and e.iexflgest='1' " +
                "   and ar1.iexcodcia="+codcia+" " +
                "   and e.iexarea=ar.iexcodarea) as cantidad " +
                "from iexarea ar " +
                "where ar.iexcodcia="+codcia+" ";

        return template.query(sql, new ResultSetExtractor<List<DashboardAreaBar>>() {
            public List<DashboardAreaBar> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DashboardAreaBar> lista = new ArrayList<DashboardAreaBar>();

                while(rs.next()) {
                    DashboardAreaBar p = new DashboardAreaBar();

                    p.setCodarea(rs.getInt("iexcodarea"));
                    p.setDesarea(rs.getString("iexdesarea"));
                    CapitalizarCadena cap= new CapitalizarCadena();
                    p.setDesarea(cap.letras(p.getDesarea()));
                    p.setCantidad(rs.getInt("cantidad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<DashboardFondosBar> obtenerDatosDashboardFodos(Integer codcia) {

        String sql="select " +
                "tb1.iexcodtab, " +
                "tb1.desdet, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexttabled tb1 on tb1.iexkey=e1.iexcodafp " +
                "where e1.iexcodcia="+codcia+" " +
                "and tb1.iexcodtab='11' " +
                "and e1.iexflgest='1' " +
                "and e1.iexcodafp=tb1.iexkey " +
                "group by tb1.iexcodtab,tb1.desdet ";

        return template.query(sql, new ResultSetExtractor<List<DashboardFondosBar>>() {
            public List<DashboardFondosBar> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DashboardFondosBar> lista = new ArrayList<DashboardFondosBar>();

                while(rs.next()) {
                    DashboardFondosBar p = new DashboardFondosBar();

                    p.setCodtab(rs.getInt("iexcodtab"));

                    p.setDesdet(rs.getString("desdet"));
                    CapitalizarCadena cap= new CapitalizarCadena();
                    p.setDesdet(cap.letras(p.getDesdet()));

                    p.setCantidad(rs.getInt("cantidad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<DashboardBancosPie> obtenerDatosDashboardBancos(Integer codcia) {

        String sql = "select " +
                "tb1.iexcodtab, " +
                "tb1.desdet, " +
                "count(e1.iexcodtra) as cantidad " +
                "from iexempleado e1 " +
                "inner join iexttabled tb1 on tb1.iexkey=e1.iexcodban_hab " +
                "where e1.iexcodcia=" + codcia + " " +
                "and tb1.iexcodtab='36' " +
                "and e1.iexflgest='1' " +
                "and e1.iexcodban_hab=tb1.iexkey " +
                "group by tb1.iexcodtab,tb1.desdet ";

        return template.query(sql, new ResultSetExtractor<List<DashboardBancosPie>>() {
            public List<DashboardBancosPie> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DashboardBancosPie> lista = new ArrayList<DashboardBancosPie>();

                while (rs.next()) {
                    DashboardBancosPie p = new DashboardBancosPie();

                    p.setCodtab(rs.getInt("iexcodtab"));

                    p.setDesdet(rs.getString("desdet"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setDesdet(cap.letras(p.getDesdet()));

                    p.setCantidad(rs.getInt("cantidad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<DashboardCcosto> obtenerDatosDashboardCCosto(Integer codcia) {

        String sql="select " +
                "cc.iexccosto, " +
                "cc.iexdesccosto, " +
                "   (select " +
                "   count(e1.iexcodtra) " +
                "   from iexempleado e1 " +
                "   inner join iexccosto cc1 on cc1.iexccosto=e1.iexccosto " +
                "   where e1.iexcodcia="+codcia+" " +
                "   and cc1.iexcodcia="+codcia+" " +
                "   and e1.iexflgest='1' " +
                "   and e1.iexccosto=cc.iexccosto) as cantidad " +
                "from iexccosto cc " +
                "where cc.iexcodcia="+codcia+" ";

        return template.query(sql, new ResultSetExtractor<List<DashboardCcosto>>() {
            public List<DashboardCcosto> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DashboardCcosto> lista = new ArrayList<DashboardCcosto>();

                while(rs.next()) {
                    DashboardCcosto p = new DashboardCcosto();

                    p.setIexccosto(rs.getInt("iexccosto"));

                    p.setIexdesccosto(rs.getString("iexdesccosto"));
                    CapitalizarCadena cap= new CapitalizarCadena();
                    p.setIexdesccosto(cap.letras(p.getIexdesccosto()));

                    p.setCantidad(rs.getInt("cantidad"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }


}
