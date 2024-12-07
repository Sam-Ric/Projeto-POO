import java.io.Serializable;

/**
 * Classe que representa um produto alimentar
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class ProdAlimentar extends Produto implements Serializable {
    /**
     * Valor booleano que define se o produto é biológico
     */
    protected boolean biologico;

    /**
     * Construtor por omissão
     */
    public ProdAlimentar() {}

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
     * @param biologico Valor booleano que define se o produto é biológico
     */
    public ProdAlimentar(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, boolean biologico) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.biologico = biologico;
    }

    /**
     * Metodo de acesso ao atributo 'biologico' (getter)
     * @return Valor booleano que define se o produto é biológico
     */
    public boolean getBiologico() {
        return biologico;
    }

    /**
     * Metodo de acesso ao atributo 'biologico' (setter)
     * @param biologico Valor booleano que define se o produto é biológico
     */
    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    /**
     * Metodo toString da classe, devolve os dados do
     * produto formatados para serem imprimidos
     *
     * @return Dados do produto
     */
    public String toString() {
        String strBiologico = "Não";
        if (biologico)
            strBiologico = "Sim";
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + strBiologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() + "\n" +
                "    Valor c/ IVA: " + calcTotalComIva() + "\n" +
                "    Valor do IVA: " + calcValorIva();
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
