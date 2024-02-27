import java.security.SecureRandom;
import java.util.Random;

public class GeradorSenhas {
    public static String gerarSenha(int comprimento, int especiais, int maiusculas, int numeros){
        String caracteresEspeciais = "!.@#$%&*";
        String todasLetrasMaiusculas = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String todasLetrasMinusculas = "abcdefghijklmnopqrstuvwxyz";
        String todosNumeros = "0123456789";


        if(especiais + maiusculas + numeros > comprimento) {
            return "ERRO";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder senha = new StringBuilder();

        senha.append(generateRandomCharacters(maiusculas, todasLetrasMaiusculas, random));
        senha.append(generateRandomCharacters(especiais, caracteresEspeciais, random));
        senha.append(generateRandomCharacters(numeros, todosNumeros, random));
        
        int minusculas = comprimento - especiais - maiusculas - numeros;

        senha.append(generateRandomCharacters(minusculas, todasLetrasMinusculas, random));
        
        char[] senhaEmbaralhada = senha.toString().toCharArray();
        for (int i = senhaEmbaralhada.length - 1; i > 0; i--) {
            int indiceAleatorio = random.nextInt(i + 1);
            char temp = senhaEmbaralhada[indiceAleatorio];
            senhaEmbaralhada[indiceAleatorio] = senhaEmbaralhada[i];
            senhaEmbaralhada[i] = temp;
        }

        return new String(senhaEmbaralhada);
    }

    private static String generateRandomCharacters(int count, String source, Random random) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < count; i++) {
            int indice = random.nextInt(source.length());
            result.append(source.charAt(indice));
        }
        return result.toString();
    }
}
