public class POO {
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
}
