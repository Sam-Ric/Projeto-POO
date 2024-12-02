import java.io.Serializable;

public class Fatura implements Serializable {
    // Atributos da classe
    private int numFatura;
    private Cliente cliente;
    private Data data;
    private ListaProdutos produtos;

    // Construtores
    public Fatura() {}

    public Fatura(int numFatura) {
        this.numFatura = numFatura;
        produtos = new ListaProdutos();
    }

    public Fatura(int numFatura, Cliente cliente, Data data, ListaProdutos produtos) {
        this.numFatura = numFatura;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    // Metodos de acesso
    public int getNumFatura() {
        return numFatura;
    }

    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public ListaProdutos getProdutos() {
        return produtos;
    }

    public void setProdutos(ListaProdutos produtos) {
        this.produtos = produtos;
    }

    public String toString() {
        return String.format(" %-3d | %-20s | %-20s | %-11d | %-12.2f | %-12.2f", numFatura, cliente.getNome(), Static.localizacaoToString(cliente.getLocalizacao()), produtos.getProdutosFatura().size(), calcTotalSemIva(), calcTotalComIva());
    }

    public float calcTotalSemIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutosFatura())
            total += produto.calcTotalSemIva();
        return total;
    }

    public float calcTotalComIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutosFatura())
            total += produto.calcTotalComIva();
        return total;
    }

    public float calcValorIva() {
        return calcTotalComIva() - calcTotalSemIva();
    }

    public void printFatura() {
        System.out.println("\n========== FATURA Nº" + numFatura + "==========");
        System.out.println("> Cliente");
        System.out.println("   Nome: " + cliente.getNome());
        System.out.println("   NIF: " + cliente.getNif());
        System.out.println("   Localizacao: " + Static.localizacaoToString(cliente.getLocalizacao()));
        System.out.println("> Produtos");
        produtos.printProdutos();
        System.out.println("> Fatura");
        System.out.printf("   Valor Total s/ IVA: %.2f\n", calcTotalSemIva());
        System.out.printf("   Valor Total c/ IVA: %.2f\n", calcTotalComIva());
        System.out.printf("   Valor Total do IVA: %.2f\n", calcValorIva());
    }

    public Data parseData(String[] dadosData) {
        Data data = new Data();
        try {
            data.setDia(Integer.parseInt(dadosData[0]));
            data.setMes(Integer.parseInt(dadosData[1]));
            data.setAno(Integer.parseInt(dadosData[2]));
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
        }
        return data;
    }

    public TaxaReduzida parseTaxaReduzida(String[] temp) {
        TaxaReduzida taxaReduzida = new TaxaReduzida();
        if (temp[1].equals("1")) taxaReduzida.setBiologico(true);
        else taxaReduzida.setBiologico(false);
        taxaReduzida.setNome(temp[2]);
        taxaReduzida.setDesc(temp[3]);
        taxaReduzida.setQuantidade(Integer.parseInt(temp[4]));
        taxaReduzida.setValorUnit(Float.parseFloat(temp[5]));
        taxaReduzida.setIva(Integer.parseInt(temp[6]));
        taxaReduzida.setCertificacoes(temp[7].split(":"));
        return taxaReduzida;
    }

    public TaxaIntermedia parseTaxaIntermedia(String[] temp) {
        TaxaIntermedia taxaIntermedia = new TaxaIntermedia();
        if (temp[1].equals("1")) taxaIntermedia.setBiologico(true);
        else taxaIntermedia.setBiologico(false);
        taxaIntermedia.setNome(temp[2]);
        taxaIntermedia.setDesc(temp[3]);
        taxaIntermedia.setQuantidade(Integer.parseInt(temp[4]));
        taxaIntermedia.setValorUnit(Float.parseFloat(temp[5]));
        taxaIntermedia.setIva(Integer.parseInt(temp[6]));
        switch (temp[7]) {
            case "enlatados":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.enlatados);
                break;
            case "congelados":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.congelados);
                break;
            case "vinho":
                taxaIntermedia.setCategoria(CategoriaTaxaIntermedia.vinho);
                break;
        }
        return taxaIntermedia;
    }

    public TaxaNormal parseTaxaNormal(String[] temp) {
        TaxaNormal taxaNormal = new TaxaNormal();
        if (temp[1].equals("1")) taxaNormal.setBiologico(true);
        else taxaNormal.setBiologico(false);
        taxaNormal.setNome(temp[2]);
        taxaNormal.setDesc(temp[3]);
        taxaNormal.setQuantidade(Integer.parseInt(temp[4]));
        taxaNormal.setValorUnit(Float.parseFloat(temp[5]));
        taxaNormal.setIva(Integer.parseInt(temp[6]));
        return taxaNormal;
    }

    public Normal parseNormal(String[] temp) {
        Normal normal = new Normal();
        normal.setNome(temp[1]);
        normal.setDesc(temp[2]);
        normal.setQuantidade(Integer.parseInt(temp[3]));
        normal.setValorUnit(Float.parseFloat(temp[4]));
        normal.setIva(Integer.parseInt(temp[5]));
        switch (temp[6]) {
            case "beleza":
                normal.setCategoria(CategoriaNormal.beleza);
                break;
            case "bemEstar":
                normal.setCategoria(CategoriaNormal.bemEstar);
                break;
            case "bebes":
                normal.setCategoria(CategoriaNormal.bebes);
                break;
            case "animais":
                normal.setCategoria(CategoriaNormal.animais);
                break;
            case "outros":
                normal.setCategoria(CategoriaNormal.outros);
                break;
        }
        return normal;
    }

    public Prescricao parsePrescricao(String[] temp) {
        Prescricao prescricao = new Prescricao();
        prescricao.setNome(temp[1]);
        prescricao.setDesc(temp[2]);
        prescricao.setQuantidade(Integer.parseInt(temp[3]));
        prescricao.setValorUnit(Float.parseFloat(temp[4]));
        prescricao.setIva(Integer.parseInt(temp[5]));
        prescricao.setMedico(temp[6]);
        return prescricao;
    }
}

