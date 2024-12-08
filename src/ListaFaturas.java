import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe para gerir as faturas registadas na aplicação
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class ListaFaturas implements Serializable {
    /**
     * ArrayList que armazena as faturas
     */
    private ArrayList<Fatura> faturas;
    /**
     * Variável com o número da próxima fatura a ser adicionada
     */
    private int numFaturaAtual;

    /**
     * Construtor por omissão, inicializa o ArrayList das faturas
     * e a variável com o número da próxima fatura a ser adicionada
     */
    public ListaFaturas() {
        faturas = new ArrayList<Fatura>();
        numFaturaAtual = 1;
    }

    /**
     * Construtor da classe, recebe dados para inicializar o ArrayList
     * e a variável com o número da próxima fatura a ser adicionada
     * @param faturas ArrayList de faturas
     * @param numFaturaAtual Número a ser atribuído à próxima fatura a
     *                       a ser inserida
     */
    public ListaFaturas(ArrayList<Fatura> faturas, int numFaturaAtual) {
        this.faturas = faturas;
        this.numFaturaAtual = numFaturaAtual;
    }

    // Metodos de acesso

    /**
     * Metodo de acesso ao atributo 'faturas' (getter)
     * @return ArrayList de faturas
     */
    public ArrayList<Fatura> getFaturas() {
        return faturas;
    }

    /**
     * Metodo de acesso ao atributo 'faturas' (setter)
     * @param faturas ArrayList de faturas
     */
    public void setFaturas(ArrayList<Fatura> faturas) {
        this.faturas = faturas;
    }

    /**
     * Metodo de acesso ao atributo 'numFaturaAtual' (getter)
     * @return Número a ser atribuído à próxima fatura a ser registada
     */
    public int getNumFaturaAtual() {
        return numFaturaAtual;
    }

    /**
     * Metodo de acesso ao atributo 'numFaturaAtual' (setter)
     * @param numFaturaAtual Número a ser atribuído à próxima fatura a ser registada
     */
    public void setNumFaturaAtual(int numFaturaAtual) {
        this.numFaturaAtual = numFaturaAtual;
    }

    /**
     * Metodo para imprimir as faturas armazenadas no ArrayList de
     * faturas. Percorre o ArrayList e invoca o metodo toString de
     * cada uma das faturas, formatando os dados numa tabela
     */
    public void printFaturas() {
        if (faturas.isEmpty())
            System.out.println("[!] Não existem faturas para imprimir!");
        else {
            System.out.printf(" %-3s | %-20s | %-20s | %-11s | %-12s | %-12s\n", "Nº", "CLIENTE", "LOCALIZAÇÃO", "Nº PRODUTOS", "TOTAL S/ IVA", "TOTAL C/ IVA");
            for (Fatura fatura : faturas) {
                System.out.println(fatura);
            }
        }
    }

    /**
     * Metodo para adicionar uma nova fatura ao ArrayList de
     * faturas, invoca o metodo 'getDadosFatura' para obter
     * os dados da fatura
     *
     * @param clientes Lista de clientes
     * @param produtosRegistados Lista de produtos registados
     */
    public void addFatura(ListaClientes clientes, Produtos produtosRegistados) {
        // Criar uma nova fatura e atribuir-lhe um número com base no número de faturas presente no ArrayList
        Fatura fatura = new Fatura(numFaturaAtual);
        numFaturaAtual++;
        // Obter os dados da fatura
        getDadosFatura(fatura, clientes, produtosRegistados);
        // Adicionar a fatura criada ao ArrayList de faturas
        faturas.add(fatura);
    }

    /**
     * Metodo para editar uma fatura já existente no ArrayList de
     * faturas, pedindo ao utilizador para associar novamente um
     * cliente e uma data de emissão e permitindo que este faça
     * alterações no ArrayList de produtos da fatura
     *
     * @param clientes Lista de clientes
     * @param produtosRegistados Lista de produtos registados
     */
    public void editFatura(ListaClientes clientes, Produtos produtosRegistados) {
        if (!faturas.isEmpty()) {
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

    /**
     * Metodo para pedir ao utilizador os dados de uma fatura, nomeadamente
     * associar um cliente existente ou criar um caso não haja nenhum
     * registado, uma data de emissão e permitindo que este realize
     * operações no ArrayList de produtos da fatura
     *
     * @param fatura Fatura onde os dados irão ser inseridos
     * @param clientes Lista de clientes
     * @param produtosRegistados Lista de produtos registados
     */
    private void getDadosFatura(Fatura fatura, ListaClientes clientes, Produtos produtosRegistados) {
        Scanner sc = new Scanner(System.in);
        // Caso não existam clientes registados, o utilizador deve registar um
        if (clientes.getClientes().isEmpty()) {
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

                if(temp.length==3 && data.isDiaValido(Integer.parseInt(temp[0])) &&
                        data.isMesValido(Integer.parseInt(temp[1]))&& temp[2].length()==4){
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
                if (!produtosRegistados.getProdutos().isEmpty()) {
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
                if (!fatura.getProdutos().getProdutosFatura().isEmpty())
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

    /**
     * Metodo que permite exportar as faturas para um ficheiro de
     * texto com o nome passado como argumento
     *
     * @param filename Nome do ficheiro a exportar
     */
    public void exportarFaturas(String filename) {
        File f = new File(filename);
        try {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write("importable\n");
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
                        bw.write("&");
                }
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("[!] Erro ao exportar faturas");
        }
    }

    /**
     * Metodo para encontrar o tipo do produto passado como argumento
     * com base na sua classe
     *
     * @param produto Produto cujo tipo é desconhecido
     * @return Tipo do produto
     */
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

    /**
     * Metodo para escrever um produto de um tipo passado como argumento
     * num ficheiro de texto também passado como argumento
     *
     * @param tipo Tipo do produto
     * @param produto Produto a ser escrito
     * @param f Ficheiro onde o produto será escrito
     * @param fw FileWriter que permite a escrita no ficheiro
     * @param bw BufferedWriter que permite a escrita no ficheiro
     */
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

    /**
     * Metodo para importar um ficheiro de texto contendo faturas,
     * verificando a integridade do ficheiro a importar e invocando
     * metodos para efetuar o parsing do texto lido
     *
     * @param filename Nome do ficheiro a importar
     * @param produtosRegistados Lista de produtos registados
     */
    public void importarFaturas(String filename, Produtos produtosRegistados) {
        File f = new File(filename);
        if (f.exists() && f.isFile() && filename.endsWith(".txt")) {
            try {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linha;
                int numFatura = 1;
                linha = br.readLine();
                if (linha.equals("importable")) {
                    // Limpar o array de faturas
                    faturas.clear();
                    while ((linha = br.readLine()) != null) {
                        Fatura fatura = parseFatura(linha, numFatura, produtosRegistados);
                        if (validFatura(fatura)) {
                            numFatura++;
                            faturas.add(fatura);
                        }
                    }
                    System.out.println("[!] Faturas importadas com sucesso!");
                } else {
                    System.out.println("[!] Não é possível importar o ficheiro");
                }
                br.close();
            } catch (FileNotFoundException e) {
                System.out.println("[!] Ficheiro ao abrir o ficheiro");
            } catch (IOException e) {
                System.out.println("[!] Erro ao ler o ficheiro");
            }
        } else
            System.out.println("[!] Ficheiro não encontrado ou inválido");
    }

    /**
     * Metodo que efetua o parsing de uma linha contendo todos os dados
     * de uma fatura, armazenando-os num objeto Fatura que será devolvido
     *
     * @param linha Linha do ficheiro de texto com os dados de uma fatura
     * @param numFatura Número a ser atribuído à fatura
     * @param produtosRegistados Lista de produtos registados
     * @return Objeto Fatura com todos os dados presentes na linha
     */
    public Fatura parseFatura(String linha, int numFatura, Produtos produtosRegistados) {
        Fatura fatura = new Fatura(numFatura);
        // Separar os diferentes elementos constituintes da fatura
        String[] dadosFatura = linha.split("#");
        if (dadosFatura.length != 3)
            System.out.println("[!] Fatura com formato inválido");
        else {
            // Obter os dados do cliente
            String[] dadosCliente = dadosFatura[0].split(";");
            if (dadosCliente.length != 3)
                System.out.println("[!] Fatura com formato inválido");
            else {
                fatura.setCliente(parseCliente(dadosCliente));

                // Obter a data da fatura
                String[] data = dadosFatura[1].split("/");
                fatura.setData(fatura.parseData(data));

                // Obter os produtos associados à fatura
                String[] produtos = dadosFatura[2].split("&");
                for (String produto : produtos) {
                    String[] dadosProduto = produto.split(";");
                    // Efetuar o parsing do produto com base no tipo de produto
                    switch (dadosProduto[0]) {
                        case "prescricao":
                            Prescricao prescricao = fatura.parsePrescricao(dadosProduto);
                            if (prescricao != null) {
                                // Registar o produto na lista de produtos registados
                                produtosRegistados.registarProduto(prescricao);
                                // Adicionar o produto à lista de produtos da fatura atual
                                fatura.getProdutos().getProdutosFatura().add(prescricao);
                            }
                            break;
                        case "normal":
                            Normal normal = fatura.parseNormal(dadosProduto);
                            if (normal != null) {
                                // Registar o produto na lista de produtos registados
                                produtosRegistados.registarProduto(normal);
                                // Adicionar o produto à lista de produtos da fatura atual
                                fatura.getProdutos().getProdutosFatura().add(normal);
                            }
                            break;
                        case "taxaNormal":
                            TaxaNormal taxaNormal = fatura.parseTaxaNormal(dadosProduto);
                            if (taxaNormal != null) {
                                // Registar o produto na lista de produtos registados
                                produtosRegistados.registarProduto(taxaNormal);
                                // Adicionar o produto à lista de produtos da fatura atual
                                fatura.getProdutos().getProdutosFatura().add(taxaNormal);
                            }
                            break;
                        case "taxaIntermedia":
                            TaxaIntermedia taxaIntermedia = fatura.parseTaxaIntermedia(dadosProduto);
                            if (taxaIntermedia != null) {
                                // Registar o produto na lista de produtos registados
                                produtosRegistados.registarProduto(taxaIntermedia);
                                // Adicionar o produto à lista de produtos da fatura atual
                                fatura.getProdutos().getProdutosFatura().add(taxaIntermedia);
                            }
                            break;
                        case "taxaReduzida":
                            TaxaReduzida taxaReduzida = fatura.parseTaxaReduzida(dadosProduto);
                            if (taxaReduzida != null) {
                                // Registar o produto na lista de produtos registados
                                produtosRegistados.registarProduto(taxaReduzida);
                                // Adicionar o produto à lista de produtos da fatura atual
                                fatura.getProdutos().getProdutosFatura().add(taxaReduzida);
                            }
                            break;
                    }
                }
            }
        }
        return fatura;
    }

    /**
     * Metodo para efetuar o parsing dos dados de um cliente passados
     * como array de strings, devolvendo um objeto Cliente contendo
     * os dados extraídos do array passado como argumento
     *
     * @param dadosCliente Array com os dados do cliente
     * @return Objeto Cliente gerado com os dados do array de strings
     */
    public Cliente parseCliente(String[] dadosCliente) {
        Cliente cliente = new Cliente();
        // Obtém o nome do cliente
        cliente.setNome(dadosCliente[0]);
        try {
            // Obtém o NIF do cliente
            cliente.setNif(Integer.parseInt(dadosCliente[1]));
            // Obtém a localização do cliente
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
        } catch (NumberFormatException e) {
            System.out.println("[!] Fatura com formato inválido");
            cliente = null;
        }
        return cliente;
    }

    /**
     * Metodo que verifica se todos os atributos de um objeto Fatura
     * foram inicializados corretamente, devolvendo o valor booleano
     * true em caso afirmativo e false em caso contrário
     *
     * @param fatura Fatura a ser verificada
     * @return Valor booleano com a validade da fatura
     */
    public boolean validFatura(Fatura fatura) {
        boolean res;
        // Verificar se o cliente lido é válido
        res = fatura.getCliente() != null;
        // Verificar se a data é válida
        res = res && fatura.getData() != null;
        // Verificar se todos os produtos são válidos
        for (Produto produto : fatura.getProdutos().getProdutosFatura()) {
            res = res && produto.validProduto();
        }
        return res;
    }

    /**
     * Metodo que imprime dados estatísticos relativos às
     * faturas registadas na aplicação
     */
    public void printEstatisticas(){
        System.out.println("========== ESTATÍSTICAS ==========");
        // Obter o numero de faturas
        int nFaturas = faturas.size();
        System.out.println("Número de faturas: " + nFaturas);

        // Obter o numero total de produtos
        int nProdutos=0;
        for (int i = 0; i < nFaturas; i++) {
            Fatura fatura = faturas.get(i);
            nProdutos+=fatura.getProdutos().getProdutosFatura().size();
        }
        System.out.println("Número de produtos: " + nProdutos);

        // Calcular o valor total sem IVA
        float valorTotalSemIva=0f;
        for (int i = 0; i < nFaturas; i++) {
            Fatura fatura = faturas.get(i);
            valorTotalSemIva+=fatura.calcTotalSemIva();
        }
        System.out.printf("Valor Total Sem IVA: %.2f\n", valorTotalSemIva);

        // Calcular o valor total com IVA
        float valorTotalComIva=0f;
        for (int i = 0; i < nFaturas; i++) {
            Fatura fatura = faturas.get(i);
            valorTotalComIva+=fatura.calcTotalComIva();
        }
        System.out.printf("Valor Total Com IVA: %.2f\n", valorTotalComIva);

        // Calcular o valor total do IVA
        float valorTotalDoIva=0f;
        for (int i = 0; i < nFaturas; i++) {
            Fatura fatura = faturas.get(i);
            valorTotalDoIva+=fatura.calcValorIva();
        }
        System.out.printf("Valor Total do IVA: %.2f\n", valorTotalDoIva);
    }
}
