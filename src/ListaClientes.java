import java.util.ArrayList;
import java.util.Scanner;

public class ListaClientes {
    // Atributos da classe
    private ArrayList<Cliente> clientes;

    // Construtores
    public ListaClientes() {

    }

    // Metodos de acesso
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }


    public void printClientes() {
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void addCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void editCliente() {
        // Imprime todos os clientes para que o utilizador possa selecionar o nome do cliente pretendido
        this.printClientes();
        // Recebe input do utilizador
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o nome ou o NIF do cliente\n>> ");
        String input = scanner.nextLine();
        try {
            int nif = Integer.parseInt(input);
            // todo: ADICIONAR VERIFICACAO DO NIF

            // Procura o cliente com o nome dado pelo utilizador
            boolean found = false;
            for (Cliente cliente : clientes) {
                if (cliente.getNif() == nif) {
                    found = true;
                    this.edit(cliente);
                }
            }
            if (!found)
                System.out.println("[!] Cliente não encontrado");
        }
        catch (NumberFormatException e) {
            String nome = input;
            // Procura o cliente com o nome dado pelo utilizador
            boolean found = false;
            for (Cliente cliente : clientes) {
                if (cliente.getNome().equalsIgnoreCase(nome)) {
                    found = true;
                    this.edit(cliente);
                }
            }
            if (!found)
                System.out.println("[!] Cliente não encontrado");
        }
    }

    private void edit(Cliente cliente) {
        // Escolhas do utilizador
        System.out.println("Selecione o que pretende editar:");
        System.out.println("[1] Nome");
        System.out.println("[2] NIF");
        System.out.println("[3] Localização");
        System.out.println("[0] Sair");

        // Selecao da açao pretendida
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print(">> ");
            String input = scanner.nextLine();
            try {
                int choice = Integer.parseInt(input);
                if (choice == 1) {
                    System.out.print("Insira o novo nome:\n>> ");
                    String nome = scanner.nextLine();
                    cliente.setNome(nome);
                } else if (choice == 2) {
                    System.out.print("Insira o novo NIF:\n>> ");
                    String temp = scanner.nextLine();
                    try {
                        int nif = Integer.parseInt(temp);
                        // todo: ADICIONAR VERIFICACAO DO NIF
                        cliente.setNif(nif);
                    } catch (NumberFormatException e) {
                        System.out.println("[!] NIF inválido");
                    }
                } else if (choice == 3) {
                    System.out.print("Insira a nova localização:\n>> ");
                    String loc = scanner.nextLine();
                    cliente.setLocalizacao(loc);
                } else if (choice == 0) {
                    running = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("[!] Escolha inválida");
            }
        }
    }
}
