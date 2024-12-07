import java.util.Scanner;

/**
 * Classe Main da aplicação, onde são chamados os
 * métodos utilizados para implementar as funcionalidades
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Main {
    /**
     * Metodo Main
     * @param args Argumentos passados na chamada do metodo main
     */
    public static void main(String[] args) {
        POO dados = new POO();
        dados = dados.fetchDados();

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
                    dados.getListaClientes().addCliente();
                    break;
                case "2":
                    dados.getListaClientes().editCliente();
                    break;
                case "3":
                    dados.getListaFaturas().addFatura(dados.getListaClientes(), dados.getProdutosRegistados());
                    break;
                case "4":
                    dados.getListaFaturas().editFatura(dados.getListaClientes(), dados.getProdutosRegistados());
                    break;
                case "5":
                    dados.getListaFaturas().printFaturas();
                    break;
                case "6":
                    dados.getListaFaturas().printFaturas();
                    System.out.println("Insira o número da fatura que prentede visualizar:");
                    System.out.print(">> ");
                    String numFatura = sc.nextLine();
                    boolean foundFatura = false;
                    try {
                        for (Fatura fatura : dados.getListaFaturas().getFaturas()) {
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
                case "7":
                    System.out.print("Nome do ficheiro a importar: (ficheiro .txt)\n>> ");
                    String fileToImport = sc.nextLine();
                    dados.getListaFaturas().importarFaturas(fileToImport, dados.getProdutosRegistados());
                    break;
                case "8":
                    System.out.println("Nome do ficheiro a exportar: (ficheiro .txt)");
                    String fileToExport;
                    do {
                        System.out.print(">> ");
                        fileToExport = sc.nextLine();
                    } while (!fileToExport.endsWith(".txt") || fileToExport.length() < 5);
                    dados.getListaFaturas().exportarFaturas(fileToExport);
                    break;
                case "9":
                    dados.printEstatiscas();
            }
        }
        dados.exportarDados(dados, "autosave.obj");
        sc.close();
    }
}

