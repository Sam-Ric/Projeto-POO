public class Produto {
    // Atributos da classe
    protected int codigo;
    protected String nome;
    protected String desc;
    protected int quantidade;
    protected float valorUnit;
    protected int iva;

    // Construtores
    public Produto() {}

    // Metodos de acesso
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnit() {
        return valorUnit;
    }

    public void setValorUnit(float valorUnit) {
        this.valorUnit = valorUnit;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    // public String toString() {}

    /*
    public float calcValorSemIva() {}

    public float calcValorComIva() {}

    public int calcTaxaIva() {}

    public float calcValorIva() {}

     */
}
