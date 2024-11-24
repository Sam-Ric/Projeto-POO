public class Prescricao extends ProdFarmacia {
    // Atributos da classe
    protected String medico;

    // Construtores
    public Prescricao(){}

    // Metodos de acesso
    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String toString() {
        return "   ==> " + nome +
                "    Código: " + codigo + "\n" +
                "    Descrição: " + desc + "\n" +
                "    Prescrito por: " + medico + "\n" +
                "    Quantidade: " + quantidade + "\n" +
                "    Valor Unitário: " + valorUnit + "\n" +
                "    Taxa IVA: " + iva + "\n" +
                "    Valor s/ IVA: " + calcTotalSemIva() +
                "    Valor c/ IVA: " + calcTotalComIva() +
                "    Valor do IVA: " + calcValorIva();
    }
}