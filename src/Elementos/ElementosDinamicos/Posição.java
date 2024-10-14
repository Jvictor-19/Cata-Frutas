package Elementos.ElementosDinamicos;

import javax.swing.ImageIcon;

/**
 * Classe abstrata que define um elemento com uma posição (x, y) e um ícone associado.
 * Esta classe fornece métodos para acessar e modificar a posição e o ícone do elemento.
 */
public abstract class Posição {

    /**
     * Um array que representa a posição do elemento, onde
     * posicao[0] é a coordenada X e posicao[1] é a coordenada Y.
     */
    private int[] posicao;

    /**
     * O ícone associado ao elemento, representado como um {@link ImageIcon}.
     */
    private ImageIcon icone;

    /**
     * Construtor da classe Posição.
     * Inicializa o array de posição com um tamanho de 2.
     */
    public Posição() {
        posicao = new int[2];
    }

    /**
     * Obtém a posição do elemento.
     *
     * @return Um array de inteiros contendo as coordenadas [x, y] do elemento.
     */
    public int[] getPosicao() {
        return posicao;
    }

    /**
     * Define a posição do elemento.
     *
     * @param x A coordenada X a ser definida.
     * @param y A coordenada Y a ser definida.
     */
    public void setPosicao(int x, int y) {
        this.posicao[0] = x;
        this.posicao[1] = y;
    }

    /**
     * Obtém o ícone do elemento.
     *
     * @return O ícone associado ao elemento como um {@link ImageIcon}.
     */
    public ImageIcon getIcone() {
        return this.icone;
    }

    /**
     * Define o ícone do elemento.
     *
     * @param icone O {@link ImageIcon} a ser definido como ícone do elemento.
     */
    public void setIcone(ImageIcon icone) {
        this.icone = icone;
    }
}
