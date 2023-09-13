package com.balkaned.gladius.dao;



import com.balkaned.gladius.beans.Grpfile;
import java.util.List;

public interface LegajoDao {
    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile);
    public Integer obtieneIdGrpFile(Grpfile grpfile);
    public void insertarGrpFile(Grpfile grpfile);

}