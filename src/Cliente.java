import java.io.Serializable;

enum Localizacao {
    continente,
    madeira,
    acores
}

public class Cliente implements Serializable {
    // Atributos da classe
    protected String nome;
    protected int nif;
    protected Localizacao localizacao;

    // Construtores
    public Cliente() {}

    public Cliente(String nome, int nif, Localizacao localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    // Metodos de acesso
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNif() {
        return nif;
    }

    public void setNif(int NIF) {
        this.nif = NIF;
    }

    public Localizacao getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    public String toString() {
        return String.format(" %-25s | %-9d | %-20s ", nome, nif, Static.localizacaoToString(localizacao));
    }
}
