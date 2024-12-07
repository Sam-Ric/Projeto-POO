import java.io.Serializable;

/**
 * Enum com os valores possíveis a serem passados como
 * localização
 */
enum Localizacao {
    /**
     * Cliente localizado em Portugal Continental
     */
    continente,
    /**
     * Cliente localizado na Madeira
     */
    madeira,
    /**
     * Cliente localizado nos Açores
     */
    acores
}

/**
 * Classe que representa um cliente
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Cliente implements Serializable {
    /**
     * Nome do cliente
     */
    protected String nome;
    /**
     * Número de contribuinte
     */
    protected int nif;
    /**
     * Localização do cliente
     */
    protected Localizacao localizacao;

    /**
     * Construtor por omissão
     */
    public Cliente() {}

    /**
     * Construtor da classe, recebe dados para inicializar
     * os atributos
     *
     * @param nome Nome do cliente
     * @param nif Número de contribuinte
     * @param localizacao Localização do cliente
     */
    public Cliente(String nome, int nif, Localizacao localizacao) {
        this.nome = nome;
        this.nif = nif;
        this.localizacao = localizacao;
    }

    /**
     * Metodo de acesso ao atributo 'nome' (getter)
     * @return Nome do cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Metodo de acesso ao atributo 'nome' (setter)
     * @param nome Nome do cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Metodo de acesso ao atributo 'nif' (getter)
     * @return Número de contribuinte
     */
    public int getNif() {
        return nif;
    }

    /**
     * Metodo de acesso ao atributo 'nif' (setter)
     * @param NIF Número de contribuinte
     */
    public void setNif(int NIF) {
        this.nif = NIF;
    }

    /**
     * Metodo de acesso ao atributo 'localizacao' (getter)
     * @return Localização do cliente
     */
    public Localizacao getLocalizacao() {
        return localizacao;
    }

    /**
     * Metodo de acesso ao atributo 'localizacao' (setter)
     * @param localizacao Localização do cliente
     */
    public void setLocalizacao(Localizacao localizacao) {
        this.localizacao = localizacao;
    }

    /**
     * Metodo toString, permite que os dados do cliente sejam
     * imprimidos numa tabela
     * @return Dados do cliente
     */
    public String toString() {
        return String.format(" %-25s | %-9d | %-20s ", nome, nif, Static.localizacaoToString(localizacao));
    }
}
