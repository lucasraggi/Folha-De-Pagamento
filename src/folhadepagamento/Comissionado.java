package folhadepagamento;


public class Comissionado extends Empregado {

    private  Venda venda[] = new Venda[100];
    private int qntVendas = 0;
    private int comissao;

    public Comissionado(int id, String nome, String endereco, String tipo, int pagamento, int comissao) {
        super(id, nome, endereco, tipo, pagamento);
        this.comissao = comissao;
    }

    public void setVendaNomeValor(int i, String nome, double valor){
        this.venda[i] = new Venda();
        this.venda[i].nome = nome;
        this.venda[i].valor = valor;
    }

    public int getComissao() {
        return comissao;
    }


    public String getVendaNome(int i){
        return this.venda[i].nome;
    }

    public double getVendaValor(int i){
        return this.venda[i].valor;
    }

    public int getQntVendas() {
        return qntVendas;
    }

    public void setQntVendas() {
        this.qntVendas++;
    }
}
