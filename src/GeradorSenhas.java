import java.security.SecureRandom;

public class GeradorSenhas {
    public static String gerarSenha(int comprimento, int especiais, int maiusculas, int numeros){
        String caracteresEspeciais = "!@#$%&*";
        String todasLetrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String todasLetrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
        String todosNumeros = "0123456789";


        if(especiais + maiusculas + numeros > comprimento) {
            return "ERRO";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();

        for(int i = 0; i < maiusculas; i++) {
            int indice = random.nextInt(todasLetrasMaiusculas.length());
            senha.append(todasLetrasMaiusculas.charAt(indice));
        }

        for(int i = 0; i < especiais; i++) {
            int indice = random.nextInt(caracteresEspeciais.length());
            senha.append(caracteresEspeciais.charAt(indice));
        }

        for(int i = 0; i < numeros; i++) {
            int indice = random.nextInt(todosNumeros.length());
            senha.append(todosNumeros.charAt(indice));
        }
        
        int restante = comprimento - especiais - maiusculas - numeros;

        for(int i = 0; i < restante; i++) {
            int indice = random.nextInt(todasLetrasMinusculas.length());
            senha.append(todasLetrasMinusculas.charAt(indice));

        }
        
        char[] senhaEmbaralhada = senha.toString().toCharArray();
        for (int i = senhaEmbaralhada.length - 1; i > 0; i--) {
            int indiceAleatorio = random.nextInt(i + 1);
            char temp = senhaEmbaralhada[indiceAleatorio];
            senhaEmbaralhada[indiceAleatorio] = senhaEmbaralhada[i];
            senhaEmbaralhada[i] = temp;
        }

        return new String(senhaEmbaralhada);
    }
}
