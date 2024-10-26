package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Maracuja extends Frutas {

    public Maracuja(int x, int y) {
        super(x, y, Maracuja.class.getResource("/imagens/maracuja.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Maracuja";
    }
    @Override
    public void aplicarEfeito(Jogador jogador) {
        jogador.adicionarPontoVitoria(); // Método específico para adicionar um ponto de vitória
    }
}

