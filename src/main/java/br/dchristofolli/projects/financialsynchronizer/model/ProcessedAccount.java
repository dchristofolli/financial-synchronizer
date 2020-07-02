package br.dchristofolli.projects.financialsynchronizer.model;

public class ProcessedAccount {
    String agencia;
    String conta;
    String saldo;
    String status;
    boolean update;

    public ProcessedAccount(String agencia, String conta, String saldo, String status, boolean update) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
        this.update = update;
    }
}
