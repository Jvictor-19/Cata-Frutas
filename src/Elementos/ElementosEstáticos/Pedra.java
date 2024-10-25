package Elementos.ElementosEstáticos;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Pedra {
    private int x, y;
    private ImageIcon imagem;

    public Pedra(int x, int y) {
        this.x = x;
        this.y = y;
        // Carrega a imagem usando getClass().getResource() para funcionar no JAR
        this.imagem = new ImageIcon(getClass().getResource("/imagens/pedra.png"));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        // Desenha a imagem da pedra com o tamanho especificado
        g.drawImage(imagem.getImage(), x * tamanhoTile, y * tamanhoTile, tamanhoTile, tamanhoTile, null);
    }
}
