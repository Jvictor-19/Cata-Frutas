package Elementos.ElementosEstáticos;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Pedra {
    private int x, y;
    private ImageIcon imagem;

    public Pedra(int x, int y) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon("src/imagens/pedra.png"); // Caminho para a imagem da pedra
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        g.drawImage(imagem.getImage(), x * tamanhoTile, y * tamanhoTile, tamanhoTile, tamanhoTile, null);
    }
}
