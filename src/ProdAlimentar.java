public class ProdAlimentar extends Produto {
    // Atributos da classe
    protected boolean biologico;

    // Construtores
    public ProdAlimentar() {

    }

    // Metodos de acesso
    public boolean getBiologico() {
        return biologico;
    }

    public void setBiologico(boolean biologico) {
        this.biologico = biologico;
    }

    public String toString() {
        String strBiologico = "Não";
        if (biologico)
            strBiologico = "Sim";
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + biologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() + "\n" +
                "    Valor c/ IVA: " + calcTotalComIva() + "\n" +
                "    Valor do IVA: " + calcValorIva();
    }
}
