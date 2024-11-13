public class TaxaReduzida extends Alimentos{
    private String certificacoes;
    public TaxaReduzida() {}

    public String getCertificacoes() {
        return certificacoes;
    }

    public void setCertificacoes(String certificacoes) {
        this.certificacoes = certificacoes;
    }
    protected String generateCertificacoes() {
        String array[]={"ISO22000","FSSC22000","HACCP","GMP"};

        return; //por acabar

    }
}
