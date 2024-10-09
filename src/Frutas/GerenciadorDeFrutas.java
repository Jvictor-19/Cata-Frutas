package Frutas;

import java.util.ArrayList;
import java.util.Random;

public class GerenciadorDeFrutas {

    private int maxColunasTela;
    private int maxLinhasTela;

    public GerenciadorDeFrutas(int maxColunasTela, int maxLinhasTela) {
        this.maxColunasTela = maxColunasTela;
        this.maxLinhasTela = maxLinhasTela;
    }

    // Método para verificar se a posição já está ocupada
    private boolean posicaoOcupada(int x, int y) {
        // Implementar a lógica para verificar se a posição está ocupada
        return false; // Exemplo básico
    }

    // Método para gerar frutas no chão
 // Mudança na assinatura do método
    public void gerarFrutasNoChao(ArrayList<Frutas> listaFrutas, int quantidadeFrutas, Class<? extends Frutas> tipoFruta) {
        Random random = new Random();
        int contagem = 0;

        while (contagem < quantidadeFrutas) {
            int x = random.nextInt(maxColunasTela);
            int y = random.nextInt(maxLinhasTela);

            if (!posicaoOcupada(x, y)) {
                try {
                    // Usa reflexão para criar uma nova instância do tipo de fruta
                    Frutas fruta = tipoFruta.getConstructor(int.class, int.class).newInstance(x, y);
                    listaFrutas.add(fruta);  // Adiciona a instância da fruta à lista
                    contagem++;
                } catch (Exception e) {
                    e.printStackTrace(); // Captura qualquer exceção de reflexão
                }
            }
        }
    }
}
