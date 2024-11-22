import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar os ArrayLists (APENAS PARA TESTES)
        ListaClientes lc =  new ListaClientes();
        ListaFaturas lf =  new ListaFaturas();

        // TUI (Terminal User Interface)
        System.out.println("\n" +
                "\n" +
                "   ___  ____  ____  ________\n" +
                "  / _ \\/ __ \\/ __ \\/ __/ __/\n" +
                " / ___/ /_/ / /_/ / _/_\\ \\  \n" +
                "/_/   \\____/\\____/_/ /___/  \n");
        System.out.println("Bem-vindo(a) ao POO Financial Services!");
        Scanner sc = new Scanner(System.in);
        boolean running = true;
        while (running) {
            System.out.println("\nSelecione que operação pretende efetuar:");
            System.out.println("[1] Criar cliente");
            System.out.println("[2] Editar cliente");
            System.out.println("[3] Criar fatura");
            System.out.println("[4] Editar fatura");
            System.out.println("[5] Listar faturas");
            System.out.println("[6] Visualizar fatura");
            System.out.println("[7] Importar faturas");
            System.out.println("[8] Exportar as faturas");
            System.out.println("[9] Estatísticas");
            System.out.println("[0] Sair");

            System.out.print(">> ");
            String opt = sc.nextLine();
            switch (opt) {
                case "0":
                    running = false;
                    break;
                case "1":
                    lc.addCliente();
                    break;
                case "2":
                    lc.editCliente();
                    break;
                case "3":
                    lf.addFatura(lc);
                    break;
                case "4":
                    lf.editFatura(lc);
                    break;
                case "5":
                    lf.printFaturas();
                    break;
                case "6":
                    lf.printFaturas();
                    System.out.println("Insira o número da fatura que prentede visualizar:");
                    System.out.print(">> ");
                    String numFatura = sc.nextLine();
                    boolean foundFatura = false;
                    try {
                        for (Fatura fatura : lf.getFaturas()) {
                            if (fatura.getNumFatura() == Integer.parseInt(numFatura)) {
                                fatura.printFatura();
                                foundFatura = true;
                            }
                        }
                        if (!foundFatura)
                            System.out.println("[!] Não foi possível encontrar a fatura");
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Não foi possível encontrar a fatura");
                    }
                    break;
            }
        }

    }
}