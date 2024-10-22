package Elementos.ElementosDinamicos;

import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.net.URL;

/**
 * A classe Jogador representa o jogador em um jogo, com uma posição (x, y) e uma imagem associada.
 * O jogador é desenhado em uma célula do tabuleiro e seu tamanho é ajustado para ocupar 80% da célula.
 */
public class Jogador {
    /**
     * A coordenada X do jogador no tabuleiro.
     */
    private int x;

    /**
     * A coordenada Y do jogador no tabuleiro.
     */
    private int y;

    /**
     * A imagem do jogador, representada como um {@link ImageIcon}.
     */
    private ImageIcon imagem;

    /**
     * Construtor da classe Jogador.
     * Inicializa a posição do jogador (x, y) e carrega a imagem a partir do caminho fornecido.
     *
     * @param x A coordenada X do jogador.
     * @param y A coordenada Y do jogador.
     * @param caminhoImagem O caminho do arquivo da imagem do jogador.
     */
    public Jogador(int x, int y, String caminhoImagem) {
        this.x = x;
        this.y = y;
        URL imagemURL = getClass().getResource(caminhoImagem);
        if (imagemURL != null) {
            this.imagem = new ImageIcon(imagemURL); // Carrega a imagem a partir do URL
        } else {
            System.err.println("Imagem não encontrada: " + caminhoImagem);
            this.imagem = null; // Pode-se adicionar uma imagem padrão ou tratar de outra forma
        }
    }

    /**
     * Obtém a coordenada X do jogador.
     *
     * @return A coordenada X do jogador.
     */
    public int getX() {
        return x;
    }

    /**
     * Obtém a coordenada Y do jogador.
     *
     * @return A coordenada Y do jogador.
     */
    public int getY() {
        return y;
    }

    /**
     * Desenha o jogador na tela com base nas coordenadas (x, y) e no tamanho do tile especificado.
     * O tamanho do jogador será ajustado para ocupar 80% do tamanho do tile.
     * O jogador é centralizado dentro do tile.
     *
     * @param g O contexto gráfico onde o jogador será desenhado.
     * @param tamanhoTile O tamanho do tile em que o jogador será desenhado.
     */
    public void desenhar(Graphics g, int tamanhoTile) {
        if (imagem != null) {
            int tamanhoJogador = (int) (tamanhoTile * 0.8); // Tamanho reduzido do jogador
            int offset = (tamanhoTile - tamanhoJogador) / 2; // Para centralizar o jogador
            g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoJogador, tamanhoJogador, null);
        }
    }
}