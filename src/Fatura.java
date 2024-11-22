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
        return String.format("%3d | %20s | %20s | %3d | %5.2f | %5.2f", numFatura, cliente.getNome(), cliente.getLocalizacao(), produtos.getProdutos().size(), calcTotalSemIva(), calcTotalComIva());
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
        for (Produto produto : produtos.getProdutos()) {
            System.out.println("   --- " + produto.getNome() + " - " + produto.getCodigo() + " ---");
            System.out.println("    Descrição: " + produto.getDesc());
            System.out.println("    Quantidade: " + produto.getQuantidade());
            System.out.println("    Valor Unitário: " + produto.getValorUnit());
            System.out.println("    Taxa IVA: " + produto.getIva());
            System.out.println("    Valor s/ IVA: " + produto.calcTotalSemIva());
            System.out.println("    Valor c/ IVA: " + produto.calcTotalComIva());
            System.out.println("    Valor do IVA: " + produto.calcValorIva());
        }
        System.out.println("> Fatura");
        System.out.println("   Valor Total s/ IVA: " + calcTotalSemIva());
        System.out.println("   Valor Total c/ IVA: " + calcTotalComIva());
        System.out.println("   Valor Total do IVA: " + calcValorIva() + "\n");
    }

}

