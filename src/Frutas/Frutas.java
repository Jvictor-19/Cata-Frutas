package Frutas;

import java.awt.Graphics;
import java.net.URL;
import javax.swing.ImageIcon;

public abstract class Frutas {
    protected int x, y;
    protected ImageIcon imagem;

    // Construtor que aceita um URL para a imagem
    public Frutas(int x, int y, URL caminhoImagem) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon(caminhoImagem); // Carrega a imagem a partir do URL
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        int tamanhoFruta = (int) (tamanhoTile * 0.5); // Tamanho reduzido da fruta
        int offset = (tamanhoTile - tamanhoFruta) / 2; // Para centralizar a fruta
        g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoFruta, tamanhoFruta, null);
    }
}
