import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe para gerir os clientes adicionados à aplicação
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class ListaClientes implements Serializable {
    /**
     * Lista de clientes
     */
    private ArrayList<Cliente> clientes;

    /**
     * Construtor por omissão, inicializa o ArrayList de clientes
     */
    public ListaClientes() {
        clientes = new ArrayList<Cliente>();
    }

    /**
     * Construtor da classe, recebe um ArrayList de clientes
     * para inicializar o respetivo atributo
     *
     * @param clientes Lista de clientes
     */
    public ListaClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Metodo de acesso ao atributo 'clientes' (getter)
     * @return Lista de clientes
     */
    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    /**
     * Metodo de acesso ao atributo 'clientes' (setter)
     * @param clientes Lista de clientes
     */
    public void setClientes(ArrayList<Cliente> clientes) {
        this.clientes = clientes;
    }

    /**
     * Metodo para imprimir todos os clientes registados
     * na aplicação em forma de tabela
     */
    public void printClientes() {
        System.out.printf("\n %-25s | %-9s | %-20s%n", "NOME DO CLIENTE", "NIF", "LOCALIZAÇÃO");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    /**
     * Metodo para adicionar um cliente à lista de clientes,
     * invoca o metodo 'getDadosCliente' para obter os dados
     * do cliente, adicionando-o ao ArrayList de seguida
     */
    public void addCliente() {
        // Criar o novo objeto Cliente
        Cliente cliente = new Cliente();
        // Obter os dados do cliente
        getDadosCliente(cliente);
        // Adicionar o objeto criado ao ArrayList dos Clientes
        clientes.add(cliente);
    }

    /**
     * Metodo para editar os dados de um cliente adicionado
     * previamente à lista de clientes, procura o cliente
     * recorrendo ao metodo 'searchCliente' e permite a
     * edição dos dados usando o metodo 'getDadosCliente'
     */
    public void editCliente() {
        // Encontrar o cliente a editar no ArrayList de Clientes
        Cliente cliente = searchCliente();
        if (cliente != null)
            // Obter os novos dados do cliente
            getDadosCliente(cliente);
    }

    /**
     * Metodo para obter do utilizador os dados de um cliente
     *
     * @param cliente Objeto Cliente onde os dados recebidos
     *                serão armazenados
     */
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

    /**
     * Metodo para procurar um cliente com base no NIF dado
     * pelo utilizador, devolve um objeto Cliente caso o cliente
     * seja encontrado no ArrayList de clientes
     *
     * @return Cliente com o NIF fornecido ou 'null', caso não
     * seja encontrado
     */
    public Cliente searchCliente() {
        Cliente res = null;
        // Imprime todos os clientes para que o utilizador possa selecionar o nome do cliente pretendido
        if (!clientes.isEmpty()) {
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
                System.out.println("[!] NIF inválido");
            }
        } else
            System.out.println("[!] Não existem clientes registados");
        return res;
    }

    /**
     * Metodo para efetuar a verificação de um NIF passado
     * como argumento, devolve o valor booleano true se o
     * NIF for válido e false caso contrário
     *
     * @param nif Número de contribuinte a verificar
     * @return Valor booleano que descreve a validade do NIF
     */
    private boolean verifyNif(int nif) {
        boolean valid = true;
        if (nif!=9)
            valid = false;
        else
            for (Cliente cliente : clientes)
                if (cliente.getNif() == nif) {
                    valid = false;
                    break;
                }
        return valid;
    }
}
