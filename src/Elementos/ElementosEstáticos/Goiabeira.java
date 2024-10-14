package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;

public class Goiabeira extends Arvore {

    public Goiabeira(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/Goiabeira.png");
        imagemArvore = icone.getImage(); // Tamanho ajustado
    }
}
