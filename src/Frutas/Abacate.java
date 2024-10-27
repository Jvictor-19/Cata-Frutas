package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Abacate extends Frutas {

    public Abacate(int x, int y) {
        super(x, y, Abacate.class.getResource("/imagens/abacate.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Abacate";
    }
    @Override
    public void aplicarEfeito(Jogador jogador) {
        jogador.dobrarForca(); // Método específico para aplicar efeito do abacate
    }
}
