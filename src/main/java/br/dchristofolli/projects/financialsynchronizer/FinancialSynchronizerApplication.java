package br.dchristofolli.projects.financialsynchronizer;

import br.dchristofolli.projects.financialsynchronizer.facade.SynchronizerFacade;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FinancialSynchronizerApplication {
    public static SynchronizerFacade synchronizerFacade;

    public FinancialSynchronizerApplication(SynchronizerFacade synchronizerFacade) {
        FinancialSynchronizerApplication.synchronizerFacade = synchronizerFacade;
    }

    public static void main(String[] args) {
        SpringApplication.run(FinancialSynchronizerApplication.class, args);
        String command = "";
        for(String str: args)
            command = str;
        synchronizerFacade.run(command);
    }
}
