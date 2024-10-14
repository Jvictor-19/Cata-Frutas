package Frutas;

import java.awt.Graphics;
import javax.swing.ImageIcon;

/**
 * A classe abstrata Frutas define as propriedades e o comportamento básico para uma fruta.
 * Cada fruta possui uma posição (x, y) e uma imagem associada.
 */
public abstract class Frutas {
    /**
     * A coordenada X da fruta no tabuleiro.
     */
    protected int x;

    /**
     * A coordenada Y da fruta no tabuleiro.
     */
    protected int y;

    /**
     * A imagem da fruta, representada como um {@link ImageIcon}.
     */
    protected ImageIcon imagem;

    /**
     * Construtor da classe Frutas.
     * Inicializa a posição da fruta (x, y) e carrega a imagem a partir do caminho fornecido.
     *
     * @param x A coordenada X da fruta.
     * @param y A coordenada Y da fruta.
     * @param caminhoImagem O caminho do arquivo da imagem da fruta.
     */
    public Frutas(int x, int y, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon(caminhoImagem); // Caminho para a imagem da fruta
    }

    /**
     * Obtém a coordenada X da fruta.
     *
     * @return A coordenada X da fruta.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a coordenada Y da fruta.
     *
     * @return A coordenada Y da fruta.
     */
    public int getY() {
        return y;
    }

    /**
     * Desenha a fruta na tela com base nas coordenadas (x, y) e no tamanho do tile especificado.
     * O tamanho da fruta será ajustado para ser uma fração (50%) do tamanho do tile.
     * A fruta é centralizada dentro do tile.
     *
     * @param g O contexto gráfico onde a fruta será desenhada.
     * @param tamanhoTile O tamanho do tile em que a fruta será desenhada.
     */
    public void desenhar(Graphics g, int tamanhoTile) {
        int tamanhoFruta = (int) (tamanhoTile * 0.5); // Tamanho reduzido da fruta
        int offset = (tamanhoTile - tamanhoFruta) / 2; // Para centralizar a fruta
        g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoFruta, tamanhoFruta, null);
    }
}