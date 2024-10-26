package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Laranja extends Frutas {

    public Laranja(int x, int y) {
        super(x, y, Laranja.class.getResource("/imagens/laranja.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Laranja";
    }
    @Override
    public void aplicarEfeito(Jogador jogador) {
        jogador.ativarAntidoto(); // Método específico para ativar o antídoto
    }
}
