import java.io.Serializable;
import java.util.Arrays;

/**
 * Classe que representa um produto alimentar de taxa reduzida
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class TaxaReduzida extends ProdAlimentar implements Serializable {
    /**
     * Certificações do produto
     */
    private String[] certificacoes;

    /**
     * Construtor por omissão
     */
    public TaxaReduzida() {}

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
     * @param certificacoes Certificações do produto
     */
    public TaxaReduzida(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, boolean biologico, String[] certificacoes) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.biologico = biologico;
        this.certificacoes = certificacoes;
    }

    /**
     * Metodo de acesso ao atributo 'certificacoes' (getter)
     * @return Certificações do produto
     */
    public String[] getCertificacoes() {
        return certificacoes;
    }

    /**
     * Metodo de acesso ao atributo 'certificacoes' (setter)
     * @param certificacoes Certificações do produto
     */
    public void setCertificacoes(String[] certificacoes) {
        this.certificacoes = certificacoes;
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
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + strBiologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Certificacoes: " + Arrays.toString(certificacoes) + "\n" +
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
        res = res && certificacoes != null;
        return res;
    }
}

