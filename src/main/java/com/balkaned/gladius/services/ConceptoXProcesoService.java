package com.balkaned.gladius.services;

import com.balkaned.gladius.beans.ConceptoXProceso;

import java.util.List;

public interface ConceptoXProcesoService {
	List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto);
}
