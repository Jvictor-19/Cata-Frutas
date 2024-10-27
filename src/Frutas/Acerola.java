package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Acerola extends Frutas {

    public Acerola(int x, int y) {
        super(x, y, Acerola.class.getResource("/imagens/acerola.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Acerola";
    }
    public void aplicarEfeito(Jogador jogador) {
    }
}