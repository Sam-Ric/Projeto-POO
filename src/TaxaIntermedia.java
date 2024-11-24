enum CategoriaTaxaIntermedia {
    congelados,
    enlatados,
    vinho
}

public class TaxaIntermedia extends ProdAlimentar {
    // Atributos da classe
    private CategoriaTaxaIntermedia categoria;

    // Construtores
    public TaxaIntermedia() {}

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
                "    Biologico: " + biologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Categoria: " + strCategoria + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
    }
}
