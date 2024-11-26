import java.io.Serializable;

public class Produto implements Serializable {
    // Atributos da classe
    protected int codigo;
    protected String nome;
    protected String desc;
    protected int quantidade;
    protected float valorUnit;
    protected int iva;

    // Construtores
    public Produto() {}

    public Produto(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
    }

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
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
    }

    public float calcTotalSemIva() {
        // valorProduto = valorUnit + quantidade
        return valorUnit * quantidade;
    }

    public float calcTotalComIva() {
        // valorProduto = (valorUnit + valorUnit * taxaIva) * quantidade
        return (valorUnit + valorUnit * (iva / 100f)) * quantidade;
    }

    public float calcValorIva() {
        return calcTotalComIva() - calcTotalSemIva();
    }
}
