package Elementos.ElementosEstáticos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public abstract class Arvore {

    protected int x; // Posição X da árvore
    protected int y; // Posição Y da árvore
    protected Image imagemArvore; // Imagem da árvore

    // Construtor da classe Arvore
    public Arvore(int x, int y) {
        this.x = x ; // Multiplicando as coordenadas por 2
        this.y = y ; // Multiplicando as coordenadas por 2
        carregarImagem(); // Carrega a imagem da árvore específica
    }

    // Método abstrato que será implementado pelas classes filhas para definir a imagem
    protected abstract void carregarImagem();

    // Métodos para obter as coordenadas
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método para desenhar a árvore
    public void desenhar(Graphics g, int tamanhoTile) {
        g.drawImage(imagemArvore, x * tamanhoTile, y * tamanhoTile, tamanhoTile, tamanhoTile, null); // Desenho ajustado
    }
}
