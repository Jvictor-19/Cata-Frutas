package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;

public class Amoreiro extends Arvore {

    public Amoreiro(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Amoreira.png");
        imagemArvore = icone.getImage(); // Tamanho ajustado
    }
}
