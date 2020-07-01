package br.dchristofolli.projects.financialsynchronizer.configuration;

import br.dchristofolli.projects.financialsynchronizer.service.ReceitaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {
    @Bean
    public String string(){
        return "";
    }

    @Bean
    public Double aDouble(){
        return 0.0;
    }

    @Bean
    public ReceitaService receitaService(){
        return new ReceitaService();
    }
}
