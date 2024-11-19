import java.util.ArrayList;

public class ListaFaturas {
    // Atributos da classe
    private ArrayList<Fatura> faturas;

    // Construtores
    public ListaFaturas() {}

    // Metodos de acesso
    public ArrayList<Fatura> getFaturas() {
        return faturas;
    }

    public void setFaturas(ArrayList<Fatura> faturas) {
        this.faturas = faturas;
    }

    // todo: PREENCHER AS FUNCOES
    public void printFaturas() {}

    public void addFatura(Fatura fatura) {
        this.faturas.add(fatura);
    }

    public void editFatura() {}
}
