package Elementos.ElementosEstáticos;

import javax.swing.ImageIcon;

public class Coqueiro extends Arvore {

    public Coqueiro(int x, int y) {
        super(x, y);
    }

    @Override
    protected void carregarImagem() {
        ImageIcon icone = new ImageIcon("src/imagens/coqueio.png");
        imagemArvore = icone.getImage(); // Tamanho ajustado
    }
}
