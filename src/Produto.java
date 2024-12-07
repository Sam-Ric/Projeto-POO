import java.io.Serializable;

/**
 * Classe que contém os dados de um produto
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Produto implements Serializable {
    /**
     * Código do produto
     */
    protected int codigo;
    /**
     * Nome do produto
     */
    protected String nome;
    /**
     * Descrição
     */
    protected String desc;
    /**
     * Quantidade do produto
     */
    protected int quantidade;
    /**
     * Valor unitário
     */
    protected float valorUnit;
    /**
     * Taxa do IVA
     */
    protected int iva;

    /**
     * Construtor por omissão
     */
    public Produto() {}

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
    public Produto(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
    }

    /**
     * Metodo de acesso ao atributo 'codigo' (getter)
     * @return Código do produto
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Metodo de acesso ao atributo 'codigo' (setter)
     * @param codigo Código do produto
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * Metodo de acesso ao atributo 'nome' (getter)
     * @return Nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo de acesso ao atributo 'nome' (setter)
     * @param nome Nome do produto
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo de acesso ao atributo 'desc' (getter)
     * @return Descrição
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Metodo de acesso ao atributo 'desc' (setter)
     * @param desc Descrição
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Metodo de acesso ao atributo 'quantidade' (getter)
     * @return Quantidade do produto
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Metodo de acesso ao atributo 'quantidade' (setter)
     * @param quantidade Quantidade do produto
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Metodo de acesso ao atributo 'valorUnit' (getter)
     * @return Valor unitário
     */
    public float getValorUnit() {
        return valorUnit;
    }

    /**
     * Metodo de acesso ao atributo 'valorUnit' (setter)
     * @param valorUnit Valor unitário
     */
    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    /**
     * Metodo de acesso ao atributo 'iva' (getter)
     * @return Taxa do IVA
     */
    public int getIva() {
        return iva;
    }

    /**
     * Metodo de acesso ao atributo 'iva' (setter)
     * @param iva Taxa do IVA
     */
    public void setIva(int iva) {
        this.iva = iva;
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
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
    }

    /**
     * Metodo para calcular o valor total de um produto
     * sem a taxa do IVA
     *
     * @return Valor total do produto sem o IVA
     */
    public float calcTotalSemIva() {
        // valorProduto = valorUnit + quantidade
        return valorUnit * quantidade;
    }

    /**
     * Metodo para calcular o valor total de um produto
     * com a taxa do IVA
     *
     * @return Valor total do produto com o IVA
     */
    public float calcTotalComIva() {
        // valorProduto = (valorUnit + valorUnit * taxaIva) * quantidade
        return (valorUnit + valorUnit * (iva / 100f)) * quantidade;
    }

    /**
     * Metodo para calcular o valor do IVA sobre um produto,
     * ou seja, a diferença entre o valor total do produto
     * com o IVA e o valor total sem o IVA
     *
     * @return Valor do IVA
     */
    public float calcValorIva() {
        return calcTotalComIva() - calcTotalSemIva();
    }

    /**
     * Metodo que verifica se todos os atributos do produto
     * foram inicializados corretamente, verificando assim se
     * o produto é válido
     *
     * @return Valor booleano que descreve a validade do produto
     */
    public boolean validProduto() {
        boolean res;
        res =  codigo != 0;
        res = res && nome != null && !nome.isEmpty();
        res = res && desc != null && !desc.isEmpty();
        res = res && quantidade > 0;
        res = res && valorUnit > 0;
        res = res && iva >= 0;
        return res;
    }
}
