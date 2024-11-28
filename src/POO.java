import java.io.*;
import java.util.ArrayList;

public class POO implements Serializable {
    // Atributos da classe
    private ListaClientes listaClientes;
    private ListaFaturas listaFaturas;
    private Produtos produtosRegistados;

    // Construtor
    public POO() {
        listaClientes = new ListaClientes();
        listaFaturas = new ListaFaturas();
        produtosRegistados = new Produtos();
    }

    public POO(ListaClientes listaClientes, ListaFaturas listaFaturas, Produtos produtosRegistados) {
        this.listaClientes = listaClientes;
        this.listaFaturas = listaFaturas;
        this.produtosRegistados = produtosRegistados;
    }

    // Metodos de acesso
    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(ListaClientes lc) {
        this.listaClientes = lc;
    }

    public ListaFaturas getListaFaturas() {
        return listaFaturas;
    }

    public void setListaFaturas(ListaFaturas lf) {
        this.listaFaturas = lf;
    }

    public Produtos getProdutosRegistados() {
        return produtosRegistados;
    }

    public void setProdutosRegistados(Produtos produtosRegistados) {
        this.produtosRegistados = produtosRegistados;
    }

    public void exportarDados(POO dados, String fileName) {
        File f = new File(fileName);
        try {
            FileOutputStream fos = new FileOutputStream(f);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(dados);
            oos.close();
        } catch (FileNotFoundException e) {
            System.out.println("[!] Erro a criar o ficheiro");
        } catch (IOException e) {
            System.out.println("[!] Erro ao escrever para o ficheiro" + e.getMessage());
        }
    }

    public POO importarDados(String fileName) {
        POO res = null;
        File f = new File(fileName);
        try {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream ois = new ObjectInputStream(fis);
            res = (POO)ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            System.out.println("[!] Erro a abrir o ficheiro");
        } catch (IOException e) {
            System.out.println("[!] Erro a ler o ficheiro");
        } catch (ClassNotFoundException e) {
            System.out.println("[!] Erro a ler o objeto");
        }
        return res;
    }

    public POO fetchDados() {
        POO res = null;
        res = importarDados("autosave.obj");
        if (res == null) {
            System.out.println("[!] Ficheiro de objectos não encontrado. A carregar ficheiro de texto");
            // Ler o ficheiro de texto
            File f = new File("dados.txt");
            if (f.exists() && f.isFile()) {
                try {
                    FileReader fr = new FileReader(f);
                    BufferedReader br = new BufferedReader(fr);
                    String linha;
                    // Ler a lista dos clientes
                    linha = br.readLine();
                    ListaClientes listaClientes = parseClientes(linha);
                    res.setListaClientes(listaClientes);

                    // Ler a lista dos produtos registados
                    linha = br.readLine();
                    Produtos produtosRegistados = parseProdutosRegistados(linha);
                    res.setProdutosRegistados(produtosRegistados);

                    // Ler as faturas registadas
                    ListaFaturas listaFaturas = new ListaFaturas();
                    ArrayList<Fatura> faturas = new ArrayList<>();
                    int numFatura = 1;
                    while ((linha = br.readLine()) != null) {
                        Fatura fatura = parseFatura(linha);
                        fatura.setNumFatura(numFatura);
                        faturas.add(fatura);
                    }
                    listaFaturas.setFaturas(faturas);
                    res.setListaFaturas(listaFaturas);

                    System.out.println("[!] Ficheiro de texto importado com sucesso!");

                } catch (FileNotFoundException e) {
                    System.out.println("[!] Erro a abrir o ficheiro de texto");
                } catch (IOException e) {
                    System.out.println("[!] Erro a ler o ficheiro de texto");
                }
            }
        }
        return res;
    }

