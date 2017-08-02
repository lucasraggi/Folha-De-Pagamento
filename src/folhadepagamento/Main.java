package folhadepagamento;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

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
            System.out.println("7 - Rodar a folha de pagamento para hoje(NAO IMPLEMENTADO");
            System.out.println("8 - Undo/Redo (NAO IMPLEMENTADO)");
            System.out.println("9 - Agenda de Pagamento");
            System.out.println("10 - Criar nova agenda de pagamento");
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
                undoRedo();
            } else if (comando == 9) {
                agendarPagamento();
            } else if (comando == 10) {
                novaAgendaPagamento();
            }

        }
    }

    public static void adicionarEmpregado() {
        if (qntEmpregado > 1)
            scan.nextLine();
        System.out.println("-----------ADICIONAR EMPREGADO-----------");
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
            System.out.println("Insira o salario base do empregado:");
            pagamento = scan.nextInt();
            System.out.println("Insira a comissao do empregado(%): ");
            int comissao = scan.nextInt();
            comissionado.add(new Comissionado(qntEmpregado, nome, endereco, "Comissionado", pagamento, comissao));
            qntEmpregado++;
        }
    }

    public static void removerEmpregado() {
        System.out.println("-----------REMOVER EMPREGADO-----------");
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
        System.out.println("-----------LANCAR CARTAO-----------");
        System.out.println("Informe o id do empregado no qual voce deseja lancar o cartao");
        exibirTodosEmpregados(false, true, false);
        int id = scan.nextInt();
        scan.nextLine();

        GregorianCalendar cal = new GregorianCalendar();
        cal.setTime(dataSistema);

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if (horista.getId() == id) {
                if (!horista.isBateuPonto()) {
                    try {
                        System.out.println("Insira o horario de entrada (dd/mm/yyyy hh:mm:ss) do empregado " + horista.getNome());
                        String dataEntrada = scan.nextLine();
                        horista.setEntrada(DateFormat.getDateTimeInstance().parse(dataEntrada));
                        horista.setBateuPonto(true);
                        System.out.println(horista.getEntrada());
                        return;
                    } catch (ParseException ex) {
                        System.out.println("Digite a data corretamente!");
                        return;
                    }
                } else {
                    try {
                        System.out.println("Insira o horario de saida (dd/mm/yyyy hh:mm:ss) do empregado " + horista.getNome());
                        String dataSaida = scan.nextLine();
                        horista.setSaida(DateFormat.getDateTimeInstance().parse(dataSaida));
                        cal.setTime(horista.getSaida());

                        int minutosTrabalhados = (int) TimeUnit.MILLISECONDS.toMinutes(horista.getSaida().getTime() - horista.getEntrada().getTime());
                        horista.setHorasTrabalhadas((double) minutosTrabalhados / 60, cal.get(Calendar.DAY_OF_MONTH));
                        System.out.println("Horas trabalhadas do empregado " + horista.getNome() + " : " + horista.getHorasTrabalhadas(cal.get(Calendar.DAY_OF_MONTH)));
                        System.out.println("No dia : " + cal.get(Calendar.DAY_OF_MONTH));

                        horista.setBateuPonto(false);
                        double total = (double) minutosTrabalhados / 60;
                        System.out.println("Tempo trabalhado  : " + total);
                        return;

                    } catch (ParseException ex) {
                        System.out.println("Digite a data corretamente!");
                        return;
                    }

                }
            }
        }

    }

    public static void lancarVenda() {
        System.out.println("-----------LANCAR VENDA-----------");
        System.out.println("Informe o id do empregado no qual voce deseja lancar a venda:");
        exibirTodosEmpregados(false, false, true);
        int id = scan.nextInt();

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if (comissionado.getId() == id) {
                System.out.println("----------------------");
                System.out.println("1 - Registrar Venda");
                System.out.println("2 - Ver produtos vendidos por este empregado");
                int comando = scan.nextInt();

                if (comando == 1) { // Registrar Venda
                    System.out.println("Empregado : " + comissionado.getNome());

                    System.out.println("Insira o nome do produto :");
                    scan.nextLine();
                    String nome = scan.nextLine();
                    System.out.println(comissionado.getQntVendas());
                    System.out.println("Insira o valor da venda :");
                    double valor = scan.nextDouble();
                    comissionado.setVendaNomeValor(comissionado.getQntVendas(), nome, valor);

                    System.out.println("Venda salva com sucesso!");
                    System.out.println("------------------------");
                    comissionado.setQntVendas();

                } else { // Ver produtos vendidos por este empregado
                    if (comissionado.getQntVendas() > 0) {
                        for (i = 0; i < comissionado.getQntVendas(); i++) {
                            System.out.println("----------- VENDA -----------");
                            System.out.println("Nome do vendedor : " + comissionado.getNome());
                            System.out.println("Id do vendedor : " + comissionado.getId());
                            System.out.println("Nome do produto : " + comissionado.getVendaNome(i));
                            System.out.println("Valor do produto : " + comissionado.getVendaValor(i));
                        }
                    }
                }
            }
        }
    }


    public static void lancarTaxaServico() {
        System.out.println("-----------LANCAR TAXA DE SERVICO-----------");
        System.out.println("Digite o id do empregado que voce deseja cobrar taxa de servico");
        exibirTodosEmpregados(true, true, true);
        int id = scan.nextInt();

        for (int i = 0; i < assalariados.size(); i++) {
            Assalariado assalariados = Main.assalariados.get(i);
            if (assalariados.getId() == id) {
                System.out.println("Informe o valor da taxa de serviço : ");
                double valor = scan.nextDouble();
                assalariados.setTaxaServico(valor);
                System.out.println("Taxa de serviço lançada!");
                return;
            }
        }

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if (horista.getId() == id) {
                System.out.println("Informe o valor da taxa de serviço : ");
                double valor = scan.nextDouble();
                horista.setTaxaServico(valor);
                System.out.println("Taxa de serviço lançada!");
                return;
            }
        }

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if (comissionado.getId() == id) {
                System.out.println("Informe o valor da taxa de serviço : ");
                double valor = scan.nextDouble();
                comissionado.setTaxaServico(valor);
                System.out.println("Taxa de serviço lançada!");
                return;
            }
        }


    }

    public static void alterarEmpregado() {
        System.out.println("-----------ALTERAR EMPREGADO-----------");
        System.out.println("Insira o id do empregado que voce deseja alterar:");
        exibirTodosEmpregados(true, true, true);
        int id = scan.nextInt();
        for (int i = 0; i < assalariados.size(); i++) {
            Assalariado assalariado = Main.assalariados.get(i);
            if(id == assalariado.getId())
            alterarDadosEmpregado(assalariado);
        }

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if(id == horista.getId())
            alterarDadosEmpregado(horista);
        }

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if(id == comissionado.getId())
            alterarDadosEmpregado(comissionado);
        }
    }

    public static void alterarDadosEmpregado(Empregado empregado) {
        int comando = -1;
        while (comando != 0) {
            System.out.println("Selecione o que deseja alterar do empregado " + empregado.getNome());
            System.out.println("1 - Nome");
            System.out.println("2 - Endereco");
            System.out.println("3 - Tipo");
            System.out.println("4 - Metodo de pagamento");
            System.out.println("5 - Se pertence ao sindicato");
            System.out.println("6 - Identificação no sindicato");
            System.out.println("7 - Taxa sindical");

            comando = scan.nextInt();
            String line;

            if (comando == 1) {
                System.out.println("Digite o novo nome : ");
                scan.nextLine();

                line = scan.nextLine();
                empregado.setNome(line);
                System.out.println("Alterado com sucesso!");
                return;
            } else if (comando == 2) {
                System.out.println("Digite o novo endereço : ");
                scan.nextLine();
                line = scan.nextLine();
                empregado.setEndereco(line);
                System.out.println("Alterado com sucesso!");
            } else if (comando == 3) {
                System.out.println("Escolha o tipo: ");
                System.out.printf("1 - Assalariado");
                System.out.printf("2 - Horista");
                System.out.printf("3 - Comissionado");
                int comandoAux = scan.nextInt();
                if (comandoAux == 1) {
                    empregado.setTipo("Assalariado");
                } else if (comandoAux == 2) {
                    empregado.setTipo("Horista");
                } else if (comandoAux == 3) {
                    empregado.setTipo("Comissionado");
                }
                System.out.println("Alterado com sucesso!");
            } else if (comando == 4) {
                System.out.println("Digite o metodo de pagamento(Mensal, Bi-semanal, Semanal) : ");
                scan.nextLine();
                line = scan.nextLine();
                empregado.setTipoPagamento(line);
                System.out.println("Alterado com sucesso!");
            } else if (comando == 5) {
                System.out.println("0 - Sim / 1 - Não");
                int op2;
                op2 = scan.nextInt();
                if (op2 == 0) {
                    empregado.setPertenceSindicato(true);
                    System.out.println("Alterado com sucesso! O empregado agora é do sindicato!");
                    System.out.println("Informe a taxa sindical : ");
                    double valor = scan.nextDouble();
                    empregado.setTaxaSindical(valor);
                } else {
                    empregado.setPertenceSindicato(false);
                    System.out.println("Alterado com sucesso! O empregado não pertence mais ao sindicato!");
                }
            } else if (comando == 6) {
                if (empregado.isPertenceSindicato()) {
                    System.out.println("Digite a nova identificação no sindicato : ");
                    int number;
                    number = scan.nextInt();
                    empregado.setIdSindicato(number);
                    System.out.println("Alterado com sucesso!");
                } else {
                    System.out.println("Esse empregado não pertence ao sindicato!");
                }

            }

        }
    }

    public static void calcularPagamentoHoje() {

    }



    public static void undoRedo() {

    }

    public static void agendarPagamento() {
        System.out.println("-----------AGENDAR PAGAMENTO-----------");
        System.out.println("Insira o id do empregado que voce deseja agendar o pagamento : ");
        int id;
        exibirTodosEmpregados(true, true, true);
        id = scan.nextInt();
        for (int i = 0; i < assalariados.size(); i++) {
            Assalariado assalariado = Main.assalariados.get(i);
            if(id == assalariado.getId())
                agendaPagamentoEmpregado(assalariado);
        }

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if(id == horista.getId())
                agendaPagamentoEmpregado(horista);
        }

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if(id == comissionado.getId())
                agendaPagamentoEmpregado(comissionado);
        }

    }

    public static void agendaPagamentoEmpregado(Empregado empregado) {
                System.out.println("Empregado : " + empregado.getNome());
                System.out.println("Metodo atual : " + empregado.getTipoPagamento());
                System.out.println("Digite o metodo desejado (Mensal, Semanal) : ");
                scan.nextLine();
                String nome = scan.nextLine();
                  if(nome.equals("Mensal")){
                    empregado.setTipoPagamento("Mensal");
                } else if (nome.equals("Semanal")){
                      empregado.setTipoPagamento("Semanal");
                }
                System.out.println("Alterado com sucesso!");

    }

    public static void novaAgendaPagamento() {
        System.out.println("-----------NOVA AGENDA DE PAGAMENTO-----------");
        System.out.println("Digite o id do empregado já existente : ");
        int id;
        exibirTodosEmpregados(true, true, true);
        id = scan.nextInt();
        for (int i = 0; i < assalariados.size(); i++) {
            Assalariado assalariado = Main.assalariados.get(i);
            if(id == assalariado.getId())
                novaAgendaPagamentoEmpregado(assalariado);
        }

        for (int i = 0; i < horista.size(); i++) {
            Horista horista = Main.horista.get(i);
            if(id == horista.getId())
                novaAgendaPagamentoEmpregado(horista);
        }

        for (int i = 0; i < comissionado.size(); i++) {
            Comissionado comissionado = Main.comissionado.get(i);
            if(id == comissionado.getId())
                novaAgendaPagamentoEmpregado(comissionado);
        }
    }

    public static void novaAgendaPagamentoEmpregado(Empregado empregado) {
        System.out.println("Escolha o novo metodo de pagamento : ");
        System.out.println("1 - Mensal");
        System.out.println("2 - Semanal");
        int op = scan.nextInt();

        if(op==1){
            empregado.setPagamentoDefault("Mensal");
            System.out.println("Digita o dia que deseja ser pago : ");
            int pagamentoDia= scan.nextInt();
            empregado.setPagamentoDia(pagamentoDia);
        } else if(op==2){
            empregado.setPagamentoDefault("Semanal");
            System.out.println("Digite o intervalo das semanas : ");
            int pagamentoIntervalo= scan.nextInt();
            empregado.setPagamentoIntervaloSemana(pagamentoIntervalo);
            System.out.println("Digite o dia da semana que deseja receber (1 - Domingo ... 7 - Sábado) : ");
            int pagamentoDia= scan.nextInt();
            empregado.setPagamentoDia(pagamentoDia);
        }
        System.out.println("Alterado com sucesso!");
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