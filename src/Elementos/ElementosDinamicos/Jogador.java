package Elementos.ElementosDinamicos;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Jogador {
    private int x, y;
    private ImageIcon imagem;

    // Construtor da classe Jogador
    public Jogador(int x, int y, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon(caminhoImagem); // Caminho para a imagem do jogador
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Reduz o tamanho do jogador para 80% da célula e centraliza
    public void desenhar(Graphics g, int tamanhoTile) {
        int tamanhoJogador = (int) (tamanhoTile * 0.8); // Tamanho reduzido do jogador
        int offset = (tamanhoTile - tamanhoJogador) / 2; // Para centralizar o jogador
        g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoJogador, tamanhoJogador, null);
    }
}
