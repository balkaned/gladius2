package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.beans.FormulaPlanilla;
import com.balkaned.gladius.dao.FormulaDao;
import com.balkaned.gladius.services.FormulaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FormulaServiceImpl implements FormulaService {
	@Autowired
	FormulaDao dao;

	@Override
	public FormulaPlanilla getByIdProcesoIdFormula(Integer idprod, Integer idformula) {
		return dao.getByIdProcesoIdFormula(idprod, idformula);
	}

	public void actualizar(FormulaPlanilla fplanilla){
		dao.actualizar(fplanilla);
	}
}
