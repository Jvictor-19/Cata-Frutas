package Main;

public class Main {

    public static void main(String[] args) {
        // Executa a tela inicial
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
        TelaJogo telaJogo = new TelaJogo(10); // Define o tamanho da matriz, 10x10 por exemplo

    }
}
