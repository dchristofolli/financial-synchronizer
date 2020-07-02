package br.dchristofolli.projects.financialsynchronizer.service;

import br.dchristofolli.projects.financialsynchronizer.model.Account;
import br.dchristofolli.projects.financialsynchronizer.model.ProcessedAccount;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@RunWith(MockitoJUnitRunner.class)
public class CsvServiceTest {
    @InjectMocks
    CsvService csvService;

    @Test
    public void shouldBeOkWhenTryToReadCsv() throws IOException {
        String fileName = "test.csv";
        Files.createFile(Paths.get(fileName));
        Reader reader = Files.newBufferedReader(Paths.get(fileName));
        List<Account> accounts = new CsvToBeanBuilder<Account>(Objects.requireNonNull(reader))
                .withType(Account.class)
                .build()
                .parse();
        csvService.csvReader(fileName);
        assertEquals(accounts, csvService.csvReader(fileName));
        Files.delete(Paths.get(fileName));
    }

    @Test(expected = IOException.class)
    public void shouldThrowExceptionWhenTryToReadCsv() throws IOException {
        String fileName = "unit-test.csv";
        Files.createFile(Paths.get(fileName));
        csvService.csvReader(fileName);
        Files.delete(Paths.get(fileName));
        throw new IOException();
    }

    @Test
    public void shoudBeOkWheTryToMakeFile() {
        String fileName = "test.csv";
        ProcessedAccount processedAccount = new ProcessedAccount(
                "1234",
                "12345-6",
                "100,00",
                "A",
                false);
        List<ProcessedAccount> processed = Collections.singletonList(processedAccount);
        csvService.csvWriter(processed);
        assertFalse(Files.exists(Paths.get(fileName)));
    }
}