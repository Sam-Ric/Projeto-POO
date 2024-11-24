enum CategoriaNormal {
    beleza,
    bemEstar,
    bebes,
    animais,
    outros
}

public class Normal extends ProdFarmacia {
    // Atributos da classe
    private CategoriaNormal categoria;

    // Construtores
    public Normal() {}

    // Metodos de acesso
    public CategoriaNormal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNormal categoria) {
        this.categoria = categoria;
    }

    public String toString() {
        String strCategoria = "N/A";
        if (categoria == CategoriaNormal.beleza)
            strCategoria = "Beleza";
        else if (categoria == CategoriaNormal.bemEstar)
            strCategoria = "Bem Estar";
        else if (categoria == CategoriaNormal.bebes)
            strCategoria = "Bebes";
        else if (categoria == CategoriaNormal.animais)
            strCategoria = "Animais";
        else if (categoria == CategoriaNormal.outros)
            strCategoria = "Outros";
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Categoria: " + strCategoria + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
    }
}
