package folhadepagamento;


public class Comissionado extends Empregado {

    private  Venda[] venda = new Venda[100];
    private int qntVendas = 0;

    public Comissionado(int id, String nome, String endereco, String tipo, int pagamento) {
        super(id, nome, endereco, tipo, pagamento);
    }

    public void setVendaNome(int i, String nome){
        this.venda[i].nome = nome;
    }

    public void setVendaValor(int i, double valor){
        this.venda[i].valor = valor;
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
