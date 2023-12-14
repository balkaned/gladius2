package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.IndexController;
import com.balkaned.gladius.beans.Empleado;
import com.balkaned.gladius.beans.UsuxCompania;
import com.balkaned.gladius.beans.UsuxOpciones;
import com.balkaned.gladius.dao.UsuxCompaniaDao;
import com.balkaned.gladius.dao.UsuxOpcionesDao;
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

@Repository("UsuxOpcionesDao")
public class UsuxOpcionesDaoImpl implements UsuxOpcionesDao {

    static Logger logger = Logger.getLogger(IndexController.class.getName());

    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    public UsuxOpciones ObtieneAccesoOpcion(Integer codcia, Integer codusu, Integer codopc) {

        String sql = "SELECT " +
                "                        O.IEXURLOPC, " +
                "                        O.IEXCODOPC , " +
                "                        O.IEXDESCRIPCION , " +
                "                         IEX_CONSULTAR, " +
                "                        IEX_REGISTRAR, " +
                "                        IEX_MODIFICAR," +
                "                        IEX_ELIMINAR, " +
                "                        IEX_DESCARGAR_PDF, " +
                "                        IEX_DESCARGAR_XLS, " +
                "                        O.IEXCODSEC, " +
                "        S.IEXDESSEC, " +
                "                        O.IEXACTION " +
                "                        FROM " +
                "						IEXUSUXCIA C  " +
                "						inner join  IEXROLXOPC R on   C.IEXCODROL=R.IEXCODROL " +
                "						inner join  IEXOPCIONES O  on R.IEXCODOPC=O.IEXCODOPC " +
                "                                               inner join  IEXSECCION S on O.IEXCODSEC = S.IEXCODSEC " +
                "						WHERE " +
                "                        C.IEXCODCIA=" + codcia + " AND  " +
                "                        C.IEXCODUSU=" + codusu + " AND  " +
                "                        R.IEXCODOPC=" + codopc + " ";

        return (UsuxOpciones) template.query(sql, new ResultSetExtractor<UsuxOpciones>() {

            public UsuxOpciones extractData(ResultSet rs) throws SQLException, DataAccessException {

                UsuxOpciones usuxopc = new UsuxOpciones();
                while (rs.next()) {
                    usuxopc.setUrlopc(rs.getString("iexurlopc"));
                    usuxopc.setCodopc(rs.getInt("iexcodopc"));
                    usuxopc.setDesopc(rs.getString("iexdescripcion"));
                    usuxopc.setConsultarOpc(rs.getString("iex_consultar"));
                    usuxopc.setRegistrarOpc(rs.getString("iex_registrar"));
                    usuxopc.setModificarOpc(rs.getString("iex_modificar"));
                    usuxopc.setEliminarOpc(rs.getString("iex_eliminar"));
                    usuxopc.setDescargarPdfOpc(rs.getString("iex_descargar_pdf"));
                    usuxopc.setDescargarXlsOpc(rs.getString("iex_descargar_xls"));
                    usuxopc.setCodsec(rs.getInt("iexcodsec"));
                    usuxopc.setDessec(rs.getString("iexdessec"));
                    usuxopc.setDesaction(rs.getString("iexaction"));

                }
                return usuxopc;
            }
        });
    }

    public List<UsuxOpciones> listarOpciones(Integer codcia, Integer codusu, Integer codsys) {

        String sql = "SELECT  " +
                "                        U.IEXCODCIA, " +
                "                        U.IEXCODUSU," +
                "                        U.IEXCODROL," +
                "                        R.IEXCODOPC," +
                "                        O.IEXDESOPC," +
                "                        O.IEXURLOPC," +
                "                        O.IEXURLIMG," +
                "                        S.IEXCODSEC," +
                "                        S.IEXDESSEC," +
                "                        S.IEXSECIMG," +
                "                        S.IEXORDSEC," +
                "                        Y.IEXDESSYS," +
                "                        CASE " +
                "                           WHEN S.IEXCODSEC = '1' THEN 'settings' " +
                "                           WHEN S.IEXCODSEC = '2' THEN 'grid' " +
                "                           WHEN S.IEXCODSEC = '3' THEN 'users' " +
                "                           WHEN S.IEXCODSEC = '5' THEN 'clock' " +
                "                           WHEN S.IEXCODSEC = '6' THEN 'sliders' " +
                "                           WHEN S.IEXCODSEC = '7' THEN 'layers' " +
                "                           WHEN S.IEXCODSEC = '11' THEN 'codesandbox' " +
                "                        END as icon, " +
                "                        iexactionspring "+
                "                        FROM " +
                "                        IEXUSUXCIA  U " +
                "                        INNER JOIN IEXROLXOPC R ON U.IEXCODROL = R.IEXCODROL  " +
                "                        INNER JOIN IEXOPCIONES O ON R.IEXCODOPC = O.IEXCODOPC " +
                "                        INNER JOIN IEXSECCION S  ON O.IEXCODSEC = S.IEXCODSEC " +
                "                        INNER JOIN IEXSYSTEMAS Y ON S.IEXCODSYS = Y.IEXCODSYS  " +
                "						WHERE " +
                "                        U.IEXCODCIA = "+codcia+" AND " +
                "                        U.IEXCODUSU = "+codusu+" AND " +
                "                        S.IEXCODSYS= "+codsys+" " +
                "                        ORDER BY s.iexordsec, R.IEXCODOPC ASC ";

        return template.query(sql, new ResultSetExtractor<List<UsuxOpciones>>() {

            public List<UsuxOpciones> extractData(ResultSet rs) throws SQLException, DataAccessException {
                List<UsuxOpciones> lista = new ArrayList<UsuxOpciones>();

                while (rs.next()) {
                    UsuxOpciones usuxopc = new UsuxOpciones();
                    CapitalizarCadena cap = new CapitalizarCadena();

                    usuxopc.setCodcia(rs.getInt("iexcodcia"));
                    usuxopc.setCodusu(rs.getInt("iexcodusu"));
                    usuxopc.setCodrol(rs.getInt("iexcodrol"));
                    usuxopc.setCodopc(rs.getInt("iexcodopc"));
                    usuxopc.setDesopc(rs.getString("iexdesopc"));
                    usuxopc.setUrlopc(rs.getString("iexurlopc"));
                    usuxopc.setUrlimg(rs.getString("iexurlimg"));
                    usuxopc.setCodsec(rs.getInt("iexcodsec"));
                    usuxopc.setDessec(rs.getString("iexdessec"));
                    usuxopc.setOrdsec(rs.getInt("iexordsec"));
                    usuxopc.setDessys(rs.getString("iexdessys"));
                    usuxopc.setDessecimg(rs.getString("iexsecimg"));
                    usuxopc.setIcon(rs.getString("icon"));
                    usuxopc.setPath(rs.getString("iexactionspring"));

                    usuxopc.setDessecCapi(cap.letras(usuxopc.getDessec()));
                    //logger.info("usuxopc.getDessecCapi(): "+usuxopc.getDessecCapi());

                    //Se quita el slash del path
                    String cadena=usuxopc.getPath();
                    String nuevacadena= usuxopc.getPath().substring(1,cadena.length());
                    usuxopc.setPath(nuevacadena);

                    lista.add(usuxopc);
                }
                return lista;
            }
        });
    }


}
