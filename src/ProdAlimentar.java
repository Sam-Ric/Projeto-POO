import java.io.Serializable;

public class ProdAlimentar extends Produto implements Serializable {
    // Atributos da classe
    protected boolean biologico;

    // Construtores
    public ProdAlimentar() {}

    public ProdAlimentar(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, boolean biologico) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.biologico = biologico;
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
                "    Biologico: " + strBiologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() + "\n" +
                "    Valor c/ IVA: " + calcTotalComIva() + "\n" +
                "    Valor do IVA: " + calcValorIva();
    }

    public boolean validProduto() {
        return super.validProduto();
    }
}
