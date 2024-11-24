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
        String strCategoria = "N/A";
        if (categoria == CategoriaTaxaIntermedia.congelados)
            strCategoria = "Congelados";
        else if (categoria == CategoriaTaxaIntermedia.enlatados)
            strCategoria = "Enlatados";
        else if (categoria == CategoriaTaxaIntermedia.vinho)
            strCategoria = "Vinho";
        return "   ==> " + nome +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Biologico: " + biologico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Categoria: " + strCategoria + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() +
                "    Valor c/ IVA: " + calcTotalComIva() +
                "    Valor do IVA: " + calcValorIva();
    }
}
