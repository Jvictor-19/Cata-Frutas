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

    // Reduz o tamanho da fruta para 80% da célula e centraliza
    public void desenhar(Graphics g, int tamanhoTile) {
        int tamanhoFruta = (int) (tamanhoTile * 0.5); // Tamanho reduzido da fruta
        int offset = (tamanhoTile - tamanhoFruta) / 2; // Para centralizar a fruta
        g.drawImage(imagem.getImage(), x * tamanhoTile + offset, y * tamanhoTile + offset, tamanhoFruta, tamanhoFruta, null);
    }
}
