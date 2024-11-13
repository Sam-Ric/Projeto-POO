public class Farmacia extends Produto{
    protected boolean prescricao;
    public Farmacia(){}

    public boolean isPrescricao() {
        return prescricao;
    }

    public void setPrescricao(boolean prescricao) {
        this.prescricao = prescricao;
    }
}
