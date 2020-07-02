package br.dchristofolli.projects.financialsynchronizer.model;

public class ContaCorrenteProcessada {
    String agencia;
    String conta;
    String saldo;
    String status;
    boolean update;

    public ContaCorrenteProcessada(String agencia, String conta, String saldo, String status, boolean update) {
        this.agencia = agencia;
        this.conta = conta;
        this.saldo = saldo;
        this.status = status;
        this.update = update;
    }
}
