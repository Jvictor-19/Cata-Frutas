package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Amora extends Frutas {

    public Amora(int x, int y) {
        super(x, y, Amora.class.getResource("/imagens/Amora.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Amora";
    }
    public void aplicarEfeito(Jogador jogador) {
    }
}
