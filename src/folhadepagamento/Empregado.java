package folhadepagamento;

import java.util.Date;
import java.util.ArrayList;

public abstract class Empregado {
    private int id;
    private String nome;
    private String endereco;
    private String tipo; //Hourly, salaried or comissioned
    private int pagamento;
    private String tipoPagamento;
    private double taxaServico;
    private int idSindicato;
    private boolean pertenceSindicato = false;
    private double taxaSindical;
    private int pagamentoDia;
    private String pagamentoDefault;
    private int pagamentoDiaSemana;
    private int pagamentoIntervaloSemana;

    public int getPagamentoDia() {
        return pagamentoDia;
    }

    public void setPagamentoDia(int pagamentoDia) {
        this.pagamentoDia = pagamentoDia;
    }

    public int getPagamentoDiaSemana() {
        return pagamentoDiaSemana;
    }

    public String getPagamentoDefault() {
        return pagamentoDefault;
    }

    public void setPagamentoDefault(String pagamentoDefault) {
        this.pagamentoDefault = pagamentoDefault;
    }

    public void setPagamentoDiaSemana(int pagamentoDiaSemana) {
        this.pagamentoDiaSemana = pagamentoDiaSemana;
    }

    public int getPagamentoIntervaloSemana() {
        return pagamentoIntervaloSemana;
    }

    public void setPagamentoIntervaloSemana(int pagamentoIntervaloSemana) {
        this.pagamentoIntervaloSemana = pagamentoIntervaloSemana;
    }

    public Empregado(int id, String nome, String endereco, String tipo, int pagamento) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return  "Id: " + id +
                ", Nome: " + nome +
                ", Endereco: " + endereco +
                ", Tipo: " + tipo +
                ", Salario: " + pagamento;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTipo() {
        return tipo;
    }

    public int getPagamento() {
        return pagamento;
    }

    public double getTaxaServico() {
        return taxaServico;
    }

    public void setTaxaServico(double taxaServico) {
        this.taxaServico = taxaServico;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTipoPagamento(String tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    public void setPertenceSindicato(boolean pertenceSindicato) {
        this.pertenceSindicato = pertenceSindicato;
    }

    public void setPagamento(int pagamento) {
        this.pagamento = pagamento;
    }

    public int getIdSindicato() {
        return idSindicato;
    }

    public void setIdSindicato(int idSindicato) {
        this.idSindicato = idSindicato;
    }

    public String getTipoPagamento() {
        return tipoPagamento;
    }

    public boolean isPertenceSindicato() {
        return pertenceSindicato;
    }

    public double getTaxaSindical() {
        return taxaSindical;
    }

    public void setTaxaSindical(double taxaSindical) {
        this.taxaSindical = taxaSindical;
    }
}