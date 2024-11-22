import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

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
            // Caso seja um Produto Alimentar
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
                            // Obter os dados comuns da classe Produto
                            getProdInfo(produto);
                            // Definir a taxa do IVA
                            setTaxaIva(cliente, produto, 6, 5, 4);
                            // Extras
                            if (cert == 4)
                                produto.setIva(produto.getIva() - 1);
                            this.produtos.add(produto);
                            validCert = true;
                        }
                        // Sem certificados => Taxa intermédia ou normal
                        else if (cert == 0) {
                            System.out.println("Selecione a categoria do produto:");
                            System.out.println("[1] Sem categoria");
                            System.out.println("[2] Congelados");
                            System.out.println("[3] Enlatados");
                            System.out.println("[4] Vinho");
                            boolean menuTaxa = false;
                            while (!menuTaxa) {
                                System.out.print(">> ");
                                String optTaxa = sc.nextLine();

                                // Taxa Normal
                                if (optTaxa.equals("1")) {
                                    TaxaNormal produto = new TaxaNormal();
                                    // Obter os dados comuns da classe Produto
                                    getProdInfo(produto);
                                    // Definir a taxa do IVA
                                    setTaxaIva(cliente, produto, 23, 22, 16);
                                    produtos.add(produto);
                                }

                                // Taxa Intermedia
                                else if (optTaxa.equals("2") || optTaxa.equals("3") || optTaxa.equals("4")) {
                                    TaxaIntermedia produto = new TaxaIntermedia();
                                    // Obter os dados comuns da classe Produto
                                    getProdInfo(produto);
                                    // Definir a taxa do IVA
                                    setTaxaIva(cliente, produto, 13, 12, 9);
                                    if (optTaxa.equals("2"))
                                        produto.setCategoria(CategoriaTaxaIntermedia.congelados);
                                    if (optTaxa.equals("3"))
                                        produto.setCategoria(CategoriaTaxaIntermedia.enlatados);
                                    if (optTaxa.equals("4")) {
                                        produto.setCategoria(CategoriaTaxaIntermedia.vinho);
                                        produto.setIva(produto.getIva() + 1);
                                    }
                                    produtos.add(produto);
                                }
                            }
                            validCert = true;
                        }
                        else {
                            System.out.println("[!] Número inválido");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("[!] Número inválido");
                    }
                }
                menu = true;
            }
            // Caso seja um Produto de Farmácia
            else if (opt.equals("2")) {
                boolean validCert = false;
                System.out.println("[1] Produto com prescricao");
                System.out.println("[2] Produto sem prescricao");
                while (!validCert) {
                    System.out.print(">> ");
                    String optCert = sc.nextLine();
                    // Caso seja um produto com prescrição
                    if (optCert.equals("1")) {
                        Prescricao produto = new Prescricao();
                        System.out.println("Insira o medico que prescreveu a receita");

                        boolean validNome = false;
                        while (!validNome) {
                            System.out.print(">> ");
                            String medico = sc.nextLine();
                            validNome = verifyNome(medico);
                            if (validNome)
                                produto.setMedico(medico);
                            else
                                System.out.println("[!] Nome inválido");
                        }

                        // Obter os dados do produto com prescrição
                        getProdInfo(produto);
                        // Definir a taxa do IVA
                        setTaxaIva(cliente, produto, 6, 5, 4);
                        // Adicionar o produto ao ArrayList de produtos da respetiva fatura
                        produtos.add(produto);
                    }
                    // Caso seja um produto sem prescrição
                    if(optCert.equals("2")) {
                        Normal produto = new Normal();
                        System.out.println("Insira o tipo de produto:");
                        System.out.println("[1] Produto de beleza");
                        System.out.println("[2] Produto de bem estar");
                        System.out.println("[3] Produto para bebes");
                        System.out.println("[4] Produto para animais");
                        System.out.println("[5] Outros produtos");
                        boolean menuNormal = true;
                        while (!menuNormal) {
                            System.out.print(">> ");
                            String optNormal = sc.nextLine();
                            if (optNormal.equals("1")) {
                                produto.setCategoria(CategoriaNormal.beleza);
                                menuNormal = false;
                            }
                            if (optNormal.equals("2")) {
                                produto.setCategoria(CategoriaNormal.bemEstar);
                                menuNormal = false;
                            }
                            if (optNormal.equals("3")) {
                                produto.setCategoria(CategoriaNormal.bebes);
                                menuNormal = false;
                            }
                            if (optNormal.equals("4")) {
                                produto.setCategoria(CategoriaNormal.animais);
                                menuNormal = false;
                            }
                            if (optNormal.equals("5")) {
                                produto.setCategoria(CategoriaNormal.outros);
                                menuNormal = false;
                            }
                            else {
                                System.out.println("[!] Categoria inválida");
                            }
                        }
                        // Obter os dados do produto sem prescrição
                        getProdInfo(produto);
                        // Definir a taxa do IVA
                        setTaxaIva(cliente, produto, 23, 23, 23);
                        if (produto.getCategoria() == CategoriaNormal.animais)
                            produto.setIva(produto.getIva() - 1);
                        // Adicionar o produto ao ArrayList de produtos da respetiva fatura
                        produtos.add(produto);
                    }
                }
                menu = true;
            }
            else
                System.out.println("[!] Escolha inválida");
        }
    }

    public void printProdutos() {
        for (Produto produto : this.produtos) {
            System.out.println(produto);
        }
    }

    private void getProdInfo(Produto produto) {
        Scanner sc = new Scanner(System.in);
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
    }

    private void setTaxaIva(Cliente cliente, Produto produto, int taxaContinente, int taxaMadeira, int taxaAçores) {
        if (cliente.getLocalizacao().equalsIgnoreCase("continente"))
            produto.setIva(taxaContinente);
        else if (cliente.getLocalizacao().equalsIgnoreCase("madeira"))
            produto.setIva(taxaMadeira);
        else if (cliente.getLocalizacao().equalsIgnoreCase("açores"))
            produto.setIva(taxaAçores);
    }

    private boolean verifyNome(String str){
        boolean res = true;
        // Verifica se o nome começa ou acaba com algum caractere que não seja uma letra
        if (!Character.isLetter(str.charAt(0)) || !Character.isLetter(str.charAt(str.length()-1)))
            res = false;
        // Verifica se existem caracteres que não sejam letras ou espaços dentro da string com o nome
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ')
                res = false ;
        }
        return res;
    }
}

