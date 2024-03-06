package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.PlanillaDao;
import com.balkaned.gladius.services.FormulaPlanillaService;
import com.balkaned.gladius.utils.FormatterFecha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Repository;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("PlanillaDAO")
@Slf4j
public class PlanillaDaoImpl implements PlanillaDao {

    JdbcTemplate template;

    @Autowired
    FormulaPlanillaService formulaPlanillaService;

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

        return template.query(sql, new ResultSetExtractor<>() {

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
                    log.info("Consulta SQL lista (caso 1): " + lista.add(p));
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

        return template.query(sql, new ResultSetExtractor<>() {

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
                    log.info("Consulta SQL lista (caso 2): " + lista.add(p));
                }
                return lista;
            }
        });
    }

    public void PlameExe(Integer codcia, String permes, String file) {

        template.update(" call prc_plame_sunat(?,?,? )  ",

                codcia,
                file,
                permes);

        log.info("Base PLameExe ");

    }

    public List<String> PlameMes(Integer codcia, String permes, String file) {

        String sql = " select   " +
                " iexdesfile " +
                " from  iexsunatfile where iexcodcia=" + codcia + " and iexcodfile='" + file + "'  and iexpermes='" + permes + "'  ";

        return template.query(sql, new ResultSetExtractor<List<String>>() {
            public List<String> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<String> lista = new ArrayList<String>();
                String p = null;
                while (rs.next()) {
                    p = rs.getString(1);
                    lista.add(p);
                }
                return lista;
            }
        });

    }


    public void AfpNetExe(Integer codcia, String permes) {

        template.update("call prc_afpnet_permes(?,?  )",

                codcia,
                permes);

    }

    public List<PlaProPeriodo> listPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String txt) {

        String sql = " select p.iexcodcia	,   p.iexcodpro	,       p.iexnroper,       p.iexpermes	,  p.iexcorrel, " +
                "p.iexcodtra	,  e.iexapepat||' '||e.iexapemat||' '||e.iexnomtra as destra, " +
                "p.iextipdoc ,       p.iexnrodoc ,      p.iexcodpuesto , " +
                "p.iexcodarea ,    p.iexcodlocal ,    p.iexcodccosto ,   p.iexfecini	, " +
                "p.iexfecfin	,   p.iexdiamestot,   p.iexdiasteorico,   p.iexdiavaca	,     p.iexdiadm	, " +
                "p.iexdiasub	,   p.iexdialic,         p.iexdiaperm	,     p.iexdiafalta	, " +
                "p.iexdiaefectivo, p.iexdiaspago,     p.totalingreso,    p.totaldescuento	, " +
                "p.totalneto	,   p.totalaporte	,    p.iexusucrea ,     p.iexfeccrea , " +
                " p.iexcodafp   ,       p.iextipafp,     to_char(p.iexfecing,'DD/MM/YYYY')  iexfecing,    TO_CHAR( p.iexfeccese ,'DD/MM/YYYY')  iexfeccese  ,  " +
                " p.iextipcese,         p.iexobscese,    p.iexanio_benef,   p.iexmes_benef, " +
                " p.iexdia_benef,       p.iexinivaca,    p.iexfinvaca,  p.usumod,  p.fecmod ,  p.flgboltrunc ,  p.iexdominical   " +
                "from iexpropertra p, iexempleado e where  " +
                "p.iexcodcia = e.iexcodcia and " +
                "p.iexcodtra = e.iexcodtra and p.iexcodcia=" + codcia + " and p.iexcodpro=" + idproceso + " and p.iexnroper='" + perpro + "' and iexcorrel=" + correl + " ";

        return template.query(sql, new ResultSetExtractor<List<PlaProPeriodo>>() {
            public List<PlaProPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PlaProPeriodo> lista = new ArrayList<PlaProPeriodo>();

                while (rs.next()) {
                    PlaProPeriodo p = new PlaProPeriodo();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIextipdoc(rs.getString("iextipdoc"));
                    p.setIexnrodoc(rs.getString("iexnrodoc"));
                    p.setIexcodpuesto(rs.getString("iexcodpuesto"));
                    p.setIexcodarea(rs.getString("iexcodarea"));
                    p.setIexcodlocal(rs.getString("iexcodlocal"));
                    p.setIexcodccosto(rs.getString("iexcodccosto"));

                    p.setIexfecini(rs.getString("iexfecini"));
                    FormatterFecha f = new FormatterFecha();
                    p.setIexfecini(f.fechaFormatterIngltoEsp(p.getIexfecini()));

                    p.setIexfecfin(rs.getString("iexfecfin"));
                    FormatterFecha f2= new FormatterFecha();
                    p.setIexfecfin(f.fechaFormatterIngltoEsp(p.getIexfecfin()));

                    p.setIexdiamestot(rs.getDouble("iexdiamestot"));
                    p.setIexdiavaca(rs.getDouble("iexdiavaca"));
                    p.setIexdiadm(rs.getDouble("iexdiadm"));
                    p.setIexdiasub(rs.getDouble("iexdiasub"));
                    p.setIexdialic(rs.getDouble("iexdialic"));
                    p.setIexdiaperm(rs.getDouble("iexdiaperm"));
                    p.setIexdiafalta(rs.getDouble("iexdiafalta"));
                    p.setIexdiaefectivo(rs.getDouble("iexdiaefectivo"));
                    p.setIexdiaspago(rs.getDouble("iexdiaspago"));
                    p.setTotalingreso(rs.getDouble("totalingreso"));
                    p.setTotaldescuento(rs.getDouble("totaldescuento"));
                    p.setTotalneto(rs.getDouble("totalneto"));
                    p.setTotalaporte(rs.getDouble("totalaporte"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setDestra(rs.getString("destra"));
                    p.setIexdiasteorico(rs.getDouble("iexdiasteorico"));
                    p.setIexcodafp(rs.getString("iexcodafp"));
                    p.setIextipafp(rs.getString("iextipafp"));
                    p.setIexfecing(rs.getString("iexfecing"));
                    p.setIexfeccese(rs.getString("iexfeccese"));
                    p.setIextipcese(rs.getString("iextipcese"));
                    p.setIexobscese(rs.getString("iexobscese"));
                    p.setIexanio_benef(rs.getInt("iexanio_benef"));
                    p.setIexmes_benef(rs.getInt("iexmes_benef"));
                    p.setIexdia_benef(rs.getInt("iexdia_benef"));
                    p.setIexinivaca(rs.getString("iexinivaca"));
                    p.setIexfinvaca(rs.getString("iexfinvaca"));
                    p.setUsumod(rs.getString("usumod"));
                    p.setFecmod(rs.getString("fecmod"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setFlgboltrunc(rs.getString("flgboltrunc"));
                    p.setIexdominical(rs.getDouble("iexdominical"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void iniPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {

        template.update(" call pl_ini_exe(?,?,?,?,?,?,?) ",

                codcia,
                idproceso,
                perpro,
                codtra,
                correl,
                grppla,
                usu);
    }

    public void calificacion_tiempo_mas(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl) {

        template.update(" call pl_exe_cons_tiempos(?,?,?,?,?,'') ",

                codcia,
                idproceso,
                idPeriodo,
                codtra,
                correl);
    }

    public void iniPlaProper_proc(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {

        template.update(" call pl_ini_exe_for_proc(?,?,?,?,?,?,?) ",

                codcia,
                idproceso,
                perpro,
                codtra,
                correl,
                grppla,
                usu);
    }

    public void timeIniexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {

        template.update("  update iexproperiodo  " +
                "	   set " +
                "	   timeini_proc = now() " +
                "       where  " +
                "         iexcodcia=" + codcia + " AND " +
                "         iexcodpro=" + idproceso + " and " +
                "         iexnroper='" + perpro + "' ");
    }

    public void procesarPla2020(List<PlaProPeriodo> Persona, Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl, Integer thread) {

        Integer v_salto = 0;
        Iterator<ProPeriodoDet> L_data = null;
        ProPeriodoDet data = null;

        // Carga las formulas desde base de datos para el proceso en curso
        List<FormulaPlanilla> lstFormula = formulaPlanillaService.listar(String.valueOf(idproceso));

        String v_variables_concat = null;   //En esta variable se alojarán la variables concatendas con los valores

        // Iniciaiza la iteraciòn de la lista de personas.
        Iterator<PlaProPeriodo> pi;
        PlaProPeriodo pi_persona = null;

        Iterator<FormulaPlanilla> l_formula;
        FormulaPlanilla for_det;
        Double v_resultadoFinal = null;

        List<String> l_variables_glob;
        String v_variables_glob_concat = "";

        Iterator<ConceptoxAgrup> i_conagrup_det;
        Double v_resultado_glob_Final = null;
        ConceptoxAgrup conagrup_glb = null;
        String v_conagrup_det_final;
        Iterator<String> i_conagrup_det_final;
        Iterator<String> i_variables_glob;

        String result = null;
        String sql = "";

        String sql_var_general = "";
        String sql_var_global = "";
        String conagrup_glob = "";

        List<ConceptoXProceso> LoadGrpcon;
        Iterator<ConceptoXProceso> i_grpcon;
        ConceptoXProceso congrp = null;

        List<ProPeriodoDet> LoadData2 = new ArrayList<ProPeriodoDet>();
        Iterator<ProPeriodoDet> i_Data2;
        ProPeriodoDet p_Data2 = null;

        // Recorre todos los trabajadores para colocarle el hilo al que pertenece
        Integer thread_id = thread;
        pi = Persona.iterator();

        while (pi.hasNext()){
            pi_persona = pi.next();

            try {
                update_iexpropertra_proc(thread_id,codcia,idproceso,idPeriodo,pi_persona.getIexcodtra(),correl);
            } catch (Exception e) {
                log.info(e.getMessage());
            }
        }

        l_formula = lstFormula.iterator();  // Se delcara la iteración de la lista formula por cada trabajador

        while (l_formula.hasNext()) { // Iteraciòn de formula por cada trabajador. Por cada trabajador se va a recorrer la lista de formula.

            for_det = l_formula.next();
            // Se asigna valor de la lista al objeto Formula.
            log.info("--Formula :" + for_det.getIdFormula());  // Se verifica el identificador de la formula

            if (for_det.getTipOut().equals("1") || for_det.getTipOut().equals("3")) {
                l_variables_glob = null; /// Selecciona los conceptos que son grupo de conceptos resultantes
                v_variables_glob_concat = "";
                v_resultado_glob_Final = 0.0; // Sumarizar los valores en el concepto grupo resultante

                // Guardar los valores del  resultado en una nueva concatenacion de variables
                sql_var_general = formulaPlanillaService.getListVars(idproceso,for_det.getDesVar());
                LoadGrpcon=formulaPlanillaService.obtenerListVariables_glb(idproceso,for_det.getDesVar());

                i_grpcon = LoadGrpcon.iterator();

                while (i_grpcon.hasNext()) {
                    congrp = i_grpcon.next();

                    try {
                        // Actualizar la suma por cada trabajador de los conceptos globales
                        update_prc_proceso_upd_grupocon_v3(codcia,codtra,idproceso,idPeriodo,correl,congrp.getConvar(),congrp.getProcodcon(),thread_id);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }
                }

                // Inicia la iteracion por persona
                LoadData2 = getMetanominaDatav3(codcia, idproceso, idPeriodo, codtra, sql_var_general, correl, thread_id);

                pi = Persona.iterator();
                while (pi.hasNext()) {
                    pi_persona = pi.next();

                    v_variables_concat = "";
                    i_Data2 = LoadData2.iterator();

                    while (i_Data2.hasNext()) {
                        p_Data2 = i_Data2.next();
                        if (p_Data2.getIexcodtra().equals(pi_persona.getIexcodtra())) {
                            v_variables_concat = " " + v_variables_concat + " " + p_Data2.getVarcon() + "=" + p_Data2.getValue() + "; ";
                        }
                    }

                    v_resultadoFinal = formulaPlanillaService.realEjecucion(for_det.getDesVar(), v_variables_concat, for_det.getDesFormula());

                    guardarMetaTrav2(codcia, idproceso, pi_persona.getIexcodtra(), idPeriodo, for_det.getIdConcepto(), v_resultadoFinal, correl);
                }
            } else if (for_det.getTipOut().equals("2")) {

                log.info("Ejecucion de Procedure ");

                if (for_det.getGrpeje().equals("1")) {   // ejecucion del procedure por trabajador

                    log.info("Ejecucion de Procedure X trabajador :" + for_det.getSqlprogram());

                    // Grabar la data en la metanomina  /// crear metodo
                    // guardarInformacionMeta(LoadData2,codcia, idproceso, idPeriodo);
                    // Iterar por trabajador . ejecutar el procedure de planillas

                    pi = Persona.iterator();

                    while (pi.hasNext()){ // Iteraciòn por cada trabajador
                        pi_persona = pi.next();

                        try{
                            update_slq_program(for_det.getSqlprogram(),codcia,pi_persona.getIexcodtra(),idproceso,idPeriodo);
                        } catch (Exception e) {
                            log.info(e.getMessage());
                        }
                    }

                    log.info("Se ejecuto el procedure 1");
                    // Traer la data de la base de datos
                    // Traer la data de la metanomina a memoria
                    // LoadData2=getProPeriodoDet(codcia, idproceso, idPeriodo,1);
                    // Colocar la data en el DataLoad.

                } else if (for_det.getGrpeje().equals("2")) {  // ejecucion del procedure de modo masivo

                    log.info("Ejecucion de Procedure x masivo :" + for_det.getSqlprogram());
                    // Grabar la data en la metanomina
                    // guardarInformacionMeta(LoadData2,codcia, idproceso, idPeriodo);
                    // Ejecutar el procedure de planillas modo masivo.
                    log.info("Se ejecuto el procedure 2");

                    try {
                        update_slq_program_masivo(for_det.getSqlprogram(),codcia,1,idproceso,idPeriodo);
                    } catch (Exception e) {
                        log.info(e.getMessage());
                    }

                    log.info("Finalizo procedure 2");

                    // Traer la data de la base de datos
                    // LoadData2=getProPeriodoDet(codcia, idproceso, idPeriodo,1);
                    // traer la data de la metanomina a memoria
                    // colocar la data en el DataLoad.
                }
            }
        }// while de la formula

        // Verificar la lista de
        // Actualiza estado del proceso.
    }
    public void update_iexpropertra_proc(Integer thread_id,Integer codcia, Integer idproceso,String idPeriodo, Integer codtra,Integer correl){

        template.update(" UPDATE iexpropertra_proc SET thread= ?  where iexcodcia= ?  and iexcodpro=? and iexnroper=? and iexcodtra=? and iexcorrel =? ",

                thread_id,
                codcia,
                idproceso,
                idPeriodo,
                codtra,
                correl);
    }

    public void update_prc_proceso_upd_grupocon_v3(Integer codcia, Integer codtra, Integer idproceso,String idPeriodo, Integer correl,String convar, String procodcon, Integer thread_id){

        template.update(" call prc_proceso_upd_grupocon_v3(?,?,?,?,?,?,?,?) ",

        codcia,
        codtra,
        idproceso,
        idPeriodo,
        correl,
        convar,
        procodcon,
        thread_id);
    }

    public List<ProPeriodoDet> getMetanominaDatav3(Integer codcia,Integer idproceso, String perpro , Integer codtra, String sqlcomand , Integer correl, Integer thread){

        ProPeriodoDet p = null;

        String var_concat = "";

        List<ProPeriodoDet> lista = null;
        //  List<String> lista = null;

        String sql="";

        if(codtra==-1){
            sql = " select " +
                    " p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexcodtra, p.procodcon, p.coocodforvar , p.provalor  " +
                    " from iexpropertra_nomina_proc p , iexpropertra_proc a "
                    + " where  "
                    +" p.iexcodcia=a.iexcodcia and  " +
                    " p.iexcodpro=a.iexcodpro and " +
                    "  p.iexnroper=a.iexnroper and " +
                    " p.iexcodtra=a.iexcodtra and " +
                    " p.iexcorrel=a.iexcorrel and "
                    + " p.iexcodcia="+codcia+" and p.iexcodpro="+idproceso+" and p.iexnroper='"+perpro+"' and  p.coocodforvar in "+sqlcomand+" and p.iexcorrel= "+correl+" and a.thread="+thread+" order by 4,5 asc ";
        }else{
            sql = " select " +
                    " p.iexcodcia, p.iexcodpro, p.iexnroper, p.iexcodtra, p.procodcon, p.coocodforvar , p.provalor  " +
                    " from iexpropertra_nomina_proc p , iexpropertra_proc a "
                    + " where  "
                    +" p.iexcodcia=a.iexcodcia and  " +
                    " p.iexcodpro=a.iexcodpro and " +
                    "  p.iexnroper=a.iexnroper and " +
                    " p.iexcodtra=a.iexcodtra and " +
                    " p.iexcorrel=a.iexcorrel and  p.iexcodcia="+codcia+" and p.iexcodpro="+idproceso+" and p.iexnroper='"+perpro+"' and p.iexcodtra="+codtra+" and p.coocodforvar in "+sqlcomand+" and p.iexcorrel= "+correl+" and a.thread="+thread+"  order by 4,5 asc  ";

        }

        return template.query(sql, new ResultSetExtractor<List<ProPeriodoDet>>() {

            public List<ProPeriodoDet> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProPeriodoDet> lista = new ArrayList<ProPeriodoDet>();

                while(rs.next()) {
                    ProPeriodoDet p = new ProPeriodoDet();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setCodcon(rs.getString("procodcon"));
                    p.setVarcon(rs.getString("coocodforvar"));
                    //  p.setTipocon(rs.getString("protipcon"));
                    p.setValue(rs.getDouble("provalor"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void guardarMetaTrav2( Integer codcia, Integer idproceso,Integer idcodtra, String idPeriodo, String codcon, Double valor, Integer correl){

        template.update("update  iexpropertra_nomina_proc set provalor="+valor+" where  iexcodcia="+codcia+" and  iexcodpro="+idproceso+"  and  iexnroper='"+idPeriodo+"'  and  iexcodtra="+idcodtra+"  and  trim(procodcon)=trim('"+codcon+"')  and iexcorrel="+correl+" ");

    }

    public void update_slq_program(String sql_program, Integer codcia, Integer codtra, Integer idproceso,String idPeriodo){

        template.update("call "+sql_program.trim()+"(?,?,?,?) ",

        codcia,
        codtra,
        idproceso,
        idPeriodo);
    }

    public void update_slq_program_masivo(String sql_program, Integer codcia, Integer codtra, Integer idproceso,String idPeriodo){

        template.update("call "+sql_program.trim()+"(?,?,?,?) ",

                codcia,
                codtra,
                idproceso,
                idPeriodo);
    }

    public void guardarNomina2020(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl){

        pl_pla_upgrade(codcia,idproceso,idPeriodo,codtra,correl);
        update_iexproperiodo(codcia,idproceso,idPeriodo);

    }

    public void pl_pla_upgrade(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl){

        template.update(" call  pl_pla_upgrade(?,?,?,?,?) ",

        codcia,
        idproceso,
        idPeriodo,
        codtra,
        correl);
    }

    public void update_iexproperiodo(Integer codcia, Integer idproceso, String idPeriodo){
        template.update(" UPDATE iexproperiodo set flgestado='2' where iexcodcia=? and  iexcodpro=? and iexnroper=? ",

        codcia,
        idproceso,
        idPeriodo);
    }

    public void timeFinexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl){

        template.update(" update iexproperiodo  " +
                        "	   set " +
                        "	   timefin_proc = now()  , " +
                        "      timenroimp_proc = round(EXTRACT (SECOND FROM ( now() - timeini_proc )))  "+
                        "       where  " +
                        "         iexcodcia="+codcia+" AND " +
                        "         iexcodpro="+idproceso+" and " +
                        "         iexnroper='"+perpro+"' ");
    }

    public void delPlaProper(Integer codcia,Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu){

        template.update("  call pl_borrar_exe(?,?,?,?,?,?,?)  ",

        codcia,
        idproceso,
        perpro,
        codtra,
        correl,
        grppla,
        usu);
    }
}

