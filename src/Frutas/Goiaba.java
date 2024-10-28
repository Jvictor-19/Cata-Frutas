package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Goiaba extends Frutas {

    public Goiaba(int x, int y) {
        super(x, y, Goiaba.class.getResource("/imagens/Goiaba.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Goiaba";
    }
    public void aplicarEfeito(Jogador jogador) {
    }
}
