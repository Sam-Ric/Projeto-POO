import java.io.Serializable;
import java.util.ArrayList;

/**
 * Classe para gerir os produtos registados na aplicação, ou
 * seja, produtos já adicionados a outras faturas
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Produtos implements Serializable {
    /**
     * Lista de produtos registados
     */
    private ArrayList<Produto> produtos;
    /**
     * Código a ser atribuído ao próximo produto a ser registado
     */
    private int codigoAtual;

    /**
     * Construtor por omissão, inicializa a lista de produtos
     * e o primeiro código a ser atribuído
     */
    public Produtos() {
        produtos = new ArrayList<Produto>();
        codigoAtual = 1;
    }

    /**
     * Construtor da classe, recebe um ArrayList de produtos
     * e um código para inicializar os atributos
     *
     * @param produtos Lista de produtos registados
     * @param codigoAtual Código a ser atribuído ao próximo
     *                    produto a ser registado
     */
    public Produtos(ArrayList<Produto> produtos, int codigoAtual) {
        this.produtos = produtos;
        this.codigoAtual = codigoAtual;
    }

    /**
     * Metodo de acesso ao atributo 'produtos' (getter)
     * @return Lista de produtos registados
     */
    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    /**
     * Metodo de acesso ao atributo 'produtos' (setter)
     * @param produtos Lista de produtos registados
     */
    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }

    /**
     * Metodo de acesso ao atributo 'codigoAtual' (getter)
     * @return Código a ser atribuído ao próximo produto a
     * ser registado
     */
    public int getCodigoAtual() {
        return codigoAtual;
    }

    /**
     * Metodo de acesso ao atributo 'codigoAtual' (setter)
     * @param codigoAtual Código a ser atribuído ao próximo
     *                    produto a ser registado
     */
    public void setCodigoAtual(int codigoAtual) {
        this.codigoAtual = codigoAtual;
    }

    /**
     * Metodo para registar um produto na aplicação, ou seja,
     * adicioná-lo à lista de produtos registados atribuindo-lhe
     * um código
     *
     * @param produto Produto a ser registado
     */
    public void registarProduto(Produto produto) {
        produto.setCodigo(codigoAtual);
        codigoAtual++;
        produtos.add(produto);
    }

    /**
     * Metodo para imprimir todos os produtos registados
     * na aplicação
     */
    public void printProdutosRegistados() {
        System.out.println("==> PRODUTOS REGISTADOS");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    /**
     * Metodo para encontrar um produto registado com base
     * no código de produto passado como argumento, devolve
     * o produto com o código respetivo ou null caso o produto
     * não seja encontrado
     *
     * @param codigo Código a ser atribuído ao próximo produto
     *               a ser registado
     * @return Produto com o código dado ou o valor null
     */
    public Produto getProdutoRegistado(int codigo) {
        Produto res = null;
        for (Produto produto : produtos)
            if (produto.getCodigo() == codigo)
                res = produto;
        return res;
    }
}
