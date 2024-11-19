public class Cliente {
    // Atributos da classe
    protected String nome;
    protected int nif;
    protected String localizacao;

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

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public String toString() {
        return "Clientes{" +
                "nome='" + nome + '\'' +
                ", NIF=" + nif +
                ", localizacao='" + localizacao + '\'' +
                '}';
    }
}
