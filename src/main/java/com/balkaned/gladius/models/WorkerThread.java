package com.balkaned.gladius.models;


import com.balkaned.gladius.planillas.Infrastructure.PlanillaDao;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

@Slf4j
public class WorkerThread implements Runnable {

    private PlanillaDao planillaDao;

    public String command;
    public Integer p_codcia;
    public Integer p_codpro;
    public String p_nroper;
    public Integer p_codtra;
    public Integer p_correl;
    public List<PlaProPeriodo> v_plaproper;
    public Integer thread;

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

    public WorkerThread() {
    }

    @Override
    public void run() {
        log.info(Thread.currentThread().getName() + " Start. Command = " + command);

        log.info("command: {} ", command);
        log.info("p_codcia: {} ", p_codcia);
        log.info("p_codpro: {} ", p_codpro);
        log.info("p_nroper: {} ", p_nroper);
        log.info("p_codtra: {} ", p_codtra);
        log.info("p_correl: {} ", p_correl);
        log.info("thread: {} ", thread);
        log.info("v_plaproper: {} ", v_plaproper);

        try {
            planillaDao.procesarPla2020(v_plaproper, p_codcia, p_codpro, p_nroper, p_codtra, p_correl, thread);
        } catch (Exception ex) {
            log.info("Error Exception, " + command + ": " + ex.getMessage());
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
