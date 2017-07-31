package folhadepagamento;

import java.util.Date;
import java.util.ArrayList;

public abstract class Empregado {
    private int id;
    private String nome;
    private String endereco;
    private String tipo; //Hourly, salaried or comissioned
    private int pagamento;

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
}