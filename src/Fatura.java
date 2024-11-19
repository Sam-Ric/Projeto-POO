public class Fatura {
    // Atributos da classe
    private int numFatura;
    private Cliente cliente;
    private Data data;
    private ListaProdutos produto;

    // Construtores
    public Fatura() {}

    // Metodos de acesso
    public int getNumFatura() {
        return numFatura;
    }

    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ListaProdutos getProduto() {
        return produto;
    }

    public void setProduto(ListaProdutos produto) {
        this.produto = produto;
    }

    public String toString() {}

    /*
    public float calcTotalSemIva() {}

    public float calcTotalComIva() {}

    public float calcValorIva() {}

    public void printFatura() {}

     */
}

