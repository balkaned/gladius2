package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.ConceptoxProcesoxTra;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.dao.PlanillaDAO;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class PlanillaDaoImpl implements PlanillaDAO {

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

    public List<PlaProPeriodo> listPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String txt){

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
                " p.iexdia_benef,       p.iexinivaca,    p.iexfinvaca,  p.usumod,  p.fecmod ,  p.flgboltrunc ,  p.iexdominical   "+
                "from iexpropertra p, iexempleado e where  " +
                "p.iexcodcia = e.iexcodcia and " +
                "p.iexcodtra = e.iexcodtra and p.iexcodcia="+codcia+" and p.iexcodpro="+idproceso+" and p.iexnroper='"+perpro+"' and iexcorrel="+correl+" ";

        return template.query(sql, new ResultSetExtractor<List<PlaProPeriodo>>() {
            public List<PlaProPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<PlaProPeriodo> lista = new ArrayList<PlaProPeriodo>();

                while(rs.next()) {
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
                    p.setIexfecfin(rs.getString("iexfecfin"));
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

}

