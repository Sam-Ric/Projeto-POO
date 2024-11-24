public class Fatura {
    // Atributos da classe
    private int numFatura;
    private Cliente cliente;
    private Data data;
    private ListaProdutos produtos;

    // Construtores
    public Fatura() {this.produtos = new ListaProdutos();}

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

    public ListaProdutos getProdutos() {
        return produtos;
    }

    public void setProdutos(ListaProdutos produtos) {
        this.produtos = produtos;
    }

    public String toString() {
        return String.format(" %-3d | %-20s | %-20s | %-11d | %-12.2f | %-12.2f", numFatura, cliente.getNome(), cliente.getLocalizacao(), produtos.getProdutos().size(), calcTotalSemIva(), calcTotalComIva());
    }

    public float calcTotalSemIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutos())
            total += produto.calcTotalSemIva();
        return total;
    }

    public float calcTotalComIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutos())
            total += produto.calcTotalComIva();
        return total;
    }

    public float calcValorIva() {
        return calcTotalComIva() - calcTotalSemIva();
    }

    public void printFatura() {
        System.out.println("\n========== FATURA Nº" + numFatura + "==========");
        System.out.println("> Cliente");
        System.out.println("   Nome: " + cliente.getNome());
        System.out.println("   NIF: " + cliente.getNif());
        System.out.println("   Localizacao: " + cliente.getLocalizacao());
        System.out.println("> Produtos");
        produtos.printProdutos();
        System.out.println("> Fatura");
        System.out.println("   Valor Total s/ IVA: " + calcTotalSemIva());
        System.out.println("   Valor Total c/ IVA: " + calcTotalComIva());
        System.out.println("   Valor Total do IVA: " + calcValorIva() + "\n");
    }

}

