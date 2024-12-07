import java.io.Serializable;

/**
 * Enum com as possíveis categorias de um produto de
 * farmácia sem prescrição
 */
enum CategoriaNormal {
    /**
     * Produto beleza
     */
    beleza,
    /**
     * Produto da categoria bem-estar
     */
    bemEstar,
    /**
     * Produto para bebés
     */
    bebes,
    /**
     * Produto para animais
     */
    animais,
    /**
     * Produto de outra categoria
     */
    outros
}

/**
 * Classe que representa um produto de farmácia sem prescrição
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Normal extends ProdFarmacia implements Serializable {
    /**
     * Categoria do produto
     */
    private CategoriaNormal categoria;

    /**
     * Construtor por omissão
     */
    public Normal() {}

    /**
     * Construtor da classe, recebe dados para a inicialização
     * dos atributos
     *
     * @param codigo Código do produto
     * @param nome Nome do produto
     * @param desc Descrição
     * @param quantidade Quantidade do produto
     * @param valorUnit Valor unitário
     * @param iva Taxa do IVA
     * @param categoria Categoria do produto
     */
    public Normal(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, CategoriaNormal categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.categoria = categoria;
    }

    /**
     * Metodo de acesso ao atributo 'categoria' (getter)
     * @return Categoria do produto
     */
    public CategoriaNormal getCategoria() {
        return categoria;
    }

    /**
     * Metodo de acesso ao atributo 'categoria' (setter)
     * @param categoria Categoria do produto
     */
    public void setCategoria(CategoriaNormal categoria) {
        this.categoria = categoria;
    }

    /**
     * Metodo para converter um valor do enum 'CategoriaNormal'
     * para uma string
     *
     * @param categoria Categoria do produto em enum
     * @return Categoria do produto em string
     */
    private String categoriaToString(CategoriaNormal categoria) {
        String strCategoria = "N/A";
        switch (categoria) {
            case beleza:
                strCategoria = "Beleza";
                break;
            case bemEstar:
                strCategoria = "Bem Estar";
                break;
            case bebes:
                strCategoria = "Bebes";
                break;
            case animais:
                strCategoria = "Animais";
                break;
            case outros:
                strCategoria = "Outros";
                break;
        }
        return strCategoria;

    }

    /**
     * Metodo toString da classe, devolve os dados do
     * produto formatados para serem imprimidos
     *
     * @return Dados do produto
     */
    public String toString() {
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Categoria: " + categoriaToString(categoria) + "\n" +
                "    Quantidade: " + quantidade + "\n" +
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
        boolean res = super.validProduto();
        res = res && categoria != null;
        return res;
    }
}
