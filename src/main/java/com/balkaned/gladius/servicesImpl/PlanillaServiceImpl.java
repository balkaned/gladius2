package com.balkaned.gladius.servicesImpl;

import com.balkaned.gladius.models.*;
import com.balkaned.gladius.dao.PlanillaDao;
import com.balkaned.gladius.services.PlanillaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanillaServiceImpl implements PlanillaService {

    @Autowired
    PlanillaDao dao;

    public List<PlaProPeriodo> listPla5ta(Integer codcia, String anio, Integer codtra) {
        return dao.listPla5ta(codcia, anio, codtra);
    }

    public List<ConceptoxProcesoxTra> listPlaProperDetCon(Integer codcia, Integer idproceso, String perpro, String codcon) {
        return dao.listPlaProperDetCon(codcia, idproceso, perpro, codcon);
    }

    public List<PlaProPeriodo> listAllPlaPerTra(Integer codcia, Integer codtra, String perini, String perfin) {
        return dao.listAllPlaPerTra(codcia, codtra, perini, perfin);
    }

    public List<PlaProPeriodo> listAllPlaPerTraPro(Integer codcia, Integer codtra, Integer codpro, String perini, String perfin) {
        return dao.listAllPlaPerTraPro(codcia, codtra, codpro, perini, perfin);
    }

    public void PlameExe(Integer codcia, String permes, String file) {

        dao.PlameExe(codcia, permes, file);
    }

    public List<String> PlameMes(Integer codcia, String permes, String file) {
        return dao.PlameMes(codcia, permes, file);
    }

    public void AfpNetExe(Integer codcia, String permes) {
        dao.AfpNetExe(codcia, permes);
    }

    public List<PlaProPeriodo> listPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String txt) {
        return dao.listPlaProper(codcia, idproceso, perpro, codtra, correl, txt);
    }

    public List<PlaProPeriodo> listLiqProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String txt) {
        return dao.listLiqProper(codcia, idproceso, perpro, codtra, correl, txt);
    }

    public void iniPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        dao.iniPlaProper(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public void calificacion_tiempo_mas(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl) {
        dao.calificacion_tiempo_mas(codcia, idproceso, idPeriodo, codtra, correl);
    }

    public void iniPlaProper_proc(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        dao.iniPlaProper_proc(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public void timeIniexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {
        dao.timeIniexe(codcia, idproceso, perpro, codtra, correl);
    }

    public void procesarPla2020(List<PlaProPeriodo> Persona, Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl, Integer thread) {
        dao.procesarPla2020(Persona, codcia, idproceso, idPeriodo, codtra, correl, thread);
    }

    public void guardarNomina2020(Integer codcia, Integer idproceso, String idPeriodo, Integer codtra, Integer correl) {
        dao.guardarNomina2020(codcia, idproceso, idPeriodo, codtra, correl);
    }

    public void timeFinexe(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {
        dao.timeFinexe(codcia, idproceso, perpro, codtra, correl);
    }

    public void delPlaProper(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        dao.delPlaProper(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public PlaProPeriodo listPlaProperTra(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl) {
        return dao.listPlaProperTra(codcia, idproceso, perpro, codtra, correl);
    }

    public List<ConceptoxProcesoxTra> listProperconConZeros(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String flgcon) {
        return dao.listProperconConZeros(codcia, idproceso, perpro, codtra, correl, flgcon);
    }

    public List<ConceptoxProcesoxTra> listProperconConZerosBuscar(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String flgcon, String textbuscar) {
        return dao.listProperconConZerosBuscar(codcia, idproceso, perpro, codtra, correl, flgcon, textbuscar);
    }

    public List<ConceptoxProcesoxTra> listProperconSinZeros(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String flgcon) {
        return dao.listProperconSinZeros(codcia, idproceso, perpro, codtra, correl, flgcon);
    }

    public List<BancoResumenPer> listBankProper(Integer codcia, Integer idproceso, String perpro, Integer correl) {
        return dao.listBankProper(codcia, idproceso, perpro, correl);
    }

    public void exeBankProper(Integer codcia, Integer idproceso, String perpro, Integer correl, String usu, Double tmcb, String fecpago) {
        dao.exeBankProper(codcia, idproceso, perpro, correl, usu, tmcb, fecpago);
    }

    public List<String> txtBancos(Integer codcia, Integer idproceso, String nroper, Integer correl, String codbank, String codmon) {
        return dao.txtBancos(codcia, idproceso, nroper, correl, codbank, codmon);
    }

    public List<PlaProPerDet> iniPlaProper_vac(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        return dao.iniPlaProper_vac(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public List<PlaProPerDet> iniPlaProper_aus(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        return dao.iniPlaProper_aus(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public List<PlaProPerDet> iniPlaProper_prest(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        return dao.iniPlaProper_prest(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public List<PlaProPerDet> iniPlaProper_prom(Integer codcia, Integer idproceso, String perpro, Integer codtra, Integer correl, String grppla, String usu) {
        return dao.iniPlaProper_prom(codcia, idproceso, perpro, codtra, correl, grppla, usu);
    }

    public void migraTrabajador(Integer cia, Integer codpro, String nroper, Integer codtra, Integer correl) {
        dao.migraTrabajador(cia, codpro, nroper, codtra, correl);
    }

    public void migraInsertarPla(List<EmpDatvar> empdatvar) {
        dao.migraInsertarPla(empdatvar);
    }

    public List<Asistencia> consultaMarka(Integer codcia, Integer codtra, String fecini, String fecfin) {
        return dao.consultaMarka(codcia, codtra, fecini, fecfin);
    }
}
