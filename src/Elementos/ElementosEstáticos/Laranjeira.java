package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;

public class Laranjeira extends Arvore {

    public Laranjeira(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Laranjeira.png");
        imagemArvore = icone.getImage(); // Tamanho ajustado
    }
}
