import java.util.Arrays;

public class TaxaReduzida extends ProdAlimentar {
    // Atributos da classe
    private String[] certificacoes;

    // Construtores
    public TaxaReduzida() {}

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

