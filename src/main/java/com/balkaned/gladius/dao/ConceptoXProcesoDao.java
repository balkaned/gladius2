package com.balkaned.gladius.dao;

import com.balkaned.gladius.beans.ConceptoXProceso;

import java.util.List;

public interface ConceptoXProcesoDao {
	List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);
}
