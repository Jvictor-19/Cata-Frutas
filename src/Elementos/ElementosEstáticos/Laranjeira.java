package Elementos.ElementosEstáticos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Laranjeira {

    private int x; // Posição X da laranjeira
    private int y; // Posição Y da laranjeira
    private Image imagemLaranjeira;

    // Construtor
    public Laranjeira(int x, int y) {
        this.x = x;
        this.y = y;
        carregarImagem();
    }

    // Método para carregar a imagem da laranjeira
    private void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Laranjeira.png"); // Caminho para a imagem da laranjeira
        imagemLaranjeira = icone.getImage();
    }

    // Métodos para obter as coordenadas
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método para desenhar a laranjeira
    public void desenhar(Graphics g, int tamanhoTile) {
        g.drawImage(imagemLaranjeira, x * tamanhoTile, y * tamanhoTile, tamanhoTile, tamanhoTile, null);
    }
}
