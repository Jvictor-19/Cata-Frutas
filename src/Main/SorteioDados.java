package Main;

import java.util.Random;

public class SorteioDados {
    // Método para sortear os dados
    public static int[] sortearDados() {
        Random random = new Random();
        int dado1 = random.nextInt(6) + 1; // Gera número de 1 a 6
        int dado2 = random.nextInt(6) + 1; // Gera número de 1 a 6
        return new int[]{dado1, dado2}; // Retorna os resultados em um array
    }
}
