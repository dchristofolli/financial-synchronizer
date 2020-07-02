package br.dchristofolli.projects.financialsynchronizer.facade;

import br.dchristofolli.projects.financialsynchronizer.model.ContaCorrenteProcessada;
import br.dchristofolli.projects.financialsynchronizer.service.CsvService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SynchronizerFacade {
    private final CsvService csvService;

    public SynchronizerFacade(CsvService csvService) {
        this.csvService = csvService;
    }

    public void run(String fileName) {
        csvService.makeDirectory();
        List<ContaCorrenteProcessada> processed = csvService.csvReader(fileName)
                .stream()
                .map(csvService::processing)
                .collect(Collectors.toList());
        csvService.csvWriter(processed);
    }
}
