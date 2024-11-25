import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inicializar os ArrayLists (APENAS PARA TESTES)
        POO dados = new POO();
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

                /*
                case "8":
                    // Exportar faturas
                    System.out.print("Insira o nome do ficheiro para exportar: ");
                    String nomeExportar = sc.nextLine();
                    escreverFicheiro(nomeExportar, lf.getFaturas());
                    System.out.println("Faturas exportadas com sucesso!");
                    break;

                 */
            }
        }
    }

    /*
    public static<T> void escreverFicheiro(String nomeFicheiro, ArrayList<T> list){
        File f = new File(nomeFicheiro);
            try {
                FileOutputStream fos= new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(list);
                oos.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("Erro ao escrever ficheiro");
        }
        catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro");
        }
    }
    public static <T> ArrayList<T> lerFicheiro(String nomeFicheiro){
        File f = new File(nomeFicheiro);
        ArrayList<T>list=null;
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois= new ObjectInputStream(fis);
            list= (ArrayList<T>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("Erro ao ler ficheiro");
        } catch (IOException e) {
            System.out.println("Erro ao escrever ficheiro");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro ao escrever ficheiro");        }
        return list;
    }

     */
}

