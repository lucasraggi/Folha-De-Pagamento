package folhadepagamento;

import java.util.Date;
import java.util.ArrayList;

public abstract class Empregado {
    private int id;
    private String nome;
    private String endereco;
    private String tipo; //Hourly, salaried or comissioned

    public Empregado(int id, String nome, String endereco, String tipo) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.tipo = tipo;
    }



}