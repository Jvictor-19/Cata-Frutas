package Frutas;

public class Laranja {
<<<<<<< Updated upstream
    
=======
    private int x, y;
    private ImageIcon imagem;

    public Laranja(int x, int y) {
        this.x = x;
        this.y = y;
        this.imagem = new ImageIcon("src/imagens/Laranja.png"); // Caminho para a imagem da laranja
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
>>>>>>> Stashed changes
}
