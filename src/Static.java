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
}
