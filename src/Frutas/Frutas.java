package Frutas;

import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 * Classe abstrata que representa frutas em um jogo.
 * A classe fornece propriedades e métodos comuns para diferentes tipos de frutas.
 */
public abstract class Frutas {
    protected int x, y; // Coordenadas da fruta
    protected ImageIcon imagem; // Imagem da fruta

    /**
     * Construtor que cria uma fruta com as coordenadas especificadas e a imagem correspondente.
     *
     * @param x Coordenada x da fruta no grid do jogo.
     * @param y Coordenada y da fruta no grid do jogo.
     * @param caminhoImagem URL que aponta para a imagem da fruta.
     */
    public Frutas(int x, int y, URL caminhoImagem) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon(caminhoImagem); // Carrega a imagem a partir do URL
    }

    /**
     * Obtém a coordenada x da fruta.
     *
     * @return A coordenada x da fruta.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a coordenada y da fruta.
     *
     * @return A coordenada y da fruta.
     */
    public int getY() {
        return y;
    }

    /**
     * Desenha a fruta na tela.
     *
     * @param g O objeto Graphics usado para desenhar.
     * @param tamanhoTile O tamanho de cada tile no grid do jogo.
     */
    public void desenhar(Graphics g, int tamanhoTile) {
        int tamanhoFruta = (int) (tamanhoTile * 0.5); // Tamanho reduzido da fruta
        int offset = (tamanhoTile - tamanhoFruta) / 2; // Para centralizar a fruta
        g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoFruta, tamanhoFruta, null);
    }
}
