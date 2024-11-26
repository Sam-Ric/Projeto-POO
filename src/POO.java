import java.io.*;

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
        if (res == null)
            System.out.println("[!] Ficheiro de objectos não encontrado. A carregar ficheiro de texto");


    }
}
