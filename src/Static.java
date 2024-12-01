public class Static {
    public Static() {}

    public static boolean verifyNome(String str){
        boolean res = true;
        // Verifica se o nome começa ou acaba com algum caractere que não seja uma letra
        if (!Character.isLetter(str.charAt(0)) || !Character.isLetter(str.charAt(str.length()-1)))
            res = false;
        // Verifica se existem caracteres que não sejam letras ou espaços dentro da string com o nome
        for (int i = 0; i < str.length(); i++) {
            if(!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ')
                res = false ;
        }
        return res;
    }

    public static String localizacaoToString(Localizacao loc) {
        String res = "N/A";
        switch (loc) {
            case continente:
                res = "Portugal Continental";
                break;
            case madeira:
                res = "Madeira";
                break;
            case acores:
                res = "Açores";
                break;
        }
        return res;
    }

    public static Cliente parseCliente(String[] dadosCliente) {
        Cliente cliente = new Cliente();
        cliente.setNome(dadosCliente[0]);
        cliente.setNif(Integer.parseInt(dadosCliente[1]));
        switch (dadosCliente[2]) {
            case "1":
                cliente.setLocalizacao(Localizacao.continente);
                break;
            case "2":
                cliente.setLocalizacao(Localizacao.madeira);
                break;
            case "3":
                cliente.setLocalizacao(Localizacao.acores);
                break;
        }
        return cliente;
    }

    public static Data parseData(String[] dadosData) {
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

    public static TaxaReduzida parseTaxaReduzida(String[] temp) {
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

    public static TaxaIntermedia parseTaxaIntermedia(String[] temp) {
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

    public static TaxaNormal parseTaxaNormal(String[] temp) {
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

    public static Normal parseNormal(String[] temp) {
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

    public static Prescricao parsePrescricao(String[] temp) {
        Prescricao prescricao = new Prescricao();
        prescricao.setNome(temp[1]);
        prescricao.setDesc(temp[2]);
        prescricao.setQuantidade(Integer.parseInt(temp[3]));
        prescricao.setValorUnit(Float.parseFloat(temp[4]));
        prescricao.setIva(Integer.parseInt(temp[5]));
        prescricao.setMedico(temp[6]);
        return prescricao;
    }

    public static Fatura parseFatura(String linha, int numFatura, Produtos produtosRegistados) {
        Fatura fatura = new Fatura(numFatura);
        // Separar os diferentes elementos constituintes da fatura
        String[] dadosFatura = linha.split("#");

        // Obter os dados do cliente
        String[] dadosCliente = dadosFatura[0].split(";");
        fatura.setCliente(Static.parseCliente(dadosCliente));

        // Obter a data da fatura
        String[] data = dadosFatura[1].split("/");
        fatura.setData(Static.parseData(data));

        // Obter os produtos associados à fatura
        String[] produtos = dadosFatura[2].split("&");
        for (int i = 0; i < produtos.length; i++) {
            String[] dadosProduto = produtos[i].split(";");
            switch (dadosProduto[0]) {
                case "prescricao":
                    Prescricao prescricao = Static.parsePrescricao(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(prescricao);
                    produtosRegistados.registarProduto(prescricao);
                    break;
                case "normal":
                    Normal normal = Static.parseNormal(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(normal);
                    produtosRegistados.registarProduto(normal);
                    break;
                case "taxaNormal":
                    TaxaNormal taxaNormal = Static.parseTaxaNormal(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaNormal);
                    produtosRegistados.registarProduto(taxaNormal);
                    break;
                case "taxaIntermedia":
                    TaxaIntermedia taxaIntermedia = Static.parseTaxaIntermedia(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaIntermedia);
                    produtosRegistados.registarProduto(taxaIntermedia);
                    break;
                case "taxaReduzida":
                    TaxaReduzida taxaReduzida = Static.parseTaxaReduzida(dadosProduto);
                    fatura.getProdutos().getProdutosFatura().add(taxaReduzida);
                    produtosRegistados.registarProduto(taxaReduzida);
                    break;
            }
        }
        return fatura;
    }
}
