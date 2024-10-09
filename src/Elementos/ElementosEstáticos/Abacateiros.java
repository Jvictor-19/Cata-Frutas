import javax.swing.ImageIcon;
import java.awt.Graphics;

public class Abacateiro {
    private int x;
    private int y;
    private ImageIcon imagem;

    public Abacateiro(int x, int y) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon("src/imagens/abacateiro.png"); // Ajuste o caminho para a imagem
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Método para desenhar o abacateiro na tela
    public void desenhar(Graphics g, int largura, int altura, int x, int y) {
        g.drawImage(imagem.getImage(), x, y, largura, altura, null);
    }
}
