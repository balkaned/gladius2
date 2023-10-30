package com.balkaned.gladius.daoImpl;

import com.balkaned.gladius.beans.ProcesoForm;
import com.balkaned.gladius.dao.ProFoDao;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository("ProFoDao")
public class ProFoDaoImpl implements ProFoDao {
    JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource datasource) {
        template = new JdbcTemplate(datasource);
    }

    @Override
    public List<ProcesoForm> listProFos() {
        String sqlQuery = "SELECT " +
                "	procodpro, " +
                "	prodespro, " +
                "	prodescorto, " +
                "	procodregimenlab, " +
                "	progrppro, " +
                "	bolproceso, " +
                "	idtipproceso, " +
                "	bolprocesoind, " +
                "	bolprocesores, " +
                "	diasteo " +
                "FROM iexprocesos " +
                "ORDER BY procodpro ASC";
        return template.query(sqlQuery, rs -> {
            List<ProcesoForm> list = new ArrayList<>();

            while (rs.next()) {
                ProcesoForm proFo = new ProcesoForm();
                proFo.setProcodpro(rs.getInt("procodpro"));
                proFo.setProdespro(rs.getString("prodespro"));
                proFo.setProdescorto(rs.getString("prodescorto"));
                proFo.setProcodregimenlab(rs.getString("procodregimenlab"));
                proFo.setProgrppro(rs.getString("progrppro"));
                proFo.setBolproceso(rs.getString("bolproceso"));
                proFo.setIdtipproceso(rs.getString("idtipproceso"));
                proFo.setBolprocesoind(rs.getString("bolprocesoind"));
                proFo.setBolprocesores(rs.getString("bolprocesores"));
                proFo.setDiasteo(rs.getInt("diasteo"));

                list.add(proFo);
            }

            return list;
        });
    }
}
