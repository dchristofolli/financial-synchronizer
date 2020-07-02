package br.dchristofolli.projects.financialsynchronizer.model;

import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteEnviada {
    String agencia;
    String conta;
    double saldo;
    String status;

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public String getStatus() {
        return status;
    }
}
