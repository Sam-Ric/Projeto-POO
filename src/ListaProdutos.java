import java.util.ArrayList;

public class ListaProdutos {
    // Atributos da classe
    private ArrayList<Produto> produtos;

    // Construtores
    public ListaProdutos() {}

    // Metodos de acesso
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }


    public void addProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void printProdutos() {
        for (int i = 0; i < this.produtos.size(); ++i) {
            System.out.println(this.produtos.get(i));
        }
    }
}
