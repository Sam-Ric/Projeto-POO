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
        if (faturas.size() == 0)
            System.out.println("[!] Não existem faturas para imprimir!");
        else {
            System.out.println(String.format(" %-3s | %-20s | %-20s | %-11s | %-12s | %-12s", "Nº", "CLIENTE", "LOCALIZAÇÃO", "Nº PRODUTOS", "TOTAL S/ IVA", "TOTAL C/ IVA"));
            for (Fatura fatura : faturas) {
                System.out.println(fatura);
            }
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
            System.out.println("\n[1] Adicionar produto");
            System.out.println("[2] Ver produtos");
            System.out.println("[3] Remover produto");
            System.out.println("[0] Finalizar\n>> ");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                fatura.getProdutos().addProduto(fatura.getCliente()); 
            }
            else if (opcao.equals("2")) {
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
            else if (opcao.equals("0")) {
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
        System.out.println("\nInsira o número da fatura que pretende editar:");
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
        // Associar um cliente à fatura
        Cliente cliente = null;
        while (fatura.getCliente() == null)
            fatura.setCliente(clientes.searchCliente());


        // Obter a data da fatura
        System.out.println("\nInsira a data: (dd/mm/aaaa)");
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
