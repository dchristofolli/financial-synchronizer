package br.dchristofolli.projects.financialsynchronizer.service;

import br.dchristofolli.projects.financialsynchronizer.model.ContaCorrenteEnviada;
import br.dchristofolli.projects.financialsynchronizer.model.ContaCorrenteProcessada;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@EnableScheduling
public class CsvService {
    private final ReceitaService receitaService;
    private final Logger logger = LoggerFactory.getLogger(CsvService.class.getName());

    public CsvService(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @Scheduled(fixedDelay = 1000)
    private void run() {
        csvReader()
                .forEach(c -> logger.info(String.valueOf(c)));
        csvReader().stream()
                .map(this::processing)
                .forEach(this::csvWriter);
    }

    private List<ContaCorrenteEnviada> csvReader() {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("sample.csv"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new CsvToBeanBuilder<ContaCorrenteEnviada>(Objects.requireNonNull(reader))
                .withType(ContaCorrenteEnviada.class)
                .withEscapeChar(',')
                .withSeparator(';')
                .build().parse();
    }

    public ContaCorrenteProcessada processing(ContaCorrenteEnviada conta) {
        boolean atualizar = false;
        try {
            atualizar = receitaService.atualizarConta(
                    conta.getAgencia(),
                    conta.getConta(),
                    conta.getSaldo(),
                    conta.getStatus());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return new ContaCorrenteProcessada(
                conta.getAgencia(),
                conta.getConta(),
                conta.getSaldo(),
                conta.getStatus(),
                atualizar);
    }

    public void csvWriter(ContaCorrenteProcessada contaCorrenteProcessada) {
        try {
            Writer writer = Files.newBufferedWriter(Paths.get("processadas.csv"));
            StatefulBeanToCsv<ContaCorrenteProcessada> beanToCsv =
                    new StatefulBeanToCsvBuilder<ContaCorrenteProcessada>(writer)
                            .build();
            beanToCsv.write(contaCorrenteProcessada);
            writer.flush();
            writer.close();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        }
    }
}
