package Frutas;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public abstract class Frutas {
    protected int x, y;
    protected ImageIcon imagem;

    public Frutas(int x, int y, String caminhoImagem) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon(caminhoImagem); // Caminho para a imagem da fruta
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
