package Elementos.ElementosEstáticos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * Classe abstrata que representa uma árvore em um jogo.
 * A classe fornece propriedades e métodos comuns para diferentes tipos de árvores.
 */
public abstract class Arvore {

    protected int x; // Posição X da árvore
    protected int y; // Posição Y da árvore
    protected Image imagemArvore; // Imagem da árvore

    /**
     * Construtor da classe Arvore.
     *
     * @param x Coordenada X da árvore no grid do jogo. As coordenadas são multiplicadas por 2.
     * @param y Coordenada Y da árvore no grid do jogo. As coordenadas são multiplicadas por 2.
     */
    public Arvore(int x, int y) {
        this.x = x; // Multiplicando as coordenadas por 2
        this.y = y; // Multiplicando as coordenadas por 2
        carregarImagem(); // Carrega a imagem da árvore específica
    }

    /**
     * Método abstrato que deve ser implementado pelas classes filhas para definir a imagem da árvore.
     */
    protected abstract void carregarImagem();

    /**
     * Obtém a coordenada X da árvore.
     *
     * @return A coordenada X da árvore.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a coordenada Y da árvore.
     *
     * @return A coordenada Y da árvore.
     */
    public int getY() {
        return y;
    }

    /**
     * Desenha a árvore na tela.
     *
     * @param g O objeto Graphics usado para desenhar.
     * @param tamanhoTile O tamanho de cada tile no grid do jogo.
     */
    public void desenhar(Graphics g, int tamanhoTile) {
        g.drawImage(imagemArvore, x * tamanhoTile, y * tamanhoTile, tamanhoTile, tamanhoTile, null); // Desenho ajustado
    }
}
