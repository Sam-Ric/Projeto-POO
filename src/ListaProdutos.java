import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.lang.*;

public class ListaProdutos implements Serializable {
    // Atributos da classe
    private ArrayList<Produto> produtosFatura;

    // Construtores
    public ListaProdutos() {
        produtosFatura = new ArrayList<Produto>();
    }

    public ListaProdutos(ArrayList<Produto> produtosFatura) {
        this.produtosFatura = produtosFatura;
    }

    // Metodos de acesso
    public ArrayList<Produto> getProdutosFatura() {
        return produtosFatura;
    }

    public void setProdutosFatura(ArrayList<Produto> produtosFatura) {
        this.produtosFatura = produtosFatura;
    }


    public void addProduto(Cliente cliente, Produtos produtosRegistados) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\nSelecione o tipo de produto:");
        System.out.println("[1] Produto Alimentar");
        System.out.println("[2] Produto de Farmácia");
        boolean menu = false;
        while (!menu) {
            // Obter o tipo de produto
            System.out.print(">> ");
            String opt = sc.nextLine();
            // Caso seja um Produto Alimentar
            if (opt.equals("1")) {
                // Verificar se o produto é biológico
                boolean biologico = false;
                System.out.println("\nO produto é biológico?");
                System.out.println("[1] Sim");
                System.out.println("[2] Não");
                boolean validBio = false;
                while (!validBio) {
                    System.out.print(">> ");
                    String op = sc.nextLine();
                    switch (op) {
                        case "1":
                            biologico = true;
                            validBio = true;
                            break;
                        case "2":
                            biologico = false;
                            validBio = true;
                            break;
                    }
                    if (!validBio)
                        System.out.println("[!] Escolha inválida");
                }
                // Verificar se o produto tem certificacoes
                boolean validCert = false;
                while (!validCert) {
                    System.out.print("\nNúmero de certificações: ");
                    try {
                        int cert = sc.nextInt();
                        sc.nextLine(); // Limpar o buffer após ler o inteiro
                        // Se tiver certificações => Taxa Reduzida
                        if (cert > 0 && cert <= 4) {
                            TaxaReduzida produto = new TaxaReduzida();
                            produto.setBiologico(biologico);
                            // Obter os certificados
                            String[] temp = new String[cert];
                            System.out.println("Insira os nomes das certificações:");
                            int index = 0;
                            while (index < temp.length) {
                                System.out.print(">> ");
                                String nomeCert = sc.nextLine();
                                temp[index] = nomeCert;
                                index++;
                            }
                            produto.setCertificacoes(temp);
                            // Obter os dados comuns da classe Produto
                            getProdInfo(produto);
                            // Definir a taxa do IVA
                            setTaxaIva(cliente, produto, 6, 5, 4);
                            // Extras
                            if (cert == 4)
                                produto.setIva(produto.getIva() - 1);
                            if (biologico)
                                if (produto.getIva() > 10)
                                    produto.setIva(produto.getIva() - 10);
                                else
                                    produto.setIva(0);
                            produtosRegistados.registarProduto(produto);
                            produtosFatura.add(produto);
                            validCert = true;
                        }
                        // Sem certificados => Taxa intermédia ou normal
                        else if (cert == 0) {
                            System.out.println("\nSelecione a categoria do produto:");
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
                                    produto.setBiologico(biologico);
                                    // Obter os dados comuns da classe Produto
                                    getProdInfo(produto);
                                    // Definir a taxa do IVA
                                    setTaxaIva(cliente, produto, 23, 22, 16);
                                    if (biologico)
                                        if (produto.getIva() > 10)
                                            produto.setIva(produto.getIva() - 10);
                                        else
                                            produto.setIva(0);
                                    // Registar o novo produto no ArrayList de todos os produtosFatura
                                    produtosRegistados.registarProduto(produto);
                                    // Adicionar o produto ao ArrayList de produtosFatura da fatura
                                    produtosFatura.add(produto);
                                    menuTaxa = true;
                                }

                                // Taxa Intermedia
                                else if (optTaxa.equals("2") || optTaxa.equals("3") || optTaxa.equals("4")) {
                                    TaxaIntermedia produto = new TaxaIntermedia();
                                    produto.setBiologico(biologico);
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
                                    if (biologico)
                                        if (produto.getIva() > 10)
                                            produto.setIva(produto.getIva() - 10);
                                        else
                                            produto.setIva(0);
                                    // Registar o novo produto no ArrayList de todos os produtosFatura
                                    produtosRegistados.registarProduto(produto);
                                    // Adicionar o produto ao ArrayList de produtosFatura da fatura
                                    produtosFatura.add(produto);
                                    menuTaxa = true;
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
                boolean validProd = false;
                System.out.println("\n[1] Produto com prescricao");
                System.out.println("[2] Produto sem prescricao");
                while (!validProd) {
                    System.out.print(">> ");
                    String optCert = sc.nextLine();
                    // Caso seja um produto com prescrição
                    if (optCert.equals("1")) {
                        Prescricao produto = new Prescricao();
                        System.out.println("\nInsira o medico que prescreveu a receita");

                        boolean validNome = false;
                        while (!validNome) {
                            System.out.print(">> ");
                            String medico = sc.nextLine();
                            validNome = Static.verifyNome(medico);
                            if (validNome)
                                produto.setMedico(medico);
                            else
                                System.out.println("[!] Nome inválido");
                        }

                        // Obter os dados do produto com prescrição
                        getProdInfo(produto);
                        // Definir a taxa do IVA
                        setTaxaIva(cliente, produto, 6, 5, 4);
                        // Registar o novo produto no ArrayList de todos os produtosFatura
                        produtosRegistados.registarProduto(produto);
                        // Adicionar o produto ao ArrayList de produtosFatura da respetiva fatura
                        produtosFatura.add(produto);
                        validProd = true;
                    }
                    // Caso seja um produto sem prescrição
                    else if (optCert.equals("2")) {
                        Normal produto = new Normal();
                        System.out.println("\nInsira o tipo de produto:");
                        System.out.println("[1] Produto de beleza");
                        System.out.println("[2] Produto de bem estar");
                        System.out.println("[3] Produto para bebes");
                        System.out.println("[4] Produto para animais");
                        System.out.println("[5] Outros produtosFatura");
                        boolean menuNormal = false;
                        while (!menuNormal) {
                            System.out.print(">> ");
                            String optNormal = sc.nextLine();
                            if (optNormal.equals("1")) {
                                produto.setCategoria(CategoriaNormal.beleza);
                                menuNormal = true;
                            }
                            else if (optNormal.equals("2")) {
                                produto.setCategoria(CategoriaNormal.bemEstar);
                                menuNormal = true;
                            }
                            else if (optNormal.equals("3")) {
                                produto.setCategoria(CategoriaNormal.bebes);
                                menuNormal = true;
                            }
                            else if (optNormal.equals("4")) {
                                produto.setCategoria(CategoriaNormal.animais);
                                menuNormal = true;
                            }
                            else if (optNormal.equals("5")) {
                                produto.setCategoria(CategoriaNormal.outros);
                                menuNormal = true;
                            }
                            else
                                System.out.println("[!] Categoria inválida");
                        }
                        // Obter os dados do produto sem prescrição
                        getProdInfo(produto);
                        // Definir a taxa do IVA
                        setTaxaIva(cliente, produto, 23, 23, 23);
                        if (produto.getCategoria() == CategoriaNormal.animais)
                            produto.setIva(produto.getIva() - 1);
                        // Registar o novo produto no ArrayList de todos os produtosFatura
                        produtosRegistados.registarProduto(produto);
                        // Adicionar o produto ao ArrayList de produtosFatura da respetiva fatura
                        produtosFatura.add(produto);
                        validProd = true;
                    }
                }
                menu = true;
            }
            else
                System.out.println("[!] Escolha inválida");
        }
    }

    public void removeProduto() {
        Scanner sc = new Scanner(System.in);
        printProdutos();
        System.out.print("Insira o código do produto: ");
        String code = sc.nextLine();
        boolean removed = false;
        try {
            for (int i = 0; i < produtosFatura.size(); i++) {
                if (produtosFatura.get(i).getCodigo() == Integer.parseInt(code)) {
                    produtosFatura.remove(produtosFatura.get(i));
                    System.out.println("[!] Produto removido com sucesso!");
                    removed = true;
                }
            }
            if (!removed)
                System.out.println("[!] Produto não encontrado");
        } catch (NumberFormatException e) {
            System.out.println("[!] Código inválido");
        }
    }

    public void printProdutos() {
        for (int i = 0; i < produtosFatura.size(); i++) {
            System.out.println(produtosFatura.get(i));
            if (i != produtosFatura.size() - 1)
                System.out.print("\n");
        }
    }

    private void getProdInfo(Produto produto) {
        Scanner sc = new Scanner(System.in);
        
        // Obter o nome do produto
        System.out.print("\nInsira o nome do produto:\n>> ");
        produto.setNome(sc.nextLine());
    
        // Obter a descricao do produto
        System.out.print("\nInsira uma breve descrição do produto:\n>> ");
        produto.setDesc(sc.nextLine());
    
        // Obter a quantidade do produto
        System.out.println("\nInsira a quantidade do produto:");
        boolean validQuantidade = false;
        while (!validQuantidade) {
            System.out.print(">> ");
            String quantidade = sc.nextLine();
            try {
                produto.setQuantidade(Integer.parseInt(quantidade));
                validQuantidade = true;
            } catch (NumberFormatException e) {
                System.out.println("[!] Quantidade inválida");
            }
        }
    
        // Obter o valor unitário
        boolean validValor = false;
        System.out.println("\nInsira o valor unitário do produto:");
        while (!validValor) {
            System.out.print(">> ");
            String valor = sc.nextLine();
            try {
                produto.setValorUnit(Float.parseFloat(valor));
                validValor = true;
            } catch (NumberFormatException e) {
                System.out.println("[!] Valor inválido");
            }
        }
    }

    private void setTaxaIva(Cliente cliente, Produto produto, int taxaContinente, int taxaMadeira, int taxaAcores) {
        if (cliente.getLocalizacao() == Localizacao.continente)
            produto.setIva(taxaContinente);
        else if (cliente.getLocalizacao() == Localizacao.madeira)
            produto.setIva(taxaMadeira);
        else if (cliente.getLocalizacao() == Localizacao.acores)
            produto.setIva(taxaAcores);
    }
}

