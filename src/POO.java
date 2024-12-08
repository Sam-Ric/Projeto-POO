import java.io.*;
import java.util.ArrayList;

/**
 * Classe para gerir os objetos que contém os dados das faturas,
 * dos clientes e dos produtos registados, assim como possibilitar a
 * importação e exportação automática de ficheiros
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class POO implements Serializable {
    /**
     * Lista de clientes
     */
    private ListaClientes listaClientes;
    /**
     * Lista de faturas
     */
    private ListaFaturas listaFaturas;
    /**
     * Lista de produtos registados
     */
    private Produtos produtosRegistados;

    /**
     * Construtor por omissão, inicializa os atributos da classe
     */
    public POO() {
        listaClientes = new ListaClientes();
        listaFaturas = new ListaFaturas();
        produtosRegistados = new Produtos();
    }

    /**
     * Construtor da classe, recebe valores como argumentos para
     * inicializar os atributos da classe
     *
     * @param listaClientes Lista de clientes
     * @param listaFaturas Lista de faturas
     * @param produtosRegistados Lista de produtos registados
     */
    public POO(ListaClientes listaClientes, ListaFaturas listaFaturas, Produtos produtosRegistados) {
        this.listaClientes = listaClientes;
        this.listaFaturas = listaFaturas;
        this.produtosRegistados = produtosRegistados;
    }

    /**
     * Metodo de acesso ao atributo 'listaClientes' (getter)
     * @return Lista de clientes
     */
    public ListaClientes getListaClientes() {
        return listaClientes;
    }

    /**
     * Metodo de acesso ao atributo 'listaClientes' (setter)
     * @param lc Lista de clientes
     */
    public void setListaClientes(ListaClientes lc) {
        this.listaClientes = lc;
    }

    /**
     * Metodo de acesso ao atributo 'listaFaturas' (getter)
     * @return Lista de faturas
     */
    public ListaFaturas getListaFaturas() {
        return listaFaturas;
    }

    /**
     * Metodo de acesso ao atributo 'listaFaturas' (setter)
     * @param lf Lista de Faturas
     */
    public void setListaFaturas(ListaFaturas lf) {
        this.listaFaturas = lf;
    }

    /**
     * Metodo de acesso ao atributo 'produtosRegistados' (getter)
     * @return Lista de produtos registados
     */
    public Produtos getProdutosRegistados() {
        return produtosRegistados;
    }

    /**
     * Metodo de acesso ao atributo 'produtosRegistados' (setter)
     * @param produtosRegistados Lista de produtos registados
     */
    public void setProdutosRegistados(Produtos produtosRegistados) {
        this.produtosRegistados = produtosRegistados;
    }

    /**
     * Metodo que permite exportar os dados para um ficheiro de objetos
     *
     * @param dados Dados a serem exportados
     * @param fileName Nome do ficheiro a exportar
     */
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

    /**
     * Metodo que permite importar dados de um ficheiro de objetos
     *
     * @param fileName Nome do ficheiro a importar
     * @return Objeto da classe POO que contém os dados importados
     */
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

    /**
     * Metodo que verifica se existe um ficheiro de objetos a ser
     * importado. Em caso afirmativo, o ficheiro é importado, caso
     * contrário é importado o ficheiro de texto 'dados.txt'
     *
     * @return Objeto da classe POO que contém os dados importados
     */
    public POO fetchDados() {
        POO res = new POO();
        File objectFile = new File("autosave.obj");
        // Verifica se o ficheiro de objetos existe
        if (objectFile.exists())
            res = importarDados("autosave.obj");
        // Caso o ficheiro de objetos não exista
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
                    Fatura fatura = listaFaturas.parseFatura(linha, numFatura, res.getProdutosRegistados());
                    if (listaFaturas.validFatura(fatura)) {
                        numFatura++;
                        faturas.add(fatura);
                        listaFaturas.setNumFaturaAtual(numFatura);
                    }
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
}
