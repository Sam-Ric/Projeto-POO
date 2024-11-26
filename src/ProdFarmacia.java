import java.io.Serializable;

public class ProdFarmacia extends Produto implements Serializable {
    // Construtores
    public ProdFarmacia() {}

    public ProdFarmacia(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
    }

    public String toString() {
        return super.toString();
    }
}
