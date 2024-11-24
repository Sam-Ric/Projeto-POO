enum Localizacao {
    continente,
    madeira,
    acores
}

public class Cliente {
    // Atributos da classe
    protected String nome;
    protected int nif;
    protected Localizacao localizacao;

    // Construtores
    public Cliente() {}

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
        String loc = "N/A";
        if (localizacao == Localizacao.continente)
            loc = "Portugal Continental";
        else if (localizacao == Localizacao.madeira)
            loc = "Madeira";
        else if (localizacao == Localizacao.acores)
            loc = "Açores";
        return String.format(" %-25s | %-9d | %-20s ", nome, nif, loc);
    }
}
