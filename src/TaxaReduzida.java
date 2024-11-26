import java.io.Serializable;
import java.util.Arrays;

public class TaxaReduzida extends ProdAlimentar implements Serializable {
    // Atributos da classe
    private String[] certificacoes;

    // Construtores
    public TaxaReduzida() {}

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

    // Metodos de acesso
    public String[] getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(String[] certificacoes) {
        this.certificacoes = certificacoes;
    }

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
}

