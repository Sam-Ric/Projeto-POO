import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ListaProdutos {
    // Atributos da classe
    private ArrayList<Produto> produtos;

    // Construtores
    public ListaProdutos() {}

    // Metodos de acesso
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    public void addProduto(Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Selecione o tipo de produto:");
        System.out.println("[1] Produto Alimentar");
        System.out.println("[2] Produto de Farmácia");
        boolean menu = false;
        while (!menu) {
            // Obter o tipo de produto
            System.out.print(">> ");
            String opt = sc.nextLine();
            if (opt.equals("1")) {
                // Verificar se o produto tem certificacoes
                boolean validCert = false;
                while (!validCert) {
                    System.out.print("Número de certificações: ");
                    try {
                        int cert = sc.nextInt();
                        // Se tiver certificações => Taxa Reduzida
                        if (cert > 0 && cert <= 4) {
                            TaxaReduzida produto = new TaxaReduzida();
                            // Obter os certificados
                            String[] temp = new String[cert + 1];
                            // TODO -> Perguntar como funciona a atribuição de certificações
                            System.out.println("Insira os nomes das certificações:");
                            for (int i = 0; i < cert; i++) {
                                System.out.print(">> ");
                                String nomeCert = sc.nextLine();
                                temp[i] = nomeCert;
                            }
                            // TODO -> Atribuir códigos
                            // Obter o nome do produto
                            System.out.print("Insira o nome do produto:\n>> ");
                            produto.setNome(sc.nextLine());
                            // Obter a descricao do produto
                            System.out.print("Insira uma breve descrição do produto:\n>> ");
                            produto.setDesc(sc.nextLine());
                            // Obter a quantidade do produto
                            System.out.println("Insira a quantidade do produto:");
                            boolean validQuantidade = false;
                            while (!validQuantidade) {
                                System.out.print(">> ");
                                try {
                                    produto.setQuantidade(sc.nextInt());
                                    validQuantidade = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("[!] Quantidade inválida");
                                }
                            }
                            // Obter o valor unitário
                            boolean validValor = false;
                            while (!validValor) {
                                System.out.print(">> ");
                                try {
                                    produto.setValorUnit(sc.nextFloat());
                                    validValor = true;
                                } catch (InputMismatchException e) {
                                    System.out.println("[!] Valor inválido");
                                }
                            }
                            // Definir a taxa do IVA
                            if (cliente.getLocalizacao().equalsIgnoreCase("continente"))
                                produto.setIva(6);
                            else if (cliente.getLocalizacao().equalsIgnoreCase("madeira"))
                                produto.setIva(5);
                            else if (cliente.getLocalizacao().equalsIgnoreCase("açores"))
                                produto.setIva(4);
                            if (cert == 4)
                                produto.setIva(produto.getIva() - 1);
                            this.produtos.add(produto);
                            validCert = true;
                        } else if (cert == 0) {
                            validCert = true;
                        } else {
                            System.out.println("[!] Número inválido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("[!] Número inválido");
                    }
                }
            }
        }
        // TODO -> Criar objeto com a classe correta
    }

    public void printProdutos() {
        for (int i = 0; i < this.produtos.size(); ++i) {
            System.out.println(this.produtos.get(i));
        }
    }
}
