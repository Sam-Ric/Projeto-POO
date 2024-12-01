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
            System.out.println("[!] Erro ao escrever para o ficheiro -> " + e.getMessage());
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
        POO res = new POO();
        File objectFile = new File("autosave1.obj");
        if (objectFile.exists()) {
            res = importarDados("autosave.obj");
            System.out.println("[!] Ficheiro 'autosave.obj' importado com sucesso!");
        }
        else {
            System.out.println("[!] Ficheiro 'autosave.obj' não encontrado. A carregar 'dados.txt'...");
            // Ler o ficheiro de texto
            System.out.println("[DEBUG] dados.txt existe? -> " + new File("dados.txt").exists());
            try {
                File f = new File("dados.txt");
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linha;

                // Ler a lista dos clientes
                linha = br.readLine();
                String[] clientes = linha.split("#");
                ListaClientes listaClientes = new ListaClientes();
                for (int i = 0; i < clientes.length; i++) {
                    String[] dadosCliente = clientes[i].split(";");
                    Cliente cliente = Static.parseCliente(dadosCliente);
                    listaClientes.getClientes().add(cliente);
                }
                res.setListaClientes(listaClientes);

                // Ler as faturas registadas
                ListaFaturas listaFaturas = new ListaFaturas();
                ArrayList<Fatura> faturas = listaFaturas.getFaturas();
                int numFatura = 1;
                while ((linha = br.readLine()) != null) {
                    Fatura fatura = Static.parseFatura(linha, numFatura, produtosRegistados);
                    faturas.add(fatura);
                }
                res.setListaFaturas(listaFaturas);
                System.out.println("[!] Ficheiro 'dados.txt' importado com sucesso!");
            } catch (FileNotFoundException e) {
                System.out.println("[!] Erro a abrir o ficheiro de texto");
            } catch (IOException e) {
                System.out.println("[!] Erro a ler o ficheiro de texto");
            }
        }
        return res;
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
                    Prescricao prescricao = Static.parsePrescricao(temp);
                    produtos.add(prescricao);
                    break;
                case "normal":
                    Normal normal = Static.parseNormal(temp);
                    produtos.add(normal);
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = Static.parseTaxaNormal(temp);
                    produtos.add(taxaNormal);
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = Static.parseTaxaIntermedia(temp);
                    produtos.add(taxaIntermedia);
                    break;
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = Static.parseTaxaReduzida(temp);
                    produtos.add(taxaReduzida);
                    break;
            }
        }
        listaProdutos.setProdutosFatura(produtos);
        fatura.setProdutos(listaProdutos);
        return fatura;
    }
}
