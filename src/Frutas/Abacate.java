package Frutas;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Abacate {
    private int x, y;
    private ImageIcon imagem;

    public Abacate(int x, int y) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon("src/imagens/abacate.png"); // Caminho para a imagem da laranja
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void desenhar(Graphics g, int tamanhoTile) {
        // Obter as dimensões da imagem
        int larguraImagem = imagem.getImage().getWidth(null);
        int alturaImagem = imagem.getImage().getHeight(null);

        // Calcular a nova largura e altura para redimensionar a fruta
        int novaLargura = (int) (tamanhoTile * 0.5); // 50% do tamanhoTile
        int novaAltura = (int) (tamanhoTile * 0.5); // 50% do tamanhoTile

        // Calcular a posição para centralizar a fruta na célula
        int posX = x * tamanhoTile + (tamanhoTile - novaLargura) / 2;
        int posY = y * tamanhoTile + (tamanhoTile - novaAltura) / 2;

        // Desenhar a imagem da fruta redimensionada
        g.drawImage(imagem.getImage(), posX, posY, novaLargura, novaAltura, null);
    }



}
