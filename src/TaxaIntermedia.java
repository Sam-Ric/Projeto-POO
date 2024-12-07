import java.io.Serializable;

/**
 * Enum que contém as possíveis categorias de um produto
 * alimentar de taxa intermédia
 */
enum CategoriaTaxaIntermedia {
    /**
     * Produto congelado
     */
    congelados,
    /**
     * Produto enlatado
     */
    enlatados,
    /**
     * Produto da categoria vinho
     */
    vinho
}

/**
 * Classe que representa um produto alimentar de taxa
 * intermédia
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class TaxaIntermedia extends ProdAlimentar implements Serializable {
    /**
     * Categoria do produto
     */
    private CategoriaTaxaIntermedia categoria;

    /**
     * Construtor por omissão
     */
    public TaxaIntermedia() {}

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
     * @param biologico Valor booleano que define se o produto
     *                  é biológico
     * @param categoria Código a ser atribuído ao próximo produto
     *                  a ser registado
     */
    public TaxaIntermedia(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, boolean biologico, CategoriaTaxaIntermedia categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.biologico = biologico;
        this.categoria = categoria;
    }

    /**
     * Metodo de acesso ao atributo 'categoria' (getter)
     * @return Categoria do produto
     */
    public CategoriaTaxaIntermedia getCategoria() {
        return categoria;
    }

    /**
     * Metodo de acesso ao atributo 'categoria' (setter)
     * @param categoria Categoria do produto
     */
    public void setCategoria(CategoriaTaxaIntermedia categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo toString da classe, devolve os dados do
     * produto formatados para serem imprimidos
     *
     * @return Dados do produto
     */
    public String toString() {
        // Associar o valor booleano a uma string a ser imprimida
        String strBiologico = "Não";
        if (biologico)
            strBiologico = "Sim";
        // Associar o enum da categoria a uma string a ser imprimida
        String strCategoria = "N/A";
        if (categoria == CategoriaTaxaIntermedia.congelados)
            strCategoria = "Congelados";
        else if (categoria == CategoriaTaxaIntermedia.enlatados)
            strCategoria = "Enlatados";
        else if (categoria == CategoriaTaxaIntermedia.vinho)
            strCategoria = "Vinho";
        // String a ser devolvida
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + strBiologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Categoria: " + strCategoria + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
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
