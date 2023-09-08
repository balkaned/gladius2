package com.balkaned.gladius.services;


import com.balkaned.gladius.beans.Grpfile;
import java.util.List;

public interface LegajoService {

    public List<Grpfile> listarGrpfile(Integer codcia, Integer codtra, String grpfile);

}
