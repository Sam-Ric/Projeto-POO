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
}
