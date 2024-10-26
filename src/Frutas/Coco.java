package Frutas;

import Elementos.ElementosDinamicos.Jogador;

public class Coco extends Frutas {

    public Coco(int x, int y) {
        super(x, y, Coco.class.getResource("/imagens/coco.png")); // Caminho específico para a imagem da laranja
    }
    @Override
    public String getNome() {
        return "Coco";
    }
   
	@Override
	public void aplicarEfeito(Jogador jogador) {
		// TODO Auto-generated method stub
		
	}
}
