import java.io.Serializable;

/**
 * Classe que representa um produto de farmácia
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class ProdFarmacia extends Produto implements Serializable {
    /**
     * Construtor por omissão
     */
    public ProdFarmacia() {}

    /**
     * Construtor da classe, recebe dados para inicializar
     * os atributos
     *
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param desc Descrição
     * @param quantidade Quantidade do produto
     * @param valorUnit Valor unitário
     * @param iva Taxa do IVA
     */
    public ProdFarmacia(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
    }

    /**
     * Metodo toString da classe, devolve os dados do
     * produto formatados para serem imprimidos
     *
     * @return Dados do produto
     */
    public String toString() {
        return super.toString();
    }

    /**
     * Metodo que verifica se todos os atributos do produto
     * foram inicializados corretamente, verificando assim se
     * o produto é válido
     *
     * @return Valor booleano que descreve a validade do produto
     */
    public boolean validProduto() {
        return super.validProduto();
    }
}
