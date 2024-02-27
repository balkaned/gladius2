package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.*;
import com.balkaned.gladius.dao.AusentismoDao;
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


@Repository("AusentismoDao")
@Slf4j
public class AusentismoDaoImpl implements AusentismoDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<AusentismoProgramacion> listarAusentismoPrg(Empleado empleado) {

        String sql = " select  " +
                "v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipaus , d.desdet as destipaus, v.iexglosa,  " +
                " v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                " from " +
                " iexausprg v, " +
                "( " +
                " select  iexkey, desdet from iexttabled where iexcodtab='57' " +
                ") d " +
                " where " +
                " v.iexcodcia=" + empleado.getIexcodcia() + " and v.iexcodtra=" + empleado.getIexcodtra() + " and " +
                " v.iextipaus = d.iexkey ";
        return template.query(sql, new ResultSetExtractor<List<AusentismoProgramacion>>() {
            public List<AusentismoProgramacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AusentismoProgramacion> lista = new ArrayList<AusentismoProgramacion>();

                while (rs.next()) {
                    AusentismoProgramacion p = new AusentismoProgramacion();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));

                    p.setIexfecini(rs.getString("iexfecini"));
                    FormatterFecha f = new FormatterFecha();
                    CapitalizarCadena capit = new CapitalizarCadena();
                    p.setIexfecini(f.fechaFormatterDia(p.getIexfecini()) + " " + capit.letras(f.fechaFormatterMes(p.getIexfecini())) + ", " + f.fechaFormatterAnio(p.getIexfecini()));

                    p.setIexfecfin(rs.getString("iexfecfin"));
                    FormatterFecha f2 = new FormatterFecha();
                    CapitalizarCadena capit2 = new CapitalizarCadena();
                    p.setIexfecfin(f2.fechaFormatterDia(p.getIexfecfin()) + " " + capit2.letras(f2.fechaFormatterMes(p.getIexfecfin())) + ", " + f2.fechaFormatterAnio(p.getIexfecfin()));

                    p.setIexnrodias(rs.getDouble("iexnrodias"));
                    p.setIextipaus(rs.getString("iextipaus"));
                    p.setDestipaus(rs.getString("destipaus"));
                    p.setIexglosa(rs.getString("iexglosa"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer getIdAusentismoPrg(AusentismoProgramacion ausprg) {

        final Integer[] idfinal = {0};

        String sql = " select  coalesce(max(iexcorrel),0)+1 as idex from iexausprg where iexcodcia=" + ausprg.getIexcodcia() + " and iexcodtra=" + ausprg.getIexcodtra() + " ";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    idfinal[0] = rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public Integer validaAus(Integer codcia, Integer codtra, String fecini, String fecfin, Integer iexcorrel) {

        final Integer[] valor = {0};

        String sql = "  select " +
                "	sum(e.dias) dias " +
                "  from (  " +
                "	 select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin and iexcorrel<>" + iexcorrel + " " +
                "	  union " +
                "	  select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  <= iexfecfin and iexcorrel<>" + iexcorrel + " " +
                "  union " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecini  and iexcorrel<>" + iexcorrel + "  " +
                " union   " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecfin  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin  and iexcorrel<>" + iexcorrel + "  " +
                " union  " +
                " select coalesce(count(iexcorrel),0) dias from iexausprg where iexcodcia=" + codcia + " and iexcodtra=" + codtra + " and to_date('" + fecini + "','dd/mm/yyyy')  <= iexfecini  and  to_date('" + fecfin + "','dd/mm/yyyy')  >= iexfecfin   and iexcorrel<>" + iexcorrel + "  " +
                "																  ) e";
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
                while (rs.next()) {
                    valor[0] = rs.getInt("dias");
                }
                return valor[0];
            }
        });
    }

    public void insertarAusentismoPrg(AusentismoProgramacion ausprg) {

        template.update("  insert into iexausprg ( " +
                        "iexcodcia, iexcodtra, iexcorrel, iexfecini, iexfecfin, iexnrodias,  " +
                        "iextipaus , iexglosa, iexusucrea, iexfeccrea " +
                        ") values ( " +
                        "  ?,   ? ,  ?,    to_date(?,'DD/MM/YYYY'),   to_date(?,'DD/MM/YYYY') ,  ?  ,   " +
                        "  ?,   ? ,  ?,  current_date " +
                        ")  ",

                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel(),
                ausprg.getIexfecini(),
                ausprg.getIexfecfin(),
                ausprg.getIexnrodias(),
                ausprg.getIextipaus(),
                ausprg.getIexglosa(),
                ausprg.getIexusucrea());
    }


    public List<AusentismoProgramacion> listaAusentismoGen(Integer codcia, String regimen, String fecini, String fecfin, Integer codtra) {
        String sql = "  select  " +
                "	 c.iexcodcia, " +
                "	 c.iexcodtra, " +
                "	  c.iexapepat||' '|| c.iexapemat||' '|| c.iexnomtra  nomtra, " +
                "    case   " +
                "    when iexflgest='1' then 'activo'  " +
                "    when iexflgest='0' then 'inactivo'  " +
                "    else 'inactivo' end desestado, " +
                "	 to_char(c.iexfecing,'dd/mm/yyyy') fecing, " +
                "	 c.iexnrodoc, " +
                "	 a.iextipaus, " +
                "	 a.iexcorrel as aus_id, " +
                "	 to_char(a.iexfecini,'dd/mm/yyyy') iexfecini, " +
                "	 to_char(a.iexfecfin,'dd/mm/yyyy') iexfecfin, " +
                " 	 case " +
                "	  when (a.iexfecini >=to_date('" + fecini + "','dd/mm/yyyy') and  a.iexfecini <= to_date('" + fecfin + "','dd/mm/yyyy')  and  a.iexfecfin <= to_date('" + fecfin + "','dd/mm/yyyy') )      " +
                "	    then  (a.iexfecfin -  a.iexfecini) +1  " +
                "      when (a.iexfecini < to_date('" + fecini + "','dd/mm/yyyy')  and    a.iexfecfin <= to_date('" + fecfin + "','dd/mm/yyyy') )     " +
                "	    then  (a.iexfecfin -  to_date('" + fecini + "','dd/mm/yyyy') ) +1	" +
                "	  when (a.iexfecini >= to_date('" + fecini + "','dd/mm/yyyy')  and  a.iexfecini <= to_date('" + fecfin + "','dd/mm/yyyy')   and  a.iexfecfin >  to_date('" + fecfin + "','dd/mm/yyyy') )    " +
                "	    then  ( to_date('" + fecfin + "','dd/mm/yyyy')  -  a.iexfecini) +1	  " +
                "	  when (a.iexfecini < to_date('" + fecini + "','dd/mm/yyyy')  and   a.iexfecfin >  to_date('" + fecfin + "','dd/mm/yyyy') )         " +
                "	    then  ( to_date('" + fecfin + "','dd/mm/yyyy') - to_date('" + fecini + "','dd/mm/yyyy') ) +1  " +
                "	 end  dias_aus,  " +
                " case " +
                "	  when (a.iexfecini >=to_date('" + fecini + "','dd/mm/yyyy') and  a.iexfecini <= to_date('" + fecfin + "','dd/mm/yyyy')  and  a.iexfecfin <= to_date('" + fecfin + "','dd/mm/yyyy') )      " +
                "	    then   to_char(a.iexfecfin,'dd/mm/yyyy')    " +
                "      when (a.iexfecini < to_date('" + fecini + "','dd/mm/yyyy')  and    a.iexfecfin <= to_date('" + fecfin + "','dd/mm/yyyy') )     " +
                "	    then  to_char(a.iexfecfin,'dd/mm/yyyy') 	" +
                "	  when (a.iexfecini >= to_date('" + fecini + "','dd/mm/yyyy')  and  a.iexfecini <= to_date('" + fecfin + "','dd/mm/yyyy')   and  a.iexfecfin >  to_date('" + fecfin + "','dd/mm/yyyy') )    " +
                "	    then   '" + fecfin + "' 	  " +
                "	  when (a.iexfecini < to_date('" + fecini + "','dd/mm/yyyy')  and   a.iexfecfin >  to_date('" + fecfin + "','dd/mm/yyyy') )         " +
                "	    then  '" + fecfin + "'  " +
                "	 end  fecfinrep,  " +
                "	 k.des1det codcon ,   k.desdet destipaus   " +
                "	 from iexempleado c, " +
                "	 iexausprg a  " +
                "	  full outer join ( " +
                "	  select  " +
                "          iexkey, desdet, des1det  " +
                "        from iexttabled where iexcodtab='57'	  " +
                "	  ) k  on  a.iextipaus = k.iexkey " +
                "	 where " +
                "	 c.iexcodcia = a.iexcodcia and  " +
                "	 c.iexcodtra = a.iexcodtra and	 " +
                "	 c.iexcodcia=" + codcia + " and   c.iexreglab='" + regimen + "'	 and " +
                "	 (  " +
                "		 (a.iexfecini >=to_date('" + fecini + "','dd/mm/yyyy') and  a.iexfecini <=to_date('" + fecfin + "','dd/mm/yyyy') )  " +
                "		or   " +
                "	      (a.iexfecfin >=to_date('" + fecini + "','dd/mm/yyyy')  and  a.iexfecfin <=to_date('" + fecfin + "','dd/mm/yyyy') )  " +
                "		 or  " +
                "		 (a.iexfecini <to_date('" + fecini + "','dd/mm/yyyy')  and  a.iexfecfin >to_date('" + fecfin + "','dd/mm/yyyy') )  " +
                "																					  )   ";

        if (codtra != null && codtra.intValue() != 0) {
            sql += " and c.iexcodtra = " + codtra + " ";
        }
        sql += " order by 3,4 asc ";
        return template.query(sql, new ResultSetExtractor<List<AusentismoProgramacion>>() {
            public List<AusentismoProgramacion> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<AusentismoProgramacion> lista = new ArrayList<AusentismoProgramacion>();

                while (rs.next()) {
                    AusentismoProgramacion p = new AusentismoProgramacion();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("aus_id"));
                    p.setNrodoc(rs.getString("iexnrodoc"));
                    p.setDesnomtra(rs.getString("nomtra"));

                    p.setFecing(rs.getString("fecing"));
                    FormatterFecha f = new FormatterFecha();
                    CapitalizarCadena capit = new CapitalizarCadena();
                    p.setFecing(f.fechaFormatterDia(p.getFecing()) + " " + capit.letras(f.fechaFormatterMes(p.getFecing())) + ", " + f.fechaFormatterAnio(p.getFecing()));

                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexnrodias(rs.getDouble("dias_aus"));
                    p.setFecfinrep(rs.getString("fecfinrep"));
                    p.setDesestado(rs.getString("desestado"));
                    p.setDestipaus(rs.getString("destipaus"));


                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public AusentismoProgramacion getAusentismoPrg(AusentismoProgramacion ausprg) {

        String sql = " select  " +
                "v.iexcodcia, v.iexcodtra, v.iexcorrel, to_char(v.iexfecini,'DD/MM/YYYY') as iexfecini, to_char(v.iexfecfin,'DD/MM/YYYY') as iexfecfin, "
                + " v.iexnrodias, v.iextipaus , d.desdet as destipaus, v.iexglosa,  " +
                " v.iexusucrea, to_char(v.iexfeccrea,'DD/MM/YYYY') as iexfeccrea, v.iexusumod, to_char(v.iexfecmod,'DD/MM/YYYY') as iexfecmod " +
                "from " +
                "iexausprg v, " +
                "( " +
                "select  iexkey, desdet from iexttabled where iexcodtab='57' " +
                ") d " +
                " where " +
                " v.iexcodcia=" + ausprg.getIexcodcia() + " and v.iexcodtra=" + ausprg.getIexcodtra() + " and v.iexcorrel=" + ausprg.getIexcorrel() + " and " +
                " v.iextipaus = d.iexkey ";
        return (AusentismoProgramacion) template.query(sql, new ResultSetExtractor<AusentismoProgramacion>() {
            public AusentismoProgramacion extractData(ResultSet rs) throws SQLException, DataAccessException {
                AusentismoProgramacion p = new AusentismoProgramacion();
                while (rs.next()) {

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcorrel(rs.getInt("iexcorrel"));
                    p.setIexfecini(rs.getString("iexfecini"));
                    p.setIexfecfin(rs.getString("iexfecfin"));
                    p.setIexnrodias(rs.getDouble("iexnrodias"));
                    p.setIextipaus(rs.getString("iextipaus"));
                    p.setDestipaus(rs.getString("destipaus"));
                    p.setIexglosa(rs.getString("iexglosa"));
                    p.setIexusucrea(rs.getString("iexusucrea"));
                    p.setIexfeccrea(rs.getString("iexfeccrea"));
                    p.setIexusumod(rs.getString("iexusumod"));
                    p.setIexfecmod(rs.getString("iexfecmod"));


                }
                return p;
            }
        });
    }


    public void actualizarAusentismoPrg(AusentismoProgramacion ausprg) {

        template.update("  update iexausprg set  " +
                        " iexfecini=to_date(?,'DD/MM/YYYY') , iexfecfin=to_date(?,'DD/MM/YYYY') , iexnrodias=? ,  " +
                        "iextipaus=?  , iexglosa=? , iexusumod=? , iexfecmod=current_date  " +
                        " where iexcodcia=?  and  iexcodtra=?  and iexcorrel=?  ",

                ausprg.getIexfecini(),
                ausprg.getIexfecfin(),
                ausprg.getIexnrodias(),
                ausprg.getIextipaus(),
                ausprg.getIexglosa(),
                ausprg.getIexusumod(),
                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel());

    }

    public void eliminarAusentismoPrg(AusentismoProgramacion ausprg) {

        template.update("  delete from  iexausprg  where iexcodcia=?  and  iexcodtra=?  and iexcorrel=? ",
                ausprg.getIexcodcia(),
                ausprg.getIexcodtra(),
                ausprg.getIexcorrel());

    }

}
