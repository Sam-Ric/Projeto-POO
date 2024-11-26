import java.io.Serializable;

enum CategoriaTaxaIntermedia {
    congelados,
    enlatados,
    vinho
}

public class TaxaIntermedia extends ProdAlimentar implements Serializable {
    // Atributos da classe
    private CategoriaTaxaIntermedia categoria;

    // Construtores
    public TaxaIntermedia() {}

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

    // Metodos de acesso
    public CategoriaTaxaIntermedia getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaTaxaIntermedia categoria) {
        this.categoria = categoria;
    }

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
}
