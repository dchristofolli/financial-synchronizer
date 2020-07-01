package br.dchristofolli.projects.financialsynchronizer.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CsvServiceTest {
    @Mock
    ReceitaService receitaService;

    @InjectMocks
    CsvService csvService;


    @Test
    public void csvReader() {
    }

    @Test
    void processing() {
    }

    @Test
    void csvWriter() {
    }
}