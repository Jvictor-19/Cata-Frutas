package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;

public class Aceroleira extends Arvore {

    public Aceroleira(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Aceroleira.png");
        imagemArvore = icone.getImage(); // Tamanho ajustado
    }
}
