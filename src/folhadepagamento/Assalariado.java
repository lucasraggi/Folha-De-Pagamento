package folhadepagamento;


public class Assalariado extends  Empregado{
    private static int pagamento;

    public Assalariado(int id, String nome, String endereco, String tipo) {
        super(id, nome, endereco, tipo);
    }
}
