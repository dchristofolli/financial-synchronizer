package br.dchristofolli.projects.financialsynchronizer.service;

import br.dchristofolli.projects.financialsynchronizer.model.ContaCorrente;
import com.opencsv.bean.CsvToBeanBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

@Service
@EnableScheduling
public class CsvService {

    @Scheduled(fixedDelay = 1000)
    private void csvReader() {
        Logger logger = LoggerFactory.getLogger(CsvService.class.getName());
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("sample.csv"));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        List<ContaCorrente> csv = new CsvToBeanBuilder<ContaCorrente>(Objects.requireNonNull(reader))
                .withType(ContaCorrente.class)
                .withSeparator(';')
                .build().parse();
        csv.parallelStream()
                .forEach(c -> logger.info(String.valueOf(c)));
    }
}
