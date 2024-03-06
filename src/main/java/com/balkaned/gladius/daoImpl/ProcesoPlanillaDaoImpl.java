package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.AsientoContableCab;
import com.balkaned.gladius.beans.PlaProPeriodo;
import com.balkaned.gladius.beans.ProcesoPeriodo;
import com.balkaned.gladius.beans.ProcesoPlanilla;
import com.balkaned.gladius.dao.ProcesoPlanillaDao;
import com.balkaned.gladius.utils.CapitalizarCadena;
import com.balkaned.gladius.utils.FormatterFecha;
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

@Repository("ProcesoPlanillaDao")
@Slf4j
public class ProcesoPlanillaDaoImpl implements ProcesoPlanillaDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<ProcesoPlanilla> listar(String text) {

        List<ProcesoPlanilla> lista = null;
        String sql = "select " +
                "procodpro, " +
                "prodespro, " +
                "prodescorto, " +
                "procodregimenlab, " +
                "procodregimenlab desregimen, " +
                "progrppro, " +
                "bolproceso, " +
                "idtipproceso, " +
                "bolprocesoind, " +
                "bolprocesores " +
                "from iexprocesos p   " +
                "where " +
                " prodespro like '%" + text + "%' order by procodpro asc ";

        return template.query(sql, new ResultSetExtractor<List<ProcesoPlanilla>>() {

            public List<ProcesoPlanilla> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPlanilla> lista = new ArrayList<ProcesoPlanilla>();

                while (rs.next()) {
                    ProcesoPlanilla p = new ProcesoPlanilla();

                    p.setIdProceso(rs.getInt("procodpro"));
                    p.setDesProceso(rs.getString("prodespro"));
                    p.setDesProcesoCorto(rs.getString("prodescorto"));
                    p.setIdRegLab(rs.getString("procodregimenlab"));
                    p.setDesRegLab(rs.getString("desregimen"));
                    p.setDesGrp(rs.getString("progrppro"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<AsientoContableCab> listarAsieCab(Integer codcia, Integer codpro, String nroper){

        List<AsientoContableCab> lista = null;
        String sql = "  select " +
                " e.iexcodcia, e.iexctbper_id , e.iexnroasiento, e.iexcodpro ,  " +
                " p.prodespro ,  e.iexnroper, e.iexpermes, e.iextcmb, e.iexcodmon, d.desdet desmon,  " +
                " e.iexcodmon_ext,  d2.desdet desmon_ext,  e.tot_cre_na, e.tot_deb_na , e.tot_cre_me, e.tot_deb_me, e.iexglosacab  " +
                " from iexctbpercab  e  " +
                " inner join iexprocesos  p  on  e.iexcodpro= p.procodpro  " +
                " left join iexttabled d on d.iexcodtab='52'  and d.iexkey = e.iexcodmon  " +
                " left join iexttabled d2 on d.iexcodtab='52'  and d2.iexkey = e.iexcodmon_ext " +
                " where  " +
                " e.iexcodcia =  "+codcia+" and  e.iexcodpro="+codpro+" and e.iexpermes = '"+nroper+"'  ";

        return template.query(sql, new ResultSetExtractor<List<AsientoContableCab>>() {

            public List<AsientoContableCab> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AsientoContableCab> lista = new ArrayList<AsientoContableCab>();

                while (rs.next()) {
                    AsientoContableCab p = new AsientoContableCab();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexctbper_id(rs.getInt("iexctbper_id"));
                    p.setIexnroasiento(rs.getString("iexnroasiento"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setDesproceso(rs.getString("prodespro"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIextcmb(rs.getDouble("iextcmb"));
                    p.setIexcodmon(rs.getString("iexcodmon"));
                    p.setCodmon_des(rs.getString("desmon"));
                    p.setIexcodmon_ext(rs.getString("iexcodmon_ext"));
                    p.setCodmon_ext_des(rs.getString("desmon_ext"));
                    p.setTot_cre_na(rs.getDouble("tot_cre_na"));
                    p.setTot_deb_na(rs.getDouble("tot_deb_na"));
                    p.setTot_cre_me(rs.getDouble("tot_cre_me"));
                    p.setTot_deb_me(rs.getDouble("tot_deb_me"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public List<ProcesoPeriodo> listarProRegpla(Integer codcia, String regpla, String permes){

        String sql = "  select  e.iexcodcia," +
                "  case " +
                "  when substring(e.iexpermes,5,2) ='01' then  'Enero' " +
                "  when substring(e.iexpermes,5,2) ='02' then  'Febrero' " +
                "  when substring(e.iexpermes,5,2) ='03' then  'Marzo' " +
                "  when substring(e.iexpermes,5,2) ='04' then  'Abril' " +
                "  when substring(e.iexpermes,5,2) ='05' then  'Mayo' " +
                "  when substring(e.iexpermes,5,2) ='06' then  'Junio' " +
                "  when substring(e.iexpermes,5,2) ='07' then  'Julio' " +
                "  when substring(e.iexpermes,5,2) ='08' then  'Agosto' " +
                "  when substring(e.iexpermes,5,2) ='09' then  'Setiembre' " +
                "  when substring(e.iexpermes,5,2) ='10' then  'Octubre' " +
                "  when substring(e.iexpermes,5,2) ='11' then  'Noviembre' " +
                "  when substring(e.iexpermes,5,2) ='12' then  'Diciembre'  " +
                " else " +
                " 'Sin mes' " +
                " end desmes, " +
                " substring(e.iexpermes,5,2) as permes, " +
                " p.procodregimenlab, " +
                " e.iexpermes, " +
                " e.iexanio, " +
                " e.iexnroper, " +
                " e.iexfecini, " +
                " e.iexfecfin, " +
                " e.timerfecini, " +
                " e.timerfecfin, "+
                " e.iexfecpago, "+
                " e.flgestado, "+
                " p.progrppro, " +
                " p.procodpro iexcodpro, " +
                " p.prodespro, " +
                " case " +
                "  when flgestado ='1' then 'Creado' " +
                "  when flgestado ='2' then 'Procesado' " +
                "  when flgestado ='3' then 'Cerrado' " +
                "  else " +
                "   'sin estado' " +
                "  end desestado " +
                " from iexprocesos p,  iexproperiodo e " +
                " where " +
                " p.procodpro = e.iexcodpro and " +
                " e.iexcodcia ="+codcia+" and " +
                " iexpermes like '%"+permes+"%' and " +
                " procodregimenlab='"+regpla+"' order by e.iexpermes, p.progrppro, e.iexnroper asc ";

        return template.query(sql, new ResultSetExtractor<List<ProcesoPeriodo>>() {

            public List<ProcesoPeriodo> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<ProcesoPeriodo> lista = new ArrayList<ProcesoPeriodo>();

                while(rs.next()) {
                    ProcesoPeriodo p = new ProcesoPeriodo();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setIexanio(rs.getString("iexanio"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexpermes(rs.getString("iexpermes"));

                    p.setIexfecini(rs.getString("iexfecini"));
                    //logger.info("fechaoriginal: "+p.getIexfecini());
                    FormatterFecha f = new FormatterFecha();
                    String fechaconv=f.fechaFormatterIngltoEsp(p.getIexfecini());
                    CapitalizarCadena capit= new CapitalizarCadena();
                    p.setIexfecini(f.fechaFormatterDia(fechaconv)+" "+capit.letras(f.fechaFormatterMes(fechaconv))+", "+f.fechaFormatterAnio(fechaconv));

                    p.setIexfecfin(rs.getString("iexfecfin"));
                    FormatterFecha f2 = new FormatterFecha();
                    String fechaconv2=f2.fechaFormatterIngltoEsp(p.getIexfecfin());
                    CapitalizarCadena capit2= new CapitalizarCadena();
                    p.setIexfecfin(f2.fechaFormatterDia(fechaconv2)+" "+capit2.letras(f2.fechaFormatterMes(fechaconv2))+", "+f2.fechaFormatterAnio(fechaconv2));

                    p.setTimerfecini(rs.getString("timerfecini"));
                    p.setTimerfecfin(rs.getString("timerfecfin"));
                    p.setIexfecpago(rs.getString("iexfecpago"));
                    p.setFlgestado(rs.getString("flgestado"));
                    p.setDesproceso(rs.getString("prodespro"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDesgrppla(rs.getString("progrppro"));
                    p.setDesmes(rs.getString("desmes"));
                    p.setCodregimen(rs.getString("procodregimenlab"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public void insertarProper(ProcesoPeriodo pperiodo){

        template.update("  insert into iexproperiodo(iexcodcia, iexcodpro, iexnroper, iexpermes, iexfecini, iexfecfin, timerfecini, timerfecfin, iexfecpago, flgestado, iexfecope, iexanio , iexfeccerti) values (?,?,?,?,to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'),to_date(?,'DD/MM/YYYY'), to_date(?,'DD/MM/YYYY'), ?,current_date ,?, to_date(?,'DD/MM/YYYY')) ",

        pperiodo.getIexcodcia(),
        pperiodo.getIexcodpro(),
        pperiodo.getIexnroper(),
        pperiodo.getIexpermes(),
        pperiodo.getIexfecini(),
        pperiodo.getIexfecfin(),
        pperiodo.getTimerfecini(),
        pperiodo.getTimerfecfin(),
        pperiodo.getIexfecpago(),
        "1",
        pperiodo.getIexanio(),
        pperiodo.getIexfeccerti());
    }

    @Override
    public ProcesoPeriodo recuperarPeriodo2(Integer codcia, Integer idproceso, String pperiodo) {

        String sql=" select  " +
                "e.iexcodcia, " +
                "e.iexcodpro, " +
                "p.prodespro," +
                "t.desdet," +
                "e.iexnroper, e.iexpermes," +
                "to_char(e.iexfecini,'DD/MM/YYYY') iexfecini, " +
                "to_char(e.iexfecfin,'DD/MM/YYYY') iexfecfin, " +
                "TO_CHAR(e.timerfecini,'DD/MM/YYYY') timerfecini, " +
                "TO_CHAR(e.timerfecfin,'DD/MM/YYYY') timerfecfin, " +
                "TO_CHAR(e.iexfecpago,'DD/MM/YYYY') iexfecpago, " +
                "to_char(e.timeini_iniciar,'yy-mm-dd hh24:mi:ss') timeini_iniciar,"+
                "to_char(e.timefin_iniciar,'yy-mm-dd hh24:mi:ss') timefin_iniciar,"+
                "timenroimp, "+
                "to_char(e.timeini_proc,'yy-mm-dd hh24:mi:ss') timeini_proc,"+
                "to_char(e.timefin_proc,'yy-mm-dd hh24:mi:ss') timefin_proc, "+
                "timenroimp_proc, "+
                "e.flgestado, "+
                "   case e.flgestado " +
                "   when  '1' Then 'Iniciado' " +
                "   when  '2' Then 'Procesado' " +
                "   when  '3' Then 'Cerrado' " +
                "   when  '0' Then 'Creado' " +
                "   end desestado, " +
                " e.iexfecope, e.iexanio , e.utiltotal , p.progrppro, to_char(e.iexfeccerti,'DD/MM/YYYY') iexfeccerti , p.procodregimenlab, e.iextcmb  " +
                " from iexproperiodo e, iexprocesos p , (  " +
                " select  iexkey, desdet from  iexttabled where iexcodtab='33'   " +
                " ) t  " +
                " where " +
                " e.iexcodpro =  p.procodpro and  " +
                " p.procodregimenlab= t.iexkey and  " +
                " e.iexcodcia= "+codcia+" AND " +
                " e.iexcodpro="+idproceso+" and  " +
                " e.iexnroper='"+pperiodo+"' ";

        return (ProcesoPeriodo) template.query(sql, new ResultSetExtractor<ProcesoPeriodo>() {
            public ProcesoPeriodo extractData(ResultSet rs) throws SQLException, DataAccessException{
                ProcesoPeriodo p = new ProcesoPeriodo();

                while(rs.next()) {
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodpro(rs.getInt("iexcodpro"));
                    p.setIexanio(rs.getString("iexanio"));
                    p.setIexnroper(rs.getString("iexnroper"));
                    p.setIexpermes(rs.getString("iexpermes"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setTimerfecini(rs.getString("timerfecini"));
                    p.setTimerfecfin(rs.getString("timerfecfin"));
                    p.setIexfecpago(rs.getString("iexfecpago"));
                    p.setFlgestado(rs.getString("flgestado"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setIexfecope(rs.getString("iexfecope"));

                    p.setTimerfecini_iniciar(rs.getString("timeini_iniciar"));
                    FormatterFecha f = new FormatterFecha();
                    p.setTimerfecini_iniciar(f.fechaFormatterIngltoEspConHora(p.getTimerfecini_iniciar()));

                    p.setTimerfecfin_iniciar(rs.getString("timefin_iniciar"));
                    FormatterFecha f2 = new FormatterFecha();
                    p.setTimerfecfin_iniciar(f2.fechaFormatterIngltoEspConHora(p.getTimerfecfin_iniciar()));

                    p.setTimerimp_iniciar(rs.getDouble("timenroimp"));

                    p.setTimerfecini_proc(rs.getString("timeini_proc"));
                    FormatterFecha f3 = new FormatterFecha();
                    p.setTimerfecini_proc(f3.fechaFormatterIngltoEspConHora(p.getTimerfecini_proc()));

                    p.setTimerfecfin_proc(rs.getString("timefin_proc"));
                    FormatterFecha f4 = new FormatterFecha();
                    p.setTimerfecfin_proc(f4.fechaFormatterIngltoEspConHora(p.getTimerfecfin_proc()));

                    p.setTimerimp_proc(rs.getDouble("timenroimp_proc"));
                    p.setDesproceso(rs.getString("prodespro"));

                    p.setDesregimen(rs.getString("desdet"));
                    CapitalizarCadena cap = new CapitalizarCadena();
                    p.setDesregimen(cap.letras(p.getDesregimen()));

                    p.setUtiltotal(rs.getDouble("utiltotal"));
                    p.setDesgrppla(rs.getString("progrppro"));
                    p.setIexfeccerti(rs.getString("iexfeccerti"));
                    p.setCodregimen(rs.getString("procodregimenlab"));
                    p.setTcmb(rs.getDouble("iextcmb"));
                }
                return p;
            }
        });
    }

    public void actualizarProper(ProcesoPeriodo pperiodo){

        template.update(" update iexproperiodo  set  iexpermes=?, iexfecini=TO_DATE(?,'DD/MM/YYYY'), iexfecfin=TO_DATE(?,'DD/MM/YYYY'), timerfecini=TO_DATE(?,'DD/MM/YYYY'), timerfecfin=TO_DATE(?,'DD/MM/YYYY'), iexfecpago=TO_DATE(?,'DD/MM/YYYY'), flgestado=?, iexfecope=current_date, iexanio=?, iexfeccerti=TO_DATE(?,'DD/MM/YYYY')  where  iexcodcia=? and iexcodpro=? and  iexnroper=? ",

        pperiodo.getIexpermes(),
        pperiodo.getIexfecini(),
        pperiodo.getIexfecfin(),
        pperiodo.getTimerfecini(),
        pperiodo.getTimerfecfin(),
        pperiodo.getIexfecpago(),
        pperiodo.getFlgestado(),
        pperiodo.getIexanio(),
        pperiodo.getIexfeccerti(),
        pperiodo.getIexcodcia(),
        pperiodo.getIexcodpro(),
        pperiodo.getIexnroper());
    }

}
