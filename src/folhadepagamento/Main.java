package folhadepagamento;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.*;


public class Main {

    private static List<Assalariado> assalariado = new ArrayList<Assalariado>();
    private static List<Horista> horista = new ArrayList<Horista>();
    private static List<Comissionado> comissionado = new ArrayList<Comissionado>();
    private static Scanner scan = new Scanner(System.in);
    private static int qntEmpregado = 0;



    public static void main(String[] args) {


        Date dataSistema = new Date();
        int comando = 1, ultimoComando;
        dataSistema = setDate(dataSistema);

        while (comando != 0) {
            Scanner scan = new Scanner(System.in);


            System.out.println("------------ MENU ------------ ");
            System.out.println("1 - Adicionar empregado");
            System.out.println("2 - Remover empregado");
            System.out.println("3 - Lançar um cartão de ponto");
            System.out.println("4 - Vendas");
            System.out.println("5 - Lançar uma taxa de serviço");
            System.out.println("6 - Alterar detalhes de um empregado");
            System.out.println("7 - Rodar a folha de pagamento para hoje");
            System.out.println("8 - Agendar pagamento");
            System.out.println("9 - Undo/Redo");
            System.out.println("10 - Mostrar empregado");
            System.out.println("11 - Criar nova agenda de pagamento");
            System.out.println("0 - Sair");

            ultimoComando = comando;
            comando = scan.nextInt();

            if (comando == 1) {
                adicionarEmpregado();
            } else if (comando == 2) {
                removerEmpregado();
            } else if (comando == 3) {
                lancarCartao();
            } else if (comando == 4) {
                lancarVenda();
            } else if (comando == 5) {
                lancarTaxaServico();
            } else if (comando == 6) {
                alterarEmpregado();
            } else if (comando == 7) {
                calcularPagamentoHoje();
            } else if (comando == 8) {
                agendarPagamento();
            } else if (comando == 9) {
                undoRedo();
            } else if (comando == 10) {
                mostrarEmpregados();
            } else if (comando == 11) {
                novaAgendaPagamento();
            }

        }
    }

    public static void adicionarEmpregado() {

        System.out.println("Insira o nome do empregado:");
        String nome = scan.nextLine();

        System.out.println("Insira o endereco do empregado");
        String endereco = scan.nextLine();

        System.out.println("Insira o tipo de empregado:");
        System.out.println("1 - Assalariado");
        System.out.println("2 - Horista");
        System.out.println("3 - Comissionado");
        int comando = scan.nextInt();

        if(comando == 1) {
            System.out.println("Insira o salario do empregado");
            assalariado.add(new Assalariado(qntEmpregado, nome, endereco, "Assalariado"));
        }
        if(comando == 2) {
            horista.add(new Horista(qntEmpregado, nome, endereco, "Horista"));
        }
        if(comando == 3) {
            comissionado.add(new Comissionado(qntEmpregado, nome, endereco, "Comissionado"));
        }


    }

    public static void removerEmpregado() {

    }

    public static void lancarCartao() {

    }

    public static void lancarVenda() {

    }

    public static void lancarTaxaServico() {

    }

    public static void alterarEmpregado() {

    }

    public static void calcularPagamentoHoje() {

    }

    public static void agendarPagamento() {

    }

    public static void undoRedo() {

    }

    public static void mostrarEmpregados() {

    }

    public static void novaAgendaPagamento() {

    }


    public static Date setDate(Date dataSistema) {
        do {
            System.out.println("Insira a data (dd/mm/aaaa hh:mm:ss) :");
            String data = "16/09/2017 14:25:30";
            //data = scan.nextLine();
            try {
                dataSistema = DateFormat.getDateTimeInstance().parse(data);
                return dataSistema;
            } catch (ParseException ex) {
                System.out.println("Digite a data corretamente!");
            }
        } while (true);
    }
}