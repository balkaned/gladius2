package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.DerechoHabiente;
import com.balkaned.gladius.beans.RetencionJudicial;
import com.balkaned.gladius.dao.DerechoHabientesDao;
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

@Repository("DerechoHabientesDao")
public class DerechoHabientesDaoImpl implements DerechoHabientesDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource){
        template = new JdbcTemplate(datasource);
    }

    public List<DerechoHabiente> listar(Integer codcia, Integer codtra){

        String sql = " select d.iexcodcia, " +
                "d.iexcodtra, " +
                "d.iexcoddep, " +
                "d.iextipnroiddep, " +
                "j.desdet destipnroiddep, " +
                "d.iexnroiddep, " +
                "d.iexpaisemisor, " +
                "d.iexfecnac, " +
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
                " d.iexcodcia = p.iexcodcia and  " +
                " d.iexcodtra = p.iexcodtra  and " +
                " d.iextipvinculo = g.iexkey and " +
                " d.iextipnroiddep = j.iexkey  and "
                + " d.iexcodcia = "+codcia+" and "
                + " d.iexcodtra = "+codtra+"  "  ;
        return template.query(sql, new ResultSetExtractor<List<DerechoHabiente>>() {
            public List<DerechoHabiente> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<DerechoHabiente> lista = new ArrayList<DerechoHabiente>();

                while (rs.next()) {
                    DerechoHabiente p = new DerechoHabiente();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcoddep(rs.getInt("iexcoddep"));
                    p.setIextipnroiddep(rs.getString("iextipnroiddep"));
                    p.setDestipnroiddep(rs.getString("destipnroiddep"));
                    p.setIexnroiddep(rs.getString("iexnroiddep"));
                    p.setIexpaisemisor(rs.getString("iexpaisemisor"));
                    p.setIexfecnac(rs.getString("iexfecnac"));
                    p.setIexapepatdep(rs.getString("iexapepatdep"));
                    p.setIexapematdep(rs.getString("iexapematdep"));
                    p.setIexnomdep(rs.getString("iexnomdep"));
                    p.setIexsexo(rs.getString("iexsexo"));
                    p.setIextipvinculo(rs.getString("iextipvinculo"));
                    p.setDestipvinculo(rs.getString("destipvinculo"));
                    p.setIextipdocacredit(rs.getString("iextipdocacredit"));
                    p.setIexnrodocacredit(rs.getString("iexnrodocacredit"));
                    p.setIexmesconcep(rs.getString("iexmesconcep"));
                    p.setIextipvia_dom1(rs.getString("iextipvia_dom1"));
                    p.setIexnomvia_dom1(rs.getString("iexnomvia_dom1"));
                    p.setIexnrovia_dom1(rs.getString("iexnrovia_dom1"));
                    p.setIexdeptin_dom1(rs.getString("iexdeptin_dom1"));
                    p.setIexinterior_dom1(rs.getString("iexinterior_dom1"));
                    p.setIexmanzana_dom1(rs.getString("iexmanzana_dom1"));
                    p.setIexlote_dom1(rs.getString("iexlote_dom1"));
                    p.setIexkilometro_dom1(rs.getString("iexkilometro_dom1"));
                    p.setIexblock_dom1(rs.getString("iexblock_dom1"));
                    p.setIexetapa_dom1(rs.getString("iexetapa_dom1"));
                    p.setIextipzona_dom1(rs.getString("iextipzona_dom1"));
                    p.setIexnomzona_dom1(rs.getString("iexnomzona_dom1"));
                    p.setIexreferencia_dom1(rs.getString("iexreferencia_dom1"));
                    p.setIexubigeo_dom1(rs.getString("iexubigeo_dom1"));
                    p.setIextipvia_dom2(rs.getString("iextipvia_dom2"));
                    p.setIexnomvia_dom2(rs.getString("iexnomvia_dom2"));
                    p.setIexnrovia_dom2(rs.getString("iexnrovia_dom2"));
                    p.setIexdeptin_dom2(rs.getString("iexdeptin_dom2"));
                    p.setIexinterior_dom2(rs.getString("iexinterior_dom2"));
                    p.setIexmanzana_dom2(rs.getString("iexmanzana_dom2"));
                    p.setIexlote_dom2(rs.getString("iexlote_dom2"));
                    p.setIexkilometro_dom2(rs.getString("iexkilometro_dom2"));
                    p.setIexblock_dom2(rs.getString("iexblock_dom2"));
                    p.setIexetapa_dom2(rs.getString("iexetapa_dom2"));
                    p.setIextipzona_dom2(rs.getString("iextipzona_dom2"));
                    p.setIexnomzona_dom2(rs.getString("iexnomzona_dom2"));
                    p.setIexreferencia_dom2(rs.getString("iexreferencia_dom2"));
                    p.setIexubigeo_dom2(rs.getString("iexubigeo_dom2"));
                    p.setIexcenasis(rs.getString("iexcenasis"));
                    p.setIexcodlar(rs.getString("iexcodlar"));
                    p.setIexnrotelf(rs.getString("iexnrotelf"));
                    p.setIexemail(rs.getString("iexemail"));

                    lista.add(p);
                }
                return lista;
            }
        });
    }

    public Integer validarDerhabiente(DerechoHabiente derhab){

        String sql = " select d.iexcodcia, " +
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
                " where " +
                " d.iexcodcia = p.iexcodcia and  " +
                " d.iexcodtra = p.iexcodtra  and " +
                " d.iextipvinculo = g.iexkey and " +
                " d.iextipnroiddep = j.iexkey  and "
                + " d.iexcodcia = "+derhab.getIexcodcia()+" and "
                + " d.iexcodtra = "+derhab.getIexcodtra()+" and  d.iextipnroiddep= '"+derhab.getIextipnroiddep()+"'  and  d.iexnroiddep='"+derhab.getIexnroiddep()+"' ";

        final DerechoHabiente[] p = {null};
        final Integer[] count = {0};
        Integer idfinal=0;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                List<DerechoHabiente> lista = new ArrayList<DerechoHabiente>();
                while(rs.next()) {
                    DerechoHabiente p = new DerechoHabiente();

                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));
                    p.setIexcoddep(rs.getInt("iexcoddep"));
                    p.setIextipnroiddep(rs.getString("iextipnroiddep"));
                    p.setDestipnroiddep(rs.getString("destipnroiddep"));
                    p.setIexnroiddep(rs.getString("iexnroiddep"));
                    p.setIexpaisemisor(rs.getString("iexpaisemisor"));
                    p.setIexfecnac(rs.getString("iexfecnac"));
                    p.setIexapepatdep(rs.getString("iexapepatdep"));
                    p.setIexapematdep(rs.getString("iexapematdep"));
                    p.setIexnomdep(rs.getString("iexnomdep"));

                    lista.add(p);
                    count[0]++;
                }
                return count[0];
            }
        });
    }

    public Integer getIdDerechoHab(DerechoHabiente derhab){

        final Integer[] idfinal = {0};

        String sql = " select coalesce(max(iexcoddep),0)+1 as idex from iexempderhab where iexcodcia="+derhab.getIexcodcia()+" and iexcodtra="+derhab.getIexcodtra()+" " ;
        return (Integer) template.query(sql, new ResultSetExtractor<Integer>() {
            public Integer extractData(ResultSet rs) throws SQLException, DataAccessException{
                while(rs.next()) {
                    idfinal[0] =rs.getInt("idex");
                }
                return idfinal[0];
            }
        });
    }

    public void insertar(DerechoHabiente derhab){

        template.update(" insert into iexempderhab(   "+
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
                " iexcenasis,          iexcodlar,             iexnrotelf,        iexemail , "+
                "  iexnacion_origen1, " +
                "    iexdepart_origen1 , " +
                "    iexprovin_origen1 , " +
                "    iexnacion_origen2 , " +
                "    iexdepart_origen2 , " +
                "    iexprovin_origen2 "+
                " ) values "+
                "  (   ? ,  ? , ?,   ?,   ?,  "+
                "  ? ,  to_date(?,'DD/MM/YYYY'),  ?,   ?,   ?,  "+
                "  ? ,  ?,  ?,   ?,   ?,   "+
                "  ?,   ?,  ?  , "+
                "  ?,   ?,  ?  , "+
                "  ?,   ?,  ?  , "+
                "  ?,   ?,  ?  , "+
                "  ?,   ?,            "+
                "  ?,   ?,  ?  , ? ,  "+
                "  ?,   ?,  ?  , ? ,  "+
                "  ?,   ?,  ?  , ? ,  "+
                "  ?,   ?,             "+
                "  ?,   ?,  ?  , ? ,   "+
                "  ?, ?, ?, ?, ?, ? ) ",

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

}
