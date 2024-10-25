package Main;

import javax.swing.*;

/**
 * A classe principal que inicia a aplicação.
 * 
 * Esta classe contém o método main, que é o ponto de entrada da aplicação.
 * Ela cria uma instância da tela inicial e a torna visível.
 */
public class Main {

    /**
     * O método main é o ponto de entrada da aplicação.
     * 
     * @param args os argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        // Cria a tela inicial
        TelaInicial telaInicial = new TelaInicial();
        telaInicial.setVisible(true);
    }
}

