package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.ConceptoxProcesoxTra;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.dao.PlanillaDAO;
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

@Repository("PlanillaDAO")
public class PlantillaDaoImpl implements PlanillaDAO {


    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra) {

        String sql = " select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS T4000, " +
                "sum(k.G0019) AS G0019, " +
                "sum(k.D2070) AS D2070, " +
                "sum(k.T4020) AS T4020, " +
                "sum(k.P0145) AS P0145, " +
                "sum(k.P0146) AS P0146 " +
                "from  ( " +
                "select  " +
                "n.iexcodcia, " +
                "n.iexcodpro, " +
                "p.prodespro  despro, " +
                "n.iexnroper, " +
                "n.iexcodtra, " +
                "p.progrppro, " +
                "e.iexfecing, " +
                "e.iextipdoc, " +
                "e.iexnrodoc, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "n.iexcorrel, " +
                "case " +
                "when procodcon ='T4000'  then provalor else 0 " +
                "END  T4000, " +
                "case " +
                "when procodcon ='G0019'  then provalor else 0 " +
                "END  G0019, " +
                "case " +
                "when procodcon ='D2070'  then provalor else 0 " +
                "END  D2070, " +
                "case " +
                "when procodcon ='T4020'  then provalor else 0 " +
                "END  T4020, " +
                "case " +
                "when procodcon ='P0145'  then provalor else 0 " +
                "END  P0145, " +
                "case " +
                "when procodcon ='P0146'  then provalor else 0 " +
                "END  P0146 " +
                "from  " +
                "iexpropertra_nomina n , iexprocesos p , iexpropertra e " +
                "where " +
                "n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and	" +
                "n.iexnroper =  e.iexnroper and  " +
                "n.iexcodtra =  e.iexcodtra and " +
                "n.iexcorrel =  e.iexcorrel and 	" +
                "n.iexcodcia=" + codcia + " and  " +
                //   "n.iexcodpro="+codpro+" and " +
                "n.iexcodpro = p.procodpro and " +
                "substring(n.iexnroper,1,4)='" + anio + "' and " +
                "n.iexcodtra=" + codtra + " and " +
                //"--n.iexcorrel=1 and " +
                "n.procodcon in ('T4000','G0019','D2070','T4020','P0145','P0146')  and " +
                "p.procodregimenlab='01' and progrppro in ('PLA','LIQ','GRA')  " +
                "	) k " +
                "group by  " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin," +
                "k.iexcorrel  order by k.iexnroper,  k.iexcodpro asc ";
        return template.query(sql, new ResultSetExtractor<List<PlaProPeriodo>>() {
            public List<PlaProPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PlaProPeriodo> lista = new ArrayList<PlaProPeriodo>();

                while (rs.next()) {
                    PlaProPeriodo p = new PlaProPeriodo();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDescodpro(rs.getString("despro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIextipdoc(rs.getString("iextipdoc"));
                    p.setIexnrodoc(rs.getString("iexnrodoc"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setTotalingreso(rs.getDouble("T4000"));
                    p.setImpafecto5ta(rs.getDouble("G0019"));
                    p.setTotalneto(rs.getDouble("T4020"));
                    p.setDesc5ta(rs.getDouble("D2070"));
                    p.setRemafect5taotrcia_mes(rs.getDouble("P0145"));
                    p.setRentafect5taotrcia_mes(rs.getDouble("P0146"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setGrppro(rs.getString("progrppro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro, String codcon) {

        String sql = "select " +
                "j.iexcodcia, " +
                "j.iexcodpro, " +
                "j.iexnroper, " +
                "j.iexcodtra, " +
                " e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra as destra ," +
                "j.iexcorrel, " +
                "j.protipcon, " +
                "j.procodcon, " +
                "c.coodescon, " +
                "j.provalor " +
                "from iexpropertra_nomina j, iexconcepto c, iexempleado e " +
                " where " +
                " j.procodcon = c.coocodcon and " +
                " j.iexcodcia = e.iexcodcia and   " +
                " j.iexcodtra = e.iexcodtra and   " +
                "j.iexcodcia= " + codcia + " and " +
                "iexcodpro= " + idproceso + "  and " +
                "iexnroper= '" + perpro + "' and " +
                "j.procodcon= '" + codcon + "'  and j.provalor <>0 " +
                "  order by e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra , j.procodcon asc  ";

        return template.query(sql, new ResultSetExtractor<List<ConceptoxProcesoxTra>>() {
            public List<ConceptoxProcesoxTra> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ConceptoxProcesoxTra> lista = new ArrayList<ConceptoxProcesoxTra>();

                while (rs.next()) {
                    ConceptoxProcesoxTra p = new ConceptoxProcesoxTra();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setProcodpro(rs.getInt("iexcodpro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setDestra(rs.getString("destra"));
                    p.setCorrel(rs.getInt("iexcorrel"));
                    p.setProtipcon(rs.getString("protipcon"));
                    p.setProcodcon(rs.getString("procodcon"));
                    p.setCoodescon(rs.getString("coodescon"));
                    p.setProvalor(rs.getDouble("provalor"));


                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin) {

        String sql = " select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS T4000, " +
                "sum(k.T4010) AS T4010, " +
                "sum(k.D2070) AS D2070, " +
                "sum(k.T4020) AS T4020, " +
                "sum(k.T4030) AS T4030 " +
                "from  ( " +
                "select  " +
                "n.iexcodcia, " +
                "n.iexcodpro, " +
                "p.prodespro  despro, " +
                "n.iexnroper, " +
                "n.iexcodtra, " +
                "p.progrppro, " +
                "e.iexfecing, " +
                "e.iextipdoc, " +
                "e.iexnrodoc, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "n.iexcorrel, " +
                "case " +
                "when procodcon ='T4000'  then provalor else 0 " +
                "END  T4000, " +
                "case " +
                "when procodcon ='T4010'  then provalor else 0 " +
                "END    T4010, " +
                "case " +
                "when procodcon ='D2070'  then provalor else 0 " +
                "END  D2070, " +
                "case " +
                "when procodcon ='T4020'  then provalor else 0 " +
                "END  T4020, " +
                "case " +
                "when procodcon ='T4030'  then provalor else 0 " +
                "END  T4030 " +
                "from  " +
                "iexpropertra_nomina n , iexprocesos p , iexpropertra e " +
                "where " +
                "n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and	" +
                "n.iexnroper =  e.iexnroper and  " +
                "n.iexcodtra =  e.iexcodtra and " +
                "n.iexcorrel =  e.iexcorrel and 	" +
                "n.iexcodcia=" + codcia + " and  " +
                //   "n.iexcodpro="+codpro+" and " +
                "n.iexcodpro = p.procodpro and " +
                "n.iexcodtra=" + codtra + " and " +
                " n.iexnroper >= '" + perini + "' and " +
                " n.iexnroper <= '" + perfin + "' and " +
                //"--n.iexcorrel=1 and " +
                "n.procodcon in ('T4000','T4010','D2070','T4020','T4030')  " +
                "  " +
                "	) k " +
                "group by  " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin," +
                "k.iexcorrel  order by k.iexnroper,  k.iexcodpro asc ";

        return template.query(sql, new ResultSetExtractor<List<PlaProPeriodo>>() {
            public List<PlaProPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PlaProPeriodo> lista = new ArrayList<PlaProPeriodo>();

                while (rs.next()) {
                    PlaProPeriodo p = new PlaProPeriodo();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDescodpro(rs.getString("despro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIextipdoc(rs.getString("iextipdoc"));
                    p.setIexnrodoc(rs.getString("iexnrodoc"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setTotalingreso(rs.getDouble("T4000"));
                    p.setTotaldescuento(rs.getDouble("T4010"));
                    p.setTotalneto(rs.getDouble("T4020"));
                    p.setDesc5ta(rs.getDouble("D2070"));
                    p.setTotalaporte(rs.getDouble("T4030"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setGrppro(rs.getString("progrppro"));
                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro, String perini, String perfin) {

        String sql = " select " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin, " +
                "k.iexcorrel, " +
                "sum(k.T4000) AS T4000, " +
                "sum(k.T4010) AS T4010, " +
                "sum(k.D2070) AS D2070, " +
                "sum(k.T4020) AS T4020, " +
                "sum(k.T4030) AS T4030 " +
                "from  ( " +
                "select  " +
                "n.iexcodcia, " +
                "n.iexcodpro, " +
                "p.prodespro  despro, " +
                "n.iexnroper, " +
                "n.iexcodtra, " +
                "p.progrppro, " +
                "e.iexfecing, " +
                "e.iextipdoc, " +
                "e.iexnrodoc, " +
                "e.iexfecini, " +
                "e.iexfecfin, " +
                "n.iexcorrel, " +
                "case " +
                "when procodcon ='T4000'  then provalor else 0 " +
                "END  T4000, " +
                "case " +
                "when procodcon ='T4010'  then provalor else 0 " +
                "END    T4010, " +
                "case " +
                "when procodcon ='D2070'  then provalor else 0 " +
                "END  D2070, " +
                "case " +
                "when procodcon ='T4020'  then provalor else 0 " +
                "END  T4020, " +
                "case " +
                "when procodcon ='T4030'  then provalor else 0 " +
                "END  T4030 " +
                "from  " +
                "iexpropertra_nomina n , iexprocesos p , iexpropertra e " +
                "where " +
                "n.iexcodcia = e.iexcodcia and " +
                "n.iexcodpro = e.iexcodpro and	" +
                "n.iexnroper =  e.iexnroper and  " +
                "n.iexcodtra =  e.iexcodtra and " +
                "n.iexcorrel =  e.iexcorrel and 	" +
                "n.iexcodcia=" + codcia + " and  " +
                "n.iexcodpro=" + codpro + " and " +
                "n.iexcodpro = p.procodpro and " +
                "n.iexcodtra=" + codtra + " and " +
                " n.iexnroper >= '" + perini + "' and " +
                " n.iexnroper <= '" + perfin + "' and " +
                //"--n.iexcorrel=1 and " +
                "n.procodcon in ('T4000','T4010','D2070','T4020','T4030')  " +
                "  " +
                "	) k " +
                "group by  " +
                "k.iexcodcia, " +
                "k.iexcodpro, " +
                "k.despro, " +
                "k.iexnroper, " +
                "k.iexcodtra, " +
                "k.progrppro, " +
                "k.iexfecing, " +
                "k.iextipdoc, " +
                "k.iexnrodoc, " +
                "k.iexfecini, " +
                "k.iexfecfin," +
                "k.iexcorrel  order by k.iexnroper,  k.iexcodpro asc ";

        return template.query(sql, new ResultSetExtractor<List<PlaProPeriodo>>() {
            public List<PlaProPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PlaProPeriodo> lista = new ArrayList<PlaProPeriodo>();

                while (rs.next()) {
                    PlaProPeriodo p = new PlaProPeriodo();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDescodpro(rs.getString("despro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIextipdoc(rs.getString("iextipdoc"));
                    p.setIexnrodoc(rs.getString("iexnrodoc"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setTotalingreso(rs.getDouble("T4000"));
                    p.setTotaldescuento(rs.getDouble("T4010"));
                    p.setTotalneto(rs.getDouble("T4020"));
                    p.setDesc5ta(rs.getDouble("D2070"));
                    p.setTotalaporte(rs.getDouble("T4030"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setGrppro(rs.getString("progrppro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

}

