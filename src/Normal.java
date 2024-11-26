import java.io.Serializable;

enum CategoriaNormal {
    beleza,
    bemEstar,
    bebes,
    animais,
    outros
}

public class Normal extends ProdFarmacia implements Serializable {
    // Atributos da classe
    private CategoriaNormal categoria;

    // Construtores
    public Normal() {}

    public Normal(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, CategoriaNormal categoria) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.categoria = categoria;
    }

    // Metodos de acesso
    public CategoriaNormal getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaNormal categoria) {
        this.categoria = categoria;
    }

    private String categoriaToString(CategoriaNormal categoria) {
        String strCategoria = "N/A";
        switch (categoria) {
            case beleza:
                strCategoria = "Beleza";
                break;
            case bemEstar:
                strCategoria = "Bem Estar";
                break;
            case bebes:
                strCategoria = "Bebes";
                break;
            case animais:
                strCategoria = "Animais";
                break;
            case outros:
                strCategoria = "Outros";
                break;
        }
        return strCategoria;

    }

    public String toString() {
        return "   ==> " + nome + "\n" +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Categoria: " + categoriaToString(categoria) + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                String.format("    Valor s/ IVA: %-6.2f\n", calcTotalSemIva()) +
                String.format("    Valor c/ IVA: %-6.2f\n", calcTotalComIva()) +
                String.format("    Valor do IVA: %-6.2f", calcValorIva());
    }
}