    private ListaClientes parseClientes(String linha) {
        String[] arrayClientes = linha.split("#");
        ListaClientes listaClientes = new ListaClientes();
        ArrayList<Cliente> clientes = new ArrayList<Cliente>();
        for (int i = 0; i < arrayClientes.length; i++) {
            String[] temp = arrayClientes[i].split(";");
            Cliente cliente = new Cliente();
            cliente.setNome(temp[0]);
            cliente.setNif(Integer.parseInt(temp[1]));
            switch (temp[2]) {
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
            clientes.add(cliente);
        }
        listaClientes.setClientes(clientes);
        return listaClientes;
    }

    private Produtos parseProdutosRegistados(String linha) {
        Produtos produtosRegistados = new Produtos();
        String[] arrayProdutos = linha.split("#");
        for (int i = 0; i < arrayProdutos.length; i++) {
            String[] temp = arrayProdutos[i].split(";");
            switch (temp[0]) {
                case "prescricao":
                    Prescricao prescricao = parsePrescricao(temp);
                    produtosRegistados.registarProduto(prescricao);
                    break;
                case "normal":
                    Normal normal = parseNormal(temp);
                    produtosRegistados.registarProduto(normal);
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = parseTaxaNormal(temp);
                    produtosRegistados.registarProduto(taxaNormal);
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = parseTaxaIntermedia(temp);
                    produtosRegistados.registarProduto(taxaIntermedia);
                    break;
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = parseTaxaReduzida(temp);
                    produtosRegistados.registarProduto(taxaReduzida);
                    break;
            }
        }
        return produtosRegistados;
    }

    private TaxaReduzida parseTaxaReduzida(String[] temp) {
        TaxaReduzida taxaReduzida = new TaxaReduzida();
        if (temp[1].equals("1")) taxaReduzida.setBiologico(true);
        else taxaReduzida.setBiologico(false);
        taxaReduzida.setNome(temp[2]);
        taxaReduzida.setDesc(temp[3]);
        taxaReduzida.setQuantidade(Integer.parseInt(temp[4]));
        taxaReduzida.setValorUnit(Float.parseFloat(temp[5]));
        taxaReduzida.setIva(Integer.parseInt(temp[6]));
        taxaReduzida.setCertificacoes(temp[7].split(":"));
        return taxaReduzida;
    }

    private TaxaIntermedia parseTaxaIntermedia(String[] temp) {
        TaxaIntermedia taxaIntermedia = new TaxaIntermedia();
        if (temp[1].equals("1")) taxaIntermedia.setBiologico(true);
        else taxaIntermedia.setBiologico(false);
        taxaIntermedia.setNome(temp[2]);
        taxaIntermedia.setDesc(temp[3]);
        taxaIntermedia.setQuantidade(Integer.parseInt(temp[4]));
        taxaIntermedia.setValorUnit(Float.parseFloat(temp[5]));
        taxaIntermedia.setIva(Integer.parseInt(temp[6]));
        switch (temp[7]) {
            case "enlatados":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.enlatados);
                break;
            case "congelados":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.congelados);
                break;
            case "vinho":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.vinho);
                break;
        }
        return taxaIntermedia;
    }

    private TaxaNormal parseTaxaNormal(String[] temp) {
        TaxaNormal taxaNormal = new TaxaNormal();
        if (temp[1].equals("1")) taxaNormal.setBiologico(true);
        else taxaNormal.setBiologico(false);
        taxaNormal.setNome(temp[2]);
        taxaNormal.setDesc(temp[3]);
        taxaNormal.setQuantidade(Integer.parseInt(temp[4]));
        taxaNormal.setValorUnit(Float.parseFloat(temp[5]));
        taxaNormal.setIva(Integer.parseInt(temp[6]));
        return taxaNormal;
    }

    private Normal parseNormal(String[] temp) {
        Normal normal = new Normal();
        normal.setNome(temp[1]);
        normal.setDesc(temp[2]);
        normal.setQuantidade(Integer.parseInt(temp[3]));
        normal.setValorUnit(Float.parseFloat(temp[4]));
        normal.setIva(Integer.parseInt(temp[5]));
        switch (temp[6]) {
            case "beleza":
                normal.setCategoria(CategoriaNormal.beleza);
                break;
            case "bemEstar":
                normal.setCategoria(CategoriaNormal.bemEstar);
                break;
            case "bebes":
                normal.setCategoria(CategoriaNormal.bebes);
                break;
            case "animais":
                normal.setCategoria(CategoriaNormal.animais);
                break;
            case "outros":
                normal.setCategoria(CategoriaNormal.outros);
                break;
        }
        return normal;
    }

    private Prescricao parsePrescricao(String[] temp) {
        Prescricao prescricao = new Prescricao();
        prescricao.setNome(temp[1]);
        prescricao.setDesc(temp[2]);
        prescricao.setQuantidade(Integer.parseInt(temp[3]));
        prescricao.setValorUnit(Float.parseFloat(temp[4]));
        prescricao.setIva(Integer.parseInt(temp[5]));
        prescricao.setMedico(temp[6]);
        return prescricao;
    }

    private Fatura parseFatura(String linha) {
        String[] dados = linha.split("#");
        Fatura fatura = new Fatura();

        // Obter os dados do cliente
        Cliente cliente = new Cliente();
        String[] dadosCliente = dados[0].split(";");
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
        fatura.setCliente(cliente);

        // Obter a data da fatura
        String[] arrayData = dados[1].split("/");
        Data data = new Data(Integer.parseInt(arrayData[0]), Integer.parseInt(arrayData[1]), Integer.parseInt(arrayData[2]));
        fatura.setData(data);

        // Obter os produtos da fatura
        ListaProdutos listaProdutos = new ListaProdutos();
        ArrayList<Produto> produtos = new ArrayList<>();
        String[] produtosFatura = dados[2].split("/");
        for (int i = 0; i < produtosFatura.length; i++) {
            String[] temp = produtosFatura[i].split(";");
            switch (temp[0]) {
                case "prescricao":
                    Prescricao prescricao = parsePrescricao(temp);
                    produtos.add(prescricao);
                    break;
                case "normal":
                    Normal normal = parseNormal(temp);
                    produtos.add(normal);
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = parseTaxaNormal(temp);
                    produtos.add(taxaNormal);
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = parseTaxaIntermedia(temp);
                    produtos.add(taxaIntermedia);
                    break;
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = parseTaxaReduzida(temp);
                    produtos.add(taxaReduzida);
                    break;
            }
        }
        listaProdutos.setProdutosFatura(produtos);
        fatura.setProdutos(listaProdutos);
        return fatura;
    }
}
