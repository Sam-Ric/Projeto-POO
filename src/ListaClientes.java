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

    public void addCliente() {
        Cliente cliente = new Cliente();
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o nome:\n>> ");
        cliente.setNome(sc.nextLine());
        System.out.println("Insira o NIF:");
        boolean validNif = false;
        while (!validNif) {
            System.out.print(">> ");
            String nif = sc.nextLine();
            try {
                int temp = Integer.parseInt(nif);
                if (Integer.toString(temp).length() == 9)
                    validNif = true;
                else
                    System.out.println("[!] NIF inválido");
            } catch (NumberFormatException e) {
                System.out.println("[!] NIF inválido");
            }
        }
        System.out.print("Insira a localização:\n>> ");
        cliente.setLocalizacao(sc.nextLine());
        clientes.add(cliente);
    }

    public void editCliente() {
        // Imprime todos os clientes para que o utilizador possa selecionar o nome do cliente pretendido
        this.printClientes();
        // Recebe input do utilizador
        Scanner scanner = new Scanner(System.in);
        System.out.println("Insira o nome ou o NIF do cliente:");
        String input = scanner.nextLine();
        // Caso seja fornecido um NIF
        try {
            int nif = Integer.parseInt(input);
            // Verifica se o NIF fornecido é válido
            if (Integer.toString(nif).length() != 9)
                System.out.println("[!] NIF inválido");
            else {
                // Procura o cliente com o NIF dado pelo utilizador
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
        }
        // Caso seja fornecido um nome
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
        Scanner sc = new Scanner(System.in);
        System.out.print("Insira o novo nome:\n>> ");
        cliente.setNome(sc.nextLine());
        System.out.println("Insira o novo NIF:");
        boolean validNif = false;
        while (!validNif) {
            System.out.print(">> ");
            String nif = sc.nextLine();
            try {
                cliente.setNif(Integer.parseInt(nif));
                validNif = true;
            } catch (NumberFormatException e) {
                System.out.println("[!] NIF inválido");
            }
        }
        System.out.print("Insira a nova localização:\n>> ");
        cliente.setLocalizacao(sc.nextLine());
    }
}
