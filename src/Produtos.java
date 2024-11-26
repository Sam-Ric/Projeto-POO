import java.io.Serializable;
import java.util.ArrayList;

public class Produtos implements Serializable {
    // Atributos da classe
    private ArrayList<Produto> produtos;
    private int codigoAtual;

    // Construtores
    public Produtos() {
        produtos = new ArrayList<Produto>();
        codigoAtual = 1;
    }

    public Produtos(ArrayList<Produto> produtos, int codigoAtual) {
        this.produtos = produtos;
        this.codigoAtual = codigoAtual;
    }

    // Metodos de acesso
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    public void registarProduto(Produto produto) {
        produto.setCodigo(codigoAtual);
        codigoAtual++;
        produtos.add(produto);
    }

    public void printProdutosRegistados() {
        System.out.println("==> PRODUTOS REGISTADOS");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    public Produto getProdutoRegistado(int codigo) {
        Produto res = null;
        for (Produto produto : produtos)
            if (produto.getCodigo() == codigo)
                res = produto;
        return res;
    }
}
