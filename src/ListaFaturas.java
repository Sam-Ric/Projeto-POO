import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class ListaFaturas implements Serializable {
    // Atributos da classe
    private ArrayList<Fatura> faturas;
    private int numFaturaAtual;

    // Construtores
    public ListaFaturas() {
        faturas = new ArrayList<Fatura>();
        numFaturaAtual = 1;
    }

    public ListaFaturas(ArrayList<Fatura> faturas, int numFaturaAtual) {
        this.faturas = faturas;
        this.numFaturaAtual = numFaturaAtual;
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

    public void addFatura(ListaClientes clientes, Produtos produtosRegistados) {
        Scanner sc = new Scanner(System.in);
        // Criar uma nova fatura e atribuir-lhe um número com base no número de faturas presente no ArrayList
        Fatura fatura = new Fatura(numFaturaAtual);
        numFaturaAtual++;
        // Obter os dados da fatura
        getDadosFatura(fatura, clientes, produtosRegistados);
        // Adicionar a fatura criada ao ArrayList de faturas
        faturas.add(fatura);
    }

    public void editFatura(ListaClientes clientes, Produtos produtosRegistados) {
        if (faturas.size() != 0) {
            // Imprimir todas as faturas registadas
            printFaturas();
            // Selecionar uma das faturas com base nos respetivos números
            Scanner sc = new Scanner(System.in);
            System.out.println("\nInsira o número da fatura que pretende editar:");
            int numFatura = 0;
            boolean valid = false;
            while (!valid) {
                System.out.print(">> ");
                String strNumFatura = sc.nextLine();
                try {
                    numFatura = Integer.parseInt(strNumFatura);
                    valid = true;
                } catch (NumberFormatException e) {
                    System.out.println("[!] Input inválido");
                }
            }
            // Procurar a fatura com o número dado pelo utilizador
            for (Fatura fatura : faturas) {
                if (fatura.getNumFatura() == numFatura) {
                    // Editar os dados da fatura
                    getDadosFatura(fatura, clientes, produtosRegistados);
                }
            }
        } else
            System.out.println("[!] Não existem faturas para editar");

    }

    private void getDadosFatura(Fatura fatura, ListaClientes clientes, Produtos produtosRegistados) {
        Scanner sc = new Scanner(System.in);
        // Caso não existam clientes registados, o utilizador deve registar um
        if (clientes.getClientes().size() == 0) {
            clientes.addCliente();
        }
        // Associar um cliente à fatura
        do {
            fatura.setCliente(clientes.searchCliente());
        } while (fatura.getCliente() == null);

        // Obter a data da fatura
        System.out.println("\nInsira a data: (dd/mm/aaaa)");
        Data data = new Data();
        boolean foundData = false;
        while (!foundData) {
            System.out.print(">> ");
            String dataFatura = sc.nextLine();
            try{
                String[] temp = dataFatura.split("/");

                if(temp.length==3 && data.isDiaValido(Integer.parseInt(temp[0])) && data.isMesValido(Integer.parseInt(temp[1]))&& temp[2].length()==4){
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

        // Permitir ao utilizador realizar operações no ArrayList de Produtos da fatura
        boolean addingProdutos = true;
        while (addingProdutos) {
            System.out.println("\n[1] Adicionar novo produto");
            System.out.println("[2] Adicionar produto existente");
            System.out.println("[3] Ver produtos adicionados");
            System.out.println("[4] Remover produto");
            System.out.println("[0] Finalizar\n>> ");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                fatura.getProdutos().addProduto(fatura.getCliente(), produtosRegistados);
            }
            else if (opcao.equals("2")) {
                if (produtosRegistados.getProdutos().size() != 0) {
                    // Imprimir todos os produtos registados
                    produtosRegistados.printProdutosRegistados();
                    System.out.print("Insira o código do produto: ");
                    String code = sc.nextLine();
                    try {
                        // Encontrar o produto com o codigo dado
                        Produto temp = produtosRegistados.getProdutoRegistado(Integer.parseInt(code));
                        if (temp != null)
                            // Adicionar o produto selecionado ao ArrayList de produtos da fatura atual
                            fatura.getProdutos().getProdutosFatura().add(temp);
                        else
                            System.out.println("[!] Produto não encontrado");
                    } catch (NumberFormatException e) {
                        System.out.println("[!] Código inválido");
                    }
                } else
                    System.out.println("[!] Não existem produtos registados");
            }
            else if (opcao.equals("3")) {
                if (fatura.getProdutos().getProdutosFatura().size() > 0)
                    fatura.getProdutos().printProdutos();
                else
                    System.out.println("[!] Ainda não foram adicionados produtos!");
            }
            if (opcao.equals("4")) {
                fatura.getProdutos().removeProduto();
            }
            else if (opcao.equals("0")) {
                addingProdutos = false;
            }
        }
    }
}
