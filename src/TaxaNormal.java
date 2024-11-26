import java.io.Serializable;

public class TaxaNormal extends ProdAlimentar implements Serializable {
    // Construtores
    public TaxaNormal() {}

    public TaxaNormal(int codigo, String nome, String desc, int quantidade, float valorUnit, int iva, boolean biologico) {
        this.codigo = codigo;
        this.nome = nome;
        this.desc = desc;
        this.quantidade = quantidade;
        this.valorUnit = valorUnit;
        this.iva = iva;
        this.biologico = biologico;
    }

    public String toString() {
        return super.toString();
    }
}
