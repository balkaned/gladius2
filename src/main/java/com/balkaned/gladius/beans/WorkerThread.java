package com.balkaned.gladius.beans;


import com.balkaned.gladius.services.PlanillaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Slf4j
public class WorkerThread implements Runnable{

    @Autowired
    PlanillaService planillaService;

    private String command;
    private Integer p_codcia;
    private Integer p_codpro;
    private String p_nroper;
    private Integer p_codtra;
    private Integer p_correl;
    private List<PlaProPeriodo> v_plaproper;
    private Integer thread;

    public WorkerThread(String s, Integer p_codcia, Integer p_codpro, String p_nroper, Integer p_codtra, Integer p_correl , List<PlaProPeriodo> v_plaproper, Integer thread_id){
        this.command=s;
        this.p_codcia=p_codcia ;
        this.p_codpro=p_codpro ;
        this.p_nroper=p_nroper ;
        this.p_codtra=p_codtra ;
        this.p_correl=p_correl ;
        this.v_plaproper=v_plaproper ;
        this.thread=thread_id;
    }

    @Override
    public void run() {

        log.info(Thread.currentThread().getName()+" Start. Command = "+command);

        try {
            planillaService.procesarPla2020(v_plaproper,p_codcia , p_codpro, p_nroper ,p_codtra, p_correl,thread);
        } catch (Exception ex) {
            log.info(ex.getMessage());
        }

        log.info(Thread.currentThread().getName()+" End.");
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public String toString(){

        return this.command;
    }
}
