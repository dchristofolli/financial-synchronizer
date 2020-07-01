package br.dchristofolli.projects.financialsynchronizer.model;

import org.springframework.stereotype.Component;

@Component
public class ContaCorrenteEnviada {
    String agencia;
    String conta;
    double saldo;
    String status;

    @Override
    public String toString() {
        return "ContaCorrente{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", saldo='" + saldo + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo/100;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
