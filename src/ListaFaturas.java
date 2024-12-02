import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
                    data.setDia(Integer.parseInt(temp[0]));
                    data.setMes(Integer.parseInt(temp[1]));
                    data.setAno(Integer.parseInt(temp[2]));
                    fatura.setData(data);
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

    public void exportarFaturas(String filename) {
        File f = new File(filename);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Fatura fatura : faturas) {
                bw.write(fatura.getCliente().getNome() + ";");  // Nome do cliente
                bw.write(fatura.getCliente().getNif() + ";");             // NIF do cliente
                if (fatura.getCliente().getLocalizacao() == Localizacao.continente)
                    bw.write("1#");                              // Localizacao -> Portugal Continental
                else if (fatura.getCliente().getLocalizacao() == Localizacao.madeira)
                    bw.write("2#");                              // Localizacao -> Madeira
                else if (fatura.getCliente().getLocalizacao() == Localizacao.acores)
                    bw.write("3#");                              // Localizacao -> Açores
                bw.write(fatura.getData().getDia() + "/" + fatura.getData().getMes() + "/" + fatura.getData().getAno() + "#");
                // Escrever produtos da fatura
                ArrayList<Produto> temp = fatura.getProdutos().getProdutosFatura();
                for (int i = 0; i < temp.size(); i++) {
                    Produto produto = temp.get(i);
                    // Obter uma string com o tipo do produto
                    String tipo = findTipoProduto(produto);
                    // Escrever os dados do produto no ficheiro de texto
                    writeProduto(tipo, produto, f, fw, bw);
                    if (i < temp.size() - 1)
                        bw.write("#");
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("[!] Erro ao exportar faturas");
        }
    }

    private String findTipoProduto(Produto produto) {
        // Verificar se o produto é de taxa reduzida
        try {
            TaxaReduzida taxaReduzida = (TaxaReduzida) produto;
            return "taxaReduzida";
        } catch (Exception _) {}
        // Verificar se o produto é de taxa intermedia
        try {
            TaxaIntermedia taxaIntermedia = (TaxaIntermedia) produto;
            return "taxaIntermedia";
        } catch (Exception _) {}
        // Verificar se o produto é de taxa reduzida
        try {
            TaxaNormal taxaNormal = (TaxaNormal) produto;
            return "taxaNormal";
        } catch (Exception _) {}
        // Verificar se o produto é da classe Prescrição
        try {
            Prescricao prescricao = (Prescricao) produto;
            return "prescricao";
        } catch (Exception _) {}
        // Verificar se o produto é da classe Normal
        try {
            Normal normal = (Normal) produto;
            return "normal";
        } catch (Exception _) {}

        // Caso o produto não corresponda a nenhuma das classes
        return "null";
    }

    private void writeProduto(String tipo, Produto produto, File f, FileWriter fw, BufferedWriter bw) {
        try {
            switch (tipo) {
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = (TaxaReduzida) produto;
                    bw.write("taxaReduzida;");
                    if (taxaReduzida.getBiologico())
                        bw.write("1;");
                    else bw.write("0;");
                    bw.write(taxaReduzida.getNome() + ";");
                    bw.write(taxaReduzida.getDesc() + ";");
                    bw.write(taxaReduzida.getQuantidade() + ";");
                    bw.write(taxaReduzida.getValorUnit() + ";");
                    bw.write(taxaReduzida.getIva() + ";");
                    String[] certificacoes = taxaReduzida.getCertificacoes();
                    for (int i = 0; i < certificacoes.length; i++) {
                        bw.write(certificacoes[i]);
                        if (i < certificacoes.length - 1)
                            bw.write(":");
                    }
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = (TaxaIntermedia) produto;
                    bw.write("taxaIntermedia;");
                    if (taxaIntermedia.getBiologico())
                        bw.write("1;");
                    else bw.write("0;");
                    bw.write(taxaIntermedia.getNome() + ";");
                    bw.write(taxaIntermedia.getDesc() + ";");
                    bw.write(taxaIntermedia.getQuantidade() + ";");
                    bw.write(taxaIntermedia.getValorUnit() + ";");
                    bw.write(taxaIntermedia.getIva() + ";");
                    if (taxaIntermedia.getCategoria() == CategoriaTaxaIntermedia.congelados)
                        bw.write("congelados");
                    else if (taxaIntermedia.getCategoria() == CategoriaTaxaIntermedia.enlatados)
                        bw.write("enlatados");
                    else
                        bw.write("vinho");
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = (TaxaNormal) produto;
                    bw.write("taxaNormal;");
                    if (taxaNormal.getBiologico())
                        bw.write("1;");
                    else bw.write("0;");
                    bw.write(taxaNormal.getNome() + ";");
                    bw.write(taxaNormal.getDesc() + ";");
                    bw.write(taxaNormal.getQuantidade() + ";");
                    bw.write(taxaNormal.getValorUnit() + ";");
                    bw.write(Integer.toString(taxaNormal.getIva()));
                    break;
                case "prescricao":
                    Prescricao prescricao = (Prescricao) produto;
                    bw.write("prescricao;");
                    bw.write(prescricao.getNome() + ";");
                    bw.write(prescricao.getDesc() + ";");
                    bw.write(prescricao.getQuantidade() + ";");
                    bw.write(prescricao.getValorUnit() + ";");
                    bw.write(prescricao.getIva() + ";");
                    bw.write(prescricao.getMedico());
                    break;
                case "normal":
                    Normal normal = (Normal) produto;
                    bw.write("normal;");
                    bw.write(normal.getNome() + ";");
                    bw.write(normal.getDesc() + ";");
                    bw.write(normal.getQuantidade() + ";");
                    bw.write(normal.getValorUnit() + ";");
                    bw.write(normal.getIva() + ";");
                    if (normal.getCategoria() == CategoriaNormal.beleza)
                        bw.write("beleza");
                    else if (normal.getCategoria() == CategoriaNormal.bemEstar)
                        bw.write("bemEstar");
                    else if (normal.getCategoria() == CategoriaNormal.bebes)
                        bw.write("bebes");
                    else if (normal.getCategoria() == CategoriaNormal.animais)
                        bw.write("animais");
                    else
                        bw.write("outros");
                    break;
            }
        } catch (IOException e) {
            System.out.println("[!] Erro ao escrever o produto");
        }
    }

    public void importarFaturas(String filename, Produtos produtosRegistados) {
        File f = new File(filename);
        if (f.exists() && f.isFile()) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linha;
                int numFatura = 1;
                while ((linha = br.readLine()) != null) {
                    Fatura fatura = parseFatura(linha, numFatura, produtosRegistados);
                    numFatura++;
                    faturas.add(fatura);
                }
                System.out.println("[!] Faturas importadas com sucesso!");
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("[!] Ficheiro ao abrir o ficheiro");
            } catch (IOException e) {
                System.out.println("[!] Erro ao ler o ficheiro");
            }
        } else
            System.out.println("[!] Ficheiro não encontrado");
    }

    public Fatura parseFatura(String linha, int numFatura, Produtos produtosRegistados) {
        Fatura fatura = new Fatura(numFatura);
        // Separar os diferentes elementos constituintes da fatura
        String[] dadosFatura = linha.split("#");

        // Obter os dados do cliente
        String[] dadosCliente = dadosFatura[0].split(";");
        fatura.setCliente(parseCliente(dadosCliente));

        // Obter a data da fatura
        String[] data = dadosFatura[1].split("/");
        fatura.setData(fatura.parseData(data));

        // Obter os produtos associados à fatura
        String[] produtos = dadosFatura[2].split("&");
        for (int i = 0; i < produtos.length; i++) {
            String[] dadosProduto = produtos[i].split(";");
            switch (dadosProduto[0]) {
                case "prescricao":
                    Prescricao prescricao = fatura.parsePrescricao(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(prescricao);
                    produtosRegistados.registarProduto(prescricao);
                    break;
                case "normal":
                    Normal normal = fatura.parseNormal(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(normal);
                    produtosRegistados.registarProduto(normal);
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = fatura.parseTaxaNormal(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaNormal);
                    produtosRegistados.registarProduto(taxaNormal);
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = fatura.parseTaxaIntermedia(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaIntermedia);
                    produtosRegistados.registarProduto(taxaIntermedia);
                    break;
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = fatura.parseTaxaReduzida(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaReduzida);
                    produtosRegistados.registarProduto(taxaReduzida);
                    break;
            }
        }
        return fatura;
    }

    public Cliente parseCliente(String[] dadosCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(dadosCliente[0]);
        cliente.setNif(Integer.parseInt(dadosCliente[1]));
        switch (dadosCliente[2]) {
            case "1":
                cliente.setLocalizacao(Localizacao.continente);
                break;
            case "2":
                cliente.setLocalizacao(Localizacao.madeira);
                break;
            case "3":
                cliente.setLocalizacao(Localizacao.acores);
                break;
        }
        return cliente;
    }
}
