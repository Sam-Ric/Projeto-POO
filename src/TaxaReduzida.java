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
        return "   ==> " + nome +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + biologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Certificacoes: " + Arrays.toString(certificacoes) + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() +
                "    Valor c/ IVA: " + calcTotalComIva() +
                "    Valor do IVA: " + calcValorIva();
    }
}

