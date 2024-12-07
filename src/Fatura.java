import java.io.Serializable;

/**
 * Classe que representa uma fatura
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Fatura implements Serializable {
    /**
     * Número da fatura
     */
    private int numFatura;
    /**
     * Cliente associado à fatura
     */
    private Cliente cliente;
    /**
     * Data de emissão da fatura
     */
    private Data data;
    /**
     * Lista de produtos
     */
    private ListaProdutos produtos;

    /**
     * Construtor por omissão
     */
    public Fatura() {}

    /**
     * Construtor da classe, recebe o número da fatura e
     * inicializa os atributos
     *
     * @param numFatura Número da fatura
     */
    public Fatura(int numFatura) {
        this.numFatura = numFatura;
        produtos = new ListaProdutos();
    }

    /**
     * Construtor da classe, recebe dados para inicializar
     * os atributos
     *
     * @param numFatura Número da classe
     * @param cliente Cliente associado à fatura
     * @param data Data de emissão
     * @param produtos Lista de produtos
     */
    public Fatura(int numFatura, Cliente cliente, Data data, ListaProdutos produtos) {
        this.numFatura = numFatura;
        this.cliente = cliente;
        this.data = data;
        this.produtos = produtos;
    }

    /**
     * Metodo de acesso ao atributo 'numFatura' (getter)
     * @return Número da fatura
     */
    public int getNumFatura() {
        return numFatura;
    }

    /**
     * Metodo de acesso ao atributo 'numFatura' (setter)
     * @param numFatura Número da fatura
     */
    public void setNumFatura(int numFatura) {
        this.numFatura = numFatura;
    }

    /**
     * Metodo de acesso ao atributo 'cliente' (getter)
     * @return Cliente associado à fatura
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Metodo de acesso ao atributo 'cliente' (setter)
     * @param cliente Cliente associado à fatura
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * Metodo de acesso ao atributo 'data' (getter)
     * @return Data de emissão
     */
    public Data getData() {
        return data;
    }

    /**
     * Metodo de acesso ao atributo 'data' (setter)
     * @param data Data de emissão
     */
    public void setData(Data data) {
        this.data = data;
    }

    /**
     * Metodo de acesso ao atributo 'listaProdutos' (getter)
     * @return Lista de produtos
     */
    public ListaProdutos getProdutos() {
        return produtos;
    }

    /**
     * Metodo de acesso ao atributo 'listaProdutos' (setter)
     * @param produtos Lista de produtos
     */
    public void setProdutos(ListaProdutos produtos) {
        this.produtos = produtos;
    }

    /**
     * Metodo toString, devolve uma string com os dados
     * da fatura formatados de forma a serem imprimidos
     * em forma de tabela
     *
     * @return Dados da fatura
     */
    public String toString() {
        return String.format(" %-3d | %-20s | %-20s | %-11d | %-12.2f | %-12.2f", numFatura, cliente.getNome(), Static.localizacaoToString(cliente.getLocalizacao()), produtos.getProdutosFatura().size(), calcTotalSemIva(), calcTotalComIva());
    }

    /**
     * Metodo para calcular o valor total da fatura, sem
     * a taxa do IVA
     *
     * @return Valor total da fatura sem o IVA
     */
    public float calcTotalSemIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutosFatura())
            total += produto.calcTotalSemIva();
        return total;
    }

    /**
     * Metodo para calcular o valor total da fatura,
     * considerando a taxa do IVA
     *
     * @return Valor total da fatura com o IVA
     */
    public float calcTotalComIva() {
        float total = 0;
        for (Produto produto : produtos.getProdutosFatura())
            total += produto.calcTotalComIva();
        return total;
    }

    /**
     * Metodo para calcular o valor total do IVA, ou seja,
     * a diferença entre o valor total da fatura com o IVA
     * e o valor total sem o IVA
     *
     * @return Valor total do IVA
     */
    public float calcValorIva() {
        return calcTotalComIva() - calcTotalSemIva();
    }

    /**
     * Metodo para imprimir todos os dados de uma fatura,
     * nomeadamente, os dados do cliente, todos os produtos
     * na lista de produtos e os valores totais da fatura
     * com e sem o IVA assim como o valor total do IVA
     */
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

    /**
     * Metodo para efetuar o parsing de uma data lida de um
     * ficheiro de texto, recebe um array de strings e devolve
     * a data no formato de um objeto do tipo Data
     *
     * @param dadosData Dados lidos do ficheiro de texto
     * @return Objeto do tipo Data contendo a data recebida
     */
    public Data parseData(String[] dadosData) {
        Data data = new Data();
        try {
            data.setDia(Integer.parseInt(dadosData[0]));
            data.setMes(Integer.parseInt(dadosData[1]));
            data.setAno(Integer.parseInt(dadosData[2]));
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            data = null;
        }
        return data;
    }

    /**
     * Metodo para efetuar o parsing de um produto alimentar
     * de taxa reduzida, lido de um ficheiro de texto, devolve
     * um objeto do tipo TaxaReduzida contendo os dados recebidos
     *
     * @param temp Dados do produto de taxa reduzida
     * @return Objeto do tipo TaxaReduzida contendo os dados recebidos
     */
    public TaxaReduzida parseTaxaReduzida(String[] temp) {
        TaxaReduzida taxaReduzida = new TaxaReduzida();
        try {
            if (temp[1].equals("1")) taxaReduzida.setBiologico(true);
            else taxaReduzida.setBiologico(false);
            taxaReduzida.setNome(temp[2]);
            taxaReduzida.setDesc(temp[3]);
            taxaReduzida.setQuantidade(Integer.parseInt(temp[4]));
            taxaReduzida.setValorUnit(Float.parseFloat(temp[5]));
            taxaReduzida.setIva(Integer.parseInt(temp[6]));
            taxaReduzida.setCertificacoes(temp[7].split(":"));
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            taxaReduzida = null;
        }
        return taxaReduzida;
    }
    /**
     * Metodo para efetuar o parsing de um produto alimentar
     * de taxa intermedia, lido de um ficheiro de texto, devolve
     * um objeto do tipo TaxaIntermedia contendo os dados recebidos
     *
     * @param temp Dados do produto de taxa intermedia
     * @return Objeto do tipo TaxaIntermedia contendo os dados recebidos
     */
    public TaxaIntermedia parseTaxaIntermedia(String[] temp) {
        TaxaIntermedia taxaIntermedia = new TaxaIntermedia();
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            taxaIntermedia = null;
        }
        return taxaIntermedia;
    }

    /**
     * Metodo para efetuar o parsing de um produto alimentar
     * de taxa normal, lido de um ficheiro de texto, devolve
     * um objeto do tipo TaxaNormal contendo os dados recebidos
     *
     * @param temp Dados do produto de taxa normal
     * @return Objeto do tipo TaxaNormal contendo os dados recebidos
     */
    public TaxaNormal parseTaxaNormal(String[] temp) {
        TaxaNormal taxaNormal = new TaxaNormal();
        try {
            if (temp[1].equals("1")) taxaNormal.setBiologico(true);
            else taxaNormal.setBiologico(false);
            taxaNormal.setNome(temp[2]);
            taxaNormal.setDesc(temp[3]);
            taxaNormal.setQuantidade(Integer.parseInt(temp[4]));
            taxaNormal.setValorUnit(Float.parseFloat(temp[5]));
            taxaNormal.setIva(Integer.parseInt(temp[6]));
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            taxaNormal = null;
        }
        return taxaNormal;
    }

    /**
     * Metodo para efetuar o parsing de um produto de farmácia
     * do tipo Normal, lido de um ficheiro de texto, devolve um
     * objeto do tipo Normal contendo os dados recebidos
     *
     * @param temp Dados do produto de farmácia sem prescrição
     * @return Objeto do tipo Normal contendo os dados recebidos
     */
    public Normal parseNormal(String[] temp) {
        Normal normal = new Normal();
        try {
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
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            normal = null;
        }
        return normal;
    }

    /**
     * Metodo para efetuar o parsing de um produto de farmácia
     * do tipo Prescricao, lido de um ficheiro de texto, devolve um
     * objeto do tipo Prescricao contendo os dados recebidos
     *
     * @param temp Dados do produto de farmácia com prescrição
     * @return Objeto do tipo Prescricao contendo os dados recebidos
     */
    public Prescricao parsePrescricao(String[] temp) {
        Prescricao prescricao = new Prescricao();
        try {
            prescricao.setNome(temp[1]);
            prescricao.setDesc(temp[2]);
            prescricao.setQuantidade(Integer.parseInt(temp[3]));
            prescricao.setValorUnit(Float.parseFloat(temp[4]));
            prescricao.setIva(Integer.parseInt(temp[5]));
            prescricao.setMedico(temp[6]);
        } catch (NumberFormatException e) {
            System.out.println("[!] Ficheiro com formato inválido");
            prescricao = null;
        }
        return prescricao;
    }
}

