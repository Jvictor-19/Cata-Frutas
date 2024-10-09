package Elementos.ElementosEstáticos;

import javax.swing.*;
import java.awt.*;

public class Abacateiro {

    private int x;
    private int y;
    private ImageIcon imagemAbacateiro;

    public Abacateiro(int x, int y) {
        this.x = x;
        this.y = y;
        // Carregue a imagem do abacateiro (substitua o caminho pela localização correta da imagem)
        imagemAbacateiro = new ImageIcon("src/imagens/Abacateiro.png");
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public void desenhar(Graphics g, int tamanhoTile) {
        // Centraliza a imagem do abacateiro na célula
        int largura = imagemAbacateiro.getIconWidth(); // Obtém a largura original da imagem
        int altura = imagemAbacateiro.getIconHeight(); // Obtém a altura original da imagem
        int posX = x * tamanhoTile + (tamanhoTile - largura) / 2;
        int posY = y * tamanhoTile + (tamanhoTile - altura) / 2;

        g.drawImage(imagemAbacateiro.getImage(), posX, posY, largura, altura, null); // Desenha a imagem no tamanho original
    }

}
