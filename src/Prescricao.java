import java.io.Serializable;

/**
 * Classe que representa um produto de farmácia com prescrição
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Prescricao extends ProdFarmacia implements Serializable {
    /**
     * Médico que prescreveu o produto
     */
    private String medico;

    /**
     * Construtor por omissão
     */
    public Prescricao(){}

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
     * @param medico Médico que prescreveu o produto
     */
    public Prescricao(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, String medico) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.medico = medico;
    }

    /**
     * Metodo de acesso ao atributo 'medico' (getter)
     * @return Médico que prescreveu o produto
     */
    public String getMedico() {
        return medico;
    }

    /**
     * Metodo de acesso ao atributo 'medico' (setter)
     * @param medico Médico que prescreveu o produto
     */
    public void setMedico(String medico) {
        this.medico = medico;
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
                "    Prescrito por: " + medico + "\n" +
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
        res = res && medico != null && !medico.isEmpty();
        return res;
    }
}