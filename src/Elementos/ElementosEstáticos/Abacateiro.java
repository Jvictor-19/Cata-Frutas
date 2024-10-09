package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Abacateiro extends Arvore {

    public Abacateiro(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Abacateiro.png");
        imagemArvore = icone.getImage(); // Mantenha a imagem original
    }

    @Override
    public void desenhar(Graphics g, int tamanhoTile) {
        int largura = 170;  // Defina a largura desejada
        int altura = 170;   // Defina a altura desejada
        g.drawImage(imagemArvore, x * tamanhoTile, y * tamanhoTile, largura, altura, null); // Desenhe com dimensões definidas
    }
}
