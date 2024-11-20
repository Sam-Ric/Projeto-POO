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
}
