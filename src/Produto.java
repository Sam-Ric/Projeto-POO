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

    public float getValorUnit() {
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

    public String toString() {
        return "   ==> " + nome +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() + "\n" +
                "    Valor c/ IVA: " + calcTotalComIva() + "\n" +
                "    Valor do IVA: " + calcValorIva();
    }

    public float calcTotalSemIva() {
        // valorProduto = valorUnit + quantidade
        return valorUnit * quantidade;
    }

    public float calcTotalComIva() {
        // valorProduto = (valorUnit + valorUnit * taxaIva) * quantidade
        return (valorUnit + valorUnit * (iva / 100)) * quantidade;
    }

    public float calcValorIva() {
        float res = 0;
        res = calcTotalComIva() - calcTotalSemIva();
        return res;
    }
}
