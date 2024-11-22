import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListaFaturas {
    // Atributos da classe
    private ArrayList<Fatura> faturas;

    // Construtores
    public ListaFaturas() {
        faturas = new ArrayList<Fatura>();
    }

    // Metodos de acesso
    public ArrayList<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(ArrayList<Fatura> faturas) {
        this.faturas = faturas;
    }


    public void printFaturas() {
        System.out.println(" [HEADER] ");
        for(Fatura fatura : faturas) {
            System.out.println(fatura);
        }
    }

    public void addFatura(ListaClientes clientes) {
        Fatura fatura = new Fatura();
        Scanner sc = new Scanner(System.in);
        // Atribuir um número à fatura com base no número de faturas presente no ArrayList
        fatura.setNumFatura(faturas.size() + 1);
        // Obter os dados da fatura
        getFaturaInfo(fatura, clientes);
        // Permitir ao utilizador realizar operações no ArrayList de Produtos da fatura
        boolean addingProdutos = true;
        while (addingProdutos) {
            System.out.println("[1] Adicionar produto");
            System.out.println("[2] Ver produtos");
            System.out.println("[3] Remover produto");
            System.out.println("[0] Finalizar");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                fatura.getProdutos().addProduto(fatura.getCliente());
            }
            if (opcao.equals("2")) {
                if (fatura.getProdutos().getProdutos().size() > 0)
                    fatura.getProdutos().printProdutos();
                else
                    System.out.println("[!] Ainda não foram adicionados produtos!");
            }
            // TODO -> Implementar função para remover um produto do ArrayList de Produtos
            /*
            if (opcao.equals("3")) {
                produtos.removeProduto();
            }
             */
            if (opcao.equals("0")) {
                addingProdutos = false;
            }
            else {
                System.out.println("[!] Opção inválida");
            }
        }
        this.faturas.add(fatura);
    }

    public void editFatura(ListaClientes clientes) {
        // Imprimir todas as faturas registadas
        printFaturas();
        // Selecionar uma das faturas com base nos respetivos números
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o número da fatura que pretende editar:");
        int numFatura = 0;
        boolean valid = false;
        while (!valid) {
            System.out.print(">> ");
            try {
                numFatura = sc.nextInt();
                valid = true;
            } catch (InputMismatchException e) {
                System.out.println("[!] Input inválido");
            }
        }
        // Procurar a fatura com o número dado pelo utilizador
        for (Fatura fatura : faturas) {
            if (fatura.getNumFatura() == numFatura) {
                // Editar os dados da fatura
                getFaturaInfo(fatura, clientes);
            }
        }
    }

    private void getFaturaInfo(Fatura fatura, ListaClientes clientes) {
        Scanner sc = new Scanner(System.in);
        // Caso não existam clientes registados, o utilizador deve registar um
        if (clientes.getClientes().size() == 0) {
            clientes.addCliente();
        }
        // Imprimir todos os clientes presentes no ArrayList de Clientes
        System.out.println("==> CLIENTES");
        clientes.printClientes();
        // Associar um cliente à fatura
        System.out.println("Insira o nome do cliente que pretende associar:");
        boolean foundNomeCliente = false;
        while (!foundNomeCliente) {
            System.out.print(">> ");
            String nomeCliente = sc.nextLine();
            for (Cliente cliente : clientes.getClientes() ) {
                if (cliente.getNome().equals(nomeCliente)) {
                    fatura.setCliente(cliente);
                    foundNomeCliente = true;
                }
            }
            if (!foundNomeCliente) {
                System.out.println("[!] Cliente não encontrado");
            }
        }
        // Obter a data da fatura
        System.out.println("Insira a data: (dd/mm/aaaa)");
        Data data = new Data();
        boolean foundData = false;
        while (!foundData) {
            System.out.print(">> ");
            String dataFatura = sc.nextLine();
            try{
                String[] temp = dataFatura.split("/");
                if(temp.length==3) {
                    data.dia = Integer.parseInt(temp[0]);
                    data.mes = Integer.parseInt(temp[1]);
                    data.ano = Integer.parseInt(temp[2]);
                    foundData = true;
                }
                else
                    System.out.println("[!] Data invalida");
            }

            catch(NumberFormatException e){
                System.out.println("[!] Data invalida");
            }
        }
    }
}
