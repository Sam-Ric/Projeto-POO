import java.util.ArrayList;
public class ListaProdutos {
    private ArrayList<Produto> produtos;
    public ListaProdutos() {}

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }
    public void printProdutos() {
        for (int i = 0; i < this.produtos.size(); ++i) {
            System.out.println(this.produtos.get(i));
        }
    }
}
