package folhadepagamento;
import java.util.Date;

public class Horista extends Empregado {
    private  double[] horasTrabalhadas = new double[30];
    private  boolean bateuPonto = false;
    private  Date entrada;
    private  Date saida;

    public Horista(int id, String nome, String endereco, String tipo, int pagamento) {
        super(id, nome, endereco, tipo, pagamento);
    }

    public double getHorasTrabalhadas(int i) {
        return horasTrabalhadas[i];
    }

    public void setHorasTrabalhadas(double horasTrabalhadas, int i ) {
        this.horasTrabalhadas[i] = horasTrabalhadas;
    }

    public boolean isBateuPonto() {
        return bateuPonto;
    }

    public void setBateuPonto(boolean bateuPonto) {
        this.bateuPonto = bateuPonto;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }
}
