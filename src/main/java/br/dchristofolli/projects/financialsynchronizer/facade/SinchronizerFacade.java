package br.dchristofolli.projects.financialsynchronizer.facade;

import br.dchristofolli.projects.financialsynchronizer.model.ContaCorrenteProcessada;
import br.dchristofolli.projects.financialsynchronizer.service.CsvService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class SinchronizerFacade {
    private final CsvService csvService;
    private final Logger logger = LoggerFactory.getLogger(SinchronizerFacade.class);

    public SinchronizerFacade(CsvService csvService) {
        this.csvService = csvService;
    }

    @Scheduled(fixedDelay = 1000)
    private void run() {
        List<ContaCorrenteProcessada> processadas = csvService.csvReader()
                .stream()
                .map(csvService::processing)
                .collect(Collectors.toList());
        csvService.csvWriter(processadas);
    }
}
