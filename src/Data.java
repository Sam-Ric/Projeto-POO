import java.io.Serializable;

/**
 * Classe que representa uma data
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Data implements Serializable {
    /**
     * Dia
     */
    private int dia;
    /**
     * Mes
     */
    private int mes;
    /**
     * Ano
     */
    private int ano;

    /**
     * Construtor por omissão
     */
    public Data() {}

    /**
     * Construtor da classe, recebe dados para inicializar
     * os atributos
     *
     * @param dia Dia
     * @param mes Mes
     * @param ano Ano
     */
    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /**
     * Metodo de acesso ao atributo 'dia' (getter)
     * @return Dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * Metodo de acesso ao atributo 'dia' (setter)
     * @param dia Dia
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * Metodo de acesso ao atributo 'mes' (getter)
     * @return Mes
     */
    public int getMes() {
        return mes;
    }

    /**
     * Metodo de acesso ao atributo 'mes' (setter)
     * @param mes Mes
     */
    public void setMes(int mes) {
        this.mes = mes;
    }

    /**
     * Metodo de acesso ao atributo 'ano' (getter)
     * @return Ano
     */
    public int getAno() {
        return ano;
    }

    /**
     * Metodo de acesso ao atributo 'ano' (setter)
     * @param ano Ano
     */
    public void setAno(int ano) {
        this.ano = ano;
    }

    /**
     * Metodo toString, devolve a data formatada em
     * formato de string
     * @return Data formatada
     */
    public String toString() {
        return String.format("%d/%d/%d", dia, mes, ano);
    }

    /**
     * Metodo para verificar se um dia é válido
     *
     * @param dia Dia a ser verificado
     * @return Valor booleano que descreve a validade do dia
     */
    public boolean isDiaValido(int dia){
        int len= String.valueOf(dia).length();
        return dia >= 1 && dia <= 31 && len <= 2;
    }

    /**
     * Metodo para verificar se uma mês é válido
     *
     * @param mes Mes a ser verificado
     * @return Valor booleano que descreve a validade do mês
     */
    public boolean isMesValido(int mes){
        int len= String.valueOf(mes).length();
        return mes >= 1 && mes <= 12 && len <= 2;
    }
}
