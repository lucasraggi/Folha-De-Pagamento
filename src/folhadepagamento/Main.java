package folhadepagamento;


import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;


public class Main {


    public static void main(String[] args) {


        Date dataSistema = new Date();
        int comando = 1;
        Sistema sis = new Sistema();
        dataSistema = sis.setDate(dataSistema);

        while (comando != 0) {
            Scanner scan = new Scanner(System.in);


            System.out.println("------------ MENU ------------ ");
            System.out.println("1 - Adicionar empregado");
            System.out.println("2 - Remover empregado");
            System.out.println("3 - Lancar um cartao de ponto");
            System.out.println("4 - Vendas");
            System.out.println("5 - Lancar uma taxa de servico");
            System.out.println("6 - Alterar detalhes de um empregado");
            System.out.println("7 - Rodar a folha de pagamento para hoje(NAO IMPLEMENTADO)");
            System.out.println("8 - Undo/Redo (NAO IMPLEMENTADO)");
            System.out.println("9 - Agenda de Pagamento");
            System.out.println("10 - Criar nova agenda de pagamento");
            System.out.println("0 - Sair");

            comando = scan.nextInt();
            if (comando == 1) {
                sis.adicionarEmpregado();
            } else if (comando == 2) {
                sis.removerEmpregado();
            } else if (comando == 3) {
                sis.lancarCartao(dataSistema);
            } else if (comando == 4) {
                sis.lancarVenda();
            } else if (comando == 5) {
                sis.lancarTaxaServico();
            } else if (comando == 6) {
                sis.alterarEmpregado();
            } else if (comando == 7) {
                sis.calcularPagamentoHoje();
            } else if (comando == 8) {
                sis.undoRedo();
            } else if (comando == 9) {
                sis.agendarPagamento();
            } else if (comando == 10) {
                sis.novaAgendaPagamento();
            }

        }
    }
}

