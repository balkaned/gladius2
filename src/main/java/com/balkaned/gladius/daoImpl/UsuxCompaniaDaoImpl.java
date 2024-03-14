package com.balkaned.gladius.daoImpl;


import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuarioxRol;
import com.balkaned.gladius.beans.UsuxCompania;
import com.balkaned.gladius.dao.UsuxCompaniaDao;
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


@Repository("UsuxCompaniaDao")
@Slf4j
public class UsuxCompaniaDaoImpl implements UsuxCompaniaDao {

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public List<UsuxCompania> listar(Integer codusu) {

        String sql = " select  "
                + " u.IEXCODCIA    codcia,  "
                + "  c.IEXDESCIA    descia, "
                + "  c.IEXNRORUC    nroruc, "
                + "  c.IEXDESCORTO  descorto, "
                + "  c.IEXDIRECCION direccion, "
                + "  c.IEXNROTELF   telefono, "
                + "  c.IEXCODACT    codactividad, "
                + "  c.IEXREPNOMBRE nombreRepresentante, "
                + "  c.IEXREPCARGO  cargoRepresentante, "
                + "    c.IEXREPDOCID  nrodocRepresentante, "
                + "    c.IEXREPLOGO   urllogo, "
                + "    u.IEXCODROL    codrol, "
                + "    r.IEXDESROL    desrol, "
                + "    u.IEXUSUCRE    usucrea, "
                + "    u.IEXFECCRE    feccrea, "
                + "   u.IEXUSUMOD    usumod, "
                + "    u.IEXFECMOD    fecmod,  "
                + "    u.IEXCODUSU    codusu ,   COALESCE ( u.iexcodtra , null , 0 ) iexcodtra ,  iexapepat||' '||iexapemat||' '||iexnomtra as destra  "
                + "    from  public.IEXCOMPANIA  c "
                + "	INNER JOIN IEXUSUXCIA  u ON  c.IEXCODCIA=u.IEXCODCIA "
                + "  INNER JOIN public.IEXROLES  r  ON  u.IEXCODROL=r.IEXCODROL "
                + "  left outer JOIN iexempleado  e  ON  u.IEXCODcia=e.IEXCODcia and u.iexcodtra = e.iexcodtra "
                + "    where u.IEXCODUSU=" + codusu + " ";

        return template.query(sql, new ResultSetExtractor<List<UsuxCompania>>() {

            public List<UsuxCompania> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<UsuxCompania> lista = new ArrayList<UsuxCompania>();

                while (rs.next()) {
                    UsuxCompania usuxcia = new UsuxCompania();

                    usuxcia.setCodcia(rs.getInt("codcia"));
                    usuxcia.setDescia(rs.getString("descia"));
                    usuxcia.setNroruc(rs.getString("nroruc"));
                    usuxcia.setDescorto(rs.getString("descorto"));
                    usuxcia.setDireccion(rs.getString("direccion"));
                    usuxcia.setTelefono(rs.getString("telefono"));
                    usuxcia.setCodactividad(rs.getString("codactividad"));
                    usuxcia.setNombrerepresentate(rs.getString("nombreRepresentante"));
                    usuxcia.setCargorepresentante(rs.getString("cargoRepresentante"));
                    usuxcia.setNrodocrepresentante(rs.getString("nrodocRepresentante"));
                    usuxcia.setUrllogo(rs.getString("urllogo"));
                    usuxcia.setCodrol(rs.getInt("codrol"));
                    usuxcia.setDesrol(rs.getString("desrol"));
                    usuxcia.setUsucreades(rs.getString("usucrea"));

                    usuxcia.setFeccrea(rs.getString("feccrea"));
                    FormatterFecha f = new FormatterFecha();
                    usuxcia.setFeccrea(f.fechaFormatterIngltoEsp(usuxcia.getFeccrea()));

                    usuxcia.setUsumod(rs.getInt("usumod"));
                    usuxcia.setFecmod(rs.getString("fecmod"));
                    usuxcia.setCodusu(rs.getInt("codusu"));
                    usuxcia.setCodtra(rs.getInt("iexcodtra"));
                    usuxcia.setDestra(rs.getString("destra"));

                    lista.add(usuxcia);
                }
                return lista;
            }
        });
    }

    public List<Empleado> listaTrabajadoresCia(Integer codcia) {

        String sql = " select  " +
                "iexcodtra, " +
                "iexapepat, iexapemat, iexnomtra, " +
                " to_char(iexfecing,'dd/mm/yyyy') as fecing " +
                "from iexempleado where iexcodcia=" + codcia + " and iexflgest='1'   order by 2,3,4 asc ";

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

    public void insertar(UsuxCompania usuxcia) {

        template.update("  call pl_gestion_rolciaxusu(?,?,?,? , ? )  ",

                usuxcia.getCodusu(),
                usuxcia.getCodcia(),
                usuxcia.getCodrol(),
                usuxcia.getCodtra(),
                "1");
    }

    public void eliminar(UsuxCompania usuxcia) {

        template.update("  call pl_gestion_rolciaxusu(?,?,?,? , ? )  ",

                usuxcia.getCodusu(),
                usuxcia.getCodcia(),
                usuxcia.getCodrol(),
                0,
                "3");
    }

    public UsuarioxRol obtenerRolxUsuario(Integer codcia, Integer codusu) {

        String sql = "select " +
                "u.iexcodusu, " +
                "u.iexdesusu, " +
                "com.iexcodcia, " +
                "com.iexdescia, " +
                "r.iexcodrol, " +
                "r.iexdesrol, " +
                "uc.iexcodtra " +
                "from iexusuario u " +
                "inner join iexusuxcia uc on uc.iexcodusu=u.iexcodusu " +
                "inner join iexcompania com on com.iexcodcia=uc.iexcodcia " +
                "inner join iexroles r on r.iexcodrol=uc.iexcodrol " +
                "where com.iexcodcia=" + codcia + " " +
                "and u.iexcodusu=" + codusu + " ";

        return (UsuarioxRol) template.query(sql, new ResultSetExtractor<UsuarioxRol>() {
            public UsuarioxRol extractData(ResultSet rs) throws SQLException, DataAccessException {
                UsuarioxRol p = new UsuarioxRol();
                while (rs.next()) {
                    p.setIexcodusu(rs.getInt("iexcodusu"));
                    p.setIexdesusu(rs.getString("iexdesusu"));
                    p.setIexcodcia(rs.getInt("iexcodcia"));
                    p.setIexdescia(rs.getString("iexdescia"));
                    p.setIexcodrol(rs.getInt("iexcodrol"));
                    p.setIexdesrol(rs.getString("iexdesrol"));
                    p.setIexcodtra(rs.getInt("iexcodtra"));

                }
                return p;
            }
        });
    }


}
