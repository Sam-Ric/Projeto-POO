import java.util.ArrayList;
import java.util.Scanner;

public class ListaFaturas {
    // Atributos da classe
    private ArrayList<Fatura> faturas;

    // Construtores
    public ListaFaturas() {}

    // Metodos de acesso
    public ArrayList<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(ArrayList<Fatura> faturas) {
        this.faturas = faturas;
    }

    // todo: PREENCHER AS FUNCOES
    public void printFaturas() {
        for(Fatura fatura : faturas) {
            System.out.println(fatura);
        }
    }

    public void addFatura(ListaClientes clientes,ListaProdutos produtos) {
        Fatura fatura = new Fatura();
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o novo numero da fatura: ");
        boolean validNumFatura = false;
        while (!validNumFatura) {
            System.out.print(">> ");
            String numFatura = sc.nextLine();
            try {
                fatura.setNumFatura(Integer.parseInt(numFatura));
                validNumFatura = true;
            } catch (NumberFormatException e) {
                System.out.println("[!] Numero da fatura inválido");
            }
        }
        System.out.println("Insira o nome do Cliente: ");
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
        }
        System.out.println("Insira a Data: (dd/mm/aaaa) ");
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

        boolean menuProduto = false;
        while (!menuProduto) {
            System.out.println("[1] Adicionar produto ");
            System.out.println("[2] Ver produtos ");
            System.out.println("[3] Remover produto ");
            System.out.println("[0] Finalizar ");
            String opcao = sc.nextLine();
            if (opcao.equals("1")) {
                produtos.addProduto();
            }
            if (opcao.equals("2")) {
                produtos.printProdutos();
            }
            if (opcao.equals("3")) {
                produtos.removeProduto();
            }
            if (opcao.equals("0")) {
                menuProduto = true;
            }
            else{
                System.out.println("opcao invalida");
            }
        }
        this.faturas.add(fatura);
    }

    public void editFatura() {
        this.printFaturas();
        Scanner sc = new Scanner(System.in);
        System.out.println("Insira o numero da fatura: ");
        boolean validNumFatura = false;
        while (!validNumFatura) {
            System.out.print(">> ");
            String numFatura = sc.nextLine();
            if(){}
        }
    }
}
