package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.ConceptoXProceso;
import com.balkaned.gladius.dao.ConceptoXProcesoDao;
import com.balkaned.gladius.services.ConceptoXProcesoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoXProcesoServiceImpl implements ConceptoXProcesoService {
	@Autowired
	ConceptoXProcesoDao conceptoXProcesoDao;

	@Override
	public List<ConceptoXProceso> listarTipconCtb(Integer xcodcia, Integer idProceso, String slc_grpconcepto) {
		return conceptoXProcesoDao.listarTipconCtb(xcodcia, idProceso, slc_grpconcepto);
	}
}
