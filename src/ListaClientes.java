import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaClientes implements Serializable {
    // Atributos da classe
    private ArrayList<Cliente> clientes;

    // Construtores
    public ListaClientes() {
        clientes = new ArrayList<Cliente>();
    }

    public ListaClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    // Metodos de acesso
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }


    public void printClientes() {
        System.out.printf("\n %-25s | %-9s | %-20s%n", "NOME DO CLIENTE", "NIF", "LOCALIZAÇÃO");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    public void addCliente() {
        // Criar o novo objeto Cliente
        Cliente cliente = new Cliente();
        // Obter os dados do cliente
        getDadosCliente(cliente);
        // Adicionar o objeto criado ao ArrayList dos Clientes
        clientes.add(cliente);
    }

    public void editCliente() {
        // Encontrar o cliente a editar no ArrayList de Clientes
        Cliente cliente = searchCliente();
        if (cliente != null)
            // Obter os novos dados do cliente
            getDadosCliente(cliente);
    }

    private void getDadosCliente(Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        // Obter o nome do cliente
        System.out.println("Insira o nome:");
        String nome = "N/A";
        boolean validNome = false;
        while (!validNome) {
            System.out.print(">> ");
            nome = sc.nextLine();
            validNome = Static.verifyNome(nome);
        }
        cliente.setNome(nome);
        // Obter um NIF válido
        System.out.println("\nInsira o NIF:");
        boolean validNif = false;
        while (!validNif) {
            System.out.print(">> ");
            String nif = sc.nextLine();
            try {
                if (verifyNif(Integer.parseInt(nif))) {
                    cliente.setNif(Integer.parseInt(nif));
                    validNif = true;
                } else
                    System.out.println("[!] NIF inválido");
            } catch (NumberFormatException e) {
                System.out.println("[!] NIF inválido");
            }
        }
        // Obter a localização do cliente
        System.out.println("\nSelecione a localização:");
        System.out.println("[1] Portugal Continental");
        System.out.println("[2] Madeira");
        System.out.println("[3] Açores");
        boolean validLoc = false;
        while (!validLoc) {
            System.out.print(">> ");
            String opt = sc.nextLine();
            switch (opt) {
                case "1":
                    cliente.setLocalizacao(Localizacao.continente);
                    validLoc = true;
                    break;
                case "2":
                    cliente.setLocalizacao(Localizacao.madeira);
                    validLoc = true;
                    break;
                case "3":
                    cliente.setLocalizacao(Localizacao.acores);
                    validLoc = true;
                    break;
            }
            if (!validLoc) {
                System.out.println("[!] Localização inválida");
            }
        }
    }

    public Cliente searchCliente() {
        Cliente res = null;
        // Imprime todos os clientes para que o utilizador possa selecionar o nome do cliente pretendido
        if (clientes.size() != 0) {
            printClientes();
            // Recebe input do utilizador
            Scanner scanner = new Scanner(System.in);
            System.out.print("\nInsira o NIF do cliente que pretende associar à fatura:\n>> ");
            String input = scanner.nextLine();
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
                            res = cliente;
                            found = true;
                        }
                    }
                    if (!found)
                        System.out.println("[!] Cliente não encontrado");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("[!]NIF invalido");
            }
        } else
            System.out.println("[!] Não existem clientes registados");
        return res;
    }
    private boolean verifyNif(int nif) {
        boolean valid = true;
        if(nif!=9){
            valid = false;
        }
        else{
            for (Cliente cliente : clientes) {
                if (cliente.getNif() == nif) {
                    valid = false;
                    break;
                }
            }
        }
        return valid;
    }
}
