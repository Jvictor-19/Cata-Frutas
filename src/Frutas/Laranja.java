package Frutas;

public class Laranja extends Frutas {

    public Laranja(int x, int y) {
        super(x, y, Laranja.class.getResource("/imagens/laranja.png")); // Caminho específico para a imagem da laranja
    }

}
