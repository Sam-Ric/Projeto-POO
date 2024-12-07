/**
 * Classe que contém todos os métodos estáticos utilizados
 * na aplicação
 *
 * @author Bernardo Mateus e Samuel Riça
 * @version 1.0
 */
public class Static {
    /**
     * Construtor por omissão
     */
    public Static() {}

    /**
     * Metodo para verificar se um nome passado como argumento
     * é válido, devolvendo o valor booleano true em caso afirmativo
     * e false caso contrário
     *
     * @param str Nome a ser verificaco
     * @return Valor booleano que descreve a validade do nome dado
     */
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

    /**
     * Metodo para passar uma localização passada como argumento
     * para o formato string
     *
     * @param loc Localização em enum
     * @return Localização em string
     */
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
