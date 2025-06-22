package com.balkaned.gladius.models;


import com.balkaned.gladius.dao.PlanillaDao;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Slf4j
@Getter
@Setter
public class WorkerThread implements Runnable {

    /*@Autowired
    PlanillaService planillaService;*/

    @Autowired
    PlanillaDao planillaDao;

    String command;
    Integer p_codcia;
    Integer p_codpro;
    String p_nroper;
    Integer p_codtra;
    Integer p_correl;
    List<PlaProPeriodo> v_plaproper;
    Integer thread;

    public WorkerThread(String s, Integer p_codcia, Integer p_codpro, String p_nroper, Integer p_codtra, Integer p_correl, List<PlaProPeriodo> v_plaproper, Integer thread_id) {

        this.command = s;
        this.p_codcia = p_codcia;
        this.p_codpro = p_codpro;
        this.p_nroper = p_nroper;
        this.p_codtra = p_codtra;
        this.p_correl = p_correl;
        this.v_plaproper = v_plaproper;
        this.thread = thread_id;
    }

    @Override
    public void run() {
        log.info("v_plaproper: ",this.v_plaproper);
        log.info("p_codcia: ",this.p_codcia);
        log.info("p_codpro: ",this.p_codpro);
        log.info("p_nroper: ",this.p_nroper);
        log.info("p_codtra: ",this.p_codtra);
        log.info("p_correl: ",this.p_correl);
        log.info("thread: ",this.thread);

        log.info(Thread.currentThread().getName() + " Start. Command = " + command);

        try {
            //planillaService.procesarPla2020(this.v_plaproper, this.p_codcia, this.p_codpro, this.p_nroper, this.p_codtra, this.p_correl, this.thread);
            planillaDao.procesarPla2020(this.v_plaproper, this.p_codcia, this.p_codpro, this.p_nroper, this.p_codtra, this.p_correl, this.thread);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        log.info(Thread.currentThread().getName() + " End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString() {

        return this.command;
    }
}
