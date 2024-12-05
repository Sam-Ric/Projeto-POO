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
            System.out.printf("[!] Ficheiro '%s' exportado com sucesso!\n", fileName);
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
            System.out.printf("[!] Ficheiro '%s' importado com sucesso!\n", fileName);
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
        File objectFile = new File("autosave.obj");
        if (objectFile.exists())
            res = importarDados("autosave.obj");
        else {
            System.out.println("[!] Ficheiro 'autosave.obj' não encontrado. A carregar 'dados.txt'...");
            // Ler o ficheiro de texto
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
                    Cliente cliente = listaFaturas.parseCliente(dadosCliente);
                    listaClientes.getClientes().add(cliente);
                }
                res.setListaClientes(listaClientes);

                // Ler as faturas registadas
                ListaFaturas listaFaturas = new ListaFaturas();
                ArrayList<Fatura> faturas = listaFaturas.getFaturas();
                int numFatura = 1;
                while ((linha = br.readLine()) != null) {
                    Fatura fatura = listaFaturas.parseFatura(linha, numFatura, produtosRegistados);
                    numFatura++;
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

    public void printEstatiscas(){
        System.out.println("========== ESTATÍSTICA ==========");
        // Obter o numero de faturas
        ArrayList<Fatura> faturas=listaFaturas.getFaturas();
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
