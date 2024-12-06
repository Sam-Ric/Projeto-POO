import java.io.Serializable;

public class Data implements Serializable {
    // Atributos da classe
    private int dia;
    private int mes;
    private int ano;

    // Construtores
    public Data() {}

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    // Metodos de acesso
    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isDiaValido(int dia){
        int len= String.valueOf(dia).length();
        return dia >= 1 && dia <= 31 && len <= 2;
    }
    public boolean isMesValido(int mes){
        int len= String.valueOf(mes).length();
        return mes >= 1 && mes <= 12 && len <= 2;
    }
}
