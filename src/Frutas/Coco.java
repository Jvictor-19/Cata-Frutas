package Frutas;

public class Coco extends Frutas {

    public Coco(int x, int y) {
        super(x, y, Coco.class.getResource("/imagens/coco.png")); // Caminho específico para a imagem da laranja
    }
}
