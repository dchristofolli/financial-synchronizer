package br.dchristofolli.projects.financialsynchronizer.model;

public class Account {
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
        return saldo/100;
    }

    public String getStatus() {
        return status;
    }
}
