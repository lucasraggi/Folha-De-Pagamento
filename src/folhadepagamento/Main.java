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

    private static List<Assalariado> assalariados = new ArrayList<>();
    private static List<Horista> horista = new ArrayList<>();
    private static List<Comissionado> comissionado = new ArrayList<>();
    private static Scanner scan = new Scanner(System.in);
    private static int qntEmpregado = 1;


    public static void main(String[] args) {


        Date dataSistema = new Date();
        int comando = 1, ultimoComando;
        dataSistema = setDate(dataSistema);

        while (comando != 0) {
            Scanner scan = new Scanner(System.in);


            System.out.println("------------ MENU ------------ ");
            System.out.println("1 - Adicionar empregado");
            System.out.println("2 - Remover empregado");
            System.out.println("3 - Lancar um cartao de ponto");
            System.out.println("4 - Vendas");
            System.out.println("5 - Lancar uma taxa de servico");
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
                lancarCartao(dataSistema);
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
        if (qntEmpregado > 1)
            scan.nextLine();
        System.out.println("Insira o nome do empregado:");
        String nome = scan.nextLine();

        System.out.println("Insira o endereco do empregado");
        String endereco = scan.nextLine();

        System.out.println("Insira o tipo de empregado:");
        System.out.println("1 - Assalariado");
        System.out.println("2 - Horista");
        System.out.println("3 - Comissionado");
        int comando = scan.nextInt();

        int pagamento;
        if (comando == 1) {
            System.out.println("Insira o salario do empregado:");
            pagamento = scan.nextInt();
            assalariados.add(new Assalariado(qntEmpregado, nome, endereco, "Assalariado", pagamento));
            qntEmpregado++;
        }
        if (comando == 2) {
            System.out.println("Insira o salario por hora do empregado:");
            pagamento = scan.nextInt();
            horista.add(new Horista(qntEmpregado, nome, endereco, "Horista", pagamento));
            qntEmpregado++;
        }
        if (comando == 3) {
            System.out.println("Insira a comissao do empregado:");
            pagamento = scan.nextInt();
            comissionado.add(new Comissionado(qntEmpregado, nome, endereco, "Comissionado", pagamento));
            qntEmpregado++;
        }
    }

    public static void removerEmpregado() {
        System.out.println("Insira o id do usuario que voce deseja remover:");
        exibirTodosEmpregados(true, true, true);
        int id = scan.nextInt();


        for (int i = 0; i < assalariados.size(); i++) {
            Assalariado assalariado = Main.assalariados.get(i);
            if (assalariado.getId() == id) {
                Main.assalariados.remove(i);
            }
        }

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if (horista.getId() == id) {
                Main.horista.remove(i);
            }
        }

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if (comissionado.getId() == id) {
                Main.comissionado.remove(i);
            }
        }

    }

    public static void lancarCartao(Date dataSistema) {
        System.out.println("Informe o id do empregado no qual voce deseja lancar o cartao");
        exibirTodosEmpregados(false, true, false);
        int id = scan.nextInt();
        scan.nextLine();

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            System.out.println(id + "oi" + horista.getId());
            if (horista.getId() == id) {
                System.out.println("oi");
                if (!horista.isBateuPonto()) {
                    try {
                        System.out.println("Insira o horario de entrada (dd/mm/yyyy hh:mm:ss) do empregado " + horista.getNome());
                        String dataEntrada = scan.nextLine();
                        horista.setEntrada(DateFormat.getDateTimeInstance().parse(dataEntrada));
                        horista.setBateuPonto(true);
                        System.out.println(horista.getEntrada());
                        return ;
                    } catch (ParseException ex) {
                        System.out.println("Digite a data corretamente!");
                        return;
                    }
                }
                else {
                    try {
                        System.out.println("Insira o horario de saida (dd/mm/yyyy hh:mm:ss) do empregado " + horista.getNome());
                        String dataSaida = scan.nextLine();
                        horista.setSaida(DateFormat.getDateTimeInstance().parse(dataSaida));
                        cal.setTime(horista.getSaida());

                        int minutosTrabalhados = (int) TimeUnit.MILLISECONDS.toMinutes(horista.getSaida().getTime() - horista.getEntrada().getTime());
                        horista.setHorasTrabalhadas((double) minutosTrabalhados/60, cal.get(Calendar.DAY_OF_MONTH));
                        System.out.println("Horas trabalhadas do empregado " + horista.getNome() + " : " + horista.getHorasTrabalhadas(cal.get(Calendar.DAY_OF_MONTH)));
                        System.out.println("No dia : " + cal.get(Calendar.DAY_OF_MONTH));

                        horista.setBateuPonto(false);
                        double total =(double) minutosTrabalhados/60;
                        System.out.println("Tempo trabalhado  : " + total);
                        return ;

                    } catch (ParseException ex) {
                        System.out.println("Digite a data corretamente!");
                        return ;
                    }

                }
            }
        }

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


    public static void exibirTodosEmpregados(boolean i, boolean j, boolean t) {
        if (i == true) {
            for (final Assalariado empregado : assalariados) {
                System.out.println(empregado);
            }
        }
        if (j == true) {
            for (final Horista empregado : horista) {
                System.out.println(empregado);
            }
        }
        if (t == true) {
            for (final Comissionado empregado : comissionado) {
                System.out.println(empregado);
            }
        }
    }


}