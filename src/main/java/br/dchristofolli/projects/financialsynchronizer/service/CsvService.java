package br.dchristofolli.projects.financialsynchronizer.service;

import br.dchristofolli.projects.financialsynchronizer.model.Account;
import br.dchristofolli.projects.financialsynchronizer.model.ProcessedAccount;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static java.lang.System.currentTimeMillis;

@Service
public class CsvService {
    private final ReceitaService receitaService;

    @Value("${outputFile}")
    private String outputFile;

    @Value("${outputDir}")
    private String outputDir;

    private static final Logger logger = LoggerFactory.getLogger(CsvService.class.getName());

    public CsvService(ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    public List<Account> csvReader(String fileName) {
        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get(fileName));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return new CsvToBeanBuilder<Account>(Objects.requireNonNull(reader))
                .withType(Account.class)
                .withEscapeChar(',')
                .withSeparator(';')
                .build()
                .parse();
    }

    public ProcessedAccount processing(Account account) {
        boolean update = false;
        try {
            update = receitaService.atualizarConta(
                    account.getAgencia(),
                    account.getConta(),
                    account.getSaldo(),
                    account.getStatus());
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return getProcessedAccount(account, update);
    }

    private ProcessedAccount getProcessedAccount(Account account, boolean update) {
        return new ProcessedAccount(
                account.getAgencia(),
                account.getConta(),
                balanceFormatter(account),
                account.getStatus(),
                update);
    }

    private String balanceFormatter(Account account) {
        return String.format("%.2f",account.getSaldo()).replace('.', ',');
    }

    public void makeDirectory() {
        try {
            Files.createDirectories(Path.of(outputDir));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public void csvWriter(List<ProcessedAccount> processed) {
        try {
            Writer writer = makeFile();
            StatefulBeanToCsv<ProcessedAccount> beanToCsv =
                    new StatefulBeanToCsvBuilder<ProcessedAccount>(writer)
                            .build();
            beanToCsv.write(processed);
            writer.flush();
            writer.close();
        } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
            logger.error(e.getMessage());
        }
    }

    private Writer makeFile() throws IOException {
        return Files.newBufferedWriter(Paths.get(
                outputDir +
                        File.separator +
                        currentTimeMillis() + outputFile));
    }
}
