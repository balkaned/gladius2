package com.balkaned.gladius.legajo.Domain;


import com.balkaned.gladius.models.Grpfile;

import java.util.List;

public interface LegajoService {

    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile);

    public Integer obtieneIdGrpFile(Grpfile grpfile);

    public void insertarGrpFile(Grpfile grpfile);

    public void insertarImage(FileImageLegajo fileImageLegajo);

    public Integer obtieneIdImage(Integer codcia, Integer idgrpfile);

    public Grpfile getGrpfile(Integer codcia, Integer idgrpfile);

    public void actualizarGrpFile(Grpfile grpfile);

    public void eliminarGrpFile(Grpfile grpfile);

    public void eliminarImage(FileImageLegajo fileImageLegajo);

    public void aprobarDocumento(FileImageLegajo fileImageLegajo);

}
