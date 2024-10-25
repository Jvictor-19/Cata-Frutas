package Elementos.ElementosEstáticos;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Aceroleira extends Arvore {
	
	private int larguraOriginal;  // Largura original da imagem
    private int alturaOriginal;

    public Aceroleira(int x, int y) {
        super(x, y);
    }
    
    @Override
    protected void carregarImagem() {
        // Carrega a imagem do recurso incorporado
        ImageIcon icone = new ImageIcon(getClass().getResource("/imagens/Aceloleira.png"));
        imagemArvore = icone.getImage(); // Mantenha a imagem original

        // Captura as dimensões originais da imagem
        larguraOriginal = imagemArvore.getWidth(null);
        alturaOriginal = imagemArvore.getHeight(null);
    }
     
    @Override
    public void desenhar(Graphics g, int tamanhoTile) {
        // Calcula a proporção de escala
        double escala = Math.min((double)tamanhoTile / larguraOriginal, (double)tamanhoTile / alturaOriginal);
        
        int largura = (int) (larguraOriginal * escala);
        int altura = (int) (alturaOriginal * escala);

        // Calcula as coordenadas para centralizar a imagem
        int posX = x * tamanhoTile + (tamanhoTile - largura) / 2;
        int posY = y * tamanhoTile + (tamanhoTile - altura) / 2;

        // Desenha a imagem escalada e centralizada
        g.drawImage(imagemArvore, posX, posY, largura, altura, null);
    }

}
